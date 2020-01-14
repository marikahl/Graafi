import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Marika Lähteenmäki marika.lahteenmaki@tuni.fi
 * Tietorakenteet 2018 harjoitustyö
 *
 * Graafi-luokka
 */
public class Graph {
    
    private MyList vertexList;
    private MyList edgeList;

    
   public Graph() {
       vertexList = new MyList();
       edgeList = new MyList();
       
   }
    
    public MyList vertexList() {
        return vertexList;
    }
    
    public MyList edgeList() {
        return edgeList;
    }
    
    public void insertVertex(float coordX, float coordY) {
        Vertex newV = new Vertex(coordX, coordY);
        vertexList.addToList(newV);
    }
    
    public void insertEdge(Vertex from, Vertex to, double lenght) {
        Edge e = new Edge(from, to, lenght);
        
        edgeList.addToList(e);
        from.outEdges().addToList(e);
        to.inEdges().addToList(e);
    }

    /*
     * TEHTÄVÄ 1. JA 2.
     * Solmujen yhdistäminen läheisimpään solmuun.
     */
    
    public void connectClosestNodes() {
        // Luodaan linkit läheisimpien ja toiseksi läheisimpien solmujen välille
            
        // pienin väli
        double smallestD;
        
        Vertex from = null;
        // lähin silmukka
        Vertex to = null;
        
        for (ListNode i = vertexList().head(); i != null; i = i.next()) {

            from = (Vertex)i.data();
            
            smallestD = Double.MAX_VALUE;
            
            for (ListNode j = vertexList().head(); j != null; j = j.next()) {

                // from ja to silmukoiden tulee olla eri
                if (!(((Vertex)i.data()).x() == ((Vertex)j.data()).x() && 
                ((Vertex)i.data()).y() == ((Vertex)j.data()).y())) {

                    // lasketaan silmukoiden etäisyys
                    double d = Math.sqrt( Math.pow((from.y() - ((Vertex)j.data()).y()),2) + 
                        Math.pow(from.x() - ((Vertex)j.data()).x(),2) );
                    
                    if (d < smallestD) {
                        
                        // Ei ole jo listassa
                        if (!isOutAdjacentVertice((Vertex)i.data(),(Vertex)j.data())) {
                        
                            // Pienin etäisyys
                            smallestD = d;
                            to = (Vertex)j.data();
                            
                        }
                        
                    }
                }
            }
            insertEdge(from, to, smallestD); 
        }
    }
        
    // Tarkistetaan, meneekö from-silmukasta kaari to-silmukkaan.
    public boolean isOutAdjacentVertice(Vertex from, Vertex to) {
        
        // Tarkistetaan, lähteekö from-silmukasta kaaria.
        if (from.outEdges().head() != null) {
            
            // Käydään from-silmukasta lähtevät kaaret läpi. 
            for (ListNode i = from.outEdges().head(); i != null; i = i.next() ) {
                
                // Jos kaaren toisessa päässä oleva silmukka on sama kuin saatu to-silmukka,
                // palautetaan true.
                if ((((Edge)i.data()).to()).equals(to)) {
                    return true;
                }
            }
            
        }
        return false;
    }
    

    // Solmujen visited -attribuutin resetoiminen (---> false).
    public void resetNodes() {
        for (ListNode i = vertexList.head(); i != null; i = i.next() ) {
            Vertex v = (Vertex)(i.data());
            v.visited(false);
        }
        for (ListNode j = edgeList.head(); j != null; j = j.next()) {
            Edge e = (Edge)(j.data());
            e.visited(false);
        }
    }

    /*
     * TEHTÄVÄ 3.
     * Graafin läpikäyminen leveyshakuna.
     */
    
