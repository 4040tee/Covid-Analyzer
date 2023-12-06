package dataretrieval;

/**
 * Concrete handler 1 for DataRetrieval Chain of Responsibility design pattern.
 * Uses factory design pattern to to generate appropriate strategy class for request if applicable, else strategy is null
 * If strategy is not null, use strategy design pattern in RestRetriever to get requested data
 * If strategy is null, pass to next successor in chain of responsibility if there is one
 *
 */
public class DataRetrievalHandler1 extends DataRetrievalHandler{

	@Override
	public double handleRequest(String request, String country) {
		// TODO Auto-generated method stub
		RestRetrieverFactory factory = new RestRetrieverFactory();
		RetrievalStrategies strategy = factory.getStrategy(request); //use factory design pattern 
		
		if(strategy != null) { //if strategy is not null, use strategy design pattern in RestRetriever to get requested data
			RestRetriever rr = new RestRetriever();
			rr.setCountry(country);
			rr.setStrategy(strategy);
			return rr.getData();
		} else if (successor != null) { //if strategy is null, pass to next successor in chain of responsibility if there is one
			return successor.handleRequest(request, country);
		} else return -1;
	}
	
}
