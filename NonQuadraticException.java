//NonQuadraticException marks that we couldn't find a
//non-quadratic residue.
public class NonQuadraticException extends RuntimeException {
  private String message;

  public NonQuadraticException(String string){
    this.message = string;
  }

  public String getMessage(){
    return this.message;
  }

}