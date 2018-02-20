package bg;

import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Tile {
    String name;
    LinkedList<Space> spaceList;
    
    public Tile(String name) {
        this.name = name;
        this.spaceList = new LinkedList<>();
    }

    void resetSpaces() {
        for(Space s : spaceList) {
            s.currentDistance = Integer.MAX_VALUE;
            s.shortEdge = null;
            s.visited = false;
        }
    }
}