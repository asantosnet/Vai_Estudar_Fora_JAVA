package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.view.orbit.BasicOrbitView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.swing.JToggleButton;

import oracle.jdeveloper.layout.XYConstraints;


public class Framee1 extends JFrame {
    //les frames pop-up
    //private FrameAdtionerManuel framaddmanuel=new FrameAdtionerManuel(); 
    //private FrameAdtionerExcel framaddexcel=new FrameAdtionerExcel();
    //les paneaux
    private PanelTest globo=new PanelTest();
    private Panelgauche gauche;
    private Google c =new Google();
    private PanelIndentification pident = new PanelIndentification();
    private JPanel background= new JPanel();
    //les choses pour les menu en haut
    private JMenuBar menurevenir = new JMenuBar();
    private JMenu menuretourner = new JMenu();
    private JMenuItem jMenuItem1 = new JMenuItem();
    private JMenu menuadd = new JMenu();
    private JMenuItem ajouterfichier = new JMenuItem();
    private JMenuItem jMenuItem3 = new JMenuItem();
    private JMenuItem jMenuItem4 = new JMenuItem();
    private JMenuItem ajoutereechange = new JMenuItem();
    private JMenuItem ajouterpays=new JMenuItem();
    private JMenuItem ajouteruniversité=new JMenuItem();
    private JButton jButton1 = new JButton();
    private Dimension frame=new Dimension();
    private JToggleButton jToggleButton1 = new JToggleButton();
    //le reste, la base de donnees e la LayerUniversite ont eté mise ici pour eviter que la classe BaseDonnéesEchanges soit lu plusier fois.
    //la meme chose pour la BaseDonneesPays et la BaseDeDonesUniversité
    private ArrayList<Echanges> tabechange=new ArrayList<Echanges>();
    private LayerUniversite layeruniversite=new LayerUniversite(tabechange);
    private BaseDonnéesEchanges bd=new BaseDonnéesEchanges();
    private ArrayList<JPanel> historique=new ArrayList<JPanel>();
    private BaseDonnéesPays bdpays=new BaseDonnéesPays();
    private BaseDeDonnéesUniversité bduni=new BaseDeDonnéesUniversité();
    //panel initial
    private java.awt.Dimension dframe = new Dimension();
    public ArrayList<JPanel> getHistorique() {
        return historique;
    }

    public void setGlobo(PanelTest globo) {
        this.globo = globo;
    }

    public PanelTest getGlobo() {
        return globo;
    }

    public void setGauche(Panelgauche gauche) {
        this.gauche = gauche;
    }

    public Panelgauche getGauche() {
        return gauche;
    }

    public void setC(Google c) {
        this.c = c;
    }

    public Google getC() {
        return c;
    }

    public void setPident(PanelIndentification pident) {
        this.pident = pident;
    }

    public PanelIndentification getPident() {
        return pident;
    }

  

    public void setJButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public JButton getJButton1() {
        return jButton1;
    }

    public void setFrame(Dimension frame) {
        this.frame = frame;
    }

    public Dimension getFrame() {
        return frame;
    }

    public void setJToggleButton1(JToggleButton jToggleButton1) {
        this.jToggleButton1 = jToggleButton1;
    }

    public JToggleButton getJToggleButton1() {
        return jToggleButton1;
    }


    public Dimension getDframe() {
        return dframe;
    }

    public void setTabechange(ArrayList<Echanges> tabechange) {
        this.tabechange = tabechange;
    }

    public ArrayList<Echanges> getTabechange() {
        return tabechange;
    }

    public void setDframe(Dimension dframe) {
        this.dframe = dframe;
    }

    public void setLayeruniversite(LayerUniversite layeruniversite) {
        this.layeruniversite = layeruniversite;
    }

    public LayerUniversite getLayeruniversite() {
        return layeruniversite;
    }


