package InterfaceFraficaAlgoritimo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Panel qui l'adm peut utiliser pour ajouter une pays
 */
public class PanelAjouterPays extends JPanel {
    private JButton ajouterlat = new JButton();
    private JButton ajouterlong = new JButton();
    private JButton ajouterdesc = new JButton();
    private JButton enregistrer= new JButton();
    private JButton revenir=new JButton();
    private JPanel background=new JPanel();

    //1-nom
    //2-lat
    //3-long
    //4-Description
    private ArrayList<String[]> infopayslist=new ArrayList<String[]>();
    private ArrayList<String> nompaysadicioner=new ArrayList<String>();
    private ArrayList<Pays> tabpays=new ArrayList<Pays>();
    private ArrayList<String> nomuniadicioner=new ArrayList<String>();
    private PanelAjouterUniversité paneluniversité=new PanelAjouterUniversité(nomuniadicioner,nompaysadicioner);

    public JButton getRevenir() {
        return revenir;
    }

    public PanelAjouterPays( ArrayList<String> nompaysadicione,ArrayList<String> nomuniadicione) {
        //les parametres
        this.nompaysadicioner=nompaysadicione;
        this.nomuniadicioner=nomuniadicione;
        
        this.setBounds(new Rectangle(10, -5, 400, 275));
        background.setLayout(new GridLayout(3,2));

        ajouterlat.setText("Ajouter latitude");
        ajouterlong.setText("Ajouter longitude");
        ajouterdesc.setText("Ajouter description");
        revenir.setText("Revenir");
        enregistrer.setText("Enregistrer");
        
        //pour donner la bonne taille a la array list infopayslist e ajouter le nom du pays
        if(nompaysadicioner!=null){
            for(int i=0;i<this.nompaysadicioner.size();i++){
         
                String[] infopays=new String[4];
                infopays[0]=this.nompaysadicioner.get(i);
                infopays[1]="";
                infopays[2]="";
                infopays[3]="";
                this.infopayslist.add(infopays);
            }
        }
        System.out.println(this.infopayslist.size());
        //pour ajouter l'information a la base de donées
        enregistrer.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        enregistrerlistener(e);
                    }
                });
        //Pour ajouter l'information sur la lat
        ajouterlat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterlatlistener(e);
            }
        });
        
        //pour ajouter l'information sur la lontitude
        ajouterlong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterlonglistener(e);
            }
        });
        
        //pour ajouter l'information sur la description
        ajouterdesc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterdesclistener(e);
            }
        });
        
        this.add(background);
        background.add(ajouterlat);
        background.add(ajouterlong);
        background.add(ajouterdesc);
        background.add(revenir);
        background.add(enregistrer);
        
    }
    /**
     *methode utiliser pour recuperer les latitudes des pays ajoutées
     * @param e
     */
    public void ajouterlatlistener(ActionEvent e){
         //pour recuperer la string
         String lats=JOptionPane.showInputDialog("Latitude, sud negatif");
         //separer la string
         String [] latlist=lats.split(";");
         //pour la mettre dans la array list

        for(int i=0;i<latlist.length;i++){
            this.infopayslist.get(i)[1]=latlist[i];
        }
       
        
    }
    /**
     *ajouter description des pays ajoutées
     * @param e
     */
    public void ajouterdesclistener(ActionEvent e){
        //pour recuperer la string
        String descriptions=JOptionPane.showInputDialog("Description ,n'utilise pas ; pour descrever un pays");
        //separer la string
        String [] descriptionslist=descriptions.split(";");
        //pour la mettre dans la array list
        for(int i=0;i<descriptionslist.length;i++){
            this.infopayslist.get(i)[3]=descriptionslist[i];
        } 
        
    }
    /**
     *ajouter longitude des pays ajoutées
     * @param e
     */
    public void ajouterlonglistener(ActionEvent e){
        //pour recuperer la string
        String longitudes=JOptionPane.showInputDialog("Longitude, est negatif");
        //separer la string
        String [] longitudeslist=longitudes.split(";");
        //pour la mettre dans la array list
        for(int i=0;i<longitudeslist.length;i++){
            this.infopayslist.get(i)[2]=longitudeslist[i];
        }
      
    }
    /**
     *pour ajouter les info une fois qui eles sont remplis
     * @param e
     */
    public void enregistrerlistener(ActionEvent e){
        AdditionerBaseDeDonées add=new AdditionerBaseDeDonées();
 
            for(int i=0;i<this.infopayslist.size();i++){
                Pays  pays=new Pays(this.infopayslist.get(i)[0],this.infopayslist.get(i)[3],Double.parseDouble(this.infopayslist.get(i)[1]),Double.parseDouble(this.infopayslist.get(i)[2]));
                this.tabpays.add(pays);
            }
            add.addpays(this.tabpays);
            this.tabpays=null;

    }
}


