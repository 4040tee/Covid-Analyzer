package dataretrieval;

/**
 * DataRetriever is the main class for the dataretrieval package. It has the interfaces getData(), which allows the interacting component to get data for a specified country
 * and request type and checkCountryInDB which takes in a country name and returns a boolean indicating whether that country is stored in the database
 *
 */
public class DataRetriever {
	
	/**
	 * Constructor for DataRetriever class
	 */
	public DataRetriever() {
		
	}
	
	/**
	 * Retrieves data for a specified country and request type using the Chain of Responsibility design pattern to enable different classes to handle different requests
	 * @param country: String, name of country data is requested for
	 * @param method: String, type of data requested -- options are cases, cases_male, cases_female, population, longitude, latitude
	 * @return Double: data retrieved
	 */
	public double getData(String country, String method) {
		//standardize country name to uppercase first letter for use with the APIs
		country = country.toLowerCase();
		country = country.substring(0, 1).toUpperCase() + country.substring(1);
		
		DataRetrievalHandler h1 = new DataRetrievalHandler1();
		DataRetrievalHandler h2 = new DataRetrievalHandler2();
		DataRetrievalHandler h3 = new DataRetrievalHandler3();
		
		h1.setSuccessor(h2);
		h2.setSuccessor(h3);
		
		double data = h1.handleRequest(method, country);
		
		return data;
	}
	
	/**
	 * Checks if country is in the application's database
	 * @param country: String. name of country being checked
	 * @return Boolean, True if country is in the database, else returns false
	 */
	public boolean checkCountryInDB(String country) {
		//standardize country name to uppercase first letter for use with the APIs
		country = country.toLowerCase();
		country = country.substring(0, 1).toUpperCase() + country.substring(1);
		
		DB db = DB.getInstance();
		return db.countryExists(country);
	}
}
