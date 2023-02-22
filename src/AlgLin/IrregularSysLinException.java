package AlgLin;

@SuppressWarnings("serial")
public class IrregularSysLinException extends Exception {
  
  private String message;
  
  public IrregularSysLinException(String message) {
    
    this.message = message;
  }
  
  public String toString() {
    
    return "IrregularSysLinException : " + message;
  }
}