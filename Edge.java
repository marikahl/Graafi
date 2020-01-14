/*
 * Marika Lähteenmäki marika.lahteenmaki@tuni.fi
 * Tietorakenteet 2018 harjoitustyö
 *
 * Graafin kaari -luokka
 */
public class Edge {
    
    
    private double lenght;
    
    private Vertex from;
    private Vertex to;
    
    private boolean visited;
    
    
    public Edge(Vertex fromVertex, Vertex toVertex, double edgeLenght) {
        if (!(fromVertex.equals(toVertex))) {
            from = fromVertex;
            to = toVertex;
            lenght = edgeLenght;   
        }   
    }
    
    public double lenght() {
        return lenght;
    }
    
    public Vertex from() {
        return from;
    }
    
    public Vertex to() {
        return to;
    }
    
    public boolean visited() {
        return visited;
    }
    
    public void visited(Boolean b) {
        visited = b;
    }
    
    public String toString() {
        return from.toString() + " --> " + to.toString();
    }

    /*public void deleteEdge() {
        for (ListNode i = to.outEdges().head(); i != null; i = i.next()) {
            Edge e = (Edge)i.data();
            if (this.equals(e)) {
                to.outEdges().deleteListNode(i);
            }
        }
        for (ListNode j = )
    }*/
}