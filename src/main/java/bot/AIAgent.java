package bot;

/**
 * Interface for AI agents
 */
public interface AIAgent {
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
