package bg;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Utils {
    private static final LinkedList<Integer> faces = new LinkedList<>();
    static {
        faces.add(0); faces.add(1); faces.add(2);
        faces.add(3); faces.add(4); faces.add(5);
    }
    
    private static final DiceResult[] diceFaces = new DiceResult[] {
        new DiceResult(DiceResult.SWORD, 2, DiceResult.FAIL),
        new DiceResult(DiceResult.SWORD, 3, DiceResult.SUCCESS),
        new DiceResult(DiceResult.SWORD_SHIELD, 2, DiceResult.SUCCESS),
        new DiceResult(DiceResult.BOW, 1, DiceResult.FAIL),
        new DiceResult(DiceResult.BOW, 2, DiceResult.SUCCESS),
        new DiceResult(DiceResult.CHEESE, 1, DiceResult.FAIL)
    };
    
    public static boolean checkMovement(Space begin, Space end, int nMoves) {
        return true;
    }
    
    public static DiceResult rollDie() {
        //int index = (int) (Math.random() * diceFaces.length);
        //return diceFaces[index];
        
        Collections.shuffle(faces);
        return diceFaces[faces.getFirst()];
    }
    
    public static LinkedList<DiceResult> rollDice(int nDices) {
        LinkedList<DiceResult> diceResultList = new LinkedList<>();
        
        for(int i = 0; i < nDices; i++) {
            diceResultList.add(rollDie());
        }
        
        return diceResultList;
    }
    
    public static void ambush(Figure figure, LinkedList<Figure> initiaveTrack) throws RuntimeException {
        int index = initiaveTrack.indexOf(figure);
        
        if(index == -1) {
            throw new RuntimeException("Figure not on initiave track!");
        }
        
        initiaveTrack.remove(index);
        
        DiceResult res = rollDie();
        index -= res.value;
        
        if(index < 0) {
            index = 0;
        }
        
        initiaveTrack.add(index, figure);
    }
}