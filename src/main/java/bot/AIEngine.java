package bot;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.List;

/**
 * AIEngine - Main AI decision making engine
 *
 * This class coordinates between different AI strategies and executes decisions.
 */
public class AIEngine {
    private static AIEngine instance;
    private AIAgent currentAgent;

    private AIEngine() {
        // Default to rule-based agent
        currentAgent = new bot.ai.RuleBasedAgent();
    }

    /**
     * Get singleton instance
     */
    public static AIEngine getInstance() {
        if (instance == null) {
            instance = new AIEngine();
        }
        return instance;
    }

    /**
     * Set the current AI agent
     */
    public void setAgent(AIAgent agent) {
        this.currentAgent = agent;
        MyBotMod.logger.info("AI Agent changed to: " + agent.getClass().getSimpleName());
    }

    /**
     * Make a decision and execute it
     *
     * @return true if an action was taken
     */
    public boolean makeDecision() {
        // Update game state first
        GameState.update();

        // Check if we're in a battle and should make decisions
        if (!GameState.isInBattle()) {
            return false;
        }

        // Check if auto-play is enabled
        if (!MyBotMod.isAutoPlayEnabled()) {
            return false;
        }

        // Check if any selection screen is active
        if (GameInterface.isSelectingFromGrid()) {
            MyBotMod.logger.debug("Grid selection active, canceling");
            GameInterface.cancelSelection();
            return false;
        }

        // Get decision from current agent
        AIAction action = currentAgent.decideAction();

        if (action != null) {
            MyBotMod.logger.info("AI Decision: " + action.getType() + " - " + action.getDescription());
            return action.execute();
        }

        // If no action decided, end turn
        MyBotMod.logger.info("No action available, ending turn");
        GameInterface.endTurn();
        return true;
    }

    /**
     * Reset AI state (call at start of new battle)
     */
    public void reset() {
        if (currentAgent != null) {
            currentAgent.reset();
        }
        MyBotMod.logger.info("AI Engine reset");
    }
}

/**
 * Interface for AI agents
 */
interface AIAgent {
    /**
     * Decide what action to take based on current game state
     *
     * @return The action to take, or null if no action
     */
    AIAction decideAction();

    /**
     * Reset agent state
     */
    void reset();
}

/**
 * AIAction - Represents a game action
 */
class AIAction {
    public enum ActionType {
        PLAY_CARD,
        END_TURN,
        USE_POTION,
        SKIP
    }

    private final ActionType type;
    private final String description;
    private final Object data;

    public AIAction(ActionType type, String description, Object data) {
        this.type = type;
        this.description = description;
        this.data = data;
    }

    public ActionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Object getData() {
        return data;
    }

    /**
     * Execute this action
     *
     * @return true if action was successful
     */
    public boolean execute() {
        switch (type) {
            case PLAY_CARD:
                if (data instanceof Integer) {
                    GameInterface.playCardByIndex((Integer) data);
                    return true;
                }
                break;
            case END_TURN:
                GameInterface.endTurn();
                return true;
            case SKIP:
                return true;
            default:
                MyBotMod.logger.warn("Unknown action type: " + type);
                return false;
        }
        return false;
    }
}
