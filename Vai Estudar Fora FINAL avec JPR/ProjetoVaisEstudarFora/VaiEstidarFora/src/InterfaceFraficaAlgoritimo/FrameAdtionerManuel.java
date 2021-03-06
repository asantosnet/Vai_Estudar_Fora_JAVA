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
    private ArrayList<String> paysnomadicionÚ=new ArrayList<String>();
    private ArrayList<String> uninomadicionÚ=new ArrayList<String>();
    private PanelAjouterUniversitÚ panelajouteruni=new PanelAjouterUniversitÚ(uninomadicionÚ,paysnomadicionÚ);
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
        //permettre l'adm a ajouter o¨ modifier une univesitÚ
        panelajouterech.getAjouteruniversitÚ().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouteruniversitÚ(e);
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
     *methode responsable pour le changement de panel, panel ajouter echange Ó panel ajouter universite
     * @param e
     */
    public void ajouteruniversitÚ(ActionEvent e){
        if(panelajouterech.getEchajouter().size()==0){
            JOptionPane.showMessageDialog(null, "Veuliez tapez un echange");
        }else{
            this.uninomadicionÚ.add((String)JOptionPane.showInputDialog("Tapez le nom de l'universitÚ"));
            this.panelajouteruni=new PanelAjouterUniversitÚ(uninomadicionÚ,this.paysnomadicionÚ);
            background=this.panelajouteruni;
            background.repaint();
            this.setContentPane(background);
            this.repaint();
            this.setSize(new Dimension(350,150));
            
        }
        
    }
    /**
     *methode responsable pour le changement de panel,panel ajouter universitÚ-panel ajouter echange
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