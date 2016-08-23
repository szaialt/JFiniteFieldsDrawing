//A finite field with prime elements reprezented by remainders.
//It comes partly from the projekt Computation_with_finite_fields.

class PrimeField implements java.io.Serializable {
  static final long serialVersionUID = 576342;
  int p;

  public PrimeField(int p){
    this.p = p;
  }
  
  public int add(int a, int b){
    int a_local = a % p;
    int b_local = b % p;
    return ((a_local + b_local) % p);
  }
  
  public int subtract(int a, int b){
    int a_local = a % p;
    int b_local = b % p;
    return ((a_local - b_local) % p);
  }
  
  public int multiply(int a, int b){
    int a_local = a % p;
    int b_local = b % p;
    return ((a_local * b_local) % p);
  }

  public int invert(int a){
    int a_local = a % p;
    if (a_local == 0){
      throw new IllegalArgumentException("Cannot find inverse for ZERO.");
    }
    else if (a_local == 1){
      return 1;
    }
    int exponent = p - 2;
    int inverse = 1;
    for (int i = 0; i < exponent; i++){
      inverse = multiply(inverse, a_local);
    }
    return inverse;
  }
  
  public int divide(int a, int b){
    if (b == 0) throw new IllegalArgumentException("Division by zero.");
    else if (b == 1) return a % p;
    else return (multiply(a, invert(b)));
  }

  public int power(int a, int exponent){
    int a_local = a % p;
    int exponent_local = exponent % p;
    if (exponent_local < 0) {
      if (a_local == 0) {
        throw new IllegalArgumentException("Negative power of ZERO.");
        }
      exponent_local = exponent_local + p; 
    }
    if (a_local == 0) return 0;
    else if (a_local == 1) return 1;
    int result = 1;
    int x = exponent_local;

     while (x != 1) {
      if ((x % 2) == 1) {
        result = multiply(result, a_local);
      }
      x /= 2;
      a_local = multiply(a_local, a_local);
    }

    return multiply(result, a);
  }
  
  public boolean eq(int a, int b){
    int a_local = a % p;
    int b_local = a % b;
    if (a_local < 0) a_local = a_local + p;
    if (b_local < 0) b_local = b_local + p;
    return (a_local == b_local);
  }
  
     
  public int distance(int x0, int y0, int x1, int y1) throws IllegalArgumentException {
    try {
      return sqrt(add(multiply(subtract(x0, x1), subtract(x0, x1)), multiply(subtract(y0, y1), subtract(y0, y1))));
    }
    catch (IllegalArgumentException ex) {
      throw ex;
    }
  }
  
  public int sqrt(int x) throws IllegalArgumentException {
    int x_loc = x % p;
    if (x_loc < 0) x_loc = x_loc + p;
    for (int i = 0; i <= p/2; i++){
      if ((i * i) % p == x_loc) return i;
    }
    String exString = "The element "+x+" doesn't have a square root modulo "+p;
    throw new IllegalArgumentException(exString);
  }

}