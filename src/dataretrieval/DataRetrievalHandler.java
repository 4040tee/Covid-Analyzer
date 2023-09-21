package dataretrieval;

/**
 * This abstract class manages the chain of responsibility design pattern for data retrieval 
 * @author Michelle Ho
 *
 */

public abstract class DataRetrievalHandler {
	/**
	 * This abstract class manages the chain of responsibility design pattern for data retrieval 
	 */
	protected DataRetrievalHandler successor;
	
	
	/**
	 * Sets the success of the data retrieval handler class down the chain of responsibility
	 * @param successor DataRetrievalHandler, class that will handle the request if the current handler is unable to handle it
	 */
	public void setSuccessor(DataRetrievalHandler successor) {
		this.successor = successor;
	}
	
	/**
	 * Abstract function handles request or sends request to successor if unable to handle
	 * @param request: String, Type of data requested
	 * @param country: String, Name of country to retrieve data for
	 * @return Double, Result of request (retrieved data)
	 */
	public abstract double handleRequest(String request, String country);

}
