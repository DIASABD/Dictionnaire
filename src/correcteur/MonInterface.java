package correcteur;

import javax.swing.*;

/**
 * Classe qui construit notre interface principal
 * Elle construit un objet JFrame contenant un objet MenuBar
 * Ecrit par l'equipe de DIASSO ABDRAMANE : Matricule 200575113 ET VINCENT Le Bourdais Goss: Matricule 20069052
 */


public class MonInterface
{
    MenuBar mb; // Declaration d'un objet MenuBar
    JPanel panelPrincipal = new JPanel();// Creation d'un objet JPanel
    JFrame frame; //Declaration d'un objet JFrame

    public  MonInterface(){
        frame= new JFrame("Mon INTERFACE");
        frame.setSize(450, 450);
        //Create and set up the content pane.
        mb= new MenuBar();
        frame.setJMenuBar(mb.createMenuBar());
        panelPrincipal.add(mb.scrollPane);
        frame.setContentPane(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
