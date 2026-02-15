package com.aimassist;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AimAssistMod implements ClientModInitializer {
    
    // Configuration
    private static boolean enabled = true;
    private static double maxDistance = 6.0; // Distance max en blocs
    private static float assistStrength = 0.3f; // Force de l'assistance (0.0 - 1.0)
    private static double fov = 90.0; // Champ de vision pour cibler
    
    private static KeyBinding toggleKey;
    
    @Override
    public void onInitializeClient() {
        // Créer le keybind pour toggle on/off
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.aimassist.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_ALT,
            "category.aimassist"
        ));
        
        // Register tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                handleToggleKey(client);
                if (enabled) {
                    applyAimAssist(client);
                }
            }
        });
        
        System.out.println("[AimAssist] Mod loaded! Press RIGHT ALT to toggle.");
    }
    
    private void handleToggleKey(MinecraftClient client) {
        while (toggleKey.wasPressed()) {
            enabled = !enabled;
            if (client.player != null) {
                String status = enabled ? "§aON" : "§cOFF";
                client.player.sendMessage(
                    net.minecraft.text.Text.literal("§6[AimAssist] " + status),
                    true // Action bar
                );
            }
        }
    }
    
    private void applyAimAssist(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        if (player == null || client.world == null) return;
        
        // Ne pas appliquer si le joueur est dans un menu
        if (client.currentScreen != null) return;
        
        // Trouver le joueur le plus proche dans le FOV
        PlayerEntity target = findNearestPlayer(client, player);
        if (target == null) return;
        
        // Calculer l'angle vers la cible
        Vec3d playerPos = player.getEyePos();
        Vec3d targetPos = target.getEyePos();
        Vec3d direction = targetPos.subtract(playerPos).normalize();
        
        // Convertir en angles yaw/pitch
        double targetYaw = Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90.0;
        double targetPitch = -Math.toDegrees(Math.asin(direction.y));
        
        // Angle actuel du joueur
        float currentYaw = player.getYaw();
        float currentPitch = player.getPitch();
        
        // Normaliser les angles
        targetYaw = MathHelper.wrapDegrees(targetYaw);
        double yawDiff = MathHelper.wrapDegrees(targetYaw - currentYaw);
        double pitchDiff = targetPitch - currentPitch;
        
        // Appliquer l'assistance progressive (smooth)
        float newYaw = currentYaw + (float)(yawDiff * assistStrength);
        float newPitch = currentPitch + (float)(pitchDiff * assistStrength);
        
        // Limiter le pitch entre -90 et 90
        newPitch = MathHelper.clamp(newPitch, -90.0f, 90.0f);
        
        // Appliquer la rotation
        player.setYaw(newYaw);
        player.setPitch(newPitch);
    }
    
    private PlayerEntity findNearestPlayer(MinecraftClient client, ClientPlayerEntity self) {
        if (client.world == null) return null;
        
        List<PlayerEntity> players = client.world.getPlayers().stream()
            .filter(p -> p != self) // Pas soi-même
            .filter(p -> !p.isSpectator()) // Pas les spectateurs
            .filter(p -> self.distanceTo(p) <= maxDistance) // Dans la distance max
            .filter(p -> isInFOV(self, p, fov)) // Dans le FOV
            .sorted(Comparator.comparingDouble(p -> self.distanceTo(p))) // Trier par distance
            .collect(Collectors.toList());
        
        return players.isEmpty() ? null : players.get(0);
    }
    
    private boolean isInFOV(PlayerEntity player, Entity target, double fovDegrees) {
        Vec3d playerPos = player.getEyePos();
        Vec3d targetPos = target.getEyePos();
        Vec3d toTarget = targetPos.subtract(playerPos).normalize();
        Vec3d playerLook = player.getRotationVec(1.0f);
        
        double dotProduct = playerLook.dotProduct(toTarget);
        double angle = Math.toDegrees(Math.acos(dotProduct));
        
        return angle <= fovDegrees / 2.0;
    }
    
    // Méthodes pour modifier la config (peut être appelé via commandes ou config GUI)
    public static void setEnabled(boolean value) {
        enabled = value;
    }
    
    public static void setMaxDistance(double distance) {
        maxDistance = Math.max(1.0, Math.min(distance, 20.0));
    }
    
    public static void setAssistStrength(float strength) {
        assistStrength = Math.max(0.0f, Math.min(strength, 1.0f));
    }
    
    public static void setFOV(double degrees) {
        fov = Math.max(10.0, Math.min(degrees, 180.0));
    }
}
