package StructuralDesign;

//import java.lang;
//if you have created an application with some legacy features but those will be compatible with new features or requirement
/*for eg: you have legace xml parser but in another system it only accepts JSON input  so instead of rewriting the XML parser again, we 
* create an adapter ( which is like a bridge in between) that wraps the XML parser and provides a jSON like interface */
//Converts the interface of one class into another interface as per the client request or expectations, It's like a bridge between incompatible classes 
//Arrays.asList() --  adapts an array to a List , JDBC drivers -- different databases are adapted to a common JDBC interface
interface XMLReader
{
	
void readXML(); // Business logic

}

interface TXTReader
{
	
void readTXT(); // Business logic

}

class JSONReader
{
public void readJSON()
{
	System.out.println("Reading JSON Data :");
}
}


//class TXTReader
//{
//public void readTxt()
//{
//	System.out.println("Reading Txt Data :");
//}
//}


// We will create an adapter to match XMLReader interface
class JSONtoAnyDataAdapter implements XMLReader,TXTReader
{

	private JSONReader jsonReader ;
	//private  TXTReader txtReader ;
	
	public JSONtoAnyDataAdapter(JSONReader reader)
	{
		
		this.jsonReader = reader;
		
	}
	
//	public JSONtoAnyDataAdapter(TXTReader reader)
//	{
//		
//		this.txtReader = reader;
//		
//	}
	
	// Internal conversion for reading the data from XML to JSON
	@Override
	public void readXML() {
		// TODO Auto-generated method stub
		System.out.println("coverting XMl to JSON ..");
		jsonReader.readJSON();
	}
	
	@Override
	public void readTXT() {
		// TODO Auto-generated method stub
		System.out.println("coverting Txt to Json ..");
		jsonReader.readJSON();
	}

	
	
}



public class AdapterPatternExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Through Adapter we are reading the JSON data to xml or to txt
		XMLReader xmlReader = new JSONtoAnyDataAdapter(new JSONReader());
		xmlReader.readXML();
		TXTReader txtReader = new JSONtoAnyDataAdapter(new JSONReader());
		txtReader.readTXT();

	}

}
