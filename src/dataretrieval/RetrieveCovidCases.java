package dataretrieval;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * RetrieveCovidCases is a class of type RetrievalStrategies, is a strategy for retrieving the total covid cases of a given country
 *
 */
public class RetrieveCovidCases extends RetrievalStrategies{
	/**
	 * URL to be called to retrieve JSON stream
	 */
	private String url = "https://api.covid19api.com/total/dayone/country/%s/status/confirmed";
	
	@Override
	public double getData(RestRetriever restRetriever) {
		String urlString = String.format(url, restRetriever.getCountry());
		System.out.println(urlString);

		double cases = 0;
		try {
			TimeUnit.SECONDS.sleep(1); //delay 1 second so if large list will not get http error 429 too many requests\
			
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
				
				//parse JSON as a JsonArray (format of JSON stored in this API)
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				cases = jsonArray.get(size - 1).getAsJsonObject().get("Cases").getAsDouble();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cases;
	}

}
