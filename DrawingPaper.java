import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;

public class DrawingPaper extends JPanel implements ActionListener, MouseListener {
  static final long serialVersionUID = -5658234;
   private int order;  //Oreder of the field
   private int size;  //Size of the drawing
   private int frameWidth; //Width of the frame
   private int step; //Size of the small quadrates
   private int mx;  //A correction
   private Vector<Pair<Vector<Point<Polynomial>>, Color>> shapes; 
   //The drawn shapes (<Vector<Point<Polynomial>) with their color (Color).
   //The shapes must have the colored shapes in the temporal sequence.
   //This isn't guaranteed for the maps, so we must use this one:
   //<Vector<Pair<Vector<Point>>, Color>> 
   //The all colored shapes.
   private Color color; //Actual color
   private Calculator calculator; //A calculator for the order size finite field
   private int x0; //coordinates of the clicked points
   private int y0; //coordinates of the clicked points
   private int x1; //coordinates of the clicked points
   private int y1; //coordinates of the clicked points
   private boolean clicked; //the first mouse click
   private String action; //The DrawingPaper is the listener of more buttons of MainWindow.
   //THis is the action of them.
    
   //To construct first time
   DrawingPaper(int order){
     this.setFocusable(true);
     this.order = order;
     Dimension bounds = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
     int w = (int)Math.round(bounds.getWidth());
     int h = (int)Math.round(bounds.getHeight());
     size = Math.min(w, h);
     frameWidth = size/50;

     step = (size - frameWidth - size/10) / (order + 1);
     mx = step * (order - 1) + frameWidth;
     
     shapes = new Vector<Pair<Vector<Point<Polynomial>>, Color>>();
     try {
       calculator = new Calculator(order);
     }
     catch (IllegalArgumentException ex) {
       //An alert window
       String alert = "A megadott rend valószínűleg nem prímhatvány.";
       JOptionPane.showMessageDialog(null, alert);
     }
     addMouseListener(this);
     
   }

   //To construct after re-read
   DrawingPaper(int order, Vector<Pair<Vector<Point<Polynomial>>, Color>> shapes){
     this(order);
     this.shapes = shapes;
   }
   
@Override
  public void paint(Graphics g) { 
    Graphics2D g2 = (Graphics2D)g;
    //We draw the grid
    g2.setColor(Color.black);
    for (int i = 0; i <= order; i++){
      //Verticals
      g2.drawLine(frameWidth + i*step, frameWidth, frameWidth + i*step, 
         mx + step);
      //Horizontals
      g2.drawLine(frameWidth, frameWidth + i*step, 
         mx + step, frameWidth + i*step);
         
    }
    drawPoints(g);
  }
  
    public void drawPoints(Graphics g){
     Graphics2D g2 = (Graphics2D)g;
    //Vector<Pair<Vector<Point<Polynomial>>, Color>> shapes;
    for (int i = 0; i < shapes.size(); i++){ //for all shapes
      Pair<Vector<Point<Polynomial>>, Color> coloredShape = shapes.elementAt(i);
      Vector<Point<Polynomial>> shape = coloredShape.getLeft();
      color = coloredShape.getRight();
      for (int j = 0; j < shape.size(); j++){ //for all points
        Point<Polynomial> point = shape.elementAt(j);
        int x = calculator.ordinalOfPolynomial(point.getX());
        int y = calculator.ordinalOfPolynomial(point.getY()); 
        g2.setColor(color);
        g2.fillRect(frameWidth + x * step, 
          frameWidth + y * step, step, step);
      }
    }
  }

  public void actionPerformed(ActionEvent event){
    action = event.getActionCommand();
    System.out.println(action);
    if (action.equals("Visszavonás")) {
      undo();
    }  
    else if (action.equals("Színválasztás")) {
      color = JColorChooser.showDialog(null, "Choose a Color", (new JLabel("Szöveg")).getForeground());
    }  
  }
 
