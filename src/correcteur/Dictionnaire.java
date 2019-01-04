package correcteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import  java.util.LinkedList;

/**
 * Classe Dictionnaire. Cette classe une liste  chainée de Mot   reprantant notre dictionnaire de vérification.
 * Cet dictionnaire est construit en prenant un paramètre de type fichier
 * Cette classe implemente l'interface Comparator pour nous permettre de trier les Objet Mot dans notre liste
 */

public class Dictionnaire implements Comparator< Mot> ,ActionListener{


    Fichier fichier;// Attribut objet fichier
    List<Mot> listDictionnaire;// liste de dictionnaire

    public Dictionnaire(Fichier fichier) {
        this.fichier= fichier;
        this.listDictionnaire= new LinkedList<>();
    }

    /**
     * mehode qui constaruit notre liste chainée  de Mot
     * @param fichier
     * @param dictionnaire
     */
    public void construireDictionnaire (Fichier fichier,Dictionnaire dictionnaire){

        FileReader fichierEntrees ;// Declaration objet Filereader
        String lecture ;// Declaration d,un chaine de caractère pour accumuler les caractère lu dans le fichier
        try {
            fichierEntrees = new FileReader(fichier.nom_Fichier);
            BufferedReader br = new BufferedReader(fichierEntrees);

            while ((lecture=br.readLine())!=null){
                int index=0;
                String mot  = new String();
                //  Limite d'un mot
                while ( index<lecture.length()-1){
                    if(lecture.charAt(index)!=','&&lecture.charAt(index)!='.'&&lecture.charAt(index)!=';'&&
                            lecture.charAt(index)!=':'&&lecture.charAt(index)!='?'&&lecture.charAt(index)!='!'&&lecture.charAt(index)!=' '){
                        mot+=lecture.charAt(index);
                    }
                    else {
                        Mot nouveauMot= new Mot(mot,fichier);
                        dictionnaire.listDictionnaire.add(nouveauMot);

                        mot  = new String();
                    }
                    index ++;
                }
            }
            fichierEntrees.close();
        }
        catch (IOException e) {}

        dictionnaire.listDictionnaire.sort(dictionnaire);  // Trier la liste obtenue à la fin
    }

    @Override
    public int compare(Mot mot1, Mot mot2) {
        return mot1.mot.compareTo(mot2.mot);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
