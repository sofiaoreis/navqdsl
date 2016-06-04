package parser;

public class STC extends Object {


	  String type;  // type,in calculator expample there only is one type==> Int.
	  String value; // value, the value in here is a string not a integer value.

	  public STC(String itype, String ivalue) {
	    type=itype;
    	value = ivalue.replaceAll("\"", "");
	  }
	}
