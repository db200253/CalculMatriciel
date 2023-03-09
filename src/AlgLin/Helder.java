package AlgLin;

public class Helder extends SysLin {
  
  public static void main(String[] args) throws IrregularSysLinException {
    
    Matrice base = new Matrice("../CalculMatriciel/src/AlgLin/matBase.txt");
    Vecteur secMembre = new Vecteur("../CalculMatriciel/src/AlgLin/vectSec.txt");
   
    Matrice mat = Matrice.produit(base, base);
    
    System.out.println("Matrice de base :");
    System.out.println(mat);
    System.out.println();
    System.out.println("Second membre :");
    System.out.println(secMembre);
    
    Helder sys1 = new Helder(mat, secMembre);
    
    sys1.factorLDR();
    
    Vecteur result1 = sys1.resolutionPartielle();
    
    System.out.println("En utilisant la resolution partielle aprés factorisation :");
    System.out.println(result1);
    
    Helder sys2 = new Helder(mat, secMembre);
    
    Vecteur result2 = sys2.resolution();
    
    System.out.println("En utilisant la resolution simple :");
    System.out.println(result2);
    
    Vecteur secMembre2 = new Vecteur("../CalculMatriciel/src/AlgLin/vectSec2.txt");
    sys2.setSecondMembre(secMembre2);
    
    System.out.println("Nouveau second membre : ");
    System.out.println(secMembre2);
    System.out.println("Résolution de ce nouveau système : ");
    System.out.println(sys2.resolution());
  }
  
  Matrice l, d, r;
  
  public Helder(Matrice premMembre, Vecteur secMembre) throws IrregularSysLinException {
    
    super(premMembre, secMembre);
    
    l = new Matrice(premMembre.nbLigne(), premMembre.nbColonne());
    d = new Matrice(premMembre.nbLigne(), premMembre.nbColonne());
    r = new Matrice(premMembre.nbLigne(), premMembre.nbColonne());
  }
  
  public void factorLDR() {
    
  Matrice u = new Matrice(this.getMatriceSystem().nbLigne(), this.getMatriceSystem().nbColonne());
    
  for(int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {
    
    for(int j = 0; j < this.getMatriceSystem().nbLigne(); ++j) {
            
      u.remplacecoef(i, j, this.getMatriceSystem().getCoef(i, j));
    }
  }
  
  for(int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {
    
    l.remplacecoef(i, i, 1);
    
    for(int j = i+1; j < this.getMatriceSystem().nbLigne(); ++j) {
      
      double facteur = u.getCoef(j, i) / u.getCoef(i, i);
      
      l.remplacecoef(j, i, facteur);
      
      for(int k = i; k < this.getMatriceSystem().nbLigne(); k++) {
        
        u.remplacecoef(j, k, u.getCoef(j, k) - facteur*u.getCoef(i, k));
      }
    }
  }
  
  for(int i = 0; i < this.getMatriceSystem().nbLigne(); ++i) {
    
    d.remplacecoef(i, i, u.getCoef(i, i));
    
    for(int j = i; j < this.getMatriceSystem().nbLigne(); ++j) {
      
      r.remplacecoef(i, j, u.getCoef(i, j)/d.getCoef(i, i));
    }
  }
  
  System.out.println("Matrice L : ");
  System.out.println(l);
  System.out.println("Matrice D : ");
  System.out.println(d);
  System.out.println("Matrice R : ");
  System.out.println(r);
  
  Matrice a = new Matrice(this.getMatriceSystem().nbLigne(), this.getMatriceSystem().nbLigne());
  a = Matrice.produit(l, Matrice.produit(d, r));
  
  System.out.println("Résultat des multiplications des matrices L, D et R entre elles : ");
  System.out.println(a);
  }
  
  @Override
  public Vecteur resolution() throws IrregularSysLinException {
    
    factorLDR();
    
    SysTriangInfUnite si = new SysTriangInfUnite(l, this.getSecondMembre());
    Vecteur inter1 = si.resolution();
    SysDiagonal sd = new SysDiagonal(d, inter1);
    Vecteur inter2 = sd.resolution();
    SysTriangSupUnite ss = new SysTriangSupUnite(r, inter2);
    Vecteur resultat = ss.resolution();
    
    return resultat;
  }
  
  public Vecteur resolutionPartielle() throws IrregularSysLinException {
    
    SysTriangInfUnite si = new SysTriangInfUnite(l, this.getSecondMembre());
    Vecteur inter1 = si.resolution();
    SysDiagonal sd = new SysDiagonal(d, inter1);
    Vecteur inter2 = sd.resolution();
    SysTriangSupUnite ss = new SysTriangSupUnite(r, inter2);
    Vecteur resultat = ss.resolution();
    
    return resultat;
  }
  
  public void setSecondMembre(Vecteur newSecondMembre) throws IrregularSysLinException {
    
    if(this.getSecondMembre().getTaille() != newSecondMembre.getTaille()) {
      
      throw new IrregularSysLinException("La taille des deux vecteurs n'est pas correspondante");
    }
    
    for(int i = 0; i < this.getSecondMembre().getTaille(); ++i) {
      
      this.getSecondMembre().remplaceCoef(i, 0, newSecondMembre.getCoeff(i));
    }
  }
}