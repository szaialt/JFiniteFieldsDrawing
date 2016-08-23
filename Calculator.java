import java.util.Vector;
import java.io.Serializable;

//Calculator adds new operations to the finite field to calculate and give
//shapes needed by drawing
public class Calculator implements Serializable {
static final long serialVersionUID = 87543;
int order;  //The order of the field
int p; //The characteristic
int n; //The dimension over the primefield
PrimePowerField field; //The finite field

public Calculator(int order){
  this.order = order;
  int upperBound = 900; //If the order would be greater there wouldn't be a DrawingPaper
  //This is also the upper bound of the control
  if (order > upperBound) throw new OutOfBoundException(order, upperBound);
  PrimePowersMap ppm = new PrimePowersMap();
  boolean found = false;
  int row = -1;
  for (int i = 0; i < ppm.primePowers.length; i++){
    if (ppm.primePowers[i][0] == order) {
      found = true;
      row = i;
    }
  }
  if (!found) {
    throw new IllegalArgumentException("The order is not a prime power.");
  }
  p = ppm.primePowers[row][1];
  n = ppm.primePowers[row][2];
  field = new PrimePowerField(p, n);  
}

//The ith Polynomial. It found the polynomial that has the x value at p
//modulo order. It is used to iterate the Polynomials or change from numeral to Polynomial.
public Polynomial ithPolynomial(int x){
  return field.ithPolynomial(x);
}

//It finds the value at p modulo order. It is used to convert the polynomial to a number.
//This is the best hash, and also used for comparasions.
//It isn't in the Polynomial because I would not save p, n and order in Polynomial.
public int ordinalOfPolynomial(Polynomial x){
  return field.ordinalOfPolynomial(x);
}

//A new point
public Point<Polynomial> newPoint(Polynomial x, Polynomial y){
  return new Point<Polynomial>(x, y);
}

//A new line as in the finite affin geometry
public Vector<Point<Polynomial>> newLine(Point<Polynomial> p0, Point<Polynomial> p1){
  Vector<Point<Polynomial>> line = new Vector<Point<Polynomial>>();
  Polynomial x0 = p0.getX();
  Polynomial x1 = p1.getX();
  Polynomial y0 = p0.getY();
  Polynomial y1 = p1.getY();

    //The points of the line with integer coordinates and between 0 and order - 1
    Polynomial a = field.minus(x1, x0);
    Polynomial b = field.minus(y1, y0);
    //Find and collect the points.
    Polynomial x = x0;
    Polynomial y = y0;
    for (int i = 0; i < order; i++){
      x = field.plus(x1, field.multiply(a, ithPolynomial(i)));
      y = field.plus(y1, field.multiply(b, ithPolynomial(i))); 
      x = field.reduce(x);
      y = field.reduce(y);
      Point<Polynomial> p = new Point<Polynomial>(x, y);
      line.add(p);
    }
  return line;
  
}

//A new circle as calculated according to the Euclidean circle formula
public Vector<Point<Polynomial>> newCircle(Point<Polynomial> o, Point<Polynomial> pp) {

    Vector<Point<Polynomial>> circle = new Vector<Point<Polynomial>>();
    Polynomial ox = field.reduce(o.x);
    Polynomial oy = field.reduce(o.y);
    Polynomial px = field.reduce(pp.x);
    Polynomial py = field.reduce(pp.y);
    Polynomial r = field.distance(ox, oy, px, py);
    Polynomial r_loc = field.reduce(r);
    //The points of the circle with integer coordinates and between 0 and order - 1
    //Find and collect the points.
    int x = 0;
    while (x < order){
      Polynomial xx = ithPolynomial(x);
      xx = field.reduce(xx);
      Polynomial y;
      Polynomial yy;
      try {
        y = field.plus(field.sqrt(field.minus(field.multiply(r_loc, r_loc), field.multiply((field.minus(xx, ox)), (field.minus(xx, ox))))), oy);  
        yy = field.plus(field.minus(ithPolynomial(0), field.sqrt(field.minus(field.multiply(r_loc, r_loc), field.multiply((field.minus(xx, ox)), (field.minus(xx, ox)))))), oy);  
        
      }
      //The square root operation isn't always successful. 
      //But the circle can have more points
      catch (IllegalArgumentException ex){
        x = x + 1;
        continue;
      }
      catch (NonQuadraticException ex){
        throw ex;
      }
      y = field.reduce(y);
      yy = field.reduce(yy);
      Point<Polynomial> p0 = new Point<Polynomial>(xx, y);
      Point<Polynomial> p1 = new Point<Polynomial>(xx, yy);
      circle.add(p0);
      circle.add(p1);
      x = x + 1;
    }
  return circle;
}

//Intersection of two shapes
public Vector<Point<Polynomial>> intersection(Vector<Point<Polynomial>> a, Vector<Point<Polynomial>> b){
  Vector<Point<Polynomial>> section = new Vector<Point<Polynomial>>();
  for (int i = 0; i < a.size(); i++){
    Polynomial ax = a.elementAt(i).getX();
    Polynomial ay = a.elementAt(i).getY();
    ax = field.reduce(ax);
    ay = field.reduce(ay);
    int axInt = ordinalOfPolynomial(ax);
    int ayInt = ordinalOfPolynomial(ay);   
    for (int j = 0; j < b.size(); j++){
      Polynomial bx = b.elementAt(j).getX();
      Polynomial by = b.elementAt(j).getY();
      bx = field.reduce(bx);
      by = field.reduce(by);
      int bxInt = ordinalOfPolynomial(bx);
      int byInt = ordinalOfPolynomial(by);   
      if ((axInt == bxInt) && (ayInt == byInt)){
        section.add(a.elementAt(i));
      }
    } 
  }
  return section;
}


}