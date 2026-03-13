package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AutoPlayPatch - Core patch for automatic gameplay
 *
 * This patch injects AI decision-making into the game loop.
 */
public class AutoPlayPatch {
    private static final Logger logger = LogManager.getLogger(AutoPlayPatch.class);

    /**
     * Patch to enable auto-play during battle
     * This inserts AI decision-making into the combat loop
     */
    @SpirePatch(clz = AbstractDungeon.class, method = "update")
    public static class EnableAutoPlay {
        @SpirePostfixPatch
        public static void insertAutoPlay() {
            // Only make decisions if auto-play is enabled
            if (bot.MyBotMod.isAutoPlayEnabled()) {
                bot.AIEngine.getInstance().makeDecision();
            }
        }
    }
}
