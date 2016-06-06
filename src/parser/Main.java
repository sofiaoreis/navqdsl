package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Main {
	
	
	public static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class); ;
	
	
	public static void main(String args []){
		
		String temp;
	    STC    temp2;

	    NavqParser parser = new NavqParser(System.in);
	    SimpleNode root = null;
		try {
			root = parser.Start();
			root.dump("");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		parser.PrintWarnings();
		
		//Semantic analyser = new Semantic(root);
		//analyser.checkSemantic();
	    

	    //readFile();

		// Enumeration t = ST.keys();

		  //System.out.println(t);
		 
		
	  /* 	while (t.hasMoreElements() == true) {

	          temp = (String)t.nextElement();
	          temp2 = (STC)ST.get(temp);

	          

	          
			

	          
	         // System.out.println(temp);
	     

	    }*/
		
	}
	
	public static void getFastestPaths(SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  graph, String startVertice, String endVertice, int numberPaths)
	  {
	     KShortestPaths kgraph = new KShortestPaths(graph, startVertice, numberPaths);
	     List shortest_paths = kgraph.getPaths(endVertice);

	     if(shortest_paths.size() < 0)
	     {
	       System.out.println("No paths founded!");
	     }
	     else
	     {
	        for(int i = 0; i < shortest_paths.size(); i++)
	     	{
	     		System.out.println(shortest_paths.get(i));
	     	}
	     }

	    
	  }

	  public static void readFile()
	  {
	    try
	    {
		    BufferedReader reader = new BufferedReader(new FileReader("./data/small.NY.gr"));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		   	   if(line.substring(0, 1).matches("^a.*"))
		   	   {
		   	     addInfoToGraph(line);
		   	   }
		   	   
		    }
		    reader.close();
		  }
		  catch (Exception e)
		  {
		    System.err.format("Exception occurred trying to read '%s'.", "./data/small.NY.gr");
		    e.printStackTrace();
		  }
	  }

	  public static void addInfoToGraph(String str)
	  {
		String[] splited = str.split(" ");

		addVertex(splited[1]);
		addVertex(splited[2]);
		
		  DefaultWeightedEdge edge = graph.addEdge(splited[1], splited[2]);
			//System.out.println("NODE1:" + splited[1] + "NODE2:" + splited[2] + "NODE3:" + splited[3]);
	    	graph.setEdgeWeight(edge, Integer.parseInt(splited[3]));
		
		
	  }

	  public static void addVertex(String vertex)
	 {
	   if(!graph.containsVertex(vertex))
	   {
	     graph.addVertex(vertex);
	   }
	 
	 }

}
