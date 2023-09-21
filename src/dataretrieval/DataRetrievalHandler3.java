package dataretrieval;

/**
 * Concrete handler 3 for DataRetrieval Chain of Responsibility design pattern.
 * If request is "latitude" get instance of DataBase and use interface getLongitude(country) to get the stored latitude value of that country
 * If strategy is null, pass to next successor in chain of responsibility if there is one
 * @author Michelle Ho
 *
 */
public class DataRetrievalHandler3 extends DataRetrievalHandler{

	@Override
	public double handleRequest(String request, String country) {
		// TODO Auto-generated method stub
		if(request == "latitude") {
			DB db = DB.getInstance();
			return db.getLatitude(country);
		} else if (successor != null) {
			return successor.handleRequest(request, country);
		} else return -1;
	}
}
