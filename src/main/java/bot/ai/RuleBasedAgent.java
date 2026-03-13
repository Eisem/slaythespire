package bot.ai;

import bot.AIAction;
import bot.AIAgent;
import bot.GameState;
import bot.MyBotMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * RuleBasedAgent - AI agent that uses rule-based decision making
 *
 * This agent uses a priority-based system to decide which card to play.
 * Rules can be easily adjusted and expanded.
 */
public class RuleBasedAgent implements AIAgent {
    private static final Logger logger = LogManager.getLogger(RuleBasedAgent.class);

    @Override
    public AIAction decideAction() {
        // Get current game state
        GameState.update();

        int energy = GameState.getEnergy();
        List<AbstractCard> hand = GameState.getHandCards();
        List<AbstractCard> playable = getPlayableCards(energy);

        if (playable.isEmpty()) {
            return new AIAction(AIAction.ActionType.END_TURN, "No playable cards", null);
        }

        // Score each card and pick the best one
        AbstractCard bestCard = scoreAndSelectCard(playable);

        if (bestCard != null) {
            int cardIndex = hand.indexOf(bestCard);
            String reason = getCardPlayReason(bestCard);
            return new AIAction(
                AIAction.ActionType.PLAY_CARD,
                reason + ": " + bestCard.name,
                cardIndex
            );
        }

        return new AIAction(AIAction.ActionType.END_TURN, "No suitable card found", null);
    }

    @Override
    public void reset() {
        logger.info("RuleBasedAgent reset");
        // Clear any cached state if needed
    }

    /**
     * Get cards that can be played with current energy
     */
    private List<AbstractCard> getPlayableCards(int energy) {
        List<AbstractCard> playable = new ArrayList<>();
        for (AbstractCard card : GameState.getHandCards()) {
            if (card.costForTurn <= energy && card.cost >= 0) {
                playable.add(card);
            }
        }
        return playable;
    }

    /**
     * Score cards and select the best one
     */
    private AbstractCard scoreAndSelectCard(List<AbstractCard> cards) {
        return cards.stream()
            .max(Comparator.comparingDouble(this::scoreCard))
            .orElse(null);
    }

    /**
     * Score a card based on game state and priorities
     * Higher score = more important to play
     */
    private double scoreCard(AbstractCard card) {
        double score = 0;

        // Base score from card type
        switch (card.type) {
            case ATTACK:
                score += 10;
                break;
            case SKILL:
                score += 15;
                break;
            case POWER:
                score += 20;
                break;
            case CURSE:
                score -= 50;
                break;
            case STATUS:
                score -= 30;
                break;
        }

        // Adjust score based on card rarity
        switch (card.rarity) {
            case BASIC:
                score += 5;
                break;
            case COMMON:
                score += 3;
                break;
            case UNCOMMON:
                score += 8;
                break;
            case RARE:
                score += 12;
                break;
        }

        // Energy efficiency (more damage/energy = higher score)
        if (card.type == AbstractCard.CardType.ATTACK && card.damage > 0 && card.costForTurn > 0) {
            double efficiency = (double) card.damage / card.costForTurn;
            score += efficiency * 5;
        }

        // Adjust for health concerns
        int hpPercent = (GameState.getPlayerHP() * 100) / GameState.getMaxPlayerHP();
        if (hpPercent < 30) {
            // Low HP: prioritize defense
            if (card.type == AbstractCard.CardType.SKILL) {
                score += 20;
            }
        }

        // Block considerations
        int block = GameState.getPlayerBlock();
        int monsterIntent = getHighestMonsterIntent();
        if (block < monsterIntent && card.type == AbstractCard.CardType.SKILL) {
            score += 15;
        }

        // Don't waste energy on low-impact cards
        if (card.costForTurn == 0) {
            score += 5; // 0-cost cards are flexible
        }

        return score;
    }

    /**
     * Get the highest damage intent among monsters
     */
    private int getHighestMonsterIntent() {
        return GameState.getMonsters().stream()
            .mapToInt(m -> m.intentDamage)
            .max()
            .orElse(0);
    }

    /**
     * Get a human-readable reason for playing a card
     */
    private String getCardPlayReason(AbstractCard card) {
        StringBuilder reason = new StringBuilder();

        // Card type
        switch (card.type) {
            case ATTACK:
                reason.append("Attack (").append(card.damage).append(" dmg)");
                break;
            case SKILL:
                reason.append("Skill");
                break;
            case POWER:
                reason.append("Power");
                break;
            default:
                reason.append(card.type.toString());
        }

        // Energy info
        reason.append(" - ").append(card.costForTurn).append(" energy");

        return reason.toString();
    }
}
