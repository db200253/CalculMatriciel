package AlgLin;

public class Mat3Diag extends Matrice {

  public static void main(String[] args) {

    double[][] tableau = {{0,1,2,1,4},{3,2,2,1,2},{2,1,1,2,0}};
    double[] vect = {2,1,1,4,3};

    Mat3Diag m = new Mat3Diag(tableau);
    Vecteur v = new Vecteur(vect);

    System.out.println(m);
    System.out.println(v);

    Vecteur r = multVect(m, v);

    System.out.println(r);
  }

  public Mat3Diag(int dim1, int dim2) {

    super(dim1, dim2);
    assert dim1 == 3;
  }

  public Mat3Diag(double[][] tableau) {

    super(tableau);

    assert tableau.length == 3;
    assert tableau[0][0] == 0;
    assert tableau[2][tableau[2].length - 1] == 0;
  }

  public Mat3Diag(int dim) {

    super(3, dim);
  }

  public static Vecteur multVect(Mat3Diag matrice, Vecteur vect) {
    
    assert vect.getTaille() == matrice.nbColonne();
 
    Vecteur result = new Vecteur(matrice.nbColonne());
    double somme;
   
    for(int i = 0; i < matrice.nbColonne(); ++i) {
       
      somme = 0;
      
      if(i >= 1) {
        
        somme += vect.getCoeff(i - 1) * matrice.getCoef(0, i);
      }
      
      if(i < matrice.nbColonne() - 1) {
        
        somme += vect.getCoeff(i + 1) * matrice.getCoef(2, i);
      }
      
      somme += vect.getCoeff(i) * matrice.getCoef(1, i); 
      result.remplaceCoef(i, 0, somme);
    }
   
    return result;    
  }
}