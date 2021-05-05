package dataretrieval;

/**
 * Abstract RetrievalStrategies class is the common interface for getting data for a given RestRetriever
 * @author Michelle Ho
 *
 */
public abstract class RetrievalStrategies {

	public abstract double getData(RestRetriever restRetriever);
}
