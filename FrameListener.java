package InterfaceFraficaAlgoritimo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * l
 */
public class FrameListener extends JFrame implements ActionListener {
    //pour le relier à la bd
    private ArrayList<Echanges> tabechange=new ArrayList<Echanges>();
    private ArrayList<String> tabpaystrie=new ArrayList<String>();
    private ArrayList<String> tabunitrie=new ArrayList<String>();
    
    private FrameAdtionerManuel frameouvrirman=new FrameAdtionerManuel();
    private FrameAdtionerExcel frameouvrirexcel=new FrameAdtionerExcel(tabechange,tabpaystrie,tabunitrie);
    private boolean codevraie=false;
    /**
     *pour quand on veut mettre des echangess manuelement
     * * @param frameouvrirman
     */
    public FrameListener(FrameAdtionerManuel frameouvrirman,ArrayList<Echanges> tabechange,ArrayList<String> tabpaystrie,ArrayList<String> tabunitrie) {
        this.tabechange=tabechange;
        this.tabpaystrie=tabpaystrie;
        this.tabunitrie=tabunitrie;
        this.frameouvrirman=frameouvrirman;
        this.frameouvrirexcel=null;
       
    }
    /**
     *quand on veut mettre des fichiers
     * @param frameouvrirexcel
     */
    public FrameListener(FrameAdtionerExcel frameouvrirexcel,ArrayList<Echanges> tabechange,ArrayList<String> tabpaystrie,ArrayList<String> tabunitrie){
        this.tabechange=tabechange;
        this.tabpaystrie=tabpaystrie;
        this.tabunitrie=tabunitrie;
        this.frameouvrirexcel=frameouvrirexcel;   
        this.frameouvrirman=null;
       
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //pour voir si votre code est bonne
        if(JOptionPane.showInputDialog("Tapez le code").equals("ThomasBoloss")){
            this.codevraie=true;
        }
        
        if(frameouvrirman==null&&codevraie){
            JOptionPane.showMessageDialog(null,"Le fichier doit etre .xlsx");
            Dimension frameSize = frameouvrirexcel.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }
            frameouvrirexcel.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
            frameouvrirexcel.setVisible(true);
            frameouvrirexcel.pack();
        }else if(frameouvrirexcel==null&&codevraie){
            Dimension frameSize = frameouvrirman.getSize();
            if (frameSize.height > screenSize.height) {
                frameSize.height = screenSize.height;
            }
            if (frameSize.width > screenSize.width) {
                frameSize.width = screenSize.width;
            }
            frameouvrirman.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
            frameouvrirman.setVisible(true);
            frameouvrirman.pack(); 
        }else {
            JOptionPane.showMessageDialog(null, "Veuliez se connecter");
        }
    }
}