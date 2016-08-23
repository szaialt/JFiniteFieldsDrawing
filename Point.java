//A generic Point class.

class Point<T> implements java.io.Serializable {
  static final long serialVersionUID = 54327;
  
  T x;
  T y;

  public Point(){}

  public Point(T x, T y){
    this.x = x;
    this.y = y;
  }

  public T getX(){
    return this.x;
  }

  public T getY(){
    return this.y;
  }

  public void setX(T x){
    this.x = x;
  }

  public void setY(T y){
    this.y = y;
  }
  
  @Override
  public int hashCode() { return x.hashCode() ^ y.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point<?>)) return false;
    Point<?> pointo = (Point<?>) o;
    return this.x.equals(pointo.getX()) &&
           this.y.equals(pointo.getY());
  }

  
}