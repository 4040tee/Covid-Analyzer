package dataretrieval;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class RetrieveCountryCode {
	
	/**
	 * URL to be called to retrieve JSON stream
	 */
	private String url = "https://restcountries.eu/rest/v2/name/%s?fullText=true";
	
	public RetrieveCountryCode() {}
	
	public String getCountryCode(String country) {
		String code = "";
		
		String urlString = String.format(url, country);
		//System.out.println(urlString);

		try {
			 URL url = new URL(urlString);
			 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			 conn.setRequestMethod("GET");
			 conn.connect();
			 int responsecode = conn.getResponseCode();
			 if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				code = jsonArray.get(size - 1).getAsJsonObject().get("alpha2Code").getAsString();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}

}