  void undo(){
    if (!shapes.isEmpty()){
      shapes.remove(shapes.size()-1);
      repaint();
    }
  }
  
  void intersect(){
    Point<Polynomial> p0 = new Point<Polynomial>();
    p0.setX(calculator.ithPolynomial(x0));
    p0.setY(calculator.ithPolynomial(y0));
    Point<Polynomial> p1 = new Point<Polynomial>();
    p1.setX(calculator.ithPolynomial(x1));
    p1.setY(calculator.ithPolynomial(y1));
    Vector<Point<Polynomial>> shape0 = null;
    Vector<Point<Polynomial>> shape1 = null;
    for (int i = 0; i < shapes.size(); i++){ //minden alakzatra
      Pair<Vector<Point<Polynomial>>, Color> coloredShape = shapes.elementAt(i);
      Vector<Point<Polynomial>> shape = coloredShape.getLeft();
      for (int j = 0; j < shape.size(); j++){
        Point<Polynomial> point = shape.elementAt(j);
        Polynomial px = point.getX();
        Polynomial py = point.getY();
        int pxInt = calculator.ordinalOfPolynomial(px);
        int pyInt = calculator.ordinalOfPolynomial(py); 
        System.out.println(point.getX().getCoefAt(0) + " " + point.getY().getCoefAt(0));
        if ((pxInt == x0) && (pyInt == y0)){
          System.out.println(p0);
          shape0 = shape;
        }
        if ((pxInt == x1) && (pyInt == y1)){
          System.out.println(p1);
          shape1 = shape;
        }
      }
    }
    if ((shape0 != null) && (shape1 != null)){
      Vector<Point<Polynomial>> intersection = calculator.intersection(shape0, shape1);
      System.out.println(intersection);
      Pair<Vector<Point<Polynomial>>, Color> pair = new Pair<Vector<Point<Polynomial>>, Color>(intersection, this.color);
      shapes.add(pair);
      repaint();
    }
  }
  
  void newPoint(int x, int y){
    Point<Polynomial> p = new Point<Polynomial>();
    p.setX(calculator.ithPolynomial(x));
    p.setY(calculator.ithPolynomial(y));
    Vector<Point<Polynomial>> point = new Vector<Point<Polynomial>>();
    point.add(p);
    Pair<Vector<Point<Polynomial>>, Color> pair = new Pair<Vector<Point<Polynomial>>, Color>(point, color);
    shapes.add(pair);
    repaint();
  }
  
  void newCircle(){
    //Centrum
    Point<Polynomial> p0 = new Point<Polynomial>();
    p0.setX(calculator.ithPolynomial(x0));
    p0.setY(calculator.ithPolynomial(y0));
    //A point that gives the distance
    Point<Polynomial> p1 = new Point<Polynomial>();
    p1.setX(calculator.ithPolynomial(x1));
    p1.setY(calculator.ithPolynomial(y1));
    try {
      Vector<Point<Polynomial>> point = calculator.newCircle(p0, p1);
      Pair<Vector<Point<Polynomial>>, Color> pair = new Pair<Vector<Point<Polynomial>>, Color>(point, color);
      shapes.add(pair);
    }
    catch (IllegalArgumentException ex) {
    //Write out that the circle doesn't exist
//       String exString = "A megadott kör nem létezik.";
//       Graphics2D g2;
//       g2.setColor(Color.red);
//       g2.drawString(exString, frameWidth, frameWidth / 2);
//       try {
//         Thread.sleep(10);
//       }
//       catch (InterruptedException ex1) {}
    }
    catch (NonQuadraticException ex){
      //Write out that we couldn't find a non-quadratic remainder
     // String exString = "Nem sikerült nem kvadratikus maradékot találni.";
//       Graphics2D g2;
//       g2.setColor(Color.red);
//       g2.drawString(exString, frameWidth, frameWidth / 2);
//       try {
//         Thread.sleep(10);
//       }
//       catch (InterruptedException ex1) {}
    }
    finally {
      repaint();
    }
  }
  
