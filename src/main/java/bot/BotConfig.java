package bot;

/**
 * BotConfig - Configuration settings for the AI Bot
 *
 * This class contains all configurable parameters for the bot behavior.
 * Modify these values to customize how the bot plays.
 */
public class BotConfig {
    // ========== Core Settings ==========
    /**
     * Enable automatic gameplay
     * Set to true for the bot to play automatically
     */
    public static boolean AUTO_PLAY_ENABLED = false;

    /**
     * Enable debug mode
     * Shows detailed logs and debug information
     */
    public static boolean DEBUG_MODE = true;

    /**
     * Delay between actions (in milliseconds)
     * Set to 0 for instant play, or increase to slow down
     */
    public static int ACTION_DELAY_MS = 0;

    // ========== AI Strategy Settings ==========
    /**
     * Minimum HP percentage to prioritize defense
     * If player HP is below this %, prioritize defense cards
     */
    public static int DEFENSE_PRIORITY_HP_THRESHOLD = 30;

    /**
     * Enable energy efficiency consideration
     * If true, prefer cards with better damage/energy ratio
     */
    public static boolean USE_ENERGY_EFFICIENCY = true;

    /**
     * Priority weights for card types
     * Higher values = more priority
     */
    public static double WEIGHT_ATTACK = 10.0;
    public static double WEIGHT_SKILL = 15.0;
    public static double WEIGHT_POWER = 20.0;
    public static double WEIGHT_CURSE = -50.0;
    public static double WEIGHT_STATUS = -30.0;

    /**
     * Priority weights for card rarity
     */
    public static double RARITY_BASIC = 5.0;
    public static double RARITY_COMMON = 3.0;
    public static double RARITY_UNCOMMON = 8.0;
    public static double RARITY_RARE = 12.0;

    // ========== Target Selection ==========
    /**
     * Target selection strategy
     * "lowest_hp" - Attack monster with lowest HP
     * "highest_hp" - Attack monster with highest HP
     * "highest_intent" - Attack monster with highest damage intent
     */
    public static String TARGET_STRATEGY = "lowest_hp";

    // ========== Safety Settings ==========
    /**
     * Maximum number of cards to play per turn
     * Set to 0 for no limit
     */
    public static int MAX_CARDS_PER_TURN = 0;

    /**
     * Reserve energy for emergencies
     * Bot will always keep at least this much energy
     */
    public static int RESERVE_ENERGY = 0;

    /**
     * Enable safe mode
     * If true, bot won't play risky cards when HP is low
     */
    public static boolean SAFE_MODE = true;

    // ========== Logging Settings ==========
    /**
     * Log card usage
     */
    public static boolean LOG_CARD_USAGE = true;

    /**
     * Log monster state changes
     */
    public static boolean LOG_MONSTER_CHANGES = true;

    /**
     * Log AI decisions
     */
    public static boolean LOG_AI_DECISIONS = true;

    /**
     * Log game state updates
     */
    public static boolean LOG_STATE_UPDATES = true;

    // ========== Feature Flags ==========
    /**
     * Enable relic selection AI (not yet implemented)
     */
    public static boolean ENABLE_RELIC_AI = false;

    /**
     * Enable card selection AI (not yet implemented)
     */
    public static boolean ENABLE_CARD_SELECTION_AI = false;

    /**
     * Enable path selection AI (not yet implemented)
     */
    public static boolean ENABLE_PATH_SELECTION_AI = false;
}
