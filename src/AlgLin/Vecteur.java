package AlgLin;

import java.io.IOException;

public class Vecteur extends Matrice {
  
  public Vecteur(int entier) {
    
    super(entier, 1);
  }
  
  public Vecteur(double[] tab) {
    
    super(new double[tab.length][1]);
    
    for(int i = 0; i < tab.length; ++i) {
      
      this.coefficient[i][0] = tab[i];
    }
  }
  
  public Vecteur(String fichier) {
    
    super(fichier);
  }
  
  public double getTaille() {
    
    return nbLigne();
  }
  
  public double getCoeff(int coef) {
    
    return getCoef(coef, 0);
  }
  
  public void remplaceCoef(int ligne, int colonne, double value) {
    
    remplacecoef(ligne, 0, value);
  }
  
  public String toString() {
    
    int ligne = this.nbLigne();
    String matr = "";
    for(int i = 0; i<ligne;i++){
      matr += this.getCoef(i, 0);
      matr += "\n";
    }
    return matr;
  }
  
  public static double produit1(Vecteur v1, Vecteur v2) throws IOException{
    
    int result = 0;
    
    if(v1.getTaille() != v2.getTaille()) throw new IOException();
    
    for(int i = 0; i < v1.getTaille(); ++i) {
      
      result += v1.getCoeff(i)*v2.getCoeff(i);
    }
    
    return result;
  }
  
  public static double produit2(Vecteur v1, Vecteur v2) {
    
    int result = 0;
    
    for(int i = 0; i < v1.getTaille(); ++i) {
      
      result += v1.getCoeff(i)*v2.getCoeff(i);
    }
    
    return result;
  }
  
  public static Vecteur soustraction(Vecteur v1, Vecteur v2) {
    
    assert v1.getTaille() == v2.getTaille();
    
    Vecteur result = new Vecteur((int)v1.getTaille());
    
    for(int i = 0; i < v1.getTaille(); ++i) {
      
      result.remplaceCoef(i, 0, v1.getCoeff(i) - v2.getCoeff(i));
    }
    
    return result;
  }
  
  public double normeL1() {
    
    double norme = 0;
    
    for(int i = 0; i < getTaille(); ++i) {
      
      norme += Math.abs(getCoeff(i));
    }
    
    return norme;
  }
  
  public double normeL2() {
    
    double norme = 0;
    
    for(int i = 0; i < getTaille(); ++i) {
      
      norme += Math.pow(getCoeff(i), 2);
    }
    
    return Math.sqrt(norme);
  }
  
  public double normeLInfini() {
    
    double max = getCoeff(0);
    
    for(int i = 0; i < getTaille(); ++i) {
      
      if(max < Math.abs(getCoeff(i))) {
        
        max = Math.abs(getCoeff(i));
      }
    }
    
    
    return max;
  }
  
  /*public static void main(String[] args) throws Exception {
    double tab[]= {1, 12, 8};
    Vecteur a = new Vecteur(tab);
    System.out.println("construction d'un vecteur par affectation d'un tableau :\n"+a);
    Vecteur b = new Vecteur("/home/etudiant/db200253/Documents/Cours/L3/Eclipse/CalculMatriciel/src/AlgLin/vect1.txt");
    System.out.println("Construction d'un vecteur par lecture d'un fichier :\n"+b);
    Vecteur c = new Vecteur(3);
    System.out.println("Construction d'un vecteur par sa taille : ");
    System.out.println(c);
    c.remplaceCoef(0, 0, 8);
    c.remplaceCoef(1, 0, 3);
    System.out.println("nouvelle valeur de c : ");
    System.out.println(c);
    c.recopie(b);
    System.out.println("Coefficient (2) du vecteur b : "+b.getCoeff(1));
    System.out.println("Nouvelle valeur de ce coefficient : 8");
    b.remplacecoef(1, 0, 8);
    System.out.println("Vérification de la modification du coefficient");
    System.out.println("Coefficient (2) du vecteur c : "+b.getCoeff(1));
    double result = produit1(a, b);
    System.out.println("Vérification produit scalaire du vecteur a*b : " + result);
  }*/
}