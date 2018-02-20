package bg;

import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Minion extends Figure {
    public Minion(int attackType, int attack, int defense, int health, int spatialUsage) {
        super(attackType, attack, defense, health, spatialUsage);
    }
    
    public Figure chooseTarget(LinkedList<Figure> initiaveTrack) {
        int minDistance = Integer.MAX_VALUE;
        Figure target = null;
        
        for(Figure f : initiaveTrack) {
            if(f instanceof Hero) {
                Path path = getShortestDistance(f.currentSpace);
                
                if(!path.isEmpty()) {
                    if(path.length < minDistance) {
                        minDistance = path.length;
                        target = f;
                    }
                }
            }
        }
        
        return target;
    }
}