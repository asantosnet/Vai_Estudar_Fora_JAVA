package InterfaceFraficaAlgoritimo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class FrameAdtionerExcel extends JFrame{
    //pour l'interface grapique
    private JLabel filelabel =new JLabel();
    private JTextPane filetextarea=new JTextPane();
    private JButton valider=new JButton();
    private JButton fichier=new JButton();
    private JButton ajouteuniversit�=new JButton();
    private JButton ajoutepays=new JButton();
    private JPanel background=new JPanel();
    private JPanel buttons=new JPanel();
    
    //pour que les echanges addicion�es soient recuperables
    private ArrayList<Echanges> tabadicion�=new ArrayList<Echanges>();
    private ArrayList<String> paysnomadicion�=new ArrayList<String>();
    private ArrayList<String> uninomadicion�=new ArrayList<String>();
    private PanelAjouterUniversit� paneladduni=new PanelAjouterUniversit�(uninomadicion�,paysnomadicion�);
    PanelAjouterPays paneladdpays=new PanelAjouterPays(this.paysnomadicion�,this.uninomadicion�);
    
    //pour avoir acc�s a la base de donn�
    private ArrayList<Echanges> tabech=new ArrayList<Echanges>();
    private ArrayList<String> tabpaysnom=new ArrayList<String>();
    private ArrayList<String> tabuninom=new ArrayList<String>();
    /**
     *classe responsable pour relier les 3 paneaux(celle fait ici+panelajouterpays+panelajouteruniversit�)
     * aussi responsable pour permettre que le administrateur ajoute un fichier excel directement a la base de don�e
     * @param tabechange
     * @param tabpaystrie
     * @param tabunitrie
     */
    public FrameAdtionerExcel(ArrayList<Echanges> tabechange,ArrayList<String> tabpaystrie,ArrayList<String> tabunitrie) {
        this.tabech=tabechange;
        this.tabpaysnom=tabpaystrie;
        this.tabuninom=tabunitrie;
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(200, 80));    
        
        background.setLayout(new BorderLayout());
        buttons.setLayout(new GridLayout(2,2));
        buttons.setPreferredSize(new Dimension(300,40));
        
        filelabel.setText("Adresse du fichier.xls");
        valider.setText("Valider");
        fichier.setText("Fichier");
        ajouteuniversit�.setText("Ajouter Universit�");
        ajoutepays.setText("Ajouter Pays");
        
        ajoutepays.setPreferredSize(new Dimension(150,20));
        ajouteuniversit�.setPreferredSize(new Dimension(150,20));
        valider.setPreferredSize(new Dimension(150,20));
        filelabel.setPreferredSize(new Dimension(300,20));
        filetextarea.setPreferredSize(new Dimension(300,50));
        fichier.setPreferredSize(new Dimension(150,20));
        
        //le listener responsable pour donner l'array lsit depuis un adrresse tape
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validerlistener(e);
            }
        });
        //le listener responsable pour pemettre l'ajoute d'un fichier
        fichier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fichierlistener(e);
            }
        });
        //le listener responsable pour aller au paneaux pour ajouter l'universit�
        ajouteuniversit�.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouteuniversit�listener(e);
            }
        });
        
        //le listener responsable pour aller aux paneux pour ajouter le pays
        ajoutepays.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajoutepayslistener(e);
            }
        });
        
        //pour mettre le textarea dans un scrollpane
        JScrollPane textscroll=new JScrollPane(filetextarea);
        textscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        textscroll.setPreferredSize(new Dimension(150,50));
        
        buttons.add(valider);
        buttons.add(fichier);
        buttons.add(ajoutepays);
        buttons.add(ajouteuniversit�);
        background.add(filelabel,BorderLayout.NORTH);
        background.add(textscroll,BorderLayout.CENTER);
        background.add(buttons,BorderLayout.SOUTH);
        
        background.repaint();
        this.setContentPane(background);
        this.repaint();
    }
    /**
     *methode qui vas renvoie l'array list qui le fichier choisi nous done
     * @param e
     * @return
     */
    public  void validerlistener(ActionEvent e){
        ExcelReader reader=new ExcelReader();
        AdditionerBaseDeDon�es add=new AdditionerBaseDeDon�es();
        //pour lire la list e apr�s donner la  lsit trie
        System.out.println(filetextarea.getText());
        this.tabadicion�=this.listfiltre(reader.recuperetabechange(filetextarea.getText()));
        
        //pour donner la list d'univesit�es a ajouter
        this.uninomadicion�=this.trouveruniversit�es(this.tabadicion�);
        
        //poiur donner la list de pays a ajouter
        this.paysnomadicion�=this.trouverpays(this.tabadicion�);
        
        //pour aditioner les echange a la base de don�es
        if(this.tabadicion�.size()!=0){
            add.addechange(this.tabadicion�);
        }
        if(this.tabadicion�.size()==0){
            JOptionPane.showMessageDialog(null,"Ces echanges sont d�ja dans la base de don�es o� vous pouvez verifie le lien o� la fai�on comment les don�es ont �te ecrit");
        }
        if((this.tabadicion�.size()!=0)&&(this.uninomadicion�.size()!=0)&&(this.paysnomadicion�.size()!=0)){
            JOptionPane.showMessageDialog(null,"il faut ajouter une universit� et un pays ");
            JOptionPane.showMessageDialog(null,"Renisialize l'application pour que les chagement soient charg�es");
        }
        if((this.tabadicion�.size()!=0)&&(this.uninomadicion�.size()!=0)&&(this.paysnomadicion�.size()==0)){
            JOptionPane.showMessageDialog(null,"il faut ajouter une universit�");
            JOptionPane.showMessageDialog(null,"Renisialize l'application pour que les chagement soient charg�es");
        }
    }
    /**
     *il vas chercher la array list lu du doc excel par des echanges deja existantes
     * @return
     */
    public ArrayList<Echanges> listfiltre(ArrayList<Echanges> nontrie){
        ArrayList<Echanges> temporaire=new ArrayList<Echanges>();
        for(int i=0;i<nontrie.size();i++){;
            if(this.tabech.contains(nontrie.get(i))){
            }else{
                temporaire.add(nontrie.get(i));
            }
        }
        return temporaire;
    }
    /**
     *pour trouver les universit�es qu'il faut ajouter 
     * @param listech
     * @return
     */
    public ArrayList<String> trouveruniversit�es(ArrayList<Echanges> listech){
        ArrayList<String> temporaire=new ArrayList<String>();

        for(int i=0;i<listech.size();i++){
            if((this.tabuninom.contains(listech.get(i).getUniversit�()))&&(temporaire.size()==0)){    
  
            }else if(temporaire.size()==0){
          
                temporaire.add(listech.get(i).getUniversit�());
            }else if(this.tabuninom.contains(listech.get(i).getUniversit�())){
   
            }else{

                temporaire.add(listech.get(i).getUniversit�());           
            }
        }
        return temporaire;
    }
    /**
     *pour trouver les pays qu'il faut ajouter 
     * @param listech
     * @return
     */
    public ArrayList<String> trouverpays(ArrayList<Echanges> listech){
        ArrayList<String> temporaire=new ArrayList<String>();
        for(int i=0;i<listech.size();i++){
            if((this.tabpaysnom.contains(listech.get(i).getPays()))&&(temporaire.size()==0)){    
            }else if(temporaire.size()==0){
                temporaire.add(listech.get(i).getPays());
            }else if(this.tabpaysnom.contains(listech.get(i).getPays())){
            }else{
                temporaire.add(listech.get(i).getPays());           
            }
        }
        return temporaire;
    }
    /**
     *pour passer au paneaux pour ajouter la universit�
     * @param e
     */
    public void ajouteuniversit�listener(ActionEvent e){
        if(this.paysnomadicion�.size()==0){
            JOptionPane.showMessageDialog(null, "Veuliez tapez un echange o� il le universit� est d�ja la");
        }else{
            JOptionPane.showMessageDialog(null, "Pour taper plusieurs universit�s, intercalez les informations avec deux ; (ex UFMG;;UFPR)");
            this.paneladduni=new PanelAjouterUniversit�(uninomadicion�,paysnomadicion�);
            
            //pour returner le panel
            //pour ajouter le listener pour revenir vers le paneeaux ajouter fichier
            paneladduni.getRevenir().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    revenirlistener(e);
                }
            });
            
            background.removeAll();
            background=this.paneladduni;
            background.repaint();
            this.setContentPane(background);
            this.repaint();
            this.setSize(new Dimension(330,120));
            
        }
    
    }
    /**
     *pour passer aux paneaux pour ajouter le pays
     * @param e
     */
    public void ajoutepayslistener(ActionEvent e){
        if(this.uninomadicion�.size()==0){
            JOptionPane.showMessageDialog(null, "Veuliez tapez un echange o� il le universit� est d�ja la");
        }else{
            JOptionPane.showMessageDialog(null, "Pour taper plusieurs pays, intercalez les informations avec ; (ex Br�sil;Chine)");
            
            this.paneladdpays=new PanelAjouterPays(this.paysnomadicion�,this.uninomadicion�);
            
            //pour returner le panel
            //pour ajouter le listener pour revenir vers le paneeaux ajouter fichier
            paneladdpays.getRevenir().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    revenirlistener(e);
                }
            });

            background=this.paneladdpays;
            background.repaint();
            this.setContentPane(background);
            this.repaint();
            this.setSize(new Dimension(330,120));
            
        } 
    }
    /**
     *permetre l'ajout des echanges en selectionent directment un fichier
     * @param Event
     */
    public void fichierlistener(ActionEvent e){
        JFileChooser chooser=new JFileChooser();
        ExcelReader reader=new ExcelReader();
        AdditionerBaseDeDon�es add=new AdditionerBaseDeDon�es();
        int returnVal = chooser.showOpenDialog(this);
        String adresse="";
        //utilis� poure recuperer l'adresse du ficheir
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            adresse=chooser.getSelectedFile().getPath();
        }  
        if(adresse!=""){
            //pour recuperer la liste d'echanges que ne sont pas dans la base de don�es
            this.tabadicion�=this.listfiltre(reader.recuperetabechange(adresse));
            
            //pour trouver les universit�s que ne sont pas dans la base de done
            this.uninomadicion�=this.trouveruniversit�es(this.tabadicion�);
            
            //pour trouver les  pays que ne sont pas dans la Base de don�es
            this.paysnomadicion�=this.trouverpays(this.tabadicion�);

           //pour aditioner les echanges � la base de don�es
            if(this.tabadicion�.size()!=0){
                add.addechange(this.tabadicion�);
                
            }
            if(this.tabadicion�.size()==0){
                JOptionPane.showMessageDialog(null,"Ces echanges sont d�ja dans la base de don�es o� vous pouvez verifie le lien o� la fai�on comment les don�es ont �te ecrit");
            }
            if((this.tabadicion�.size()!=0)&&(this.uninomadicion�.size()!=0)&&(this.paysnomadicion�.size()!=0)){
                JOptionPane.showMessageDialog(null,"il faut ajouter une universit� et un pays ");
                JOptionPane.showMessageDialog(null,"Renisialize l'application pour que les chagement soient charg�es");
            }
            if((this.tabadicion�.size()!=0)&&(this.uninomadicion�.size()!=0)&&(this.paysnomadicion�.size()==0)){
                JOptionPane.showMessageDialog(null,"il faut ajouter une universit�");
                JOptionPane.showMessageDialog(null,"Renisialize l'application pour que les chagement soient charg�es");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Il faut choisir un fichier");
        }
    }
    /**
     *methode responsable pour le changement de panel,panel ajouter universit�-panel ajouter echange
     * @param e
     */
    public void revenirlistener(ActionEvent e){
        JScrollPane textscroll=new JScrollPane(filetextarea);
        textscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        textscroll.setPreferredSize(new Dimension(300,50));
        background.removeAll();
        buttons.add(valider);
        buttons.add(fichier);
        buttons.add(ajoutepays);
        buttons.add(ajouteuniversit�);
        background.add(filelabel,BorderLayout.NORTH);
        background.add(textscroll,BorderLayout.CENTER);
        background.add(buttons,BorderLayout.SOUTH);
        background.repaint();     
        this.setContentPane(background);
        this.repaint();
        this.setSize(new Dimension(350,180));
        
    }
  
}
