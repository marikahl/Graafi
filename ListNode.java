/*
 * Marika Lähteenmäki marika.lahteenmaki@tuni.fi
 * Tietorakenteet 2018 harjoitustyö
 *
 * Listan solmu -luokka
 */
public class ListNode {
    
    private Object data;
    private ListNode next;
    private ListNode prev;
    
    public ListNode(Object d) {
        
        if (d instanceof Vertex) {
            data = d;
        }
        else if (d instanceof Edge) {
            data = d;
        }
        else {
            System.out.print("instanceOf palauttaa false: " );
        }
    }
    
    public Object data() {
        return data;
    }
    
    public void next(ListNode n) {
        next = n;
        
    }
    
    public ListNode next() {
        return next;
    }
    
    public void prev(ListNode p) {
        prev = p;
        
    }
    
    public ListNode prev() {
        return prev;
    }
    
}