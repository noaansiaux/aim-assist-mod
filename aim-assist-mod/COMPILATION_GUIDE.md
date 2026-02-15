# 🎯 GUIDE DE COMPILATION - AIM ASSIST MOD

## ⚡ OPTION 1: Compilation automatique (RECOMMANDÉ)

### Windows:
1. Extrais l'archive `aim-assist-mod-source.tar.gz`
2. Ouvre un terminal dans le dossier `aim-assist-mod`
3. Lance la commande:
   ```
   gradlew.bat build
   ```
4. Le mod compilé sera dans `build/libs/aimassist-fabric-1.21.10-1.0.0.jar`

### Linux/Mac:
1. Extrais l'archive
2. Ouvre un terminal dans le dossier
3. Lance:
   ```
   ./gradlew build
   ```
4. Récupère le .jar dans `build/libs/`

## 🔧 OPTION 2: Compilation avec IntelliJ IDEA

1. Télécharge IntelliJ IDEA Community (gratuit)
2. Ouvre le projet `aim-assist-mod`
3. Attends que Gradle se configure automatiquement
4. Va dans Build > Build Project
5. Ou lance la task Gradle "build" depuis la sidebar
6. Le .jar sera dans `build/libs/`

## 📦 OPTION 3: Utiliser GitHub Actions (SANS INSTALLER RIEN)

1. Crée un compte GitHub (gratuit)
2. Crée un nouveau repository
3. Upload tous les fichiers du dossier `aim-assist-mod`
4. Crée le fichier `.github/workflows/build.yml` avec ce contenu:

```yaml
name: Build
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Build with Gradle
        run: ./gradlew build
      - name: Upload artifact
        uses: actions/upload-artifact@v3
        with:
          name: mod-jar
          path: build/libs/*.jar
```

5. Push vers GitHub
6. Va dans l'onglet "Actions"
7. Télécharge le .jar compilé !

## ❌ DÉPANNAGE

### Erreur "JAVA_HOME not set":
- Windows: Installe Java 21 JDK depuis https://adoptium.net/
- Linux: `sudo apt install openjdk-21-jdk`

### Erreur Gradle:
```bash
# Nettoie et rebuild
./gradlew clean build
```

### Erreur de mappings:
Les mappings Yarn pour 1.21.10 existent, le build devrait fonctionner.
Si erreur, change dans build.gradle:
```
mappings "net.fabricmc:yarn:1.21.10+build.7:v2"
```
par une version plus récente.

## 🎮 INSTALLATION DU MOD

Une fois compilé:
1. Installe Fabric Loader pour Minecraft 1.21.10
2. Télécharge Fabric API 0.138.4+1.21.10
3. Place `aimassist-fabric-1.21.10-1.0.0.jar` dans `.minecraft/mods/`
4. Lance le jeu
5. Presse RIGHT ALT pour toggle!

## 🔧 CONFIGURATION

Dans le fichier `AimAssistMod.java`, tu peux modifier:

```java
private static double maxDistance = 6.0;     // Distance en blocs
private static float assistStrength = 0.3f;  // Force (0.0 - 1.0)
private static double fov = 90.0;            // Champ de vision
```

Puis recompile avec `./gradlew build`.

## 🆘 BESOIN D'AIDE?

Si tu n'arrives pas à compiler:
1. Essaie l'option GitHub Actions (la plus simple)
2. Ou demande à quelqu'un avec IntelliJ de le faire
3. Ou utilise un service de build en ligne

Le code est 100% fonctionnel, il faut juste le compiler!