  void newLine(){
    Point<Polynomial> p0 = new Point<Polynomial>();
    Point<Polynomial> p1 = new Point<Polynomial>();

    p0.setX(calculator.ithPolynomial(x0));
    p0.setY(calculator.ithPolynomial(y0));
    p1.setX(calculator.ithPolynomial(x1));
    p1.setY(calculator.ithPolynomial(y1));
    Vector<Point<Polynomial>> point = calculator.newLine(p0, p1);
    Pair<Vector<Point<Polynomial>>, Color> pair = new Pair<Vector<Point<Polynomial>>, Color>(point, color);
    shapes.add(pair);
    repaint();
  }
  
    public void mousePressed(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e){
    if (action.equals("Új pont")) {
      System.out.println("Új pont");
      int x = e.getX();
      int y = e.getY();
      int xx = (x - frameWidth) / step;
      int yy = (y - frameWidth) / step;
      newPoint(xx, yy);
    }  
    else {
      if (!clicked){
        int x = e.getX();
        int y = e.getY();
        x0 = (x - frameWidth) / step;
        y0 = (y - frameWidth) / step;
        clicked = true;
        System.out.println("Kattintás észlelve: 1.");
      }
      else {
        int x = e.getX();
        int y = e.getY();
        x1 = (x - frameWidth) / step;
        y1 = (y - frameWidth) / step;  
        clicked = false;
        System.out.println("Kattintás észlelve: 2.");
        repaint();
      }
      if (action.equals("Új kör")) {
        if (clicked == false){
          System.out.println("Új kör");
          newCircle();
        }
      }  
      else if (action.equals("Új egyenes")) {
        if (clicked == false){
          System.out.println("Új egyenes"); 
          newLine();
        }
      }  
      else if (action.equals("Metszéspontok")) {
        if (clicked == false){
          System.out.println("Metszéspontok");
          intersect();
        }
      }  
    }
    
    }
    
    public void mouseReleased(MouseEvent e){}
    
    public void mouseEntered(MouseEvent e){}
    
    public void mouseExited(MouseEvent e){}
    
    public void mouseMoved(MouseEvent e){}
    
