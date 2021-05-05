package dataretrieval;

/**
 * RestRetrieval class uses the strategy design pattern to call the appropriate strategy to retrieve the requested data using an HTTP GET request to retrieve a JSON stream
 * @author Michelle Ho
 *
 */
public class RestRetriever {
	/**
	 * RetrievalStrategy to be used
	 */
	private RetrievalStrategies hasStrategy;
	
	/**
	 * Name of country information is requested for
	 */
	private String country;
	
	public RestRetriever() {
	}
	
	/**
	 * Set country name data is requested for
	 * @param country: String, name of country information is requested for
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Set strategy to be used
	 * @param strat: RetrievalStrategies -- strategy to be used
	 */
	public void setStrategy(RetrievalStrategies strat) {
		this.hasStrategy = strat;
	}
	
	/**
	 * Function retrieves data using the set rest retrieval strategy
	 * @return
	 */
	public double getData() {
		return hasStrategy.getData(this);
	}
	
	/**
	 * Return name of country RestRetriever is set to
	 * @return String, country currently set
	 */
	public String getCountry() {
		return this.country;
	}

}
