package correcteur;

import javax.swing.text.DefaultHighlighter;
import java.awt.*;

/**
 * Ecrit par l'equipe de DIASSO ABDRAMANE : Matricule 200575113 ET VINCENT Le Bourdais Goss: Matricule 20069052
 */


// A private subclass of the default highlight painter
class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
{
    public MyHighlightPainter(Color color) {
        super(color);
    }
}
