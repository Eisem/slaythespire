package bot;

/**
 * AIAction - Represents a game action
 */
public class AIAction {
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
