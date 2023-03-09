package AlgLin;

public class SysDiagonal extends SysLin {
  
  public static void main(String[] args) throws IrregularSysLinException {
    
    double[][] t1 = {{8.0,0.0,0.0},{0.0,4.0,0.0},{0.0,0.0,16}};
    double[][] t2 = {{8.0,0.0,1.0},{0.0,4.0,0.0},{0.0,0.0,16}};
    Matrice m1 = new Matrice(t1);
    Matrice m2 = new Matrice(t2);
    
    double[] tv = {4.0,16.0,9.0};
    Vecteur v1 = new Vecteur(tv);
    
    SysDiagonal s1 = new SysDiagonal(m1, v1);
    SysDiagonal s2 = new SysDiagonal(m2, v1);
    
    Vecteur r1 = null;
    
    try {
      
      r1 = s1.resolution();
      System.out.println("solution r1 :\n" + r1);
    } catch (IrregularSysLinException e) {
      
      e.printStackTrace();
    }
    
    try {
      
      Vecteur r2 = s2.resolution();
      System.out.println(r2);
    } catch (IrregularSysLinException e) {
      
      e.printStackTrace();
    }
    
    Vecteur verif = new Vecteur(tv.length);
    Vecteur inter = new Vecteur(tv.length);
    
    for(int i = 0; i < tv.length; ++i) {
      
      inter.remplaceCoef(i, 0, Matrice.produit(m1, r1).getCoef(i, 0));
    }
    
    verif = Vecteur.soustraction(inter, v1);
    
    System.out.println("norme L1 = " + verif.normeL1());
    System.out.println("norme L2 = " + verif.normeL2()); 
    System.out.println("norme Linfini = " + verif.normeLInfini());
    
    if(verif.normeLInfini() <= epsilon) {
      
      System.out.println("La résolution est bonne");
    } else {
      
      System.out.println("La résolution n'est pas bonne");
    }
  }

  public SysDiagonal(Matrice premMembre, Vecteur secMembre) throws IrregularSysLinException {
    
    super(premMembre, secMembre);
  }

  @Override
  public Vecteur resolution() throws IrregularSysLinException {
    
    Vecteur result = new Vecteur(this.getMatriceSystem().nbLigne());
    
    for(int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {
      
      for(int j = 0; j < this.getMatriceSystem().nbColonne(); ++j) {
        
        if(i != j && this.getMatriceSystem().getCoef(i, j) != 0) {
          
          throw new IrregularSysLinException("La matrice n'est pas diagonale.");
        }
      }
    }
    
    double matCoef, vectCoef;
    
    for(int i = 0; i < result.nbLigne(); ++i) {
      
      matCoef = this.getMatriceSystem().getCoef(i, i);
      vectCoef = this.getSecondMembre().getCoeff(i);
      result.remplaceCoef(i, 0, vectCoef/matCoef);
    }
    
    return result;
  }
}