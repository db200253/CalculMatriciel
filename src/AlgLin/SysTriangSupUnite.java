package AlgLin;

public class SysTriangSupUnite extends SysLin {
  
  public static void main(String[] args) throws IrregularSysLinException {
    
    double[][] t1 = {{1.0,2.0,16.0},{0.0,1.0,3.0},{0.0,0.0,1.0}};
    double[][] t2 = {{8.0,0.0,1.0},{1.0,4.0,0.0},{0.0,0.0,16.0}};
    Matrice m1 = new Matrice(t1);
    Matrice m2 = new Matrice(t2);
    
    double[] tv = {4.0,16.0,9.0};
    Vecteur v1 = new Vecteur(tv);
    
    SysTriangSupUnite s1 = new SysTriangSupUnite(m1, v1);
    SysTriangSupUnite s2 = new SysTriangSupUnite(m2, v1);
    
    Vecteur r1 = null;
    
    System.out.println("r1 (matrice correcte) : ");
    try {
      
      r1 = s1.resolution();
      System.out.println("solution r1 :\n" + r1);
    } catch (IrregularSysLinException e) {
      
      e.printStackTrace();
    }
    
    System.out.println("r2 (matrice erronée) : ");
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

  public SysTriangSupUnite(Matrice premMembre, Vecteur secMembre) throws IrregularSysLinException {
    
    super(premMembre, secMembre);
  }

  @Override
  public Vecteur resolution() throws IrregularSysLinException {
    
    Vecteur result = new Vecteur(this.getMatriceSystem().nbLigne());
    
    for(int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {
      
      for(int j = 0; j < this.getMatriceSystem().nbColonne(); ++j) {
        
        if(i > j && this.getMatriceSystem().getCoef(i, j) != 0) {
          
          throw new IrregularSysLinException("La matrice n'est pas en triangle supÃ©rieur.");
        } else if(i == j && this.getMatriceSystem().getCoef(i, j) != 1) {
          
          throw new IrregularSysLinException("La diagonale ne contient pas que des 1.");
        }
      }
    }
    
    for(int i = (int)result.getTaille()-1; i >= 0; --i) {
      
      double somme = 0;
      
      for(int j = i+1; j < (int)result.getTaille(); ++j) {
        
        somme += this.getMatriceSystem().getCoef(i, j) * result.getCoeff(j);
      }
      
      result.remplaceCoef(i, 0, (this.getSecondMembre().getCoeff(i) - somme)/this.getMatriceSystem().getCoef(i, i));
    }
    
    return result;
  }
}