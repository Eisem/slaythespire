package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * HandCardPatch - Hooks to track hand card changes
 *
 * These patches monitor when cards are added, removed, or used from hand.
 */
public class HandCardPatch {
    private static final Logger logger = LogManager.getLogger(HandCardPatch.class);

    /**
     * Patch when cards are drawn
     */
    @SpirePatch(clz = AbstractPlayer.class, method = "draw", paramtypez = {int.class})
    public static class OnCardsDrawn {
        @SpirePostfixPatch
        public static void onCardsDrawn(int __result, int numCards) {
            if (bot.MyBotMod.isDebugMode()) {
                logger.info("Player drew " + numCards + " card(s)");
            }
            bot.GameState.update();
        }
    }

    /**
     * Patch when a card is played
     */
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class OnCardUsed {
        @SpirePostfixPatch
        public static void onCardUsed(AbstractPlayer __instance, AbstractCard card) {
            if (bot.MyBotMod.isDebugMode()) {
                logger.info("Player used card: " + card.name);
            }
            bot.GameState.update();
        }
    }

    /**
     * Patch when hand is refreshed (start of turn)
     */
    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnRelics")
    public static class OnTurnStart {
        @SpirePostfixPatch
        public static void onTurnStart(AbstractPlayer __instance) {
            if (bot.MyBotMod.isDebugMode()) {
                logger.info("Turn started");
            }
            bot.GameState.update();
        }
    }
}
