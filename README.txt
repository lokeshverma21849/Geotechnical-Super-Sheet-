MiniSheet APK (bina Android Studio ke)

1. github.com pe free account banao, naya repository banao (naam: minisheet).
2. Is zip ke saare files repo mein upload karo (www folder, package.json,
   capacitor.config.json, .gitignore).
3. Workflow file: repo mein "Add file > Create new file" dabao, naam mein likho
   .github/workflows/build.yml  aur build.yml ka content paste karo.
   (Zip mein ye file already hai, agar .github folder upload ho jaye to ye step skip.)
4. Repo ke "Actions" tab mein "Build APK" chalao (Run workflow). 5-8 minute lagte hain.
5. Complete hone pe us run mein "MiniSheet-apk" artifact download karo,
   zip kholo, app-debug.apk phone mein install karo
   (Settings mein "Install unknown apps" allow karna padega).
