package dataretrieval;

/**
 * RestRetrieverFactory uses the factory design pattern to dynamically create the appropriate RestRetrievalStrategy for the type of data requested
 * @author Michelle Ho
 *
 */
public class RestRetrieverFactory {
	/**
	 * Use getStrategy method to get object of type RetrievalStrategies depending on the strategy requested
	 * @param strategy: String, strategy type requested
	 * @return RetrievalStrategies object
	 */
	   public RetrievalStrategies getStrategy(String strategy){
	      if(strategy == null){
	         return null;
	      }		
	      
	      //standardize strategy request string to lowercase
	      strategy = strategy.toLowerCase();
	      
	      if(strategy.equalsIgnoreCase("cases"))
	      {
	         return new RetrieveCovidCases();
	      }
	      else if(strategy.equalsIgnoreCase("cases_male"))
	      {
	         return new RetrieveCovidCasesMale();
	      } 
	      else if(strategy.equalsIgnoreCase("cases_female"))
	      {
	         return new RetrieveCovidCasesFemale();
	      } 
	      else if(strategy.equalsIgnoreCase("population"))
	      {
	    	  return new RetrieveCountryPopulation();
	      }
	      
	      //return null if no strategy available
	      return null;
	   }
}
