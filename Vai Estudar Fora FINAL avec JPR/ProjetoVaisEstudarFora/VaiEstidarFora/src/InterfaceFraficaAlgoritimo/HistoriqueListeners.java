package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**
 * La calsse responsable pour la deconexion, revenir arriere , revenir le panel principal(celle avec le panel où l'utilisateur peut choisir le echange)
 * et pour revenir le globe  a une altitude où l'utilisateur peut voir tout
 * A FAIRE-CORRIGER LE BUG Où QUAND J'APPUIE SUR DECONECTION E JE REVIEN O PANEL CHOIX? LE GLOBE MONTRE TOUS LES ECHANGES QUI ONT éTE CHOSI AVANT
 */
public class HistoriqueListeners implements ActionListener {
    private ArrayList<JPanel> historique=new ArrayList<JPanel>();
    private JMenuItem jMenuItem1 = new JMenuItem();
    private JMenuItem jMenuItem4 = new JMenuItem();
    private JMenuItem jMenuItem3 = new JMenuItem();
    private JPanel background=new JPanel();
    private JFrame fenetre=new JFrame();
    private Google c=new Google();
    private Panelgauche pgauche;
    private PanelTest globe=new PanelTest();
     /**
     *les methodes nescessaires quand on veut metre le listener dans jmenuitem3
     * @param fenetre
     * @param historique
     * @param jMenuItem3
     * @param background
     * @param c
     * @param pgauche
     * @param globe
     */
    public HistoriqueListeners(JFrame fenetre,ArrayList<JPanel> historique,JMenuItem jMenuItem3,JPanel background,Google c,Panelgauche pgauche,PanelTest globe) {
        this.historique=historique;
        this.background=background;
        this.jMenuItem1=null;
        this.jMenuItem3=jMenuItem3;
        this.jMenuItem4=null;
        this.fenetre=fenetre;
        this.c=c;
        this.globe=globe;
        this.pgauche=pgauche;
    }
     /**
     * les parametres necessaires quand on veut mettre listener dasn jmeuitem1
     * @param fenetre
     * @param historique
     * @param background
     * @param jMenuItem1
     */
    public HistoriqueListeners(JFrame fenetre,ArrayList<JPanel> historique,JPanel background,JMenuItem jMenuItem1){
        this.historique=historique;
        this.jMenuItem1=jMenuItem1;
        this.jMenuItem3=null;
        this.jMenuItem4=null;
        this.background=background;
        this.fenetre=fenetre;
    }
     /**
     *parametres necessaires quand on veut mettre un listener dans jMenuItem4
     * @param fenetre
     * @param jMenuItem4
     * @param globo
     */
     public HistoriqueListeners(JFrame fenetre,JMenuItem jMenuItem4,PanelTest globo){
        this.jMenuItem4=jMenuItem4;
        this.jMenuItem3=null;
        this.jMenuItem1=null;
        this.fenetre=fenetre;
        this.globe=globo;
        
    } 

    public void actionPerformed(ActionEvent actionEvent) {
            System.out.println(historique.size());
        //pour voir quell actionn a éte chosi
        if(jMenuItem1!=null){
            background.removeAll();
            background.setLayout(new BorderLayout());
            background.add(historique.get(0),BorderLayout.CENTER);
            background.setPreferredSize(new Dimension(500,500));
            background.repaint();
            fenetre.repaint();
            fenetre.setSize(new Dimension(350,200));
        }else if(jMenuItem3!=null){
            PanelAcceder pacceder=new PanelAcceder(c,pgauche,globe);
            background.removeAll();
            background.setLayout(new BorderLayout());
            background.add(pacceder);
            background.setPreferredSize(new Dimension(500,500));
            background.repaint();
            fenetre.repaint();
            fenetre.setSize(new Dimension(700,700));
        }else if(jMenuItem4!=null){
            BasicOrbitView view = (BasicOrbitView)globe.getWwd().getView();
            //pour s'eloigner d globe
            view.addPanToAnimator(new Position(Angle.fromDegrees(50),Angle.fromDegrees(50), 0),Angle.ZERO, Angle.ZERO,15000e3);
            }
    }
        
        
}

