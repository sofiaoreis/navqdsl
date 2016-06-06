package parser;

public class Entry {
	
	private String error;
	private String entry_type;
	private String entry_name;

	public Entry(String entry_type, String entry_name){
		error = "";
		this.entry_type = entry_type;
		this.entry_name = entry_name;
	}

}
