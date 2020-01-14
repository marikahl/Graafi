import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Float;
import java.util.Scanner;

/*
 * 
 * Toiminnallisuudet 1-5 toteutettu.
 * 
 * Main-luokka
 */
public class T2018
{
	
    private	String line;
    private float x[];
    private float y[];
    private void readInput()
    {

	  x=new float[400];
	  y=new float[400];
	 try {
            BufferedReader br = new BufferedReader( new FileReader("Tdata.txt"));
            
            for(int i=0; i<400; i++)
		 {
              line=br.readLine();
              String[] values=line.split(",");	
              x[i]=Float.parseFloat(values[0]);	
              y[i]=Float.parseFloat(values[1]);				  
              System.out.print(x[i]+" , "+y[i]+"\n");
			  
         }
         
	 
	} catch(IOException e)
        {
	    System.out.println("File not found.");
        }
    }
    
    public static void main(String[] args)
	{
	    T2018 ht=new T2018();		      
        ht.readInput();
        /*
         * TEHTÄVÄ 1
         * 
         * Graafin luominen, solmujen luominen, solmun yhdistäminen lähimpään solmuun.
         */
        Graph graph = createGraph(ht.x,ht.y);
        graph.connectClosestNodes();
        /*
         * TEHTÄVÄ 2
         * 
         * Solmun yhdistäminen toisiksi lähimpään solmuun.
         */
        graph.connectClosestNodes();
        /*
         * TEHTÄVÄ 3
         * 
         * Leveyshaus suorittaminen graafille.
         * ---> BFS.txt
         * 
         */
        graph.BFSloop();
        /*
         * TEHTÄVÄ 4
         * 
         * Syvyyshaun suorittaminen graafille.
         * ---> DFS.txt
         * 
         */
        graph.DFSloop();
        /*
         * TEHTÄVÄ 5
         * 
         * Solmujen tulo- ja lähtöasteiden tulostus tiedostoon.
         * ---> Degrees.txt
         * 
         */
        graph.Degrees();


	}
    /*
     * Luodaan graafi ja luodaan solmut.
     */

    public static Graph createGraph(float[] x, float[] y) {
        
        // Luodaan uusi graafi
        Graph graph = new Graph();
        // Luodaan solmut
        for (int i = 0; i < 400; i++) {
            graph.insertVertex(x[i],y[i]);
        }
        
        return graph;
    }
    
}
