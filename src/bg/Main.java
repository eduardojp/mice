package bg;

import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Figure> initiaveTrack = new LinkedList<>();
        
        Tile tile = new Tile("test");
        Space s1 = new Space(tile, 4, false);
        Space s2 = new Space(tile, 4, false);
        Space s3 = new Space(tile, 4, false);
        Space s4 = new Space(tile, 4, false);
        Space s5 = new Space(tile, 4, false);
        Space s6 = new Space(tile, 4, false);
        
        Edge.connect(s1, s2, 7);
        Edge.connect(s1, s3, 9);
        Edge.connect(s1, s6, 14);
        Edge.connect(s2, s3, 10);
        Edge.connect(s2, s4, 15);
        Edge.connect(s3, s4, 11);
        Edge.connect(s3, s6, 2);
        Edge.connect(s4, s5, 6);
        Edge.connect(s5, s6, 9);
        
        Hero h = new Hero(Figure.MELEE, 3, 2, 3, 1, 3);
        h.currentSpace = s1;
        h.getShortestDistance(s4);
        
        Tile tile1 = new Tile("Guard Room");
    }
}
