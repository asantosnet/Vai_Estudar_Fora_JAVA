package InterfaceFraficaAlgoritimo;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;
/**
 * Le premier panel qui s'ouvre
 */
public class PanelIndentification extends JPanel {
    private JPasswordField code= new JPasswordField();
    private JComboBox type= new JComboBox();
    private GroupLayout layout = new GroupLayout(this);
    private JLabel sivisiteur=new JLabel();
    private JButton aller= new JButton();
    public PanelIndentification() {
        this.setLayout(layout);
        this.setSize(new Dimension(300, 150));
        type.setPreferredSize(new Dimension(100,30));
        type.addItem("Admin");
        type.addItem("Visiteur");
        code.setPreferredSize(new Dimension(200,30));
        sivisiteur.setText("Si vous etes visiteur,tapez sur aller");
        sivisiteur.setPreferredSize(new Dimension(250,10));
        aller.setText("aller");
        aller.setPreferredSize(new Dimension(200,40));
        //interface
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addComponent(type,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(code,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                   .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(sivisiteur,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(aller,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)))
            
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(type,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(code,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                .addComponent(sivisiteur,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                .addComponent(aller,0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
    }

    public JPasswordField getCode() {
        return code;
    }

    public JComboBox getType() {
        return type;
    }

    public JButton getAller() {
        return aller;
    }
}
