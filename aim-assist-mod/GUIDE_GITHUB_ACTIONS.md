# 🚀 GUIDE COMPLET : COMPILER TON MOD AVEC GITHUB (SANS RIEN INSTALLER)

## 📋 CE QU'IL TE FAUT
- ✅ Un compte GitHub (gratuit, 2 minutes pour créer)
- ✅ L'archive `aim-assist-mod-source.tar.gz` que je t'ai donnée
- ✅ 5-10 minutes de ton temps
- ❌ RIEN À INSTALLER SUR TON PC !

---

## 📝 ÉTAPE 1 : CRÉER UN COMPTE GITHUB

1. Va sur https://github.com/
2. Clique sur "Sign up" en haut à droite
3. Entre ton email, crée un mot de passe, choisis un username
4. Vérifie ton email
5. C'est bon ! ✅

---

## 📦 ÉTAPE 2 : EXTRAIRE L'ARCHIVE

1. Fais clic droit sur `aim-assist-mod-source.tar.gz`
2. "Extraire ici" ou utilise 7-Zip/WinRAR
3. Tu auras un dossier `aim-assist-mod` avec plein de fichiers dedans

---

## 🆕 ÉTAPE 3 : CRÉER UN NOUVEAU REPOSITORY

1. Va sur GitHub (connecte-toi)
2. Clique sur le **"+"** en haut à droite
3. Clique sur **"New repository"**
4. Configure comme ça :
   ```
   Repository name: aim-assist-mod
   Description: (vide ou "Custom aim assist mod")
   Public ✅ (cochée)
   Add README file: ❌ (PAS cochée)
   Add .gitignore: ❌ (PAS cochée)
   Choose a license: ❌ (PAS cochée)
   ```
5. Clique sur **"Create repository"** (bouton vert)

---

## 📤 ÉTAPE 4 : UPLOADER TES FICHIERS

Tu es maintenant sur la page de ton repo vide. Tu vas voir des instructions.

**Option A : Upload via l'interface web (LE PLUS SIMPLE)**

1. Clique sur **"uploading an existing file"** (lien en bleu au milieu)
2. **Drag & Drop** TOUS les fichiers du dossier `aim-assist-mod` dans la zone
   - Sélectionne TOUT dans le dossier (Ctrl+A)
   - Drag dans la page GitHub
3. ⚠️ **IMPORTANT** : Upload aussi les dossiers cachés :
   - Le dossier `.github` (très important !)
   - Si tu ne vois pas les dossiers cachés :
     * Windows : Affichage > Cocher "Éléments masqués"
     * Ou upload le dossier `.github` séparément
4. Attends que tous les fichiers soient uploadés
5. En bas, écris un message : "Initial commit"
6. Clique sur **"Commit changes"** (bouton vert)

