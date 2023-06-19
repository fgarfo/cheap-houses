import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.*;
import java.io.File;

public class CheapHouses extends JFrame {
    static Map<String, double[]> housePrices = new TreeMap<>();
    static ArrayList<Double[]> points = new ArrayList<>();

    /** The main method calls the createAndShowGUI
     *  method and runs the program.
     */
    public static void main(String[] args) {
        createAndShowGUI();
    }

    /** This method creates the structure for the GUI
     *  and an action button in order to plot points.
     *  It calls the getHouse method to collect the
     *  info for the points.
     */
    public static void createAndShowGUI() {
        // set main frame
        JFrame mainFrame = new JFrame("House Price Distribution");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 475);

        // set main panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        // set data panel and place on main panel
        JPanel dataPanel = new GPanel();
        dataPanel.setBounds(0, 0, 400, 400);
        dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(dataPanel);

        // set widget panel and place on main panel
        JPanel widgetPanel = new JPanel();
        widgetPanel.setBounds(0, 400, 400, 75);
        mainPanel.add(widgetPanel);

        // add text field, labels, and button to widget panel
        JLabel fileLabel = new JLabel("File:");
        JTextField fileField = new JTextField(8);
        fileField.setText("houses.csv");
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(8);
        JButton plotButton = new JButton("Plot");
        plotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = fileField.getText();
                String priceStr = priceField.getText();
                if (fileName != null && !fileName.trim().isEmpty() && priceStr != null && !priceStr.trim().isEmpty()) {
                    try {
                        points.clear();
                        getHouses(fileName, Double.parseDouble(priceStr));
                        dataPanel.repaint();
                    }
                    catch (NumberFormatException ex) {
                        dataPanel.repaint();
                    }
                }
            }
        });
        widgetPanel.add(fileLabel);
        widgetPanel.add(fileField);
        widgetPanel.add(priceLabel);
        widgetPanel.add(priceField);
        widgetPanel.add(plotButton);


        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    /** This method opens the "houses.csv" file to collect
     *  the info necessary to produce and show on the GUI.
     *  It passes the args "fn"(the file name inputted) and
     *  "price"(the price threshold inputted).
     */
    public static void getHouses(String fn, double price){
        try
        {
            Scanner houseFile = new Scanner(new File(fn));
            houseFile.nextLine();
            while (houseFile.hasNext())
            {
                String file = houseFile.nextLine();
                String[] f = file.split(",");
                String address = f[0];
                double[] info = new double[3];
                info[0] = Double.parseDouble(f[9]);
                info[1] = Double.parseDouble(f[10]);
                info[2] = Double.parseDouble(f[11]);
                housePrices.put(address, info);
            }
        }
        catch (java.io.FileNotFoundException ex)
        {
            System.out.println("Error: File not found");
        }
        try{
            PrintWriter outputFile = new PrintWriter("cheapHouses.txt");
            for (String key: housePrices.keySet()){
                double p = housePrices.get(key)[0];
                double lat = housePrices.get(key)[1];
                double lon = housePrices.get(key)[2];
                if (p < price){
                    points.add(new Double[]{lat, lon});
                    outputFile.print(key + "," + p + "\n");
                }
            }
            outputFile.close();
        }
        catch (java.io.IOException ex){
            System.out.println("Error");
        }

    }

    /** This is the graphics method that sets the
     *  traits for the plot points in appropriate scale
     *  to the panel it is on. It passes the arg "g" that
     *  which sets the color and shape of the plot points.
     */
    static class GPanel extends JPanel{
        public void paintComponent(Graphics g){
            int width = getSize().width;
            int height = getSize().height;
            g.drawRect(0,0,width,height);

            // set min and max
            if (points.size() == 0){
                return;
            }
            double minLat = points.get(0)[0];
            double maxLat = points.get(0)[0];
            double minLon = points.get(0)[1];
            double maxLon = points.get(0)[1];
            for (Double[] p: points){
                if (p[0] <= minLat){
                    minLat = p[0];
                }
                if (p[0] >= maxLat){
                    maxLat = p[0];
                }
            }
            for (Double[] p: points){
                if (p[1] <= minLon){
                    minLon = p[1];
                }
                if (p[1] >= maxLon){
                    maxLon = p[1];
                }
            }
            for (Double[] p: points){
                int y = (int) ((1-((p[0]-minLat)/(maxLat-minLat)))*width);
                int x = (int) (((p[1]-minLon)/(maxLon-minLon))*height);
                g.setColor(Color.black);
                g.fillOval(x, y, 5, 5);
            }
        }
    }
}