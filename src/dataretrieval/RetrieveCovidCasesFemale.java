package dataretrieval;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * RetrieveCovidCasesFemale is a class of type RetrievalStrategies, is a strategy for retrieving the total covid cases of a given country
 * @author Michelle Ho
 *
 */
public class RetrieveCovidCasesFemale extends RetrievalStrategies{
	/**
	 * URL to be called to retrieve JSON stream
	 */
	private String url = "https://api.globalhealth5050.org/api/v1/summary?code=%s";


	@Override
	public double getData(RestRetriever restRetriever) {
		// TODO Auto-generated method stub
		String country = restRetriever.getCountry();
		String urlString = String.format(url, getCountryCode(country));
		
		double cases = -1; //return -1 if no data available for country
		try {
			
			//open and read JSON stream from url via HTTP GET request
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
				
				//parse JSON
				JsonParser parser = new JsonParser();
				JsonElement jsonElement = parser.parse(inline);
				
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				
				JsonObject json_data = jsonObject.getAsJsonObject("data");
				
				
				//get cases_female key
				for (Entry<String, JsonElement> e : json_data.entrySet()) {
					JsonElement json_country = e.getValue();
				    System.out.println(e.getKey() + "; " + json_country);
				    if (json_country.getAsJsonObject().get("cases_female").getAsString().contentEquals("")) cases = -1; //if there is no cases_female key, return -1
					else cases = json_country.getAsJsonObject().get("cases_female").getAsDouble();
				}	
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return cases;		
	}
	
	/**
	 * Retrieves country code for given country from database for use with the API
	 * @param country: String, name of country code is requested for
	 * @return String, country code of requested country
	 */
	private String getCountryCode(String country) {
		DB db = DB.getInstance();
		return db.getCode(country);
	}

}
