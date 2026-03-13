package bot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

/**
 * GameState - Manages current game state information
 *
 * This class extracts and stores relevant game information for the AI to make decisions.
 */
public class GameState {
    // Player state
    private static int playerHP;
    private static int maxPlayerHP;
    private static int energy;
    private static int block;

    // Hand state
    private static List<AbstractCard> handCards;
    private static int handSize;

    // Monster state
    private static List<MonsterInfo> monsters;

    // Dungeon state
    private static String dungeonName;
    private static int floorNumber;

    public GameState() {
        handCards = new ArrayList<>();
        monsters = new ArrayList<>();
    }

    /**
     * Update game state from current dungeon
     */
    public static void update() {
        if (AbstractDungeon.player == null) {
            return;
        }

        AbstractPlayer player = AbstractDungeon.player;

        // Update player state
        playerHP = player.currentHealth;
        maxPlayerHP = player.maxHealth;
        energy = player.energy.energy;
        block = player.currentBlock;

        // Update hand
        handCards.clear();
        if (player.hand != null) {
            handCards.addAll(player.hand.group);
        }
        handSize = handCards.size();

        // Update monsters
        monsters.clear();
        if (AbstractDungeon.getCurrRoom().monsters != null) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!monster.isDead && !monster.isDying) {
                    monsters.add(new MonsterInfo(
                        monster.name,
                        monster.currentHealth,
                        monster.maxHealth,
                        monster.currentBlock,
                        0, // intentDamage - not available in all versions
                        0, // intentBaseDamage - not available in all versions
                        (byte) 0 // intent - not available in all versions
                    ));
                }
            }
        }

        // Update dungeon info
        dungeonName = AbstractDungeon.id;
        floorNumber = AbstractDungeon.floorNum;

        if (MyBotMod.isDebugMode()) {
            logState();
        }
    }

    /**
     * Get current energy
     */
    public static int getEnergy() {
        return energy;
    }

    /**
     * Get current hand cards
     */
    public static List<AbstractCard> getHandCards() {
        return new ArrayList<>(handCards);
    }

    /**
     * Get playable cards (enough energy)
     */
    public static List<AbstractCard> getPlayableCards() {
        List<AbstractCard> playable = new ArrayList<>();
        for (AbstractCard card : handCards) {
            if (card.costForTurn <= energy) {
                playable.add(card);
            }
        }
        return playable;
    }

    /**
     * Get monsters info
     */
    public static List<MonsterInfo> getMonsters() {
        return new ArrayList<>(monsters);
    }

    /**
     * Get player HP
     */
    public static int getPlayerHP() {
        return playerHP;
    }

    /**
     * Get player max HP
     */
    public static int getMaxPlayerHP() {
        return maxPlayerHP;
    }

    /**
     * Get player block
     */
    public static int getPlayerBlock() {
        return block;
    }

    /**
     * Check if in battle
     */
    public static boolean isInBattle() {
        return AbstractDungeon.getCurrRoom() != null &&
               AbstractDungeon.getCurrRoom().monsters != null &&
               !monsters.isEmpty();
    }

    /**
     * Log current state for debugging
     */
    private static void logState() {
        System.out.println("=== Game State ===");
        System.out.println("Player HP: " + playerHP + "/" + maxPlayerHP);
        System.out.println("Energy: " + energy);
        System.out.println("Block: " + block);
        System.out.println("Hand: " + handSize + " cards");
        System.out.println("Monsters: " + monsters.size());
        System.out.println("================");
    }

    /**
     * MonsterInfo - Information about a monster
     */
    public static class MonsterInfo {
        public final String name;
        public final int currentHP;
        public final int maxHP;
        public final int block;
        public final int intentDamage;
        public final int intentBaseDamage;
        public final byte intent;

        public MonsterInfo(String name, int currentHP, int maxHP, int block,
                          int intentDamage, int intentBaseDamage, byte intent) {
            this.name = name;
            this.currentHP = currentHP;
            this.maxHP = maxHP;
            this.block = block;
            this.intentDamage = intentDamage;
            this.intentBaseDamage = intentBaseDamage;
            this.intent = intent;
        }

        @Override
        public String toString() {
            return String.format("%s (%d/%d HP, %d Block, Intent: %d Dmg)",
                name, currentHP, maxHP, block, intentDamage);
        }
    }
}
