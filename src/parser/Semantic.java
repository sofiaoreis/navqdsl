package parser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Semantic {
	
	SimpleNode root;
	
	public Semantic(SimpleNode r){
		root=r;
		
	}

	public void checkSemantic() {
		parseTree();
		
	}
	
	public void parseTree() {
		//System.out.println(root.getNodeType());

		SimpleNode entriesNode = (SimpleNode)root.jjtGetChild(0);
		System.out.println(entriesNode.jjtGetChild(0));
		//for each entry in the entriesNode, create an Entry object and add it to the array list of entries
		for(int i = 0; i < entriesNode.jjtGetNumChildren(); i++) {
			SimpleNode entryNode = (SimpleNode)entriesNode.jjtGetChild(i);
			//System.out.println(entryNode.getNodeType());
			/*Entry entry = */parseEntry(entryNode);
			
			
		}

	}
	
	public void parseEntry(SimpleNode entryNode) {
		//String entry_type = entryNode.getInfo().toLowerCase();
		//System.out.println(entry_type);
		
		//String entry_name = ((SimpleNode)entryNode.jjtGetChild(1)).getInfo();
		//SimpleNode fields = (SimpleNode)entryNode.jjtGetChild(3);

		//Entry entry = new Entry(entry_type, entry_name);

		/*if(entry_type.equals("string")) {
			SimpleNode stringNode = (SimpleNode)entryNode.jjtGetChild(1);
			String key = stringNode.getInfo();
			//String -> StringEquals -> StringContent
			String value = ((SimpleNode)((SimpleNode)stringNode.jjtGetChild(0)).jjtGetChild(0)).getInfo();
			if(!definedStrings.containsKey(key)) {
				definedStrings.put(key, value);
				semanticString.append("String:\n\tName:"+key+"\n\tValue:"+value+"\n");
			}
			else {
				definedStrings.put(key, value);
				semanticString.append("Redefinition of String "+key+" with Value: "+value+"\n");
			}			
			return null;
		}
		else {
			String entry_name = ((SimpleNode)entryNode.jjtGetChild(1)).getInfo();
			SimpleNode fields = (SimpleNode)entryNode.jjtGetChild(3);

			Entry entry = new Entry(entry_type, entry_name);
			//System.out.println("Entry: " + entry.entry_type);
			//semanticString.append("Entry: " + entry.entry_type + "\n");
			//System.out.println("\tEntry name: " + entry.entry_name);
			//semanticString.append("\tEntry name: " + entry.entry_name + "\n");

			//parseFields(entry, fields);

			System.out.print("\n");

			return entry;
		}*/
		//return entry;
	}


}
