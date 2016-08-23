import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
//We need also a window,
//save
//undo
//choose color
//new point
//circle
//line
//intersections

public class MainWindow extends JFrame implements ActionListener {

//The paper must know from the mouse click what it must do.
//This one don't need know the order.
//THe buttons are listened by the paper as an ActionListener.
//
//It is better save the points into the DrawingPaper 

  private JButton save; //listened by this
  private JButton open; //listened by this
  private JButton undo; //listened by paper
  private JButton chooseColor; //listened by paper
  private JButton newPoint; //listened by paper
  private JButton newCircle; //listened by paper
  private JButton newLine; //listened by paper
  private JButton intersect; //listened by paper
  private JButton close; //listened by this
  private DrawingPaper paper; // The DrawingPaper with the draws
  private String myExtension = "ffd"; //If a file path is given with this extension,
  //an XML constructed file will be saved by the given path. 
  //This program will able to reread it.
  private String foreignExtension = "png"; //This program can also save into an 
  //.png image file.
  //Another file types are not taken.
  private String file; //Path of the file
  private int size; //Size of the MainWindow
  private GridBagLayout gb; //Layout of the MainWindow
  private GridBagConstraints c; //GridBagConstraints to gb
  
  public MainWindow(DrawingPaper paper) {
  //Setting size
    Dimension bounds = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int w = (int)Math.round(bounds.getWidth());
    int h = (int)Math.round(bounds.getHeight());
    size = Math.min(w, h);
    setSize(w, h);
    //Constructing the buttons
    save = new JButton("Mentés");
    open = new JButton("Megnyitás");
    undo = new JButton("Visszavonás");
    chooseColor = new JButton("Színválasztás");
    newPoint = new JButton("Új pont");
    newCircle = new JButton("Új kör");
    newLine = new JButton("Új egyenes");
    intersect = new JButton("Metszéspontok");
    close = new JButton("Bezárás");
    //adding the paper
    this.paper = paper;
    save.addActionListener(this);
    open.addActionListener(this);
    undo.addActionListener(paper);
    chooseColor.addActionListener(paper);
    newPoint.addActionListener(paper);
    newCircle.addActionListener(paper);
    newLine.addActionListener(paper);
    intersect.addActionListener(paper);
    close.addActionListener(this);
  
    //Layout
    gb = new GridBagLayout();
    setLayout(gb);
    c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    add(save, c);
    c.gridx = 1;
    c.gridy = 0;
    add(open, c);    
    c.gridx = 2;
    c.gridy = 0;
    add(undo, c);
    c.gridx = 3;
    c.gridy = 0;
    add(chooseColor, c);    
    c.gridx = 4;
    c.gridy = 0;
    add(newPoint, c);    
    c.gridx = 5;
    c.gridy = 0;
    add(newCircle, c);    
    c.gridx = 6;
    c.gridy = 0;
    add(newLine, c);
    c.gridx = 7;
    c.gridy = 0;
    add(intersect, c);
    c.gridx = 8;
    c.gridy = 0;
    add(close, c);
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0.0;
    c.ipady = size - size/10; 
    c.ipadx = size - size/10;
    c.gridwidth = 9;
    add(paper, c);
    //last ones
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
 
  public void actionPerformed(ActionEvent event){
    //Getting the action
    String action = event.getActionCommand();
    System.out.println(action);
    //Save
    if (action.equals("Mentés")) {
      JFileChooser fc = new JFileChooser();
      fc.showDialog(null, "Mentés");
      file = fc.getSelectedFile().getAbsolutePath();
      String filename = fc.getSelectedFile().getName();
      String extension = getExtension(file);
      if (extension.equalsIgnoreCase(myExtension)){
        //Save into ffd
        try {
          paper.save(file);
        } catch (Exception ex){
          System.out.println(ex);
        }
      }
      if (extension.equalsIgnoreCase(foreignExtension)){
        //Save into png
        try {
          BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
          File outputfile = new File(file);
          Graphics g = image.createGraphics();
          paper.paint(g);
          ImageIO.write(image, "png", outputfile);
          } catch (IOException e) {
            System.out.println(e);
          }
      }
    }
    //Open file
    else if (action.equals("Megnyitás")) {
      System.out.println("open Button pressed!"); 
      JFileChooser fc = new JFileChooser();
      fc.showDialog(null, "Megnyitás");
      file = fc.getSelectedFile().getAbsolutePath();
      String filename = fc.getSelectedFile().getName();
      String extension = getExtension(file);
      System.out.println(file);
      //Open ffd file
      if (extension.equalsIgnoreCase(myExtension)){
        try {
          DrawingPaper pp = paper.read(file);
          System.out.println(pp);          
          this.paper = pp;
          c.gridx = 0;
          c.gridy = 1;
          c.weightx = 0.0;
          c.ipady = size - size/10; 
          c.ipadx = size - size/10;
          c.gridwidth = 9;
          add(paper, c);
          pack();
          paper.repaint();
          setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }     
    //Close
    else if (action.equals("Bezárás")) {
      System.out.println("close Button pressed!"); 
      setVisible(false);
      dispose();  //Remove 
    } 
    else {
      System.out.println("Talált!");
    }
    
  }
  
     /*
     * Get the extension of a file.
     */  
    public static String getExtension(String s) {
        String ext = null;
        //String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
  
}