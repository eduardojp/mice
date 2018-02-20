package bg;

import java.util.LinkedList;

/**
 *
 * @author eduardo
 */
public class Path {
    public LinkedList<Edge> edges;
    public int length;
    
    public Path() {
        this.edges = new LinkedList<>();
        this.length = 0;
    }
    
    public void addFirst(Edge e, int value) {
        edges.addFirst(e);
        length += value;
    }

    boolean isEmpty() {
        return edges.isEmpty();
    }
}