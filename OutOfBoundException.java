
//OutOfBoundException marks that the given parameter is out of bound
public class OutOfBoundException extends RuntimeException {
  int order; //the parameter that violates the upperBound
  int upperBound; // the upper bound

  public OutOfBoundException(int order, int upperBound){
    this.order = order;
    this.upperBound = upperBound;
  }

  int getOrder(){
    return this.order;
  }

  int getUpperBound(){
    return this.upperBound;
  }

  void setOrder(int order){
    this.order = order;
  }

  void setUpperBound(int upperBound){
    this.upperBound = upperBound;
  }

}