package bg;

import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Space implements Comparable<Space> {
    public Tile tile;
    public LinkedList<Edge> edges;
    public LinkedList<Figure> figures;
    public int currentDistance;
    public int capacity;
    public boolean isWater;
    public Edge shortEdge;
    public boolean visited;
    
    public int number;
    
    public static int nSpaces = 1;
    
    public Space(Tile tile, int capacity, boolean isWater) {
        this.tile = tile;
        this.edges = new LinkedList<>();
        this.figures = new LinkedList<>();
        this.currentDistance = Integer.MAX_VALUE;
        this.capacity = capacity;
        this.isWater = isWater;
        this.visited = false;
        this.shortEdge = null;
        this.number = Space.nSpaces++;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
        figure.currentSpace = this;
    }
    
    public void addEdge(Edge edge) {
        edges.add(edge);
    }
    
    public int getFreeSlots() {
        int freeSlots = capacity;
        
        for(Figure f : figures) {
            capacity -= f.nSlots;
        }
        
        return freeSlots;
    }

    @Override
    public int compareTo(Space s) {
        return this.currentDistance - s.currentDistance;
    }
}
