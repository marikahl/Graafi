/*
 * Marika Lähteenmäki marika.lahteenmaki@tuni.fi
 * Tietorakenteet 2018 harjoitustyö
 * 
 * Graafin solmun luokka.
 */
public class Vertex {
    
    // Pisteen x-koordinaatti.
    private float x;
    // Pisteen y-koordinaatti.
    private float y;
    
    // Tulevat kaaret
    private MyList inEdges;
    
    // Lähetevät kaaret
    private MyList outEdges;
    
    private boolean visited;
    
    
    // Rakentaja
    public Vertex(float coordX, float coordY) {
        x = coordX;
        y = coordY;
        
        inEdges = new MyList();
        outEdges = new MyList();
    }
    
    public float y() {
        return y;
    }
    
    public void y(float number) {
        y = number;
    }
    
    public float x() {
        return x;
    }
    
    public void x(float number) {
        x = number;
    }
    
    public MyList inEdges() {
        return inEdges;
    }
    
    public MyList outEdges() {
        return outEdges;
    }
    
    public boolean visited() {
        return visited;
    }
    
    public void visited(Boolean b) {
        visited = b;
    }
    
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}