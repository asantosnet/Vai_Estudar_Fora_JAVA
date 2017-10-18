package InterfaceFraficaAlgoritimo;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * classe responsable pour le listener dans PanelInfoUnivglobe 
 */
public class ButtonImage implements ActionListener{
    private JButton buttonselectioné=new JButton();
    public ButtonImage(JButton button) {
        buttonselectioné=button;
    }

    @Override
    /**
     * aciton responsable pour la ouverture d'une pop-up avec l'image agrandi
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if(buttonselectioné.getLabel().equals("Imagen non existante")){
            JOptionPane.showMessageDialog(null,"image non existante");
        }else{
            ImageIcon imageicon = new ImageIcon(chagertaille((ImageIcon)buttonselectioné.getIcon(),600,600));
            JLabel label=new JLabel();
            label.setIcon(imageicon);
            JOptionPane.showMessageDialog(null,label,"L'image selectioné",JOptionPane.PLAIN_MESSAGE);
        }
    }
    /**
     *methode fait pour changer la taille de l'image
     * @param ii
     * @return
     */
    public BufferedImage chagertaille(ImageIcon ii,int altura,int largura){
        BufferedImage bi = null;
            try {
                bi = new BufferedImage(altura, largura, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = (Graphics2D) bi.createGraphics();
                g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
                g2d.drawImage(ii.getImage(), 0, 0, altura, largura, null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return bi;
    }
}
