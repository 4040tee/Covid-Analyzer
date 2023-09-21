package Facade;

import AnalysisEngine.AnalysisEngine;
import CountryListServices.CountryListServices;

/**
 * This class functions as a "Facade" layer between the GUI and the rest of the program 
 * providing it with a simplified interface with which to access and manipulate the other components.
 */
public class Facade {
    
    private  CountryListServices facadeCountries;

    /**
     * Constructor method which creates a new Facade, intializing a CountryListServices object within it.
     */
    public Facade(){
       this.facadeCountries = new CountryListServices();
    }

    /**
     * Adds a new country to the list of countries to be analysed.
     * @param country A countries name in string format.
     * @return A boolean indicating whether or not the country exists in the data retrieval system.
     */
    public boolean addCountry(String country){
        return this.facadeCountries.addToList(country);
    }

    /**
     * Removes a country from the list of countries to be analysed.
     * @param country A countries name in string format.
     * @return A boolean indicating whether or not the country was in the list of countries to be analysed.
     */
    public boolean removeCountry(String country){
        return this.facadeCountries.removeFromList(country);
    }

    /**
     * Produces the results of the analysis and other neccessary data to output the results.
     * @param type The type of analysis requested in string format.
     * @return  A 2-dimensional array containing the names, requested data, and positions of the countries in the list of countries to be analysed.
     */
    public String[][] performAnalysis(String type){
        AnalysisEngine analyzer = new AnalysisEngine(this.facadeCountries,type);
        return analyzer.calculate();
    }

    /**
     * Checks if the country exists in the data retrieval system
     * @param country A countries name in string format.
     * @return A boolean indicating whether or not the country exists in the data retrieval system.
     */
    public boolean checkCountryExists(String country){
        return this.facadeCountries.checkExists(country);
    }

    /**
     * Provides the number of countries in the list of countries to be analysed.
     * @return An integer representing the size of the ArrayList in which the countries are stored.
     */
    public int getSize(){
        return this.facadeCountries.getAnalysisList().size();
    }

}