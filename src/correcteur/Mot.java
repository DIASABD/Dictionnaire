package correcteur;

import java.util.Iterator;

/**
 *  Cette classe crée un objet Mot
 * Ecrit par l'equipe de DIASSO ABDRAMANE : Matricule 200575113 ET VINCENT Le Bourdais Goss: Matricule 20069052
 *  */

public class Mot  {
    String mot;
    Fichier fichier;
    int distance;

    /**
     * Constructeur de la classe qui prend deux paramètres
     * @param mot: caractère reprensentant le mot
     * @param fichier: fichier dont est lié le mot
     */
    public Mot(String mot, Fichier fichier) {
        this.mot = mot;
        this.fichier = fichier;
        this.distance=0;
    }

    /**
     * Methode qui retourne une valeur booleenne indiquant si un mot est conténu ou pas dans un dictionnaire
     * Cette methode prend deux paramètre : le mot et le dictionnaire de reference pour la verification
     * @param _mot: le mot à verifier
     * @param dictionnaire: le dictionnaire de reference
     * @return
     */
    public  boolean estConnu( String _mot,Dictionnaire dictionnaire){
        boolean connu= false;
        Iterator<Mot> iterator =dictionnaire.listDictionnaire.listIterator();// Iterateur servant pour l'iteration sur la liste
        while (iterator.hasNext()){
            Mot suivant= iterator.next();
            if(suivant.mot.compareTo(_mot)==0){
                connu= true;
                break;
            }
        }
        return connu;
    }
}

