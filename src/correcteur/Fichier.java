package correcteur;


import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Classe qui crée un objet fichier
 * Precisement elle lit un fichier et le stock dans une liste
 * Ecrit par l'equipe de DIASSO ABDRAMANE : Matricule 200575113 ET VINCENT Le Bourdais Goss: Matricule 20069052
 * */

public class Fichier {

    // attribut de classe
    String nom_Fichier;

    /**
     * Constructeur de la classe qui prend un paramètre.
     * @param nom_Fichier: paramètre qui est le nom du fichier à lire dans le repertoire
     */
    public Fichier(String nom_Fichier) {
        this.nom_Fichier = nom_Fichier;
    }

    /**
     * Methode qui prend en paramètre une texte lu et  returne une liste de mot qui compose le texte
     * @param lecture: paramètre representant la chaine lue
     * @return
     */
    public ArrayList<String> listDeMot(String lecture) {

        ArrayList<String> resultat = new ArrayList<>();// Creation d'un objet list
        char[] tab= lecture.toCharArray();
        String mot = new String();
        int index=0;
        // Determiner la limite d'un mot
        while (index < lecture.length() ) {
            char carac= tab[index];
            if(carac == ','|| carac == '.'|| carac== ';'|| carac == ':'|| carac== '?'|| carac== '!' ||carac == ' ') {
                mot=new String(mot.getBytes(), Charset.forName("UTF-8"));
                resultat.add(mot);
                mot= new String();
            }
            else {
                mot += lecture.charAt(index);
            }
            index++;
        }
        resultat.add(mot);
        return resultat;

    }
}
