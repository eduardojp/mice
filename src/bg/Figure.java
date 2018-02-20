package bg;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author eduardo
 */
public class Figure {
    public static int MELEE = 0;
    public static int RANGED = 1;
    
    public Space currentSpace;
    public int attackType;
    public int attack;
    public int defense;
    public int health;
    public int nSlots;
    
    public Figure(int attackType, int attack, int defense, int health, int spatialUsage) {
        this.attackType = attackType;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.nSlots = spatialUsage;
    }
    
    void move() {
        
    }
    
    public void battle(Figure figure) {
        LinkedList<DiceResult> diceResultList1 = Utils.rollDice(attack);
        
        int hits = 0;
        for(DiceResult res : diceResultList1) {
            switch(res.weapon) {
                case DiceResult.CHEESE:
                    addCheese();
                    break;
                case DiceResult.SWORD:
                case DiceResult.SWORD_SHIELD:
                    if(attackType == MELEE) hits++;
                    break;
                case DiceResult.BOW:
                    if(attackType == RANGED) hits++;
                    break;
            }
        }
        
        if(hits == 0) {
            return;
        }
        
        LinkedList<DiceResult> diceResultList2 = Utils.rollDice(figure.defense);
        
        for(DiceResult res : diceResultList2) {
            switch(res.weapon) {
                case DiceResult.CHEESE:
                    figure.addCheese();
                    break;
                case DiceResult.SWORD_SHIELD:
                    if(attackType == MELEE) hits--;
                    break;
            }
        }
        
        if(hits > 0) {
            figure.takeHits(hits);
        }
    }
    
    public void addCheese() {
        
    }
    
    public boolean canPassThrough(Space s) {
        for(Figure f : s.figures) {
            if(getClass() != f.getClass()) {
                return false;
            }
        }
        
        return true;
    }
    
    public void takeHits(int hits) {
        health -= hits;
        
        if(health <= 0) {
            System.out.println("DIED");
        }
    }
    
    public Path getShortestDistance(Space end) {
        Space begin = this.currentSpace;
        Tile tile = begin.tile;
        
        tile.resetSpaces();
        begin.currentDistance = 0;
        
        Path shortestPath = new Path();
        LinkedList<Space> fringe = new LinkedList<>();
        fringe.add(begin);
        
        if(begin == end) {
            return shortestPath;
        }
        
        while(!fringe.isEmpty()) {
            Space s1 = fringe.remove();
            s1.visited = true;
            
            System.out.println("---------------------------");
            System.out.println("EXPLORING " + s1.number);
            
            for(Edge e : s1.edges) {
                Space s2 = e.getDestination(s1);
                int value = e.getValue(s1);
                int nextDistance = s1.currentDistance + value;
                
                if(s2.tile != begin.tile) continue;
                if(s2.visited) continue;
                if(s2.getFreeSlots() < this.nSlots) continue;
                
                if(s2 == end) {
                    s2.currentDistance = nextDistance;
                    s2.shortEdge = e;
                    
                    System.out.println("FOUND!");
                    continue;
                }
                
                if(!canPassThrough(s2)) continue;
                if(s1.isWater || s2.isWater) continue;

                //O nó não está na franja
                if(s2.currentDistance == Integer.MAX_VALUE) {
                    s2.currentDistance = nextDistance;
                    s2.shortEdge = e;
                    fringe.add(s2);
                    
                    System.out.println("ADDED " + s2.number + ": " + s2.currentDistance);
                }
                //O nó Já está na franja, atualiza a distância mínima até ele
                else if(nextDistance < s2.currentDistance) {
                    s2.currentDistance = nextDistance;
                    s2.shortEdge = e;
                    
                    System.out.println("UPDATED " + s2.number + ": " + s2.currentDistance);
                }
                else {
                    System.out.println("DENIED " + s2.number + ": " + nextDistance);
                }
            }
            
            Collections.sort(fringe);
        }
        
        if(end.shortEdge != null) {
            Space destination = end;
            
            while(destination != begin) {
                Edge e = destination.shortEdge;
                shortestPath.addFirst(e, e.getValueFromDestination(destination));
                destination = e.getDestination(destination);
            }
        }
        
        return shortestPath;
    }
}