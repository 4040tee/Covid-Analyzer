package Rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import Facade.Facade;

/**
 * A class which defines a point on the GUI's map, using given geographical position data and the dimensions of the map.
 */

public class position {
	
	/**
	 * Constructor method which creates a new position object.
	 */
	public position() {
	}
	
	/**
	 * A method which creates new point at a (x,y) position based on the geographical coordiantes adjusted to the map dimensions.
	 * @param lat Latitude of the point.
	 * @param lng Longitude of the point.
	 * @param mapWidth The width of the map.
	 * @param mapHeight The height of the map.
	 * @return
	 */
	private Point getXY(double lat, double lng, int mapWidth, int mapHeight) {
		int screenX = (int) Math.round((((lng + 180) / 360) * mapWidth));
		int screenY = (int) Math.round(((((lat * -1) + 90) / 180) * mapHeight));
		return new Point(screenX, screenY);
		}
	
	/**
	 * Creates a new point with the given parameters and displays it on the given map/panel.
	 * @param Facade A Facade layer object.
	 * @param latX The latitude of the point.
	 * @param lngY The longitude of the point.
	 * @param max The maximum data value in the analysis results.
	 * @param percentage The fraction of the maximum of the data point.
	 * @param mapHeight The height of the map.
	 * @param mapWidth The width of the map.
	 * @param panel The map panel.
	 */
	public void display(Facade Facade,double latX,double lngY,double max,double percentage, int mapHeight, int mapWidth, JPanel panel) {
		Point coord = getXY(latX,lngY,mapWidth,mapHeight);
		Point2D coords = new Point2D.Double(lngY, latX);

		
		int ovald=dimension(max,percentage);
		Graphics2D editableImage = (Graphics2D) panel.getGraphics();
		
		
		editableImage.setColor(Color.RED);
		editableImage.setStroke(new BasicStroke(3));
		editableImage.fillOval(coord.x - (ovald / 2), coord.y - (ovald / 2), ovald, ovald);
		
		System.out.println("mapHeight: " + mapHeight + "mapWidth: " + mapWidth);
		System.out.println("Coordinates: " + coords.getX() + ", " + coords.getY());
		System.out.println("oval: " + ovald);
	}
	
	/**
	 * Determines the oval dimension of the point, relative to the oval dimensions of the maximum value.
	 * @param maxValue The maximum value
	 * @param percentage The fraction of the maximum value of the data point.
	 * @return
	 */
	public int dimension(double maxValue,double percentage) {
		
		int maxOvalDimension;
		if (maxValue < 10000)
		maxOvalDimension = 20;
		else if (maxValue < 50000)
		maxOvalDimension = 30;
		else if (maxValue < 100000)
		maxOvalDimension = 50;
		else
		maxOvalDimension = 70;
		int minOvalDimension = 15;
		
		int ovalDimension = (int)Math.round(((maxOvalDimension - minOvalDimension) * percentage) + minOvalDimension);
		return ovalDimension;
	}
}	
