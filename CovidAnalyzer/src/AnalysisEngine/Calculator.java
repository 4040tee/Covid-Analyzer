package AnalysisEngine;

import static java.util.Map.entry;

import java.util.Map;

import CountryListServices.CountryListServices;
import dataretrieval.DataRetriever;

/**
 * A class which acquires the data for a specific set of countries and analysis type, and stores it in a 2-dimnensional array.
 */

public class Calculator {

    private String[][] resultList;
    private String analysisMethod;
    private CountryListServices countryList;
    private int numCountries;
    
    /**
     *Converts the analysis type string, received as parameter, to its short form.
     */
    private Map<String, String> Strategy2DataRetrieval = Map.ofEntries(
    	    entry("CovidConfirmedCases", "cases"),
    	    entry("CovidConfirmedCasesPerCapita", "per_capita"),
    	    entry("CovidConfirmedCasesMales", "cases_male"),
    	    entry("CovidConfirmedCasesFemales", "cases_female")
    	);

    /**
     * A constructor method which creates a new calculator object, and initializes the provided country list. 
     * The constructor also creates a 2-dimensional array containing the names of the requested countries in the 1st column.
     * @param countryList
     * @param analysisMethod
     */
    public Calculator(CountryListServices countryList, String analysisMethod){
        this.analysisMethod = analysisMethod;
        this.countryList = countryList;
        this.numCountries = this.countryList.getAnalysisList().size();
        this.resultList = new String[numCountries][5];
        for(int i = 0; i < numCountries; i++){
            resultList[i][0] = this.countryList.getAnalysisList().get(i);
        }
    }

    /**
     * Acquires the data pertaining to the type of analysis requested, and the geographical positions of the countries
     * and stores it in the 2-dimensional array. 
     * @return A 2 dimensional array containing the requested countries names, the requested data, and the geographical positions of the countries.
     */
    public String[][] calculate(){
        
        //Initialize the data retriever
        DataRetriever pullData = new DataRetriever();
        String analysisMethod = Strategy2DataRetrieval.get(this.analysisMethod);
        double maxCountries = -1.0;
        
        //For every country in the list acquire the needed data and store it in the array
        for(int i = 0; i < this.numCountries; i++){
        	String country = this.resultList[i][0];
            //Acquire the data for all the cases in a country, divide it by the population of the country, and store it in the array.
            if(!analysisMethod.equals("per_capita")){
                double data = pullData.getData(country, analysisMethod);
                if(data==-1.0) this.resultList[i][1] = "Data Not Available";
                else{ 
                    this.resultList[i][1] = Double.toString(data);
                    //Store the maximum value in the 5th column.
                    if((maxCountries == -1.0) | (data > maxCountries)){
                        maxCountries = data;
                        this.resultList[0][4] = this.resultList[i][1];
                    }
                }
            }
            //Acquire the data for any other kind of analysis and store it in the array.
            else{
                double data = pullData.getData(country,"cases");
                if(data==-1.0) this.resultList[i][1] = "Data Not Available";
                else {
                	double population = pullData.getData(country,"population");
                    data = data / population;
                    this.resultList[i][1] = Double.toString(data);
                    //Store the maximum value in the 5th column.
                    if((maxCountries == -1.0) | (data > maxCountries)){
                        maxCountries = data;
                        this.resultList[0][4] = this.resultList[i][1];
                    }
                }
            }
            
            //Acquire the geographical position of the country and store it in the array.
            double latitude = pullData.getData(country,"latitude");
            this.resultList[i][2] = Double.toString(latitude);
            double longitude = pullData.getData(country,"longitude");
            this.resultList[i][3] = Double.toString(longitude);
        }
        
        return resultList;
    }


}