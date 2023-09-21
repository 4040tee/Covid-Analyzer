package CountryListServices;

import java.util.ArrayList;

import dataretrieval.DataRetriever;
/**
 * An ArrayList containing the list of countries to be analysed by the COVID Analyzer, and other methods to check for the existence of requested countries.
 */
public class CountryListServices{

    private ArrayList<String> analysisList;

    /**
     * A constructor method which creates a new CountryListServices object, intializing a new ArrayList of type String.
     */
    public CountryListServices(){
        this.analysisList = new ArrayList<String>();
    }

    /**
     * Checks if a country is in the data retrieval system.
     * @param country A country's name in string format.
     * @return A boolean identifying if a country is in the data retrieval system, or not.
     */
    public boolean checkExists(String country){
        boolean result = false;
        DataRetriever checkData = new DataRetriever();
        result = checkData.checkCountryInDB(country);
        return result;
    }

    /**
     * Checks if a country is in the list of countries to be analysed.
     * @param country A country's name in string format.
     * @return A boolean identifying if a country is in the list, or not.
     */
    public boolean checkName(String country){
        return this.analysisList.contains(country);
    }

    /**
     * Adds a new country to the list of countries to be analysed
     * @param country A country's name in string format.
     * @return A boolean identifying if a country is in the data retrieval system, or not.
     */
    public boolean addToList(String country){
        boolean exists = checkExists(country);
        if(exists && !checkName(country)){ //add country if exists in DB and is not currently in list
            this.analysisList.add(country);
            return true;
        }
        return false;
    }

    /**
     * Removes a country from the list of countries to be analysed.
     * @param country A country's name in string format.
     * @return A boolean identifying if a country is in the list, or not.
     */
    public boolean removeFromList(String country){
        boolean foundCountry = checkName(country);
        if(foundCountry){this.analysisList.remove(country);}
        return foundCountry;
    }

    /**
     * Provides the list of countries to be analysed.
     * @return The ArrayList containing countries to be analysed.
     */
    public ArrayList<String> getAnalysisList(){
        return this.analysisList;
    }

}