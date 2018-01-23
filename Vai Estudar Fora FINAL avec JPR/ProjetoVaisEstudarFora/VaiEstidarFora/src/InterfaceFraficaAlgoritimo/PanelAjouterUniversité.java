package InterfaceFraficaAlgoritimo;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
/**
 * Panel qui l'adm peut utiliser pour ajouter une université
 */
public class PanelAjouterUniversité extends JPanel{
    private JButton revenir = new JButton();
    private JButton info = new JButton();
    private JButton save = new JButton();
    private JButton url = new JButton();
    private JButton addville=new JButton();
    private JButton addpays= new JButton();
    private JPanel background=new JPanel();

    private ArrayList<String> nompaysadicioner=new ArrayList<String>();
    private ArrayList<String> nomuniadicioner=new ArrayList<String>();
    //1-universite
    //2-ville
    //3-pays
    //4-desc
    //5-url
    private ArrayList<String[]> infounilist=new ArrayList<String[]>();
    private ArrayList<Université> tabuniversité=new ArrayList<Université>();
    
    public JButton getRevenir() {
        return revenir;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getModifierurl() {
        return addville;
    }

    public JButton getModifierinfo() {
        return addpays;
    }
    public PanelAjouterUniversité(ArrayList<String> nomuniadicione,ArrayList<String> nompaysadicione) {
        this.nompaysadicioner=nompaysadicione;
        this.nomuniadicioner=nomuniadicione;
        
        this.setBounds(new Rectangle(10, -5, 400, 275));
        background.setLayout(new GridLayout(3,2));
        background.setBounds(new Rectangle(10, -5, 400, 275));
        revenir.setText("Revenir");
        info.setText("Informations");
        
        //pour donner la bonne taille a la array list infopayslist e ajouter le nom du pays
        if(nomuniadicioner!=null){    
            for(int i=0;i<this.nomuniadicioner.size();i++){
         
                String[] infouni=new String[5];
                infouni[0]=this.nomuniadicioner.get(i);
                infouni[1]="";
                infouni[2]="";
                infouni[3]="";
                infouni[4]="";
                this.infounilist.add(infouni);      
            }    
        }
            
        //pour ajouter une description
        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Info_actionPerformed(e);
            }
        });
        
        //pour ajouter une Url
        url.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                URL_actionPerformed(e);
            }
        });   
        //pour ajouter une nom du pays
        addpays.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addpays_actionPerformed(e);
            }
        });   
        //pour ajouter une nom de ville
        addville.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addville_actionPerformed(e);
            }
        });   
        //pour aditioner les univessitées a la base de donées 
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enregistrerlistener(e);
            }
        });   
       
        save.setText("Save");
        url.setText("URL");
        addpays.setText("Ajoure nom ville");
        addville.setText("Ajouter nom pays");
       
        
        background.add(info);
        background.add(url);
        background.add(addpays);
        background.add(addville);
        background.add(save);
        background.add(revenir);;
        background.repaint();
        this.add(background);
    }
    /**
     *methode responsable pour lire la description tapé
     * @param e
     */
    private void Info_actionPerformed(ActionEvent e) {
        //pour recuperer la string
        String infolist=JOptionPane.showInputDialog("Tapez l'information sur l'université");
        //separer la string
        String [] longitudeslist=infolist.split(";;");
        //pour la mettre dans la array list
        for(int i=0;i<longitudeslist.length;i++){
            this.infounilist.get(i)[3]=longitudeslist[i];
        }
 
    }
    /**
     *methode responsable pour lire les urls tapé
     * @param e
     */
    private void URL_actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "\"Tapez les URLS, elles doivent etre separés par ; \"");
        //pour recuperer la string
        String longitudes=JOptionPane.showInputDialog("Tapez les URLS, elles doivent etre separés par ;;");
        //separer la string
        String [] longitudeslist=longitudes.split(";;");
        //pour la mettre dans la array list
        for(int i=0;i<longitudeslist.length;i++){
            this.infounilist.get(i)[4]=longitudeslist[i];
        }

    }
    /**
     *methode pour lire les nompays
     * @param e
     */
    private void addpays_actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "tapez le noom du pays separe par deux ;; , ex.Brésil;;Chine");
        //pour recuperer la string
        String longitudes=JOptionPane.showInputDialog("Tapez les non du pays;");
        //separer la string
        String [] longitudeslist=longitudes.split(";;");
        //pour la mettre dans la array list
        for(int i=0;i<longitudeslist.length;i++){
            this.infounilist.get(i)[2]=longitudeslist[i];
        }
  
    }
    /**
     *methode pour lier les nonvile
     * @param e
     */
    private void addville_actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "tapez le noom de la ville separe par deux ;; , ex.Séville;;Toronto");
        //pour recuperer la string
        String longitudes=JOptionPane.showInputDialog("Tapez les  des villes");
        //separer la string
        String [] longitudeslist=longitudes.split(";;");
        //pour la mettre dans la array list
        for(int i=0;i<longitudeslist.length;i++){
            this.infounilist.get(i)[1]=longitudeslist[i];
        }
      
    }
    /**
     *pour ajouter la université un fois qui tous a éte mis
     * @param e
     */
    public void enregistrerlistener(ActionEvent e){
        AdditionerBaseDeDonées add=new AdditionerBaseDeDonées();
       
            for(int i=0;i<this.infounilist.size();i++){
                Université université=new Université(this.infounilist.get(i)[2],this.infounilist.get(i)[1],this.infounilist.get(i)[0],this.infounilist.get(i)[3],this.infounilist.get(i)[4]);
                this.tabuniversité.add(université);
            }
            add.adduniversité(this.tabuniversité);
            System.out.println("ecrit");
            this.tabuniversité=null;

    }
        
}
