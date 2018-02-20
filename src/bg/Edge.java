package bg;

/**
 *
 * @author eduardo
 */
public class Edge {
    private Space s1, s2;
    private int value1, value2;
    
    public static void connect(Space s1, Space s2, int value) {
        Edge.connect(s1, value, s2, value);
    }
    
    public static void connect(Space s1, int value1, Space s2, int value2) {
        Edge edge = new Edge(s1, value1, s2, value2);
        s1.addEdge(edge);
        s2.addEdge(edge);
    }
    
    private Edge(Space s1, int value1, Space s2, int value2) {
        this.s1 = s1;
        this.value1 = value1;
        this.s2 = s2;
        this.value2 = value2;
    }
    
    public Space getDestination(Space origin) {
        if(s1 == origin) return s2;
        else return s1;
    }
    
    public int getValue(Space origin) {
        if(s1 == origin) return value1;
        else return value2;
    }

    int getValueFromDestination(Space destination) {
        if(s2 == destination) return value1;
        else return value2;
    }
}
