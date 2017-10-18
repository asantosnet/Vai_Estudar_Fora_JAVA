package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import java.awt.TextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
/**
 * Le panel o� il y a l'information sur les echanges 
 */
public class PanelInfoUniv extends JPanel{
    private GridBagLayout layout = new GridBagLayout();
    private JLabel universite = new JLabel();
    private JSeparator univedepart = new JSeparator();
    private JTextPane departements=new JTextPane();
    private JScrollPane departementsscroll=new JScrollPane();
    private JSeparator departinfo=new JSeparator();
    private JTextPane informationpays=new JTextPane();
    private JScrollPane inoformationspayscroll=new JScrollPane();
    private JTextPane informationuniversite=new JTextPane();
    private JScrollPane inoformationuniversitescroll=new JScrollPane();
    private JButton zommunive = new JButton();
    private JButton zommpays = new JButton();
    private JButton changerdescpays=new JButton();
    private JButton changerdescuniversit�=new JButton();
    private JButton changerurl=new JButton();
    private JSeparator infonote = new JSeparator();
    private JSeparator plusinfo = new JSeparator();     
    private JSeparator entrebuttons = new JSeparator();
    private JLabel separator=new JLabel();
    
    private String universit�nom=new String();
    private Marker marker;
    private ArrayList<Pays> payslist=new ArrayList<Pays>();
    private ArrayList<Universit�> universitelist=new ArrayList<Universit�>();
    
