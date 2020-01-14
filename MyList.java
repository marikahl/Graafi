/*
 * Marika Lähteenmäki marika.lahteenmaki@tuni.fi
 * Tietorakenteet 2018 harjoitustyö
 * 
 * Lista-luokka
 */
public class MyList {
    
    private int size;
    
    private ListNode head;
    private ListNode tail;
    
    
    public MyList() {
        head = tail = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }

    public void size(int lkm) {
        size += lkm;
    }
    
    public ListNode head() {
        return head;
    }
    
    public void head(ListNode n) {
        if (head == null) {
            head = tail = n;
        }
        else {
            head.prev(n);
            head = n;
            head.prev(null);
        }
    }
    
    public ListNode tail() {
        return tail;
    }

    public void tail(ListNode n) {
        if (size == 0) {
            tail = head = n;
        }
        else {
            tail.next(n);
            tail = n;
            tail.next(null);
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void addToList(Object n) {
        ListNode node = new ListNode(n);
        if (size == 0) {
            head = tail = node;
            size++;
        }
        else {
            tail.next(node);
            tail = node;
            tail.next(null);
            size++;
        }
    }
    
    public String toString() {
        String list = "";
        for (ListNode i = head; i != tail.next(); i = i.next()) {
            list = list + i.data().toString() + "-";
        }
        
        return list;
    }
}