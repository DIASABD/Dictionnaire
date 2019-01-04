package correcteur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;

/**
 * Cette classe MenuBar est la PLUS GRAND E DE NOTRE PROJET
 * Elle construit le JPanel principal de notre interface avec les JMenu
 * Elle defini aussi la plupart des evenement de notre classe
 * Pour cela elle etent la classe JPanel et implemente l'interface ACtionListner de java
 */


public class MenuBar extends JPanel implements ActionListener{

    JMenuBar menuBar; // Declaration d'une instance de notre Objet MenuBar
    JMenu menu1, menu2, menu3; // Instanciation de nos 3 menus de notre interface
    JMenuItem menuItemFichier1, menuItemFichier2,menuItemFichier3, menuItemFichier4;//Instanciation de nos items des menus
    JFileChooser jfc; // Declaration d'un objet JFileChooser
    JTextArea textArea ;// Declaration de nore JTextarea
    JScrollPane scrollPane; //Declaration d'un objet JSCrolpane  pour bien afficher les contenu de notre textarea
    Fichier fichier; //Declaration d'un objet Fichier
    Dictionnaire dictionnaire;// Declaration d'un objet Dictionnaire
    ArrayList<String> tableauDecar;// Declaration d'une liste de chiane de caractère
    File file; // Declarationd'un objet file pour les fichier qui seront lus
    MotProches motProches;// Declaration d'un objet de notre classe Mot proche
    DefaultListModel<String> defaultListModel =new DefaultListModel<>();// Instanciation d'un objet DefaultListModele
    // pour mettre une Jlist pour les instance
    // dans ma liste des proches

    /**
     * Constructeur qui appelle la methode create manuBar
     */
    public  MenuBar(){
        createMenuBar();
    };

    /**
     * Methode qui crées les objets declarées plus haut et les met dans mon panel principal
     * @return
     */

    public JMenuBar createMenuBar() {
        //Create the menu bar.
        tableauDecar=new ArrayList<>();
        menuBar = new JMenuBar();
        jfc = new JFileChooser();
        textArea= new JTextArea(30,30); // Dimension de mon JTextera defini à 30 x 30
        scrollPane= new JScrollPane(textArea); //  le textArea est mis dans un JTscrolpane
        //Build the menu row
        menu1 = new JMenu("Fichier");
        menu2 =  new JMenu("Dictionnaire");
        menu3 = new JMenu("Verifier");

        //Add the menu options to the menu bar
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        //menu items for main menu
        menuItemFichier1 = new JMenuItem("Ouvrir");
        menuItemFichier1.addActionListener( this);

        menuItemFichier2 = new JMenuItem("Enregistrer");
        menuItemFichier2.addActionListener(this);

        menuItemFichier3= new JMenuItem("Dictionnaire");
        menuItemFichier3.addActionListener(this);
        menuItemFichier4= new JMenuItem("Vérifié");
        menuItemFichier4.addActionListener(this);
        //add items to menus
        menu1.add(menuItemFichier1);
        menu1.add(menuItemFichier2);
        menu2.add(menuItemFichier3);
        menu3.add(menuItemFichier4);
        return menuBar;
    }

