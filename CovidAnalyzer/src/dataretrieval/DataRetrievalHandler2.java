package dataretrieval;

/**
 * Concrete handler 2 for DataRetrieval Chain of Responsibility design pattern.
 * If request is "longitude" get instance of DataBase and use interface getLongitude(country) to get the stored longitude value of that country
 * If strategy is null, pass to next successor in chain of responsibility if there is one
 * @author Michelle Ho
 *
 */
public class DataRetrievalHandler2 extends DataRetrievalHandler{

	@Override
	public double handleRequest(String request, String country) {
		// TODO Auto-generated method stub
		if(request == "longitude") {
			DB db = DB.getInstance(); //DB is singleton  design pattern
			return db.getLongitude(country);
		} else if (successor != null) {
			return successor.handleRequest(request, country);
		} else return -1;
	}
}
