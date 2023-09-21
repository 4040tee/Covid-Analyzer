package dataretrieval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DB class reads from database text file (coordinates.csv)
 * DB class is implemented using the Singleton design pattern, which ensures that only one DB class is created since there is just one database that is accessed
 * @author Michelle Ho
 *
 */
public class DB {
	/**
	 * Name of file storing database
	 */
	private String DBfile = "coordinates.csv";
	
	/*
	 * Static instance of DB for singleton method. This instance is what is called when DB class is requested
	 */
	private static DB instance = null;
	
	/**
	 * Dictionaries containing country codes, longitude and latitude values for a given country name key
	 */
	Map<String, String> country_codes = new HashMap<String, String>();
	Map<String, Double> country_latitude = new HashMap<String, Double>();
	Map<String, Double> country_longitude = new HashMap<String, Double>();
	
	/**
	 * Contructor for DB. Set to private as per singleton design pattern.
	 * When instance of DB class is created, it reads the .csv file that stores the information in the DB and stores the information into the appropriate dictionaries()country_codes, country_latitude, country_longitude).
	 */
	private DB() {
		File csvFile = new File(DBfile);
		if (csvFile.isFile()) {
		    // create BufferedReader and read data from csv
			BufferedReader csvReader = null;
			try {
				csvReader = new BufferedReader(new FileReader(DBfile));
				String row = "";
				while ((row = csvReader.readLine()) != null) {
				    String[] columns = row.split(",");
				    String code = columns[0];
				    Double latitude = Double.parseDouble(columns[1]);
				    Double longitude = Double.parseDouble(columns[2]);
				    String name = columns[3];
				    
					//standardize country name to uppercase first letter for use with the APIs
				    name = name.toLowerCase();
				    name = name.substring(0, 1).toUpperCase() + name.substring(1);
				    
				    //store information into appropriate dictionary
				    country_codes.put(name, code);
				    country_latitude.put(name, latitude);
				    country_longitude.put(name, longitude);
				}
				csvReader.close();
			}catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (csvReader != null) {
	                try {
	                	csvReader.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
		
	}
	
	/**
	 * Contructor for DB singleton design pattern
	 * @return static instance of DB class. If instance is null, call constructor to create a new instance.
	 */
	public static DB getInstance() {
		if(instance == null) instance = new DB();
		return instance;
	}
	

	/**
	 * Function retrieves the stored latitude value for a given country name from the database
	 * @param country: String, name of country information is requested for
	 * @return Double, latitude coordinate of requested country
	 */
	public double getLatitude(String country) {
		if(countryExists(country)) return country_latitude.get(country);
		else return -1;
	}
	
	/**
	 * Function retrieves the stored longitude value for a given country name from the database
	 * @param country: String, name of country information is requested for
	 * @return Double, longitude coordinate of requested country
	 */
	public double getLongitude(String country) {
		if(countryExists(country)) return country_longitude.get(country);
		else return -1;
	}
	
	/**
	 * Function returns whether or not country exists in the Database (i.e. if there is information stored for that country)
	 * @param country: String, name of country being checked
	 * @return Boolean, True if country is found in database else return False
	 */
	public boolean countryExists(String country) {
		return country_codes.containsKey(country);
	}
	
	/**
	 * Function returns the 2-letter code representing a country
	 * @param country: String, name of country information is requested for
	 * @return String, the 2-letter code representing a country
	 */
	public String getCode(String country) {
		//standardize country name to uppercase first letter
		country = country.toLowerCase();
		country = country.substring(0, 1).toUpperCase() + country.substring(1);
		
		if(countryExists(country)) return country_codes.get(country);
		else return null;
	}
}
