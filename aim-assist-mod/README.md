# Simple Aim Assist Mod

Un mod d'aim assist simple et configurable pour Minecraft 1.21.10 Fabric.

## Fonctionnalités

- ✅ Aim assist doux vers les joueurs proches
- ✅ Toggle ON/OFF avec RIGHT ALT
- ✅ Configurable (distance, force, FOV)
- ✅ Tu gardes le contrôle - c'est juste une assistance
- ✅ Client-side seulement

## Configuration par défaut

- **Distance max**: 6 blocs
- **Force**: 30% (0.3)
- **FOV**: 90 degrés
- **Toggle key**: RIGHT ALT

## Installation

1. Installe Fabric Loader 0.16.0+ pour Minecraft 1.21.10
2. Télécharge Fabric API 0.138.4+1.21.10
3. Place `aimassist-fabric-1.21.10-1.0.0.jar` dans ton dossier `mods/`
4. Lance Minecraft !

## Utilisation

- Presse **RIGHT ALT** pour activer/désactiver
- Un message apparaîtra dans l'action bar (ON/OFF)
- L'aim assist se déclenche automatiquement quand un joueur est proche

## Comment ça marche

Le mod détecte le joueur le plus proche dans ton champ de vision (FOV) et ajuste doucement ta visée vers lui. Tu peux toujours bouger ta souris normalement, c'est juste une légère assistance.

## Modifier la configuration

Pour changer les paramètres, édite le fichier source `AimAssistMod.java`:
- `maxDistance`: Distance en blocs (1.0 - 20.0)
- `assistStrength`: Force (0.0 = désactivé, 1.0 = full lock)
- `fov`: Champ de vision en degrés (10 - 180)

## Notes

- Ce mod est **client-side** uniquement
- Ne fonctionne que sur les joueurs (pas les mobs)
- Respecte les spectateurs (ne les cible pas)

## Compilation depuis le source

Si tu veux compiler toi-même:
```bash
./gradlew build
```
Le .jar sera dans `build/libs/`
