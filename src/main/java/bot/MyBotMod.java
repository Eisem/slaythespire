package bot;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostRenderSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * STS Bot Mod - Main Entry Point
 *
 * This mod provides an AI bot that can automatically play Slay the Spire.
 * It hooks into game events to extract state information and make decisions.
 */
@SpireInitializer
public class MyBotMod implements PostInitializeSubscriber, PostRenderSubscriber {
    public static final Logger logger = LogManager.getLogger(MyBotMod.class);
    public static final String MOD_ID = "sts-bot-mod";
    public static final String MOD_NAME = "STS Bot Mod";
    public static final String MOD_VERSION = "1.0.0";
    public static final String MOD_AUTHOR = "Eisem";
    public static final String MOD_DESCRIPTION = "AI Bot for automated Slay the Spire gameplay";

    // Mod settings
    private static boolean autoPlayEnabled = false;
    private static boolean debugMode = true;

    public MyBotMod() {
        BaseMod.subscribe(this);
        logger.info("STS Bot Mod initialized successfully!");
    }

    /**
     * Mod initialization - Called once when the mod loads
     */
    public static void initialize() {
        logger.info("========================================");
        logger.info("STS Bot Mod v" + MOD_VERSION);
        logger.info("========================================");

        new MyBotMod();
    }

    /**
     * Post-initialization - Add mod panel and settings
     */
    @Override
    public void receivePostInitialize() {
        logger.info("STS Bot Mod: Post initialization");

        // Create mod panel
        ModPanel settingsPanel = new ModPanel();

        // Add settings here (buttons, labels, etc.)
        // TODO: Add UI controls for enabling/disabling auto-play

        BaseMod.registerModBadge(
            new Texture(Gdx.files.internal("mod_badge.png")),
            MOD_NAME,
            MOD_AUTHOR,
            MOD_DESCRIPTION,
            settingsPanel
        );

        logger.info("STS Bot Mod: Mod panel registered");
    }

    /**
     * Post-render - Called after every frame is rendered
     * Used for debug information display
     */
    @Override
    public void receivePostRender(SpriteBatch sb) {
        if (debugMode) {
            // TODO: Render debug information on screen
            // GameState.renderDebugInfo(sb);
        }
    }

    // Getters and setters
    public static boolean isAutoPlayEnabled() {
        return autoPlayEnabled;
    }

    public static void setAutoPlayEnabled(boolean enabled) {
        autoPlayEnabled = enabled;
        logger.info("Auto-play " + (enabled ? "enabled" : "disabled"));
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean enabled) {
        debugMode = enabled;
    }
}