    public PanelInfoUniv(String universit�nom, Marker marker,ArrayList<Pays> payslist,ArrayList<Universit�> universitelist) {
        //pour recupere le paisuniversit� et le donn�s du marker
        this.universit�nom=universit�nom;
        this.universitelist=universitelist;
        this.payslist=payslist;
        this.setLayout(layout);
        this.marker=marker;
        //on utilise chaque contraint pour chaque element du jpanel pour qu'il n'aye pas de bug
         GridBagConstraints a = new GridBagConstraints();
         GridBagConstraints  b= new GridBagConstraints();
         GridBagConstraints c = new GridBagConstraints();
         GridBagConstraints d= new GridBagConstraints();
         GridBagConstraints e = new GridBagConstraints();
         GridBagConstraints f = new GridBagConstraints();
         GridBagConstraints g = new GridBagConstraints();
         GridBagConstraints h = new GridBagConstraints();
         GridBagConstraints l = new GridBagConstraints();
         GridBagConstraints m = new GridBagConstraints();
         GridBagConstraints n = new GridBagConstraints();
         GridBagConstraints o = new GridBagConstraints();
         GridBagConstraints p = new GridBagConstraints();
        GridBagConstraints q = new GridBagConstraints();
        GridBagConstraints s = new GridBagConstraints();
        GridBagConstraints t= new GridBagConstraints();
         
         informationpays.setBackground(Color.WHITE);
         //pour dire le nom de l'universite
         universite.setText(universit�nom);
        
         //pour dire le departements qu'on peut faire l'echange et la dur�ee associe a chaq'un
         SimpleAttributeSet center = new SimpleAttributeSet();
         StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
         departements.setEditable(false);
         departements.setText(this.departplusdure());
         departements.setParagraphAttributes(center,false);
         departements.setBackground(Color.lightGray);
         departementsscroll=new JScrollPane(departements);
         departementsscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         departementsscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         
         
         //pour que l'utilisatuer ne puisse pas changer la descripition
         informationpays.setEditable(false);
         
         //pour ecrire la descrpition du  pays
         informationpays.setText("INFORMATION SUR LE PAYS "+marker.getPays()+" : "+this.recuperedescrpitionpays());
         
         //pour mettre l'information dans un secrolbar pane
         inoformationspayscroll = new JScrollPane(informationpays);
         inoformationspayscroll.setVerticalScrollBarPolicy(
                         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         inoformationspayscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         
         //pour mettre l'information sur l'universite
         String [] nomlist2=universit�nom.split(",");
         String nom2=nomlist2[1];
         informationuniversite.setEditable(false);
         informationuniversite.setText("INFORMATION SUR LA UNIVERSITE  "+nom2+"  : "+this.recuperadescrptionuniversite(universit�nom));
         inoformationuniversitescroll=new JScrollPane(this.informationuniversite);
         inoformationuniversitescroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         inoformationuniversitescroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        zommunive.setText("ZommUniversit�");
        zommpays.setText("ZommPays");
        changerdescpays.setText("Changer desc pays");
        changerdescuniversit�.setText("Changer desc universit�");
        changerurl.setText("Changer Url");
        //pour ouvir une fenetre avec le text avec l'information de l'universite
        zommunive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plusinfo_actionPerformed(e);
            }
        });
        
        //pour ouvrir une fenetre avec le text avec l'information du pays
        zommpays.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               plusinfo_actionPerformed(e);
           }
        });
        
        //pour ouvrir un fenetre pour modifier information pays
        changerdescpays.addActionListener(new ModifierBaseDeDon�esListener(marker.getPays(),null,this.recuperedescrpitionpays(),null));
        
        //pour ouvrir un fenetre pour modifier information universit�
        changerdescuniversit�.addActionListener(new ModifierBaseDeDon�esListener(null,nom2,this.recuperadescrptionuniversite(this.universit�nom),null));
        
        //pour ouvrir un fenetre pour modifier url
        changerurl.addActionListener(new ModifierBaseDeDon�esListener(null,nom2,null,this.recupererurl(nom2)));
        
        //interface
         //pour le premier composant
         a.fill=GridBagConstraints.BOTH;
         a.weighty=0.4;
         a.gridwidth = 3;
         a.gridx = 0;
         a.gridy = 0;
         this.add(universite, a);
         //pour le deuxieme
         b.fill=GridBagConstraints.BOTH;
         b.weighty=0.1;
         b.gridwidth = 3;
         b.gridx = 0;
         b.gridy = 1;
         this.add(univedepart, b);
         o.fill=GridBagConstraints.BOTH;
         o.weighty=0.6;
         o.gridwidth = 3;
         o.gridx = 0;
         o.gridy = 2;
         this.add(departementsscroll,o);
         n.fill=GridBagConstraints.BOTH;
         n.weighty=0.1;
         n.gridwidth = 3;
         n.gridx = 0;
         n.gridy = 3;
         this.add(this.departinfo,n);
         c.fill=GridBagConstraints.BOTH;
         c.weighty=1.5;
         c.gridwidth = 3;
         c.gridx = 0;
         c.gridy = 4;
         this.add(inoformationspayscroll, c);
         this.add(this.departinfo,n);
         p.fill=GridBagConstraints.BOTH;
         p.weighty=1;
         p.gridwidth = 3;
         p.gridx = 0;
         p.gridy = 5;
         this.add(inoformationuniversitescroll, p);
         d.fill=GridBagConstraints.BOTH;
         d.weighty=0.1;
         d.gridwidth = 3;
         d.weightx = 2;
         d.gridx = 0;
         d.gridy = 6;
         this.add(infonote, d);
         e.fill=GridBagConstraints.BOTH;
         e.weighty=0.1;
         e.weightx = 0.3;
         e.gridx = 2;
         e.gridy = 7;
         this.add(zommunive, e);
         f.fill=GridBagConstraints.BOTH;
         f.weighty=0.1;
         f.weightx = 0.3;
         f.gridwidth=1;
         f.gridx = 0;
         f.gridy = 7;
         this.add(zommpays, f);
         m.fill=GridBagConstraints.BOTH;
         m.weighty=0.1;
         m.weightx = 0.3;
         m.gridwidth=1;
         m.gridx = 1;
         m.gridy = 7;
         this.add(separator, m);
         g.fill=GridBagConstraints.BOTH;
         g.weightx=0.2;
         g.weighty=0.2;
         g.gridwidth=1;
         g.gridx=1;
         g.gridy = 9;
         this.add(plusinfo, g);
         q.fill=GridBagConstraints.BOTH;
         q.weightx = 0.4;
         q.weighty=0.25;
         q.gridwidth = 1;
         q.gridx = 0;
         q.gridy = 11;
         this.add(changerdescpays, q);
         s.fill=GridBagConstraints.BOTH;
         s.weightx = 0.4;
         s.weighty=0.25;
         s.gridwidth = 1;
         s.gridx = 1;
         s.gridy = 11;
         this.add(changerdescuniversit�, s);
         t.fill=GridBagConstraints.BOTH;
         t.weightx = 0.4;
         t.weighty=0.25;
         t.gridwidth = 1;
         t.gridx = 2;
         t.gridy = 11;
         this.add(changerurl, t);
         h.fill=GridBagConstraints.BOTH;
         h.weightx = 0.3;
         h.weighty=0.3;
         h.gridwidth = 3;
         h.gridx = 0;
         h.gridy = 12;
         this.add(entrebuttons, h);
        
    }
    /**
     *methode responsable pour recupere la description du pays 
     * @param paisuniversite
     * @return
     */
    public String recuperedescrpitionpays(){
        String description=new String();
        boolean hasnotfoundpays=true;
        //pour recuperer l'information sur le pays
        int i=0;
        while(hasnotfoundpays&&i<payslist.size()){
            if(marker.getPays().equals(payslist.get(i).getNom())){
                description=payslist.get(i).getDescription();
                hasnotfoundpays=false;
            }
            i++;
        } 
        return description;   
    }
    /**
     *methode responsable pour recupere la description de l'universite
     * @param universite
     * @return
     */
    public String recuperadescrptionuniversite(String universite){
        //le premier terme est le pays le deuxi�me est l'universite;
        String[] paisuniversitelist = new String[4];
        paisuniversitelist=universite.split(",");
        String description=new String();
        boolean hasnotfounduniversite=true;
        //pour recuperer les informations sur la universti�
        int b=0;
        while(hasnotfounduniversite&&b<universitelist.size()){
            if(paisuniversitelist[1].equals(universitelist.get(b).getUniversit�())){
                description=universitelist.get(b).getDescription();
                hasnotfounduniversite=false;
            }
            b++;
        }
        return description;
    }
    /**
     *ce methode renvoi l'information de quel departement est lie� a cette universit� et combien de temps dure� chaque echange
     * @return
     */
    public String departplusdure(){
        String description=new String();
        description="Pour le departement "+marker.getDepartementlist().get(0)+" la dur�e est de "+marker.getDur�elist().get(0)+" semaines ";
            for(int i=1;i<marker.getDepartementlist().size();i++){
                description=description+" ; "+marker.getDepartementlist().get(i)+"--"+marker.getDur�elist().get(i)+" ";    
            }
        return description;  
    }
    /**
     *Pour faire apairaitre le texte de chaque univesite o� pays en pop-up
     * @param e
     */
    public void plusinfo_actionPerformed(ActionEvent e) {
        
        JTextPane moreinfo=new JTextPane();
        SimpleAttributeSet center = new SimpleAttributeSet();
        JScrollPane scroolpane;
        String nom="";
        String text="";
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
       
        moreinfo.setParagraphAttributes(center,false);
        moreinfo.setFont(Font.decode("TimesRoman-DIALOG-15"));
        moreinfo.setEditable(false);
        
        if(e.getSource().equals(zommunive)){
            String[] nomlist=new String[4];
            nomlist=universit�nom.split(",");
            nom=nomlist[1];
            text=informationuniversite.getText();
        }else if(e.getSource().equals(zommpays)){
            nom=marker.getPays();
            text=informationpays.getText();
        }
        moreinfo.setText(text);
        scroolpane=new JScrollPane(moreinfo);
        scroolpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroolpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroolpane.setPreferredSize(new Dimension(400,400));
        JOptionPane.showMessageDialog(null, scroolpane, nom, JOptionPane.INFORMATION_MESSAGE);
        
    }
    /**
     *methode responsable pour renvoier la(les) urls associe�s a l'universit�
     * @param nomuniversit�
     * @return
     */
    public  String recupererurl(String nomuniversit�){
        String urltrouv�="";
        boolean pastrouve=true;
        int i=0;
        while(pastrouve&&i<this.universitelist.size()){
            if(this.universitelist.get(i).getUniversit�().equals(nomuniversit�)){
                urltrouv�=this.universitelist.get(i).getPhoto();
                pastrouve=false;
            }
            i++;
        }
        return urltrouv�;
    
    }
}

