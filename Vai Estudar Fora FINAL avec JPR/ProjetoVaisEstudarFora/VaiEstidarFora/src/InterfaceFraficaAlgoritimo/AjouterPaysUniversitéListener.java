package InterfaceFraficaAlgoritimo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AjouterPaysUniversitéListener extends JPanel implements ActionListener {
    
    private PanelAjouterUniversité panelajouteruniversité=new PanelAjouterUniversité(null,null);
    private PanelAjouterPays panelajouterpays=new PanelAjouterPays(null,null);
    private boolean codevraie=false;
    //pour recuperer la base de donées
    private ArrayList<String> listpays=new ArrayList<String>();
    private ArrayList<String> listuniversité=new ArrayList<String>();
    
    public AjouterPaysUniversitéListener(ArrayList<String> listpaystrie,ArrayList<String> listuniversitétrie) {
        this.listuniversité=listuniversitétrie; 
        this.listpays=listpaystrie;
    }
    /**
     *pour faire apparaitre le panel pour ajouter le pays e le université
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        //pour savoir s'il a le droit de modifier la basededonées
        if(JOptionPane.showInputDialog("Tapez le code").equals("ThomasBoloss")){
            this.codevraie=true;
        }
        //pour savoir qu'elle paeaux on doit aller 
        if(this.listpays==null&&codevraie){
            String nomuniversité=JOptionPane.showInputDialog(null, new String(), "Tapez le nom de l'université", JOptionPane.INFORMATION_MESSAGE);
            
            //pour savoir si le pays est déja das la base de donées
            if(this.contienpas(this.listuniversité, nomuniversité)){
                   
                ArrayList<String> nomunivesitélist=new ArrayList<String>();
                nomunivesitélist.add(nomuniversité);
                this.panelajouteruniversité=new PanelAjouterUniversité(nomunivesitélist,null);
                JOptionPane.showMessageDialog(null,this.panelajouteruniversité,"Tapez les informations de l'unviersité",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Cette université est déja la");
            }
        
        }else if(this.listuniversité==null&&codevraie){
            String nomupays=JOptionPane.showInputDialog(null, new String(), "Tapez le nom du pays", JOptionPane.INFORMATION_MESSAGE);
            
            //pour savoir si le pays est déja das la base de donées
            if(this.contienpas(this.listpays, nomupays)){
                ArrayList<String> nompayslist=new ArrayList<String>();
                nompayslist.add(nomupays);
                this.panelajouterpays=new PanelAjouterPays(nompayslist,null);
                JOptionPane.showMessageDialog(null,this.panelajouterpays,"Tapez les informations du pays",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Ce pays est déja la");
            }
        
        }else{
            JOptionPane.showMessageDialog(null, "Veuliez se connecter");    
        }
        
    }
    /**
     *pour voir si la string est dans la base de doné 
     * @param list
     * @param atrouver
     * @return
     */
    public boolean contienpas(ArrayList<String> list,String atrouver){
        boolean paatrouve=true;
            if(list.contains(atrouver)){
                paatrouve=false;
            }
        return paatrouve;
    }
}
