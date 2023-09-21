package AnalysisEngine;

import CountryListServices.CountryListServices;

/**
 * An interface providing the facade with methods to usefully access the Calculator, and CountryListServices.
 */
public class AnalysisEngine {
    
    private String analysisMethod;
    private CountryListServices countryList;

    /**
     * A constructor method which creates a new AnalysisEngine object, and initializes the provided country list.
     * @param countryList A CountryListServices object.
     * @param analysisMethod The type of analysis requested in string format.
     */
    public AnalysisEngine(CountryListServices countryList, String analysisMethod){
        this.analysisMethod = analysisMethod;
        this.countryList = countryList;
    }

    /**
     * Changes the analysis type.
     * @param newMethod The new type of analysis requested in string format.
     */
    public void setAnalysisMethod(String newMethod){
        this.analysisMethod = newMethod;
    }

    /**
     * Provides the results of the type of analysis requested for the provided list of countries, and the positions of the countries.
     * @return A 2-dimensional array containing the names of the countries, the requested data, and their geographical positions.
     */
    public String[][] calculate(){
        Calculator analyzer = new Calculator(this.countryList,this.analysisMethod);
        return analyzer.calculate();
    }

}
