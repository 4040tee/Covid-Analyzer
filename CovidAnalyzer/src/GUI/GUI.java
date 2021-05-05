package GUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Facade.Facade;
import Rendering.position;

/**
 * A class which provides a graphical user interface for the COVIDAnalyzer system. This contains a map, a list of countries, a data output list, 
 * and drop down/text box systems to select countries and types of analysis.
 */
public class GUI {//extends AnalysisStrategies
	private static int width = JFrame.MAXIMIZED_HORIZ;
	private static int height = JFrame.MAXIMIZED_HORIZ;
	private static Facade Facade = new Facade();
	private static position pos = new position();

    /**
     * Initializes the graphical user interface frame and makes it visible
     */
    public  void GUIinit() {
    	
    	//Create and set up the window.
        JFrame frame = new JFrame("CovidAnalyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        try {
			addComponentsToPane(frame.getContentPane(), frame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Use the content pane's default BorderLayout. No need for
        //setLayout(new BorderLayout());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    public static boolean RIGHT_TO_LEFT = false;
    
    /**
     * Adds the components of the GUI to a given frame and content pane
     * @param pane The content pane.
     * @param frame The window.
     * @throws IOException
     */
    public static void addComponentsToPane(Container pane, JFrame frame) throws IOException {
    	int fontsize = 20;
         
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
         
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
         
        JButton button = new JButton("Button 1 (PAGE_START)");
        
        JLabel AddC = new JLabel();
        AddC.setText("Add a Country:");
        AddC.setFont(new java.awt.Font("Dialog", Font.BOLD, fontsize));
        
        JTextField AddCTF = new JTextField(20);
        
        JButton AddCB = new JButton("+");
        //AddCB.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
        //AddCB.setPreferredSize(new Dimension(30, 30));
        
        // Remove
        JLabel RemoveC = new JLabel();
        RemoveC.setText("Remove a Country:");
        RemoveC.setFont(new java.awt.Font("Dialog", Font.BOLD, fontsize));

        // Remove Text Field
        JTextField RemoveCTF = new JTextField(20);

        JButton RemoveCB = new JButton("-");
        //RemoveCB.setFont(new java.awt.Font("Dialog", Font.PLAIN, 12));
        //RemoveCB.setPreferredSize(new Dimension(30, 30));
        
        FlowLayout flowlayout = new FlowLayout();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(flowlayout);
        //flowlayout.setAlignment(FlowLayout.TRAILING);
        
        topPanel.add(AddC);
        topPanel.add(AddCTF);
        topPanel.add(AddCB);
        topPanel.add(RemoveC);
        topPanel.add(RemoveCTF);
        topPanel.add(RemoveCB);
        
        pane.add(topPanel, BorderLayout.PAGE_START);
         
        //Make the center component big, since that's the
        //typical usage of BorderLayout.
        JLabel MapL = new JLabel();
        ImageIcon map = new ImageIcon("map.jpg");
        MapL.setIcon(map);
        
        File mapFile = new File("map.jpg");
        //BufferedImage bimage = ImageIO.read(mapFile);
        Image img = ImageIO.read(mapFile);
        JPanel mapPanel = new ImagePanel(img);
        pane.add(mapPanel, BorderLayout.CENTER);
        
        
        
        /////////////////////////////////////////////
        //bottom panel
        
        JLabel AnalysisT = new JLabel();
        AnalysisT.setText("Choose analysis method:");
        AnalysisT.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
        
        JComboBox<String> method = new JComboBox<>(new String[]{});
        method.addItem("CovidConfirmedCases");
        method.addItem("CovidConfirmedCasesPerCapita");
        method.addItem("CovidConfirmedCasesMales");
        method.addItem("CovidConfirmedCasesFemales");
        method.setFont(new java.awt.Font("Dialog", Font.PLAIN, 20));
         
        //button = new JButton("Long-Named Button 4 (PAGE_END)");
        JPanel bottomPanel = new JPanel(flowlayout);
        bottomPanel.add(AnalysisT);
        bottomPanel.add(method);
        pane.add(bottomPanel, BorderLayout.PAGE_END);
        
        /////////////////////////////////////////////
        //right side panel
        JLabel ListS = new JLabel();
        ListS.setText("List of selected countries:");
        ListS.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));

        // List Display
        JTextArea CList = new JTextArea();
        CList.setColumns(10);
        //CList.setSize(100, 100);
        CList.setLineWrap(true);
        JScrollPane scroll_CList = new JScrollPane(CList);
        scroll_CList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        // Calculate Button
        JButton ReCal = new JButton("Recalculate");
        ReCal.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
        ReCal.setSize(20, 5);


        JLabel Output = new JLabel();
        Output.setText("Output:");
        Output.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));

