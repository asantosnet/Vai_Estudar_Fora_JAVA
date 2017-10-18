package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;

import gov.nasa.worldwind.view.orbit.BasicOrbitView;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * classe avec le panel qui permetre que l'utilisateur vas a une coordone, pays où ville directement
 */
public class Google extends JPanel {
    
    private JComboBox jComboBox1 = new JComboBox();
    private JTextArea jTextArea1 = new JTextArea();
    private JButton   jvalider  =new JButton();
    private String lat =new String();
    private String lonng =new String();
    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLong(String param) {
        this.lonng = param;
    }

    public void setJvalider(JButton jvalider) {
        this.jvalider = jvalider;
    }

    public JTextArea getJTextArea1() {
        return jTextArea1;
    }

    public String getLat() {
        return lat;
    }

    public String getLong() {
        return lonng;
    }

    
    public Google() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(30, 30));
        jComboBox1.addItem("Coordonnees");
        jComboBox1.addItem("Pays");
        jComboBox1.addItem("Ville");
        jvalider.setText("Valider");
        this.add(jTextArea1, BorderLayout.CENTER);
        this.add(jComboBox1, BorderLayout.WEST);
        this.add(jvalider,BorderLayout.EAST);
        //pour indiquer comment il doit taper
        if(jComboBox1.getSelectedItem().equals("Coordonnees")){
                jTextArea1.setText("Lat;Longitude;Soud at Est sont des valuer negatifs par ex -50.5;-30.5");
                this.repaint();
            }
        
        //pour changer le text dans TextArea1
        ItemListener itemlistener=new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(jComboBox1.getSelectedItem().equals("Ville")){
                    jTextArea1.setText("Tapez le nom de la ville en français avec accent sans spaceex 'Séville' et non ' Séville");
                    repaint();
                }else if(jComboBox1.getSelectedItem().equals("Pays")){
                    jTextArea1.setText("Tapez le nom du pais avec accent sans space, par ex 'Brésil' et non ' Brésil'");
                    repaint();
                }else{
                    jTextArea1.setText("Lat;Longitude;Soud at Est sont des valuer negatifs par ex -50.5;-30.5");
                    repaint();
                }
                System.out.println("funciona");
        }
     };
    
    jComboBox1.addItemListener(itemlistener);
    
    }
    public void setJComboBox1(JComboBox jComboBox1) {
        this.jComboBox1 = jComboBox1;
    }

    public JComboBox getJComboBox1() {
        return jComboBox1;
    }

    public void setJTextArea1(JTextArea jTextArea1) {
        this.jTextArea1 = jTextArea1;
    }

    public JButton getJvalider() {
        return jvalider;
    }

    
}