package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Main {
	
	
	public static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class); ;
	public static HashMap<String,String> vars;
	
	public static void main(String args []){

	    NavqParser parser = new NavqParser(System.in);
	    SimpleNode root = null;
		try {
			root = parser.Start();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		parser.PrintWarnings();
		
		Hashtable st = parser.getHashTable();
		Enumeration t = st.keys();
		
		getValuesOfQuery(t,st);
		String criteria = "none";
		
		if(root.jjtGetChild(0).jjtGetChild(0).toString().equals("placesAtDistance"))
		{
			String distance = vars.get("number");
			String place = getStringBetweenDoubleQuotes(vars.get("place1"));
			String compare = vars.get("compare");
			
			readFile(criteria);
			
			if(!graph.containsVertex(place))
			{
				System.out.println("ERROR: Node with id="+place+" does not exist.");
				System.exit(0);
			}
			
			getPlacesAtDistance(graph, place, Integer.valueOf(distance), compare);
		}
		else if(root.jjtGetChild(0).jjtGetChild(0).toString().equals("numberOfPaths"))
		{
			String place1 = getStringBetweenDoubleQuotes(vars.get("place2"));
			String place2 = getStringBetweenDoubleQuotes(vars.get("place1"));
			String number = vars.get("number");
			criteria = vars.get("criteria_type");
			
			readFile(criteria);
			
			if(!graph.containsVertex(place1) && !graph.containsVertex(place2))
			{
				System.out.println("ERROR: Both nodes do not exist.");
				System.exit(0);
			}
			else if(!graph.containsVertex(place1))
			{
				System.out.println("ERROR: Node with id="+place1+" does not exist.");
				System.exit(0);
			}else if(!graph.containsVertex(place2))
			{
				System.out.println("ERROR: Node with id="+place2+" does not exist.");
				System.exit(0);
			}
			
			
			getPaths(graph, place1, place2, Integer.valueOf(number));	
		}
		
	}
	
	
	public static void getPlacesAtDistance(SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph, String startVertice, int distance, String compare)
	{
		
		FloydWarshallShortestPaths kgraph = new FloydWarshallShortestPaths(graph);
		List<GraphPath> shortest_paths = kgraph.getShortestPaths(startVertice);
		ArrayList<String> places = new ArrayList<String>();
		
		for(int i = 0; i < shortest_paths.size();i++){
			
			if(compare.equals("<=") && shortest_paths.get(i).getWeight() <= distance){
				places.add(shortest_paths.get(i).getEndVertex().toString());
			}
			if(compare.equals(">=") && shortest_paths.get(i).getWeight() >= distance){
				places.add(shortest_paths.get(i).getEndVertex().toString());
			}
			if(compare.equals("=") && shortest_paths.get(i).getWeight() == distance){
				places.add(shortest_paths.get(i).getEndVertex().toString());
			}
			if(compare.equals("<") && shortest_paths.get(i).getWeight() < distance){
				places.add(shortest_paths.get(i).getEndVertex().toString());
			}
			if(compare.equals(">") && shortest_paths.get(i).getWeight() > distance){
				places.add(shortest_paths.get(i).getEndVertex().toString());
			}
		}

		if(places.size() >= 0)
		{
			System.out.print("Final Result: The places are ");
			for(int j=0; j<places.size(); j++){
			
				System.out.print(places.get(j));
				if(j < places.size()-1)
					System.out.print(", ");
			}
		}
		else
			System.out.println("There were no places that meet the given conditions.\n");
	}
	
	
	public static String getStringBetweenDoubleQuotes(String str)
	{
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(str);
		m.find();
		return m.group(1);
	}
	
	public static void getValuesOfQuery(Enumeration t, Hashtable st)
	{
		String temp;
	    STC    temp2;
		vars = new HashMap<String,String>();
		boolean firstPlace = true;
		while(t.hasMoreElements())
		{

	          temp = (String)t.nextElement();
	          temp2 = (STC)st.get(temp);

	          if(temp2.type.toString().equals("place") && firstPlace == true)
	          {
	        	  vars.put("place1", temp2.value);  
	        	  firstPlace = false;
	          }
	          else if(temp2.type.toString().equals("place") && firstPlace == false)
	          {
	        	  vars.put("place2", temp2.value);
	          }
	          else
	          {
	        	  vars.put(temp2.type, temp2.value);
	          }	 
	          
		}
		
	}
	
	public static void getPaths(SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  graph, String startVertice, String endVertice, int numberPaths)
	  {
	     KShortestPaths kgraph = new KShortestPaths(graph, startVertice, numberPaths);
	     List shortest_paths = kgraph.getPaths(endVertice);
	     if(shortest_paths.size() == 0){
	    	 System.out.println("Final Result: It was not possible to calculate any path.");
	     }
	     else
	     {
	    	 System.out.println("Final Result: ");
	    	 for(int i = 0; i < shortest_paths.size(); i++)
		     {
	    		 	
		     		System.out.println("Path "+(i+1)+": "+shortest_paths.get(i));
		     }
	     }

	       
	  }

	  public static void readFile(String criteria)
	  {
	    try
	    {
		    BufferedReader reader = new BufferedReader(new FileReader("./data/small.NY.gr"));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		   	   if(line.substring(0, 1).matches("^a.*"))
		   	   {
		   	     addInfoToGraph(line,criteria);
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

	  public static void addInfoToGraph(String str, String criteria)
	  {
		String[] splited = str.split(" ");

		addVertex(splited[1]);
		addVertex(splited[2]);
		
		  DefaultWeightedEdge edge = graph.addEdge(splited[1], splited[2]);
	    	if(criteria.equals("shortest"))
	    		graph.setEdgeWeight(edge, 1);
	    	else if (criteria.equals("fastest"))
	    		graph.setEdgeWeight(edge, Integer.parseInt(splited[3]));
	    	else
	    		graph.setEdgeWeight(edge, 1);
		
		
	  }

	  public static void addVertex(String vertex)
	 {
	   if(!graph.containsVertex(vertex))
	   {
	     graph.addVertex(vertex);
	   }
	 
	 }

}