    public void mouseDragged(MouseEvent e){}
    
    
    //The DrawingPaper saves itself into a given .ffd file that has an xml construction 
    //written in construction.txt.
    public void save(String file){
      try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	// root element: DrawingPaper
	Document doc = docBuilder.newDocument();
	Element rootElement = doc.createElement("DrawingPaper");
	doc.appendChild(rootElement);
	
	//order node under the DrawingPaper
	Element orderNode = doc.createElement("order");
	orderNode.appendChild(doc.createTextNode(""+order));
	rootElement.appendChild(orderNode);
	
	//shapes node under the DrawingPaper
	Element shapesNode = doc.createElement("shapes");
	  rootElement.appendChild(shapesNode);

	//colored shape nodes under the shapes node
	for (int i = 0; i < shapes.size();i++){
	  Pair<Vector<Point<Polynomial>>, Color> coloredShape = shapes.elementAt(i);
          Vector<Point<Polynomial>> shape = coloredShape.getLeft();
          Color shapeColor = coloredShape.getRight();
      
          //shape node
          Element shapeNode = doc.createElement("shape");
	  shapesNode.appendChild(shapeNode);
	  
	  //color node
	  Element colorNode = doc.createElement("color");
	  colorNode.appendChild(doc.createTextNode(Integer.toString(shapeColor.getRGB())));
	  shapeNode.appendChild(colorNode);
	  
	  //point nodes under the shape node
	  for (int j = 0; j < shape.size();j++){
	    Point<Polynomial> p = shape.elementAt(j);
	    
	    //point node
            Element pointNode = doc.createElement("point");
	    shapeNode.appendChild(pointNode);
	    
	    //x coordinate node
	    Element xNode = doc.createElement("x");
	    xNode.appendChild(doc.createTextNode(""+(calculator.ordinalOfPolynomial(p.getX()))));
	    pointNode.appendChild(xNode);
	
	    //y coordinate node
	    Element yNode = doc.createElement("y");
	    yNode.appendChild(doc.createTextNode(""+(calculator.ordinalOfPolynomial(p.getY()))));
	    pointNode.appendChild(yNode);
	  }
	  
	}
	
	// write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File(file));
	transformer.transform(source, result);
      }
      catch (ParserConfigurationException pce) {
        pce.printStackTrace();
      } catch (TransformerException tfe) {
          tfe.printStackTrace();
      }
    }
    
    public DrawingPaper read(String file) 
           throws IOException, SAXException, ParserConfigurationException{
     
      File fXmlFile = new File(file);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);

      //optional, but recommended
      //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
      doc.getDocumentElement().normalize();
      // root element: DrawingPaper
      Element rootElement = doc.getDocumentElement();
      
      //order node under the DrawingPaper
      NodeList orderNodes = rootElement.getElementsByTagName("order");
      Node orderNode = orderNodes.item(0);
      String orderString = orderNode.getTextContent();
      int order_ = Integer.parseInt(orderString);
      Element orderElement = (Element) orderNode;
     
      //shapes node under the DrawingPaper
      NodeList shapesNodes = doc.getElementsByTagName("shapes");
      Node shapesNode = shapesNodes.item(0);
      NodeList coloredShapeNodes = ((Element)shapesNode).getElementsByTagName("shape");
      Vector<Pair<Vector<Point<Polynomial>>, Color>> shapes_ = new Vector<Pair<Vector<Point<Polynomial>>, Color>>();
      for (int temp = 0; temp < coloredShapeNodes.getLength(); temp++) {
	
	//colored shape nodes under the shapes node
	Node coloredShapeNode = coloredShapeNodes.item(temp);
	Element coloredShapeElement = (Element) coloredShapeNode;
	NodeList colorNodes = coloredShapeElement.getElementsByTagName("color");
	Node colorNode = colorNodes.item(0);
        String colorString = colorNode.getTextContent();
        Color color_ = new Color(Integer.parseInt(colorString));
        
        //point nodes under the shape node        
        NodeList pointNodes = ((Element)(coloredShapeNode)).getElementsByTagName("point");
        Vector<Point<Polynomial>> shape = new Vector<Point<Polynomial>>();
	for (int temp2 = 0; temp2 < pointNodes.getLength(); temp2++) {
	  Node pointNode = pointNodes.item(temp2);
	  
	  //x coordinate node	 
	  NodeList xNodes = ((Element)pointNode).getElementsByTagName("x");
	  Node xNode = xNodes.item(0);
	  String xString = xNode.getTextContent();
	  int x_ = Integer.parseInt(xString);
	  
	  //y coordinate node
	  NodeList yNodes = ((Element)pointNode).getElementsByTagName("y");
	  Node yNode = yNodes.item(0);
	  String yString = yNode.getTextContent();
	  int y_ = Integer.parseInt(yString);
	  
	  //reconstruction of Point
	  Polynomial xPol = calculator.ithPolynomial(x_);
	  Polynomial yPol = calculator.ithPolynomial(y_);
          Point<Polynomial> p = new Point<Polynomial>(xPol, yPol);
          //reconstruction of shape
          shape.add(p);
        }
        //reconstruction of colored shape
        Pair<Vector<Point<Polynomial>>, Color> coloredShape = new Pair<Vector<Point<Polynomial>>, Color>(shape, color_);
        //reconstruction of spapes
        shapes_.add(coloredShape);
      }
      //reread DrawingPaper
      return new DrawingPaper(order_, shapes_);  
  }
  
}