    public Framee1() {
    
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        
        //pour recupere la base de dones
        this.setTabechange(bd.getTabEch());
        System.out.println(tabechange.size());
        this.setLayeruniversite(new LayerUniversite(tabechange));
        
        //pour ajouter la couche avec les marqueur a une altitude de 1000m,il est ici pour lesses l'app moins lourde
        layeruniversite.getLayer().setMaxActiveAltitude(1150e3);
        globo.insertBeforeCompass(globo.getWwd(),layeruniversite.getLayer());
        
        //pour aditioner le premier panel a l'historique
        historique.add(pident);
        
        //pour que le panel gauche soit capable d'etre modifie sans qu'on change le code , en changeant seulement la base de donées,
        //il faut mettre comme parametres le tambe payslist,duréelistrie et languelisttrie
        //ces deux derniers de la clase basedonneechange et le premier de la basedonnepays
        gauche=new Panelgauche(bd.getDuréelistrie(),bd.getLanguelistrie());
        this.getContentPane().setLayout(new BorderLayout());
        
        //le menubar, le menu en haut
        this.setJMenuBar(menurevenir);
        
        jToggleButton1.setText("jToggleButton1");
        menuretourner.setText("Retourner");
        jMenuItem1.setText("Deconexion");
        menuadd.setText("Add");
        ajouterfichier.setText("Fichier");
        jMenuItem3.setText("Premier Fenetre");
        jMenuItem4.setText("Retourner Globe");
        ajoutereechange.setText("Echange");
        jButton1.setText("jButton1");
        ajouteruniversité.setText("Ajouter université");
        ajouterpays.setText("Ajouter pays");
        
        menuretourner.add(jMenuItem1);
        menuretourner.add(jMenuItem3);
        menuretourner.add(jMenuItem4);
        
        menuadd.add(ajouterfichier);
        menuadd.add(ajoutereechange);
        menuadd.add(ajouterpays);
        menuadd.add(ajouteruniversité);
        
        menurevenir.add(menuretourner);
        menurevenir.add(menuadd);
        //pour regler le reste de la fenetre
        this.setContentPane(background);
        background.setLayout(new BorderLayout());
        setDframe(pident.getSize());
        background.setPreferredSize(dframe);
        background.add(pident, BorderLayout.CENTER);
        
        //pour accerder au deuxieme panel
        pident.getAller().addActionListener(new Acceder(historique,this,pident,c,gauche,globo,background,pident));
        
        //pour faire la recherche de coordone
        c.getJvalider().addActionListener(new GotoCordinatesGoogle(this.tabechange,c,globo,bduni.getVillelisttrie(),bdpays.getPayslist(),bdpays.getTabPays(),bduni.getTabEch()));
        
        //pour ajouter des listener aux marqueurs
        ArrayList<Marker> markslist= layeruniversite.getMarkslist();
        
        //pour aficher quelques info sur l'echange
        for(int i=0;i<markslist.size();i++){
            globo.getWwd().addSelectListener(new RoolOverMarker(markslist,markslist.get(i).getMarker(),globo));
        }
        
        //pour acceder aux informations
        globo.getWwd().addSelectListener(new LeftClickMarker(background,globo,this,c,gauche,markslist,bdpays.getTabPays(),bduni.getTabEch()));
       
        //pour maruquer les pais que respectent les conditions
        gauche.getPanelhaut().getJButton1().addActionListener(new SelectionerPais(globo,gauche,tabechange,bdpays.getTabPays()));
        gauche.getPanelhaut().getRedemarrer().addActionListener(new SelectionerPais(globo,gauche,tabechange,bdpays.getTabPays()));
        
        //pour les listeners revenir arriere,premier fenetre, deconexion
        jMenuItem3.addActionListener(new HistoriqueListeners(this,historique,jMenuItem3,background,c,gauche,globo));
        jMenuItem1.addActionListener(new HistoriqueListeners(this,historique,background,jMenuItem1));
        jMenuItem4.addActionListener(new HistoriqueListeners(this,jMenuItem4,globo));
        
        //pour na fenetre pop-up où on vas mettre les nouvelles info sur les neuvau echange
         ajoutereechange.addActionListener(new FrameListener(new FrameAdtionerManuel(),this.tabechange,bdpays.getPayslist(),bduni.getUnivelistrie()));
         ajouterfichier.addActionListener(new FrameListener(new FrameAdtionerExcel(this.tabechange,bdpays.getPayslist(),bduni.getUnivelistrie()),this.tabechange,bdpays.getPayslist(),bduni.getUnivelistrie()));
         ajouterpays.addActionListener(new AjouterPaysUniversitéListener(bdpays.getPayslist(),null));
         ajouteruniversité.addActionListener(new AjouterPaysUniversitéListener(null,bduni.getUnivelistrie()));
    }
    


}
