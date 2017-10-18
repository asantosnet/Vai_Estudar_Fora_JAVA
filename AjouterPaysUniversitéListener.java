package InterfaceFraficaAlgoritimo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AjouterPaysUniversit�Listener extends JPanel implements ActionListener {
    
    private PanelAjouterUniversit� panelajouteruniversit�=new PanelAjouterUniversit�(null,null);
    private PanelAjouterPays panelajouterpays=new PanelAjouterPays(null,null);
    private boolean codevraie=false;
    //pour recuperer la base de don�es
    private ArrayList<String> listpays=new ArrayList<String>();
    private ArrayList<String> listuniversit�=new ArrayList<String>();
    
    public AjouterPaysUniversit�Listener(ArrayList<String> listpaystrie,ArrayList<String> listuniversit�trie) {
        this.listuniversit�=listuniversit�trie; 
        this.listpays=listpaystrie;
    }
    /**
     *pour faire apparaitre le panel pour ajouter le pays e le universit�
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        //pour savoir s'il a le droit de modifier la basededon�es
        if(JOptionPane.showInputDialog("Tapez le code").equals("ThomasBoloss")){
            this.codevraie=true;
        }
        //pour savoir qu'elle paeaux on doit aller 
        if(this.listpays==null&&codevraie){
            String nomuniversit�=JOptionPane.showInputDialog(null, new String(), "Tapez le nom de l'universit�", JOptionPane.INFORMATION_MESSAGE);
            
            //pour savoir si le pays est d�ja das la base de don�es
            if(this.contienpas(this.listuniversit�, nomuniversit�)){
                   
                ArrayList<String> nomunivesit�list=new ArrayList<String>();
                nomunivesit�list.add(nomuniversit�);
                this.panelajouteruniversit�=new PanelAjouterUniversit�(nomunivesit�list,null);
                JOptionPane.showMessageDialog(null,this.panelajouteruniversit�,"Tapez les informations de l'unviersit�",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Cette universit� est d�ja la");
            }
        
        }else if(this.listuniversit�==null&&codevraie){
            String nomupays=JOptionPane.showInputDialog(null, new String(), "Tapez le nom du pays", JOptionPane.INFORMATION_MESSAGE);
            
            //pour savoir si le pays est d�ja das la base de don�es
            if(this.contienpas(this.listpays, nomupays)){
                ArrayList<String> nompayslist=new ArrayList<String>();
                nompayslist.add(nomupays);
                this.panelajouterpays=new PanelAjouterPays(nompayslist,null);
                JOptionPane.showMessageDialog(null,this.panelajouterpays,"Tapez les informations du pays",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Ce pays est d�ja la");
            }
        
        }else{
            JOptionPane.showMessageDialog(null, "Veuliez se connecter");    
        }
        
    }
    /**
     *pour voir si la string est dans la base de don� 
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
