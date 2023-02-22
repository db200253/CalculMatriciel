package AlgLin;

public abstract class SysLin {
  
  public final static double epsilon = 0.000001;
  
  private int ordre;
  private Matrice matriceSystem;
  private Vecteur secondMembre;
  
  public SysLin(Matrice premMembre, Vecteur secMembre) throws IrregularSysLinException{
    
    if(premMembre.nbLigne() != secMembre.nbLigne()) {
      
      throw new IrregularSysLinException("La taille des deux membres ne correspond pas.");
    } else if(premMembre.nbColonne() != secMembre.nbLigne()) {
      
      throw new IrregularSysLinException("La matrice n'est pas carrée.");
    } else {
      
      ordre = premMembre.nbLigne();
      matriceSystem = premMembre;
      secondMembre = secMembre;
    }
  }
  
  public int getOrdre() {
    
    return ordre;
  }
  
  public Matrice getMatriceSystem() {
    
    return matriceSystem;
  }
  
  public Vecteur getSecondMembre() {
    
    return secondMembre;
  }
  
  public abstract Vecteur resolution() throws IrregularSysLinException;
}