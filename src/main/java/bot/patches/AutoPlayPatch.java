package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
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
        // Use rloc (relative line) for more robust patching
        @SpireInsertPatch(rloc = 50)
        public static void insertAutoPlay() {
            // Only make decisions if auto-play is enabled
            if (bot.MyBotMod.isAutoPlayEnabled()) {
                bot.AIEngine.getInstance().makeDecision();
            }
        }
    }

    /**
     * Alternative patch that uses locator for more robust targeting
     * This is more resilient to game updates
     */
    @SpirePatch(clz = com.megacrit.cardcrawl.rooms.AbstractRoom.class, method = "update")
    public static class CombatUpdatePatch {
        @SpireInsertPatch(locator = CombatLocator.class)
        public static void combatUpdate(com.megacrit.cardcrawl.rooms.AbstractRoom __instance) {
            // Check if we're in combat phase
            if (__instance.phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) {
                if (bot.MyBotMod.isAutoPlayEnabled()) {
                    bot.AIEngine.getInstance().makeDecision();
                }
            }
        }

        /**
         * Locator to find the right place to insert our code
         * This finds the line where monsters are updated in combat
         */
        private static class CombatLocator extends com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator {
            @Override
            public int[] Locate(com.evacipated.cardcrawl.modthespire.lib.spire.CtBehavior ctMethodToPatch)
                throws com.evacipated.cardcrawl.modthespire.lib.spire.CannotCompileException,
                   com.evacipated.cardcrawl.modthespire.lib.PatchingException {

                // Find the line where monsters are updated
                com.evacipated.cardcrawl.modthespire.lib.finders.Matcher finalMatcher =
                    new com.evacipated.cardcrawl.modthespire.lib.finders.Matcher.MethodCallMatcher(
                        com.megacrit.cardcrawl.dungeons.AbstractDungeon.class,
                        "getMonsters"
                    );

                return com.evacipated.cardcrawl.modthespire.lib.finders.LineFinder.findInOrder(
                    ctMethodToPatch,
                    new java.util.ArrayList<>(),
                    finalMatcher
                );
            }
        }
    }
}
