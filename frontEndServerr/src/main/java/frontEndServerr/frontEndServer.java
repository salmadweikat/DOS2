package frontEndServerr;

import static spark.Spark.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;


public class frontEndServer {

	public static void main(String[] args) {
		port(8000);
	 	Gson gson = new Gson();
	 	
	 	get("search/:topic",(request,response)
				->{ String topic =request.params(":topic");
			 	   String url = "http://192.168.1.59:2000/Search/"+ topic;
				 URL obj = new URL (url);
			 	 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 	  BufferedReader in = new BufferedReader(
				             new InputStreamReader(con.getInputStream()));
				    String inputLine;
				    inputLine = in.readLine();
				    StringBuffer data = new StringBuffer();

				    while (inputLine != null) {
				        data.append(inputLine);
				    }
			   return data;});
	 	
		get("lookup/:id",(request,response)
	->{ String id =request.params(":id");
	     String url = "http://192.168.1.59:2000/Lookup/"+ id;
	     URL obj = new URL (url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		return con.getInputStream();});
		
		
		
		get("buy/:id",(request,response)
				->{ String id =request.params(":id");
				     String url = "http://192.168.1.130:7000/Buy/"+ id;
				     URL obj = new URL (url);
				     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					return con.getInputStream();});
	}
}