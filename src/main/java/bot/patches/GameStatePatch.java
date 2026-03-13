package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GameStatePatch - Hooks to update game state
 *
 * These patches ensure game state is updated at key moments in the game loop.
 */
public class GameStatePatch {
    private static final Logger logger = LogManager.getLogger(GameStatePatch.class);

    /**
     * Patch the game update method to continuously update state
     */
    @SpirePatch(clz = AbstractDungeon.class, method = "update")
    public static class UpdateGameState {
        @SpirePostfixPatch
        public static void onUpdate() {
            // Update game state every frame
            bot.GameState.update();

            // Let AI make decisions if auto-play is enabled
            if (bot.MyBotMod.isAutoPlayEnabled()) {
                bot.AIEngine.getInstance().makeDecision();
            }
        }
    }

    /**
     * Patch the battle start to reset AI state
     */
    @SpirePatch(clz = com.megacrit.cardcrawl.rooms.AbstractRoom.class, method = "onPlayerEntry")
    public static class OnBattleStart {
        @SpirePostfixPatch
        public static void onBattleStart(com.megacrit.cardcrawl.rooms.AbstractRoom __instance) {
            logger.info("Battle started - resetting AI state");
            bot.AIEngine.getInstance().reset();
        }
    }
}
