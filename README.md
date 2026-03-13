# STS Bot Mod

An AI-powered bot that can automatically play Slay the Spire using game mods and rule-based decision making.

## 🎯 Features

- ✅ Automatic gameplay with rule-based AI
- ✅ Real-time game state extraction
- ✅ Extensible AI agent system
- ✅ Debug mode with detailed logging
- ✅ Simple toggle for auto-play

## 📋 Requirements

- **Java 8** or higher
- **Maven** for building
- **Slay the Spire** (Steam or GOG version)
- **ModTheSpire** - External mod loader
- **BaseMod** - Modding framework

## 🚀 Installation

### Step 1: Install Dependencies

1. **Download ModTheSpire**
   - Go to: https://github.com/kiooeht/ModTheSpire/releases
   - Download the latest `ModTheSpire.jar`
   - Copy to your Slay the Spire installation directory

2. **Download BaseMod**
   - Go to: https://github.com/SkyeStarfall/BaseMod/releases
   - Download the latest `BaseMod.jar`
   - Copy to the `mods` folder in your Slay the Spire directory

### Step 2: Build the Bot Mod

```bash
# Clone the repository
git clone https://github.com/Eisem/slaythespire.git
cd slaythespire

# Create libs directory for game dependencies
mkdir -p libs

# Copy game JARs to libs directory (see Setup Guide below)
# - Copy desktop-1.0.jar from Slay the Spire directory
# - Copy ModTheSpire.jar to libs
# - Copy BaseMod.jar to libs

# Build with Maven
mvn clean package

# The compiled JAR will be in target/sts-bot-mod-1.0.0.jar
```

### Step 3: Install the Mod

1. Copy `target/sts-bot-mod-1.0.0.jar` to the `mods` folder
2. Run the game using ModTheSpire:
   - Windows: `MTS.cmd`
   - Linux: `MTS.sh`

## 🎮 Usage

### Enabling Auto-Play

The mod currently has auto-play **disabled by default**. To enable it:

1. **Quick Method** - Edit code:
   - Open `src/main/java/bot/MyBotMod.java`
   - Find line 38: `private static boolean autoPlayEnabled = false;`
   - Change to: `private static boolean autoPlayEnabled = true;`
   - Rebuild: `mvn clean package`

2. **Console Method** (if using BaseMod console):
   - Press backtick (`` ` ``) to open console
   - Type: `bot.MyBotMod.setAutoPlayEnabled(true)`

### Debug Mode

Debug mode is enabled by default and will:
- Log all game state changes
- Display AI decisions in console
- Show detailed information about each action

To disable, set `debugMode = false` in `MyBotMod.java`.

## 🏗️ Architecture

```
sts-bot-mod/
├── src/main/java/bot/
│   ├── MyBotMod.java           # Mod entry point
│   ├── GameState.java          # Game state management
│   ├── GameInterface.java      # Game action interface
│   ├── AIEngine.java           # AI decision engine
│   ├── patches/                # Game hooks
│   │   ├── GameStatePatch.java
│   │   ├── HandCardPatch.java
│   │   ├── MonsterPatch.java
│   │   └── AutoPlayPatch.java
│   └── ai/                     # AI agents
│       └── RuleBasedAgent.java # Rule-based AI
└── src/main/resources/
    └── mod.json                # Mod metadata
```

## 🧠 How It Works

### 1. Game State Extraction

The mod hooks into game events using **SpirePatch** to extract:
- Player HP, energy, block
- Hand cards
- Monster information (HP, intent)
- Dungeon state

### 2. Decision Making

The **RuleBasedAgent** uses a priority system:

1. Calculate scores for all playable cards
2. Consider card type, energy efficiency, and game state
3. Select the highest-scoring card
4. If no good cards, end turn

### 3. Action Execution

The AI plays cards through the **GameInterface**:
- Automatically selects targets
- Manages energy usage
- Handles special card types (AOE, self-target)

## 🔧 Development

### Adding New AI Agents

Create a new class implementing `AIAgent`:

```java
package bot.ai;

import bot.AIAction;

public class MyCustomAgent implements bot.AIAgent {
    @Override
    public AIAction decideAction() {
        // Your custom logic here
        return new AIAction(AIAction.ActionType.PLAY_CARD, "My logic", 0);
    }

    @Override
    public void reset() {
        // Reset state if needed
    }
}
```

### Adding New Game Hooks

Create a new patch class in `patches/`:

```java
package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;

@SpirePatch(clz = SomeClass.class, method = "someMethod")
public class MyNewPatch {
    @SpirePostfixPatch
    public static void onSomeMethod(Object __instance) {
        // Your hook logic here
        bot.GameState.update();
    }
}
```

## 🐛 Troubleshooting

### Mod not loading
- Check that `mod.json` is in the correct location
- Verify Java 8 is installed
- Check console logs for errors

### Auto-play not working
- Verify `autoPlayEnabled` is set to `true`
- Make sure you're in battle
- Check that there are playable cards in hand

### Compilation errors
- Ensure all dependency JARs are in the `libs` folder
- Verify Maven is installed correctly
- Check Java version (must be 1.8)

## 📚 Resources

- [ModTheSpire Wiki](https://github.com/kiooeht/ModTheSpire/wiki)
- [BaseMod Wiki](https://github.com/SkyeStarfall/BaseMod/wiki)
- [SpirePatch Documentation](https://github.com/kiooeht/ModTheSpire/wiki/SpirePatch)
- [Slay the Spire Modding Discord](https://discord.gg/slaythespiremods)

## 🎯 Roadmap

- [ ] Improve AI scoring system
- [ ] Add deck tracking
- [ ] Implement ML-based agent
- [ ] Add UI for settings
- [ ] Support multiple characters
- [ ] Relic selection AI

## 📄 License

MIT License

## 👥 Author

Eisem

## 🙏 Acknowledgments

- [ModTheSpire](https://github.com/kiooeht/ModTheSpire) - Mod loader framework
- [BaseMod](https://github.com/SkyeStarfall/BaseMod) - Modding utilities
- The Slay the Spire modding community