**Option B : Upload via ligne de commande (si tu es à l'aise)**

GitHub te donnera les commandes exactes sur la page, genre :
```bash
cd aim-assist-mod
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/TON_USERNAME/aim-assist-mod.git
git push -u origin main
```

---

## ⚡ ÉTAPE 5 : LANCER LA COMPILATION AUTOMATIQUE

1. Sur la page de ton repo, clique sur l'onglet **"Actions"** (en haut)
2. GitHub va détecter le workflow et te demander d'activer les Actions
3. Clique sur **"I understand my workflows, go ahead and enable them"** (bouton vert)
4. La compilation devrait se lancer automatiquement !
5. Si elle ne démarre pas, va dans "Actions" > "Build Mod" > "Run workflow" (bouton à droite)

**Tu vas voir :**
```
Build Mod
━━━━━━━━━━━━━━━━━━━━━━
✅ build  (en cours...)
```

---

## ⏱️ ÉTAPE 6 : ATTENDRE LA COMPILATION

1. Clique sur le build en cours (le nom "Initial commit" ou autre)
2. Tu verras les étapes défiler :
   ```
   ✅ Checkout code
   ✅ Setup Java 21
   ✅ Setup Gradle
   ✅ Make gradlew executable
   🔄 Build with Gradle (en cours...)
   ⏳ Upload artifact
   ```
3. Ça prend **2-5 minutes** généralement
4. Attends que tout soit vert avec des ✅

---

## 📥 ÉTAPE 7 : TÉLÉCHARGER TON MOD COMPILÉ

1. Une fois que tout est ✅ vert, scroll en bas de la page
2. Tu verras une section **"Artifacts"**
3. Clique sur **"aimassist-mod-1.21.10"**
4. Un fichier ZIP va se télécharger
5. Extrais le ZIP
6. À l'intérieur : **`aimassist-fabric-1.21.10-1.0.0.jar`** 🎉

**C'EST TON MOD COMPILÉ !**

---

## 🎮 ÉTAPE 8 : INSTALLER LE MOD

1. Va dans ton dossier `.minecraft/mods/`
2. Place `aimassist-fabric-1.21.10-1.0.0.jar` dedans
3. Assure-toi d'avoir :
   - ✅ Fabric Loader 0.16+ pour Minecraft 1.21.10
   - ✅ Fabric API 0.138.4+1.21.10
4. Lance Minecraft !

---

## ⌨️ UTILISATION

- **Toggle ON/OFF** : Presse **RIGHT ALT**
- Un message apparaîtra : "§a[AimAssist] ON" ou "§c[AimAssist] OFF"
- C'est tout ! Le mod fonctionne automatiquement quand activé

---

## 🔧 MODIFIER LA CONFIGURATION

Si tu veux changer la force, distance, etc. :

1. Sur GitHub, ouvre le fichier : `src/main/java/com/aimassist/AimAssistMod.java`
2. Clique sur le crayon ✏️ (Edit)
3. Modifie les lignes 18-20 :
   ```java
   private static double maxDistance = 6.0;     // Change la distance
   private static float assistStrength = 0.3f;  // Change la force (0.0 à 1.0)
   private static double fov = 90.0;            // Change le FOV
   ```
4. Clique sur "Commit changes"
5. Retourne dans "Actions" - une nouvelle compilation démarre !
6. Télécharge le nouveau .jar

---

## ❓ DÉPANNAGE

### ❌ "Build failed"
- Clique sur le build rouge
- Regarde les logs pour voir l'erreur
- Souvent c'est un fichier mal uploadé

### ❌ "No workflows found"
- Le dossier `.github/workflows/` n'a pas été uploadé
- Upload-le manuellement

### ❌ "Actions are disabled"
- Va dans Settings > Actions > General
- Coche "Allow all actions"

### ❌ Le mod ne se lance pas
- Vérifie que tu as Fabric API installé
- Vérifie que c'est bien Minecraft 1.21.10
- Regarde les logs dans `.minecraft/logs/latest.log`

---

## 🎯 RÉSUMÉ ULTRA RAPIDE

1. Crée compte GitHub ➜ https://github.com/
2. New repository ➜ "aim-assist-mod"
3. Upload tous les fichiers (surtout `.github/`)
4. Va dans Actions ➜ Active les workflows
5. Attends 3-5 min ⏱️
6. Download le .jar dans Artifacts
7. Place dans `.minecraft/mods/`
8. GG ! 🎉

---

## 💡 ASTUCES

- **Modifier le code ?** Change directement sur GitHub ➜ nouvelle compilation auto
- **Partager le mod ?** Le .jar est dans Artifacts de chaque build
- **Code open source ?** Laisse le repo public, les gens peuvent voir le code

---

## 🆘 BESOIN D'AIDE ?

Si ça marche pas :
1. Vérifie que TOUS les fichiers sont bien sur GitHub (surtout `.github/`)
2. Regarde les logs du build qui a échoué
3. Assure-toi que le fichier `build.yml` est bien dans `.github/workflows/`

**Le système GitHub Actions est GRATUIT et illimité pour les repos publics !**

Tu peux compiler autant de fois que tu veux, modifier le code, etc. - tout dans ton navigateur ! 🚀
