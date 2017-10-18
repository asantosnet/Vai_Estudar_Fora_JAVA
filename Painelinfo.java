package InterfaceFraficaAlgoritimo;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Rectangle;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


/**
 * Le panel qui contient les jcombobox où il y a les critiérer a chosiir pour la recherche d'un echange
 */
public class Painelinfo extends JPanel {
    private JComboBox jComboBox1 = new JComboBox();
    private JComboBox jComboBox2 = new JComboBox();
    private JComboBox departementcombobox= new JComboBox();
    private JComboBox jComboBox3 = new JComboBox();
    private JButton jButton1 = new JButton();
    private JButton redemarrer=new JButton();
    private BorderLayout borderLayout1 = new BorderLayout();
    private ArrayList<String> duréelist=new ArrayList<String>();
    private ArrayList<String> languelist=new ArrayList<String>();
    private Info info=new Info(); 
    public JButton getRedemarrer() {
        return redemarrer;
    }
    public JComboBox getJComboBox2() {
        return jComboBox2;
    }

    public JComboBox getJComboBox3() {
        return jComboBox3;
    }

    public BorderLayout getBorderLayout1() {
        return borderLayout1;
    }

    public JComboBox getJComboBox1() {
        return jComboBox1;
    }

    public JButton getJButton1() {
        return jButton1;
    }

    public Painelinfo(ArrayList<String> duréelist,ArrayList<String> languelist) {
        //pour initialiser les arraylist avec les valurs de dureé , leslangues et les pays où on a des echanges
        this.duréelist=duréelist;
        this.languelist=languelist;
       
        //l'interface graphique
        this.setFont(new Font("DejaVu Sans", 0, 12));
        this.setToolTipText("Thomas boloss");
        this.setLayout(null);
        this.setPreferredSize(new Dimension(200,200));
        jComboBox1.setPreferredSize(new Dimension(100, 21));
        jComboBox2.setPreferredSize(new Dimension(100, 21));
        jComboBox3.setSize(new Dimension(100, 21));
        jButton1.setText("Valider");
        redemarrer.setText("Redemarrer");
        for(int i=0;i< info.getStringListdepartement().size();i++){
            jComboBox1.addItem(info.getStringListdepartement().get(i));
        }
        redemarrer.setBounds(new Rectangle(10,240,85,20));
        jButton1.setBounds(new Rectangle(10,220,85,20));
        jComboBox3.setBounds(new Rectangle(10,160,100,25));
        jComboBox2.setBounds(new Rectangle(10,90,100,25));
        jComboBox1.setBounds(new Rectangle(10,40,100,20));
        this.add(redemarrer);
        this.add(jButton1);
        this.add(jComboBox3);
        this.add(jComboBox1);
        this.add(jComboBox2);
        languelist.add("");
        languelist.add("");
        duréelist.add("");
        duréelist.add("");
        for(int i=0;i<languelist.size()-1;i++){
            if(i==0){
                jComboBox2.addItem("Langue");
            }else{
                jComboBox2.addItem(languelist.get(i-1));
            }
        }
        for(int i=0;i<duréelist.size()-1;i++){
            if(i==0){
                jComboBox3.addItem("Durée en semaine");
            }else{
                jComboBox3.addItem(duréelist.get(i-1)+" semaine");
            }
        }
    }
}
