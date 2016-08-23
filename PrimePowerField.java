//A field with prime power elements and modulo $x^{q-1}-1$.
//It uses polinomials with coeficients from its prime field 

class PrimePowerField implements java.io.Serializable {
  static final long serialVersionUID = 76213;
  PrimeField pr; //The primefield
  int p; //The characteristic
  int exponent; //The dimension over the primefield
  int order; //The order of the field
  
  public PrimePowerField(int p, int n){
    this.pr = new PrimeField(p);
    this.p = p;
    this.exponent = n;
    this.order = pow(p, n);
  }
  
  public static int pow (int a, int b) {
    if ( b == 0)        return 1;
    if ( b == 1)        return a;
    if ( b % 2 == 0 )    return     pow ( a * a, b/2); //even a=(a^2)^b/2
    else                return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2
  }
  
  //After the operations we must reduce the polynomial modulo $x^{q-1}-1$
  //and its coeficients modulo the PrimeField
  public Polynomial reduce(Polynomial c){
    if (c.getDegree() == -1) return c;
    while (c.getDegree() >= (order - 1)){
      int cdeg = c.getDegree();
      int deg = cdeg - (order - 1);
      int coeff = c.getCoefAt(c.getDegree());
      //Csak a legmagasabb fokú tag és a nála q-1-gyel kisebb indexű együttható változik
      c.setCoefAt(cdeg, 0);
      c.setCoefAt(deg, pr.add(c.getCoefAt(deg), coeff));
      c.degree();
    }
    for (int i = 0; i < c.getDegree(); i++){
      int coeff = c.getCoefAt(i);
      coeff = coeff % p;
      if (coeff < 0) coeff = coeff + p;
      c.setCoefAt(i, coeff);
    }
    return c;
  }
  
  // return c = a + b
  public Polynomial plus(Polynomial a, Polynomial b) {
    if (a.getDegree() == -1) return b;
    if (b.getDegree() == -1) return a;
    Polynomial c = new Polynomial(0, Math.max(a.getDegree(), b.getDegree()));
    for (int i = 0; i <= a.getDegree(); i++) {
      c.setCoefAt(i, pr.add(c.getCoefAt(i), a.getCoefAt(i)));
    }
    for (int i = 0; i <= b.getDegree(); i++) {
      c.setCoefAt(i,  pr.add(c.getCoefAt(i), b.getCoefAt(i)));
    }
    c.degree();
    c = reduce(c);
    return c;
    }

  // return (a - b)
  public Polynomial minus(Polynomial a, Polynomial b) {
    if (b.getDegree() == -1) return a;
    if (a.getDegree() == -1) {
      a = new Polynomial(1, order-1);
      a.setCoefAt(0, p-1);
    }
    Polynomial c = new Polynomial(0, Math.max(a.getDegree(), b.getDegree()));
    for (int i = 0; i <= a.getDegree(); i++) {
      c.setCoefAt(i,  pr.add(c.getCoefAt(i), a.getCoefAt(i)));
    }
    for (int i = 0; i <= b.getDegree(); i++) {
      c.setCoefAt(i,  pr.subtract(c.getCoefAt(i), b.getCoefAt(i)));
    }
    c.degree();
    c = reduce(c);
    return c;
    }

    // return (a * b)
    public Polynomial multiply(Polynomial a, Polynomial b) {
     if (a.getDegree() == -1) return a;
     if (b.getDegree() == -1) return b;
      Polynomial c = new Polynomial(0, a.getDegree() + b.getDegree());
      for (int i = 0; i <= a.getDegree(); i++){
        for (int j = 0; j <= b.getDegree(); j++){
          c.setCoefAt(i+j, pr.add(c.getCoefAt(i+j), 
          (pr.multiply(a.getCoefAt(i), b.getCoefAt(j)))));
        }
      }
      c.degree();
      c = reduce(c);
      return c;
    }
  
  //Finds the inverse element
  public Polynomial invert(Polynomial a){
    if (a.getDegree() < 0) {
      throw new IllegalArgumentException("Cannot find inverse for ZERO.");
    }
    if (a.getDegree() == 0){
      int inverse = pr.invert(a.getCoefAt(0));
      Polynomial c = new Polynomial(inverse, 0);
      return c;
    }
    else {
      int exp = order - 2;
      Polynomial inverse = new Polynomial(1, 0);
      for (int i = 0; i < exp; i++){
        inverse = multiply(inverse, a);
      }
      inverse = reduce(inverse);
      return inverse;
    }
  }
    
  public Polynomial divide(Polynomial a, Polynomial b){
    if (b.getDegree() < 0) throw new IllegalArgumentException("Division by zero.");
    else return (reduce(multiply(a, invert(b))));
  }
 
  public Polynomial power(Polynomial a, int exponent){
    int exponent_local = exponent % p;
    if (exponent_local < 0) {
      if (a.getDegree() < 0) {
        throw new IllegalArgumentException("Negative power of ZERO.");
        }
      exponent_local = exponent_local + p; 
    }
    if (a.getDegree() < 0) return a;
    else if (a.getDegree() == 0) {
      int pow = pr.power(a.getCoefAt(0), exponent);
      Polynomial c = new Polynomial(pow, 0);
      return c;
    }
    Polynomial result = new Polynomial(1, 0);      
    int x = exponent_local;

     while (x != 1) {
      if ((x % 2) == 1) {
        result = reduce(multiply(result, a));
      }
      x /= 2;
      a = reduce(multiply(a, a));
    }

    return reduce(multiply(result, a));
  }
 
