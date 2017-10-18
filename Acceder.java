package InterfaceFraficaAlgoritimo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 * Classe qui esy le listener responsable pour changer entre le premier panel et le deuxiéme, qui est le panel où il  y a le globe e les options pour choisir un echange
 */
public class Acceder extends JFrame implements ActionListener {
    private PanelIndentification pindet =new PanelIndentification();
    private Google c=new Google();
    private Panelgauche pgauche;
    private PanelTest globe=new PanelTest();
    private JPanel background=new JPanel();
    private PanelIndentification pident = new PanelIndentification();
    private JFrame fenetre=new JFrame();
    private ArrayList<JPanel> historique=new ArrayList<JPanel>(); 
    /**
     *
     * @param pindet
     * @param c
     * @param pgauche
     * @param globe
     * @param background
     * @param pident
     */
     public Acceder(ArrayList<JPanel> historique,Framee1 fenetre,PanelIndentification pindet,Google c,Panelgauche pgauche,PanelTest globe,JPanel background,PanelIndentification pident) {
        this.pindet=pindet;
        this.c=c;
        this.background=background;
        this.globe=globe;
        this.pgauche=pgauche;
        this.pident=pident;
        this.fenetre=fenetre;
        this.historique=historique;
       
    }
    
    /**
     *cette classe sert à différencier les visiteurs des administrateurs, deux panneaux différents sont chargés suivant l'identité de l'utilisteur d'ou les
     * if(IsPasswordCorrect) et if(IsPasswordIncorrect)
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if(IsPasswordCorrect()){
            PanelAcceder pacceder=new PanelAcceder(c,pgauche,globe);
            //pour l'ajouter dans l'historique
            historique.add(pacceder);
            //pour retirer tous le panel dans la fenetre
            background.removeAll();
            background.setLayout(new BorderLayout());
            background.add(pacceder,BorderLayout.CENTER);
            background.setPreferredSize(new Dimension(500,500));
            background.repaint();
            fenetre.repaint();
            fenetre.setSize(new Dimension(1000,700));
           }else{
             JOptionPane.showMessageDialog(null,"veuillez retaper votre code où acceder comme visiteur");
        }
    }
    /**
     *
     * @return
     */
    public boolean IsPasswordCorrect(){
        //il manque la base de donne, il faut le faire
        boolean correct=false;
        if(pindet.getType().getSelectedItem()=="Visiteur"){
            correct=true;
        }else if(pindet.getType().getSelectedItem()=="Admin"){
            String indentifiant="ThomasBoloss";
            correct=true;
            int i=0;
            //pour voir s'il a tape le bonne code
            while(i<indentifiant.length()&&correct||i<pident.getCode().getPassword().length&&correct){
                if(pident.getCode().getPassword()[i]!=indentifiant.charAt(i)){
                    correct=false;
                }        
                i++;
            }
        }
        return correct;
        }

}

