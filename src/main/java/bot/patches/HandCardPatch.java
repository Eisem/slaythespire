package bot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
     * Patch when a card is played
     */
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard", paramtypez = {AbstractCard.class, AbstractMonster.class, int.class})
    public static class OnCardUsed {
        @SpirePostfixPatch
        public static void onCardUsed(AbstractPlayer __instance, AbstractCard card, AbstractMonster monster, int energyOnUse) {
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
