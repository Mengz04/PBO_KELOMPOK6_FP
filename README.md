# DEV NOTES  

**UPDATE 17/12/22 - 09:26:00**

Penambahan fitur pada game yang meliputi:
  1. Sistem drop reward ketika zombie mati dengan rate 5% menggunakan RNG
  2. Upgrade talent dan buf via drop reward
  3. Penyesuaian beberapa class perihal concurrentLinkedQueue
  
>By: Muhammad Rafi Insan Fillah / 5025211169 (Mengz04)

**UPDATE 17/12/22 - 01:10:00**

Penambahan fitur pada game yang meliputi:
  1. Sistem Collision Player dan Mob terhadap Block di Area Permainan

Serta memperbarui GameObject.java dengan:
  1. Menambah variabel EXPCap
  2. Melakukan penetapan nilai EXPCap player pada konstruktor

>by Ahda Filza Ghaffaru / 5025211144 (Ahdaaa)

**UPDATE 16/12/22 - 22:06:00**

Penambahan class dan assets pada base game yang meliputi:
```
application:
  BombDevil.java
  Grenade.java
  Explosion.java
  GunDevil.java
  Bullet.java
  
resources:
  rezeGrenade.gif
  explosionGif.gif
  warehouse.png (update)
```
Bug fix meliputi:
  1. concurrent exception
  2. boundary game area
  3. mob spawn kuadran adjustment
  4. EXPBar dan leveling system

>By: Muhammad Rafi Insan Fillah / 5025211169 (Mengz04)

**UPDATE 16/12/22 - 20:37:00**

Penambahan class dan assets pada base game yang meliputi:
```
application:
  MainMenuWindow.java  
  MenuButton.java
  
resources:
  buttonLong_beige_pressed.png  
  buttonLong_beige.png
  chainsawman_bg.png
```
Penambahan fitur meliputi:
  1. Main menu yang berisi tombol start, credit, dan exit  

Current bug yang harus diperbaiki:
  1. Formula vertical area boundary pada movement player
  2. Spawn zombie yang out of bound
  3. Collision block dengan player dan zombie

Next target:
  1. Collision system pada block terhadap player dan zombie  
  2. EXP dan HP HUD  
  3. Talent system  
  4. Menambah Credit Subscene
  
>By: Aurelio Killian Lexi Verrill / 5025211126 (aurelioklv)

**UPDATE 16/12/22**

Penambahan class dan assets pada base game yang meliputi:
```
application:
  BloodEXP.java
  BLock.java
  
resources:
  blood.gif
  container-green-horizontal.png
  container-green-vertical.png
```
Penambahan fitur meliputi:
  1. BloodEXP yang akan menambah player EXP jika diambil
  2. Sistem leveling dan mob spawn rate increase berdasarkan level

Bug fix yang meliputi:
  1. Penerapan ConcurrentLinkedQueue pada handler untuk exception ketika banyak zombie despawn sekaligus
  2. Formula horizontal area boundary pada movement player

Current bug yang harus diperbaiki:
  1. Formula vertical area boundary pada movement player
  2. Spawn zombie yang out of bound
  3. Collision block dengan player dan zombie

Next target:
  1. Sistem menu
  2. Collision system pada block terhadap player dan zombie
  3. EXP dan HP HUD
  4. Talent system
  
>By: Muhammad Rafi Insan Fillah / 5025211169 (Mengz04)

**UPDATE 11/12/22**

Upload base game yang meliputi:
```
application (base game package):
  Game.java
  GameWindow.java
  GameObject.java
  Handler.java
  Camera.java
  ID.java
  Player.java
  Mob.java
  Pochita.java
  
resources (media assets package):
  CSM-left.png
  CSM-right.png
  CSM-idle-right-GIF.gif
  CSM-walk-left-GIF-1.gif
  CSM-walk-right-1.gif
  zombie-walk-right-GIF.gif
  zombie-walk-left-GIF.gif
  pochita-left.gif
  pochita-leftDown.gif
  pochita-right.gif
  pochita-rightDown.gif
  warehouse.png
```
Next target:
  1. Sistem main menu dengan methods return Stage ke Game.java
  2. Fix problem background ancorPane yang terbatas pada size Scene
  3. Penambahan variasi talent dan tipe normal attack
  4. Penambahan audio dan video media player untuk BGM dan cutscene (cutscene opsional jika memungkinkan)
  5. Sistem exp dan level-up pada gameWindow dengan pertumbuhan spawn-rate Mob
  6. Sistem talent drop reward dari Mob yang gugur
>By: Muhammad Rafi Insan Fillah / 5025211169 (Mengz04)
