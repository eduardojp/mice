package bg;

/**
 *
 * @author eduardo
 */
public class DiceResult {
    public static final int FAIL = 0;
    public static final int SUCCESS = 1;
    
    public static final int SWORD = 0;
    public static final int SWORD_SHIELD = 1;
    public static final int BOW = 2;
    public static final int CHEESE = 3;
    
    public int weapon;
    public int value;
    public int specialAction;
    
    public DiceResult(int weapon, int value, int specialAction) {
        this.weapon = weapon;
        this.value = value;
        this.specialAction = specialAction;
    }
}