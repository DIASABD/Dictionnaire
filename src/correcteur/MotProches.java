package correcteur;

import java.util.*;

/**
 * Cette classe definie les mots  du dictionnaires qui sont proche de notre Mot du textarea
 * Elle utlise un file  de priorité pour determiner les 5 mots les plus proches
 * Elle utlise aussi un table de hachage pour lié cahque  liste de mots proches à son objet Mot
 *    Cette classe implemente l'interface Comparator pour nous permettre de trier les Objet Mot dans file
 */

public class MotProches implements Comparator<Mot> {

    private Mot mot; // Declaration attribut de l'objet Mot
    private Dictionnaire dictionnaire;// Declaration attribut de l'objet Dictionnaire
    PriorityQueue<Mot> priorityQueue;// Declaration attribut de l'objet PriorityQueue


    private HashMap<Mot,List<Mot>> motsPlusProche; // Declaration d,une table de hachage
    List<Mot> l;// Declaration dune liste de Mot

    /**
     * Constructeur de notre classe qui prend en paramètre un mot et notre dictionnaire de reference
     * Ce constructeur prend en paramètre un motre et construt la liste des mots les plus proches
     * @param mot:
     * @param dictionnaire
     */
    public MotProches(Mot mot, Dictionnaire dictionnaire) {
        this.mot = mot;
        this.dictionnaire = dictionnaire;
        l=new ArrayList<>();
        this.motsPlusProche= new HashMap();
        this.priorityQueue = new PriorityQueue<>(dictionnaire.listDictionnaire.size(),this);
    }

    /**
     * Methode fournie par le Prof qui determine la distance entre deux mots.
     * @param s1
     * @param s2
     * @return
     */
    public static int distance(String s1, String s2){
        int edits[][]=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++)
            edits[i][0]=i;
        for(int j=1;j<=s2.length();j++)
            edits[0][j]=j;
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
                edits[i][j]=Math.min(
                        edits[i-1][j]+1,
                        Math.min(
                                edits[i][j-1]+1,
                                edits[i-1][j-1]+u
                        )
                );
            }
        }
        return edits[s1.length()][s2.length()];
    }

    /**
     * Methode qui construit notre file de priorité
     */
    public  void listPQ(){
        Iterator<Mot> iterator = dictionnaire.listDictionnaire.listIterator();

        while (iterator.hasNext()){
            Mot mot =iterator.next();
            if(this.mot.mot.compareTo(mot.mot)!=0){
                mot.distance=distance(this.mot.mot,mot.mot);
                priorityQueue.add(mot);

            }
        }
    }

    /**
     * Methode qui construit notre table de Hachage . Cette utilise les objet Mot et Liste de Mot initialiser
     * dans notre consteure
     * @return
     */
    public HashMap<Mot,List<Mot>> listDesProche(){
        this.listPQ();
        int nbElement=0;
        while (!this.priorityQueue.isEmpty()&&nbElement<5){ // On se limite d'ajouter dans notre liste les 5
            // premier elements sortie de notre file de priorité
            l.add(priorityQueue.poll());
            nbElement++;
        }
        motsPlusProche.put(mot,l);
        return motsPlusProche;
    }

    @Override
    public int compare(Mot mot1, Mot mot2) {
        return Integer.compare(mot1.distance,mot2.distance);
    }

}