    /**
     *  Methode capitale qui gère les evenement de mon interface
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        //Handle open button action.

        String S=""; // Declaration d'un caractères pour les fichier lu
        if (e.getSource() == menuItemFichier1) {  // Le premier Item de mon menu fichier
            int returnVal = jfc.showOpenDialog(MenuBar.this);
            String lecture = null;
            FileReader  fichierChoisi ;

            // int returnVal = jfc.showSaveDialog(MenuBar.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    file = new File(jfc.getSelectedFile().getPath());
                    fichierChoisi = new FileReader(file);
                    BufferedReader br = new BufferedReader(fichierChoisi);
                    lecture = br.readLine();
                    while (lecture != null) { // lecture de mon fichier et creation d'une chaine de caractere
                        S +=lecture +"\n";
                        lecture = br.readLine();
                    }
                    S= new String(S.getBytes(),Charset.forName("UTF-8")); // Ecriture en UTF-8
                    textArea.append(S); // Le cahine de caratère lu est mise dans mon textArea
                    fichierChoisi.close();
                } catch (Exception exep){}

            }

            else {    }
        }
        if (e.getSource() == menuItemFichier2) { // Deuxième Item de mon menu fichier

            int returnVal = jfc.showSaveDialog(MenuBar.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fileWriter;
                    String areaText=textArea.getText(); // On recupere le text de notre texArea
                    areaText= new String(areaText.getBytes(),Charset.forName("UTF-8"));
                    jfc.getSelectedFile(); // Choisir le fichier selectionner dans le reprtoire
                    fileWriter=new FileWriter(file.getPath());
                    PrintWriter pw=new PrintWriter(fileWriter);
                    for(int i =0;i<areaText.length();i++){
                        pw.print(areaText.charAt(i)); // Ecrire le fichier dans mon textArea
                    }
                    pw.close();
                } catch (Exception exep){}

            }
            else {}
        }

        if(e.getSource()==menuItemFichier3){ // Construire mon dictionnaire

            int returnVal = jfc.showOpenDialog(MenuBar.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                jfc.getSelectedFile();

                try {
                    file = new File(jfc.getSelectedFile().getPath());
                    fichier= new Fichier(file.getName());
                    dictionnaire= new Dictionnaire(fichier);
                    dictionnaire.construireDictionnaire(fichier,dictionnaire); //  le dictionnaire est construit
                    // à partir de fichier
                }
                catch (Exception exep){}
            }
        }
        if(e.getSource()==menuItemFichier4){  // Evenement qui lance l'option verifier
            int returnVal = jfc.getApproveButtonMnemonic();
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String lecture= textArea.getText();
                    tableauDecar=fichier.listDeMot(lecture);
                    for(String string:tableauDecar){
                        if(!new Mot(string,fichier).estConnu(string,dictionnaire)){
                            this.highlight(this.textArea, string); // colores les mot inexistant dans le dictionnaire
                        }
                    }
                }
                catch (Exception e1){}            }

            textArea.addMouseListener( new MouseAdapter()
            { // Defini les evenements concernant mon JTextArea
                public void mouseClicked(MouseEvent e)
                {
                    if ( SwingUtilities.isLeftMouseButton(e) )
                    {
                        try
                        {
                            int offset = textArea.viewToModel( e.getPoint() );

                            int start = Utilities.getWordStart(textArea, offset);
                            int end = Utilities.getWordEnd(textArea, offset);
                            String word = textArea.getDocument().getText (start, end-start);
                            motProches = new MotProches( new Mot(word,fichier),dictionnaire);

                            Set<Map.Entry<Mot, List<Mot>>> list= motProches.listDesProche().entrySet();
                            Iterator<Map.Entry<Mot, List<Mot>>> iterator= list.iterator();

                            while (iterator.hasNext()) {
                                List<Mot> list1 = iterator.next().getValue();
                                Iterator <Mot> iterator1= list1.listIterator();
                                while (iterator1.hasNext()){
                                    String motChanger =iterator1.next().mot;
                                    defaultListModel.addElement(motChanger);// Mettre mes mot dans mon DefautListModel
                                }
                                break;
                            }
                            JList<String> jList=new JList<>(defaultListModel);
                            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            JOptionPane.showMessageDialog(null,jList);
                            String choixMot=jList.getSelectedValue();
                            jList.addListSelectionListener(new ListSelectionListener() {
                                @Override
                                public void valueChanged(ListSelectionEvent e) {
                                    textArea.replaceRange(choixMot,start,start+word.length());

                                    removeHighlights((JTextComponent) JTextComponent.removeKeymap(choixMot));
                                }
                            });
                        }
                        catch (Exception e2) {}
                    }
                    defaultListModel.clear(); // Vider DefaultListModel si je change de mot dans mon textArea
                }
            } );

        }
    }

    public void highlight(JTextComponent textComp, String pattern) {
        // First remove all old highlights
        // removeHighlights(textComp);
        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos+pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }
        } catch (Exception e) {
        }
    }
    // Removes only our private highlights
    public void removeHighlights(JTextComponent textComp) {

        try {
            Highlighter hilite = textComp.getHighlighter();
            Highlighter.Highlight[] hilites = hilite.getHighlights();

            for (int i=0; i<hilites.length; i++) {
                if (hilites[i].getPainter() instanceof MyHighlightPainter) {
                    hilite.removeHighlight(hilites[i]);
                }
            }
        }
        catch (Exception e){}

    }
    // An instance of the private subclass of the default highlight painter
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);
}














