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
    private ArrayList<String> paysnomadicioné=new ArrayList<String>();
    private ArrayList<String> uninomadicioné=new ArrayList<String>();
    private PanelAjouterUniversité panelajouteruni=new PanelAjouterUniversité(uninomadicioné,paysnomadicioné);
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
        //permettre l'adm a ajouter où modifier une univesité
        panelajouterech.getAjouteruniversité().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouteruniversité(e);
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
     *methode responsable pour le changement de panel, panel ajouter echange à panel ajouter universite
     * @param e
     */
    public void ajouteruniversité(ActionEvent e){
        if(panelajouterech.getEchajouter().size()==0){
            JOptionPane.showMessageDialog(null, "Veuliez tapez un echange");
        }else{
            this.uninomadicioné.add((String)JOptionPane.showInputDialog("Tapez le nom de l'université"));
            this.panelajouteruni=new PanelAjouterUniversité(uninomadicioné,this.paysnomadicioné);
            background=this.panelajouteruni;
            background.repaint();
            this.setContentPane(background);
            this.repaint();
            this.setSize(new Dimension(350,150));
            
        }
        
    }
    /**
     *methode responsable pour le changement de panel,panel ajouter université-panel ajouter echange
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