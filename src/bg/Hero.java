package bg;

import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Hero extends Figure {
    private int cheese;
    private int movement;
    private int weightCarried;
    public LinkedList<Item> pack;
    public HashSet<Tile> successfulSearches;
    public boolean attackedThisTurn;
    
    public Hero(int attackType, int attack, int defense, int health, int spatialUsage, int movement) {
        super(attackType, attack, defense, health, spatialUsage);
        
        this.cheese = 0;
        this.movement = movement;
        this.weightCarried = 0;
        this.pack = new LinkedList<>();
        this.successfulSearches = new HashSet<>();
        this.attackedThisTurn = false;
    }
    
    public void addItem(Item item) {
        if(weightCarried + item.weight <= 3) {
            pack.add(item);
            weightCarried += item.weight;
        }
    }
    
    public void useItem() {
        
    }
    
    public void search(Tile tile) {
        DiceResult res = Utils.rollDie();
        
        if(res.specialAction == DiceResult.SUCCESS) {
            successfulSearches.add(tile);
            System.out.println("Success!");
        }
        else {
            System.out.println("Failed!");
        }
    }
    
    public void move() {
        LinkedList<Space> spaceOptions = getSpaceOptions();
        
        //TODO choose
        
        Space dest = spaceOptions.get(spaceOptions.size()-1);
        
        currentSpace.figures.remove(this);
        currentSpace = dest;
        dest.figures.add(this);
    }
    
    public LinkedList<Space> getSpaceOptions() {
        //TODO travado por um minion
        
        currentSpace.tile.resetSpaces();
        currentSpace.currentDistance = 0;
        
        int maxMoves = Utils.rollDie().value + this.movement;
        
        LinkedList<Space> spaceOptions = new LinkedList<>();
        simulateMovement(currentSpace, 0, maxMoves, spaceOptions);
        
        return spaceOptions;
    }
    
    public void simulateMovement(Space s1, int moves, int maxMoves, LinkedList<Space> spaceOptions) {
        if(!s1.visited) {
            s1.visited = true;
            spaceOptions.add(s1);
        }
        
        for(Edge e : s1.edges) {
            Space s2 = e.getDestination(s1);
            int value = e.getValue(s1);
            
            if(s2.tile != s1.tile) continue;
            if(moves + value > maxMoves) continue;
            if(s2.getFreeSlots() < this.nSlots) continue;
            
            //Caso este seja um caminho mais curto até s2
            if(moves < s2.currentDistance) {
                s2.currentDistance = moves;
            }
            else {
                continue;
            }
            
            if(s1.isWater || s2.isWater) {
                if(!s2.visited) {
                    s2.visited = true;
                    spaceOptions.add(s2);
                }
                continue;
            }
            
            simulateMovement(s2, moves + value, maxMoves, spaceOptions);
        }
    }
    
    public void simulateMovement2(Space s1, int maxMoves, LinkedList<Space> spaceOptions) {
        LinkedList<Space> fringe = new LinkedList<>();
        LinkedList<Space> nextFringe = new LinkedList<>();
        fringe.add(s1);
        
        for(int moves = 0; moves < maxMoves; moves++) {
            while(!fringe.isEmpty()) {
                s1 = fringe.removeFirst();
                
                s1.visited = true;
                spaceOptions.add(s1);

                for(Edge e : s1.edges) {
                    Space s2 = e.getDestination(s1);
                    int value = e.getValue(s1);

                    if(s2.tile != s1.tile) continue;
                    if(s2.visited) continue;
                    if(s2.currentDistance + value > maxMoves) continue;
                    if(s2.getFreeSlots() < this.nSlots) continue;

                    if(s1.isWater || s2.isWater) {
                        s2.visited = true;
                        spaceOptions.add(s2);
                        continue;
                    }
                }
            }
            
            fringe.addAll(nextFringe);
            nextFringe.clear();
        }
    }
}