//A part of Polynomial from  Robert Sedgewick and Kevin Wayne.
public class Polynomial implements java.io.Serializable {
  static final long serialVersionUID = 53465;
  private int[] coef;  // coefficients
  private int deg;     // degree of polynomial (-1 for the zero polynomial)

  // a * x^b
  public Polynomial(int a, int b) {
    coef = new int[b+1];
    coef[b] = a;
    degree();
  }

  // calculate and set the degree of this polynomial (-1 for the zero polynomial)
  public void degree() {
    int d = -1;
    for (int i = 0; i < coef.length; i++)
      if (coef[i] != 0) d = i;
      this.deg = d;
  }

  public int getCoefAt(int i){
    return coef[i];
  }

  public void setCoefAt(int i, int c){
    coef[i] = c;
    degree();
  }

  public int getDegree(){
    degree();
    return this.deg;
  }
  
  // use Horner's method to compute and return the polynomial evaluated at x
  public int evaluate(int x) {
    int p = 0;
    for (int i = deg; i >= 0; i--)
      p = coef[i] + (x * p);
    return p;
  }
  
  
  
}