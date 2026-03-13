package bot;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * GameInterface - Interface to control game actions
 *
 * This class provides methods to interact with the game programmatically.
 */
public class GameInterface {
    /**
     * Play a card from hand
     *
     * @param card The card to play
     * @param target The target monster (can be null for cards without targets)
     */
    public static void playCard(AbstractCard card, AbstractMonster target) {
        if (card == null) {
            MyBotMod.logger.warn("Attempted to play null card");
            return;
        }

        // Check if card is in hand
        if (!AbstractDungeon.player.hand.contains(card)) {
            MyBotMod.logger.warn("Card " + card.name + " not in hand");
            return;
        }

        // Check if we have enough energy
        if (EnergyPanel.getCurrentEnergy() < card.costForTurn) {
            MyBotMod.logger.warn("Not enough energy for " + card.name);
            return;
        }

        MyBotMod.logger.info("Playing card: " + card.name + " (Energy: " + card.costForTurn + ")");

        // Play the card
        if (card.target == AbstractCard.CardTarget.ENEMY && target != null) {
            AbstractDungeon.player.hand.moveToExhaustPile(card);
            card.use(AbstractDungeon.player, target);
        } else if (card.target == AbstractCard.CardTarget.SELF || card.target == AbstractCard.CardTarget.NONE) {
            AbstractDungeon.player.hand.moveToExhaustPile(card);
            card.use(AbstractDungeon.player, null);
        } else if (card.target == AbstractCard.CardTarget.ALL || card.target == AbstractCard.CardTarget.ALL_ENEMY) {
            AbstractDungeon.player.hand.moveToExhaustPile(card);
            card.use(AbstractDungeon.player, null);
        }
    }

    /**
     * Play a card by index from hand
     *
     * @param index The index of the card in hand
     */
    public static void playCardByIndex(int index) {
        if (index < 0 || index >= AbstractDungeon.player.hand.group.size()) {
            MyBotMod.logger.warn("Invalid card index: " + index);
            return;
        }

        AbstractCard card = AbstractDungeon.player.hand.group.get(index);
        AbstractMonster target = selectTarget(card);
        playCard(card, target);
    }

    /**
     * Select appropriate target for a card
     *
     * @param card The card to find a target for
     * @return The selected monster or null
     */
    private static AbstractMonster selectTarget(AbstractCard card) {
        if (card.target != AbstractCard.CardTarget.ENEMY) {
            return null;
        }

        // Simple strategy: target the monster with lowest HP
        List<AbstractMonster> monsters = getAliveMonsters();
        if (monsters.isEmpty()) {
            return null;
        }

        AbstractMonster lowestHP = monsters.get(0);
        for (AbstractMonster monster : monsters) {
            if (monster.currentHealth < lowestHP.currentHealth) {
                lowestHP = monster;
            }
        }

        return lowestHP;
    }

    /**
     * End current turn
     */
    public static void endTurn() {
        MyBotMod.logger.info("Ending turn");
        AbstractDungeon.overlayMenu.endTurnButton.clicked();
    }

    /**
     * Get all alive monsters
     *
     * @return List of alive monsters
     */
    public static List<AbstractMonster> getAliveMonsters() {
        List<AbstractMonster> alive = new ArrayList<>();
        if (AbstractDungeon.getCurrRoom() != null &&
            AbstractDungeon.getCurrRoom().monsters != null) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!monster.isDead && !monster.isDying && !monster.halfDead) {
                    alive.add(monster);
                }
            }
        }
        return alive;
    }

    /**
     * Get current hand cards
     *
     * @return List of cards in hand
     */
    public static List<AbstractCard> getHandCards() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand != null) {
            return new ArrayList<>(AbstractDungeon.player.hand.group);
        }
        return new ArrayList<>();
    }

    /**
     * Get current energy
     *
     * @return Current energy value
     */
    public static int getEnergy() {
        return EnergyPanel.getCurrentEnergy();
    }

    /**
     * Check if we are in a battle
     *
     * @return true if in battle
     */
    public static boolean isInBattle() {
        return AbstractDungeon.getCurrRoom() != null &&
               AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT &&
               !AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead();
    }

}
