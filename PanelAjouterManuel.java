package InterfaceFraficaAlgoritimo;


import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
/**
 * panel pour l'ajout manuel d'un echange
 */
public class PanelAjouterManuel extends JPanel{
    private JPanel paneltab = new JPanel();
    private JTable tableinfo = new JTable();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JButton valider = new JButton();
    private JButton ajouteruniversité = new JButton();
    
    //pour recuperer les donées 
    private ArrayList<Echanges> echajouter=new ArrayList<Echanges>();
    private ArrayList<String> paysnomadicioné=new ArrayList<String>();
    private ArrayList<String> uninomadicioné=new ArrayList<String>();
    
    //pour avoir accés a la base de donné
    private ArrayList<Echanges> tabech=new ArrayList<Echanges>();
    private ArrayList<String> tabpaysnom=new ArrayList<String>();
    private ArrayList<String> tabuninom=new ArrayList<String>();

    public ArrayList<Echanges> getEchajouter() {
        return echajouter;
    }

    public JButton getAjouteruniversité() {
        return ajouteruniversité;
    }

    public void setTableinfo(JTable tableinfo) {
        this.tableinfo = tableinfo;
    }



    public PanelAjouterManuel()  {
        this.setPreferredSize(new Dimension(462, 159));
        this.setLayout(null);
        paneltab.setBounds(new Rectangle(0, 5, 460, 100));
        paneltab.setLayout(borderLayout1);
        tableinfo.setMinimumSize(new Dimension(100, 100));
        tableinfo.setSize(new Dimension(100, 100));
        tableinfo.setBounds(new Rectangle(0, 0, 405, 200));
        tableinfo.setPreferredSize(new Dimension(100, 100));
        valider.setText("Valider");
        valider.setBounds(new Rectangle(0, 105, 460, 25));
        ajouteruniversité.setBounds(new Rectangle(0, 125, 460, 30));
        ajouteruniversité.setText("Ajouter Université");
        tableinfo.setSize(new Dimension(405, 200));
            Object[][] entetes={
                        {"Bresil","Curitiba","Port","PUC","GMD","40","56.43","54.78"},
        };
        Object[] donnees={"Pays","Ville","Langue","Université","Département","Durée","latitude","longitude"};
        this.setTableinfo(new JTable(entetes,donnees));
        paneltab.add(tableinfo.getTableHeader(), BorderLayout.NORTH);
        paneltab.add(tableinfo, BorderLayout.CENTER);
        this.add(paneltab);
        this.add(valider);
        this.add(ajouteruniversité);
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                valider_actionPerformed(e);
            }
        });
    }
    /**
     *methode responsable pour aditioner les informations qui sont dans le tableau a l'arraylist
     * @param table
     */
    public  ArrayList<Echanges> recuperertableau(){   
        ArrayList<Echanges> listechange=new ArrayList<Echanges>();
        boolean notnull=true;
            //pour savoir si le valeur est null où non
            int b=0;
            while(b<8&&notnull){
                if(((String)tableinfo.getValueAt(0,b)).equals("")){
                    notnull=false;
                }    
                b++;
            }
            //pour recuperer les valeurs
            if(notnull){
                String pays=(String)tableinfo.getValueAt(0,0);
                String ville=(String)tableinfo.getValueAt(0,1);
                String langue=(String)tableinfo.getValueAt(0,2);
                String université=(String)tableinfo.getValueAt(0,3);
                String departement=(String)tableinfo.getValueAt(0,4);
                int durée=Integer.parseInt((String)tableinfo.getValueAt(0,5));
                double latitude=Double.parseDouble((String)tableinfo.getValueAt(0,6));
                double longitude=Double.parseDouble((String)tableinfo.getValueAt(0,7));
                listechange.add(new Echanges(pays,ville,langue,université,departement,durée,latitude,longitude));
                
            }
        return listechange;
    }
    /**
     *action listener responsable pour declanger le methode valider e ajouter le echange a la base de donées
     * @param
     */
    public void valider_actionPerformed(ActionEvent e){
            this.echajouter=this.recuperertableau();
            AdditionerBaseDeDonées add=new AdditionerBaseDeDonées();
            if(this.echangedejala(echajouter.get(0))){
                JOptionPane.showMessageDialog(null, "Ce echange est déja dans la base de donées où la faiçon comment les donées ont éte ecrit");
            }else{
                //pour donner la list d'univesitées a ajouter
                this.uninomadicioné=this.trouveruniversitées(this.echajouter);
        
                //poiur donner la list de pays a ajouter
                this.paysnomadicioné=this.trouverpays(this.echajouter);
                
                //pour aditioner les echange a la base de donées
                if(this.echajouter.size()!=0){
                    add.addechange(this.echajouter);
                }
                if(this.echajouter.size()==0){
                    JOptionPane.showMessageDialog(null,"L'echange null n'existe pas^^");
                }
                if((this.echajouter.size()!=0)&&(this.uninomadicioné.size()!=0)&&(this.paysnomadicioné.size()!=0)){
                    JOptionPane.showMessageDialog(null,"il faut ajouter une université et un pays ");
                    JOptionPane.showMessageDialog(null,"Renisialize l'application pour que les chagement soient chargées");
                }
                if((this.echajouter.size()!=0)&&(this.uninomadicioné.size()!=0)&&(this.paysnomadicioné.size()==0)){
                    JOptionPane.showMessageDialog(null,"il faut ajouter une université");
                    JOptionPane.showMessageDialog(null,"Renisialize l'application pour que les chagement soient chargées");
               }
            }
    }
    /**
     *pour trouver les universitées qu'il faut ajouter 
     * @param listech
     * @return
     */
    public ArrayList<String> trouveruniversitées(ArrayList<Echanges> listech){
        ArrayList<String> temporaire=new ArrayList<String>();

        for(int i=0;i<listech.size();i++){
            if((this.tabuninom.contains(listech.get(i).getUniversité()))&&(temporaire.size()==0)){    
    
            }else if(temporaire.size()==0){
          
                temporaire.add(listech.get(i).getUniversité());
            }else if(this.tabuninom.contains(listech.get(i).getUniversité())){
    
            }else{

                temporaire.add(listech.get(i).getUniversité());           
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
     * pour savoir si l'echange est déja dans la base de donées
     */
    public boolean echangedejala(Echanges echangelist){
        boolean dansbd=false;
        if(this.tabech.contains(echangelist)){
            dansbd=true;
            }
        return dansbd;
    }
}