    // Käydään läpi graafin kaikki aligraafit leveyshakuna
    public void BFSloop() {
        resetNodes();
        MyList BFSnodes = new MyList();
        // Käydään kaikki graafin solmut läpi.
        for (ListNode i = vertexList.head(); i != null; i = i.next()) {
            Vertex v = (Vertex)i.data();
            if (!v.visited()) {

                MyList BFSreturnList = BFS(i);

                if (BFSnodes.isEmpty()) {
                    BFSnodes = BFSreturnList;
                } 
                else {
                    
                    for (ListNode n = BFSreturnList.head(); n != null; n = n.next()) {
                        Vertex nextV = (Vertex)n.data();
                        BFSnodes.addToList(nextV);
                    }
                }
            }
        }

        // Kirjoitetaan lista tiedostoon.
        try {
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("BFS.txt"));
               for(ListNode i = BFSnodes.head(); i != null; i = i.next()) {
                   bw.write(((Vertex)(i.data())).toString());
                   bw.newLine();
               }
           bw.close();
           } 
           catch (IOException e) {
               System.err.format("IOException: %s%n", e);
           }
           System.out.println("Tiedosto kirjoitettu");
    }
    
    // Käydään aligraafi läpi leveys hakuna
    public MyList BFS(ListNode n) {
        
        MyList BFSnodes = new MyList();
        BFSnodes.addToList((Vertex)(n.data()));
        // Lista tason solmuista.
        MyList nodes = new MyList();
        nodes.addToList((Vertex)(n.data()));

        // 
        while (!(nodes.isEmpty())) {
            MyList nodes2 = new MyList();
            // Käydään BFSnodes-listan solmuja läpi aloittaen viimeisimmän tason ekasta solmusta.
            for (ListNode i = nodes.head(); i != null; i = i.next()) {
                Vertex v = (Vertex)i.data();
                v.visited(true);
                
                // Jos solmusta lähtee kaaria.
                if (v.outEdges().head() != null) {
                    // Käydään solmun lähtevät kaaret läpi.
                    for (ListNode j = v.outEdges().head(); j != null; j = j.next()) {
                        Edge e = (Edge)j.data();
                        
                        
                        // Jos kaaressa ei ole käyty
                        if (e.visited() == false) {
                            
                            // Jos kaaren toisen pään solmussa ei ole käyty.
                            if (e.to().visited() == false) {
                                
                                e.visited(true);
                                e.to().visited(true);
                                // Lisätään solmu BFSnodes-listaan.
                                BFSnodes.addToList(e.to());
                                
                                // Lisätään solmu myös tason listaan.
                               
                                nodes2.addToList(e.to());
                            }
                            else {

                                e.visited(true);
                            }
                        }   
                    }
                }
            }
            // Asetetaan nodes listaksi nodes2.
            nodes = nodes2;
            n = nodes.head();
        }
        return BFSnodes;
    }

    /*
     * TEHTÄVÄ 4.
     * Graafin läpikäyminen syvyyshakuna.
     */

    // Käydään läpi graafin kaikki aligraafit syvyyshakuna.
    
     public void DFSloop() {
        resetNodes();
        MyList DFSnodes = new MyList();
        // Käydään kaikki graafin solmut läpi.
        for (ListNode i = vertexList.head(); i != null; i = i.next()) {
            Vertex v = (Vertex)i.data();
            if (!v.visited()) {
                DFSnodes.addToList(v);
                v.visited(true);
                MyList DFSreturnList = DFS(v);
                
                for (ListNode n = DFSreturnList.head(); n != null; n = n.next()) {
                    Vertex nextV = (Vertex)n.data();
                    DFSnodes.addToList(nextV);
                }
                
            }
        }

        // Kirjoitetaan lista tiedostoon.
        try {
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("DFS.txt"));
               for(ListNode i = DFSnodes.head(); i != null; i = i.next()) {
                   bw.write(((Vertex)(i.data())).toString());
                   bw.newLine();
               }
           bw.close();
           } 
           catch (IOException e) {
               System.err.format("IOException: %s%n", e);
           }
           System.out.println("Tiedosto kirjoitettu");
    }

    // Aligraafien syvyyshaku rekursiona
    public MyList DFS(Vertex v) {
        MyList DFSnodes = new MyList();
        // Käydään solmusta lähtevät kaaret läpi.
        for (ListNode i = v.outEdges().head(); i != null; i = i.next()) {
            Edge e = (Edge)i.data();
            // Jos kaaressa ei ole käyty:
            if (!e.visited()) {
                Vertex to = e.to();
                e.visited(true);
                // Jos kaaren päätesolmussa ei ole käyty:
                if (!to.visited()) {
                    to.visited(true);
                    DFSnodes.addToList(to);
                    // Käydään rekursiivisesti läpi kaaren päätesolmu ja siitä lähtevät kaaret.
                    MyList returnList = DFS(to);
                    // DFS(to) palauttama lista lisätään DFSnodes listaan.
                    for (ListNode l = returnList.head(); l != null; l = l.next()) {
                        DFSnodes.addToList((Vertex)l.data());
                    }
                }
            }
        }
        return DFSnodes;
    }

    /*
     * TEHTÄVÄ 5.
     * Tulostetaan solmujen tulo- ja lähtöasteet Degrees.txt -tiedostoon.
     */
    public void Degrees() {

        // Kirjoitetaan asteet tiedostoon.
        try {
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("Degrees.txt"));
                for(ListNode i = vertexList.head(); i != null; i = i.next()) {
                    Vertex v = (Vertex)i.data();
                    int inD = v.inEdges().size();
                    int outD = v.outEdges().size();
                    
                    bw.write("Vertex: " + v + ": ");
                    bw.write("inDegree: " + inD + ", ");
                    bw.write("outDegree: " + outD + ".");
                    bw.newLine();
                }
            bw.close();
            } 
            catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
            System.out.println("Tiedosto kirjoitettu");
    }
}



















