import os, shutil
p = 'android/app/src/main/AndroidManifest.xml'
s = open(p, encoding='utf8').read()
f = r'''
<intent-filter>
<action android:name="android.intent.action.VIEW"/>
<category android:name="android.intent.category.DEFAULT"/>
<category android:name="android.intent.category.BROWSABLE"/>
<data android:scheme="content"/>
<data android:scheme="file"/>
<data android:mimeType="*/*"/>
</intent-filter>
<intent-filter>
<action android:name="android.intent.action.VIEW"/>
<category android:name="android.intent.category.DEFAULT"/>
<category android:name="android.intent.category.BROWSABLE"/>
<data android:scheme="content"/>
<data android:scheme="file"/>
<data android:host="*"/>
<data android:mimeType="*/*"/>
<data android:pathPattern=".*\\.lokesh"/>
<data android:pathPattern=".*\\..*\\.lokesh"/>
</intent-filter>
'''
assert '</activity>' in s
s = s.replace('</activity>', f + '</activity>', 1)
open(p, 'w', encoding='utf8').write(s)
d = 'android/app/src/main/java/com/lokesh/minisheet'
os.makedirs(d, exist_ok=True)
shutil.copy('MainActivity.java', d + '/MainActivity.java')
print('patched')
