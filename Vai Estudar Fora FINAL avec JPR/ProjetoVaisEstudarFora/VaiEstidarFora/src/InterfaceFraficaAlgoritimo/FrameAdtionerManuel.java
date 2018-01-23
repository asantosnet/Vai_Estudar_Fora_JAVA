package InterfaceFraficaAlgoritimo;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * la fenetre qui vas appairatire si on veut ajouter un echange manuelment
 */
public class FrameAdtionerManuel extends JFrame {
    private PanelAjouterManuel panelajouterech=new PanelAjouterManuel();
    private JPanel background =new JPanel();
    private ArrayList<String> paysnomadicion�=new ArrayList<String>();
    private ArrayList<String> uninomadicion�=new ArrayList<String>();
    private PanelAjouterUniversit� panelajouteruni=new PanelAjouterUniversit�(uninomadicion�,paysnomadicion�);
    public FrameAdtionerManuel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(466, 183));
        background.setPreferredSize(new Dimension(466, 183));
        background=panelajouterech;
        background.repaint();
        this.setContentPane(background);
        this.repaint();
        
        //listener responsable pour appeler le methode qui vas charger le panel qui vas 
        //permettre l'adm a ajouter o� modifier une univesit�
        panelajouterech.getAjouteruniversit�().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouteruniversit�(e);
            }
        });
        
        //permettre que l'adm ajoute un autre echange;
        panelajouteruni.getRevenir().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revenir(e);
            }
        });
    }
    /**
     *methode responsable pour le changement de panel, panel ajouter echange � panel ajouter universite
     * @param e
     */
    public void ajouteruniversit�(ActionEvent e){
        if(panelajouterech.getEchajouter().size()==0){
            JOptionPane.showMessageDialog(null, "Veuliez tapez un echange");
        }else{
            this.uninomadicion�.add((String)JOptionPane.showInputDialog("Tapez le nom de l'universit�"));
            this.panelajouteruni=new PanelAjouterUniversit�(uninomadicion�,this.paysnomadicion�);
            background=this.panelajouteruni;
            background.repaint();
            this.setContentPane(background);
            this.repaint();
            this.setSize(new Dimension(350,150));
            
        }
        
    }
    /**
     *methode responsable pour le changement de panel,panel ajouter universit�-panel ajouter echange
     * @param e
     */
    public void revenir(ActionEvent e){
        background.removeAll();
        background=this.panelajouterech;
        background.repaint();     
        this.setContentPane(background);
        this.repaint();
        this.setSize(new Dimension(466, 183));
    }
    
}