   // do a and b represent the same polynomial?
  public boolean eq(Polynomial a, Polynomial b) {
    Polynomial a_local = reduce(a);
    Polynomial b_local = reduce(b);
    if (a_local.getDegree() != b_local.getDegree()) return false;
    for (int i = a_local.getDegree(); i >= 0; i--){
      if ( !pr.eq(a_local.getCoefAt(i), b_local.getCoefAt(i))) {
        return false;
      }
    }
    
    return true;
  }
  
  //Is the parameter x a quadratic residue?
  public boolean quadraticResidue(Polynomial x){
    System.out.println("residue");
    Polynomial x_loc = reduce(x);
    Polynomial test = power(x_loc, (order - 1)/2);
    System.out.println("return residue");
    return ((ordinalOfPolynomial(test) == 0) || (ordinalOfPolynomial(test) == 1));
  }
  
  //The ith Polynomial. It found the polynomial that has the x value at p
  //modulo order. It is used to iterate the Polynomials or change from numeral to Polynomial.
  public Polynomial ithPolynomial(int x){
    Polynomial xx = new Polynomial(1, exponent - 1);
    int x1 = x;
    for (int i = 0; i < exponent; i++) {
      int xi = x1 % p;
      x1 = x1 / p;
      xx.setCoefAt(i, xi);
    }
    return reduce(xx);
  } 
  
  //It finds the value at p modulo order. It is used to convert the polynomial to a number.
  //This is the best hash, and also used for comparasions.
  //It isn't in the Polynomial because I would not save p, n and order in Polynomial.
  public int ordinalOfPolynomial(Polynomial x){
    int ordinal = x.evaluate(p);
    ordinal = ordinal % order;
    if (ordinal < 0) ordinal = ordinal + order;
    return ordinal;
  }
  
  //It gives a quadratic nonresidue or throws a NonQuadraticException
  public Polynomial giveQuadraticNonResidue() {
    int x = 0;
     while (x < order){
      Polynomial xx = ithPolynomial(x);
      reduce(xx);
      if (!quadraticResidue(xx)) return xx;
    }
    throw new NonQuadraticException("Quadratic nonresidue not found.");
  }
  
  //It calculates the distance bethween (x0, y0) and (x1, y1) as in the 
  //Euclidean planar geometry.
  public Polynomial distance(Polynomial x0, Polynomial y0, Polynomial x1, Polynomial y1)  {
    try {
      System.out.println("distance try");
      return sqrt(plus(multiply(minus(x0, x1), minus(x0, x1)), multiply(minus(y0, y1), minus(y0, y1))));
    }
    catch (NonQuadraticException ex) {
      throw ex;
    }
  }
  
  //If x is a quadratic residue, it gives a square root of x. 
  //If x is a quadratic nonresidue, it throws an IllegalArgumentException.
  public Polynomial sqrt(Polynomial x) {
    Polynomial x_loc = reduce(x);
    Polynomial minusOne = ithPolynomial(order - 1);
    Polynomial one = ithPolynomial(1);
    if (order % 2 == 1){
      if (!quadraticResidue(x)){
       String exString = "The element "+x+" doesn't have a square root in the field F"+order;
       throw new IllegalArgumentException(exString);
      }
    }
    //Odd characteristic
    if (order % 2 == 1){
      Polynomial two = ithPolynomial(2);
      if (order % 4 == 3){
        System.out.println("sqrt");
        System.out.println((order + 1)/4);
        return (power(x, (order + 1)/4));
      } 
      else if (order % 8 == 5){
        Polynomial b = power(multiply(two, x_loc), (order - 5)/8);
        Polynomial i = multiply(multiply(two, x_loc), b);
        return (multiply(multiply(x_loc, b), minus(i, one)));
      }
      else if (order % 16 == 9){
        Polynomial b = power(multiply(two, x_loc), (order - 9)/16);
        Polynomial i = multiply(multiply(two, x_loc), b);
        Polynomial r = multiply(i, i);
        Polynomial v;
        if (eq(r, minusOne)){
          v = multiply(multiply(x_loc, b), minus(i, one));
        }
        else {
          Polynomial d = giveQuadraticNonResidue();
          Polynomial num = new Polynomial((order - 9)/8, 0);
          Polynomial u = multiply(multiply(b, d), num);
          i = multiply(multiply(multiply(two, u), multiply(u, d)), multiply(d, x_loc));
          v = multiply(multiply(u, d), multiply(x_loc, minus(i, one)));
        }
        return v;
      }
      else if (order % 16 == 1){
        int t = order - 1;
        while (t % 2 == 0) {
          t = t / 2;
        }
        return (power(x_loc, (t - 1)/2));
      }
    }
    //Even caracteristic
    else {
      if (order == 2) return x;
      else {
        int k = 0;
        while (k < order){
          Polynomial xx = ithPolynomial(k);
          reduce(xx);
          if (eq(multiply(xx, xx), x)) return xx;
        }
      String exString = "The algorithm isn't properly implemented!";
      throw new IllegalArgumentException(exString);
    }
    
    }
    String exString = "The algorithm isn't properly implemented!";
    throw new IllegalArgumentException(exString);
  }
  
}