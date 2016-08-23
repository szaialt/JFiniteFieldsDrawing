//Sorry, but Java don't have its Pair. 
//A generic Pair that comes from 
//https://github.com/JohnDickerson/KidneyExchange/blob/master/src/edu/cmu/cs/dickerson/
//kpd/helper/Pair.java
public class Pair<L,R> implements java.io.Serializable {
  static final long serialVersionUID = 24578;

  private L left;
  private R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public L getLeft() { return left; }
  public R getRight() { return right; }

  public void setLeft(L left) { this.left = left; }
  public void setRight(R right) { this.right = right; }

  @Override
  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair<?, ?>)) return false;
    Pair<?, ?> pairo = (Pair<?, ?>) o;
    return this.left.equals(pairo.getLeft()) &&
           this.right.equals(pairo.getRight());
  }

}