# DEV NOTES 
**UPDATE 16/12/22**
Penambahan class pada base game yang meliputi:
```
application:
  BloodEXP.java
  BLock.java
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
