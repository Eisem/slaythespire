package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MonsterPatch - Hooks to track monster state changes
 *
 * These patches monitor monster actions and state changes.
 */
public class MonsterPatch {
    private static final Logger logger = LogManager.getLogger(MonsterPatch.class);

    /**
     * Patch when monster intent changes
     */
    @SpirePatch(clz = AbstractMonster.class, method = "createIntent")
    public static class OnIntentChange {
        @SpirePostfixPatch
        public static void onIntentChange(AbstractMonster __instance) {
            if (bot.MyBotMod.isDebugMode()) {
                logger.info("Monster " + __instance.name + " intent changed");
            }
            bot.GameState.update();
        }
    }
}
