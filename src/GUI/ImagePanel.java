package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * A class which defines a GUI panel which presents an image. This is used for the map in the GUI.
 */

public class ImagePanel extends JPanel {

	  private static final long serialVersionUID = 1L;

	  private Image img;
	  private Image scaled;
	
	  /**
	   * A constructor method which creates an ImagePanel object with an Image attribute created from a string.
	   * @param img A string referring to an image.
	   */
	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  /**
	   * A constructor method which creates an ImagePanel object, intializing an Image attribute.
	   * @param img An Image object.
	   */
	  public ImagePanel(Image img) {
	    this.img = img;
	  }

	  /**
	   * Changes the Image of an ImagePanel object.
	   * @param img An Image object.
	   */
	  public void setImage (Image img) {
		  this.img = img;
	  }
	  
	  /**
	   * Sets the Image Panel as invalid in the JPanel system.
	   */
	  @Override
	  public void invalidate() {
	    super.invalidate();
	    int width = getWidth();
	    int height = getHeight();

	    if (width > 0 && height > 0) {
	      scaled = img.getScaledInstance(getWidth(), getHeight(),
	          Image.SCALE_SMOOTH);
	    }
	  }

	  /**
	   * Provides a set of dimensions that are preferable for the image's width and height
	   * @return Dimension object, containing image width and height.
	   */
	  public Dimension getPresferredSize() {
	    return img == null ? new Dimension(200, 200) : new Dimension(
	        img.getWidth(this), img.getHeight(this));
	  }

	  /**
	   * Draws an Image object onto a component of the GUI
	   */
	  @Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(scaled, 0, 0, null);
	  }
	}
