# Setup Guide - STS Bot Mod

## Quick Start (5 minutes)

### 1. Install Game Dependencies

You need 3 JAR files in the `libs/` directory:

```bash
mkdir -p libs
cd libs
```

#### A. Get Game JAR
- Find your Slay the Spire installation directory
- Locate `desktop-1.0.jar`
- Copy to `libs/` folder

**Typical locations:**
- Windows: `C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\`
- Linux: `~/.steam/steam/steamapps/common/SlayTheSpire/`
- macOS: `~/Library/Application Support/Steam/steamapps/common/SlayTheSpire/`

#### B. Get ModTheSpire
- Visit: https://github.com/kiooeht/ModTheSpire/releases
- Download latest `ModTheSpire.jar`
- Copy to `libs/` folder

#### C. Get BaseMod
- Visit: https://github.com/SkyeStarfall/BaseMod/releases
- Download latest `BaseMod.jar`
- Copy to `libs/` folder

**Your libs/ folder should look like:**
```
libs/
├── desktop-1.0.jar
├── ModTheSpire.jar
└── BaseMod.jar
```

### 2. Build the Mod

```bash
# From project root
mvn clean package
```

**Success indicator:** `BUILD SUCCESS` and `target/sts-bot-mod-1.0.0.jar` created

### 3. Install to Game

1. Copy `target/sts-bot-mod-1.0.0.jar` to your game's `mods/` folder
2. Make sure ModTheSpire and BaseMod are also in `mods/`
3. Run game with ModTheSpire

**Windows:**
```cmd
cd "C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\"
MTS.cmd
```

**Linux:**
```bash
cd ~/.steam/steam/steamapps/common/SlayTheSpire/
chmod +x MTS.sh
./MTS.sh
```

### 4. Enable Auto-Play

Edit `src/main/java/bot/MyBotMod.java`:
```java
// Line 38 - Change this:
private static boolean autoPlayEnabled = true;  // was: false
```

Rebuild:
```bash
mvn clean package
```

Copy new JAR to `mods/` folder again.

### 5. Test!

1. Start a new game (Ironclad recommended)
2. Enter any battle
3. Watch the AI automatically play cards!

Check console for:
```
STS Bot Mod initialized successfully!
AI Decision: PLAY_CARD - Attack (6 dmg) - 1 energy
```

## Troubleshooting

### Error: "Cannot find symbol"
**Cause:** Game JARs not in `libs/` folder
**Fix:** Make sure all 3 JARs are copied to `libs/`

### Error: "Mod not loading"
**Cause:** `mod.json` missing or wrong location
**Fix:** Check that `src/main/resources/mod.json` exists

### Auto-play not working
**Cause:** `autoPlayEnabled` is still `false`
**Fix:** Edit `MyBotMod.java` and rebuild

### Compilation fails with Java version error
**Cause:** Wrong Java version
**Fix:** Install Java 8 and set JAVA_HOME
```bash
java -version  # Should show 1.8.x
```

## Advanced Setup

### Using Maven with System Dependencies

The `pom.xml` uses system-scoped dependencies. This means:
- JARs must be in `libs/` folder
- JARs are NOT uploaded to Maven repositories
- Project is self-contained

### Creating a Development Environment

1. Install IntelliJ IDEA or Eclipse
2. Import project as Maven project
3. Configure SDK to Java 8
4. Mark `libs/` as source library

### Running Tests

```bash
mvn test
```

## Next Steps

- Read the full [README.md](README.md)
- Understand the [Architecture](README.md#-architecture)
- Customize the [AI Agent](README.md#adding-new-ai-agents)
- Add your own [Game Hooks](README.md#adding-new-game-hooks)

## Need Help?

Check logs in:
- ModTheSpire console (press `` ` `` to open)
- Game log files in your OS's temp directory
- GitHub Issues: https://github.com/Eisem/slaythespire/issues