        // Output List
        JTextArea OutputA = new JTextArea();
        //OutputA.setSize(100, 100);
        OutputA.setColumns(10);
        OutputA.setLineWrap(true);
        JScrollPane scroll_output = new JScrollPane(OutputA);
        scroll_output.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(ListS);
        sidePanel.add(scroll_CList);
        sidePanel.add(Box.createRigidArea(new Dimension(0,20)));
        sidePanel.add(ReCal);
        sidePanel.add(Box.createRigidArea(new Dimension(0,20)));
        sidePanel.add(Output);
        sidePanel.add(scroll_output);
        pane.add(sidePanel, BorderLayout.LINE_END);
        
        ArrayList list = new ArrayList();
        getCountryToAdd(list, frame, AddCB, AddCTF, CList);
        getCountryToRemove(list,frame,RemoveCB,RemoveCTF,CList);
        performAnalysis(frame,ReCal,OutputA,method, mapPanel);
    }
     
    /**
     * Provides the user with the option to add a country and attempts to do so when triggered.
     * @param list The list of countries to be analysed.
     * @param frame The window of the GUI.
     * @param AddCB The button which this function is connnected to.
     * @param AddCTF The text field which this function is connectd to.
     * @param CList The list which displays the selected countries.
     */
    private static void getCountryToAdd(final ArrayList list, final JFrame frame, JButton AddCB, final JTextField AddCTF, final JTextArea CList) {
        AddCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPerform();
            }

            private void addPerform() {
                String name = AddCTF.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, " Please add a Country");
                }

                if(Facade.addCountry(name)) {
                    list.add(name);
                    CList.append(name + "\n");
                    AddCTF.setText("");
                }
                else{
                	if(!Facade.checkCountryExists(name)) JOptionPane.showMessageDialog(frame, "Country could not be added. This Country does not exist.");
                	else JOptionPane.showMessageDialog(frame, "Country could not be added. Country already added to list");
                }
            }

        });

    }

    /**
     * Provides the user with option to remove a country and attempts to do so when triggered.
     * @param list The list of countries to be analysed
     * @param frame The window of the GUI.
     * @param RemoveCB The button which this function is connected to.
     * @param RemoveCTF The text field which this function is connected to.
     * @param CList The list which dsplays the selected countries.
     */
    private static void getCountryToRemove(final ArrayList list, final JFrame frame, JButton RemoveCB, final JTextField RemoveCTF, final JTextArea CList){
        RemoveCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCountryToRemove();
            }
            private void getCountryToRemove(){
                String nameadded =CList.getText();
                String Rname=RemoveCTF.getText();
                if(Rname.isEmpty()){
                    JOptionPane.showMessageDialog(frame,  "Nothing to delete");
                }
                list.remove(Rname);
                Facade.removeCountry(Rname);
                CList.setText("");
                for (Object n:list){
                    CList.append(n+"\n");
                }
                RemoveCTF.setText("");
            }
        });
    }
    
    /**
     * Provides the user with the option to acquire the data for the selected countries, and analysis type.
     */
    private static void performAnalysis(JFrame frame, JButton ReCal, final JTextArea OutputA,final JComboBox method, JPanel mapPanel){
        ReCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                String type=(String)method.getSelectedItem();
                String[][] result= Facade.performAnalysis(type);
                
                OutputA.setText("");
                // clear the graphics
                resetMapPanel(mapPanel);
                
                // the maxValue for rendering
                String maxt= result[0][4].toString();
                System.out.println("maxt"+maxt);
                
                for(i=0;i<Facade.getSize();i++){
                    String formatOutput= result[i][0]+" => COVID:"+result[i][1]+"\n";
                    System.out.println(formatOutput);
                    OutputA.append(formatOutput);
                    // displaying
                    double max =Double.valueOf(result[0][4].toString());
                    
                    double latX= Double.valueOf(result[i][2].toString());
                    double lngY =Double.valueOf(result[i][3].toString());  
                    double percentage=Double.valueOf(result[i][1].toString())/max;
                    
                    int mapHeight = mapPanel.getHeight();
                    int mapWidth = mapPanel.getWidth();
                    pos.display(Facade, latX, lngY, max, percentage, mapHeight, mapWidth, mapPanel);
                }	

            }

        });

    }
    
    /**
     * Resets the GUI map. This is used to clear data points placed on the map from an analysis.
     * @param mapPanel The map panel.
     */
    private static void resetMapPanel(JPanel mapPanel) {
    	mapPanel.removeAll();
    	Point p = mapPanel.getLocation();
        int mapHeight = mapPanel.getHeight();
        int mapWidth = mapPanel.getWidth();
    	mapPanel.paintImmediately(p.x, p.y, mapWidth, mapHeight);;
    }

}
