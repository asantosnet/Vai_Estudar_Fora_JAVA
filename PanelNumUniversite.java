package InterfaceFraficaAlgoritimo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;


import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * 
 */
public class PanelNumUniversite extends JPanel{
    private JPanel jPanel1 = new JPanel();
    private JLabel jLabel1 = new JLabel();
    private JList jList2 = new JList();
    private BorderLayout borderLayout1 = new BorderLayout();
    
    public PanelNumUniversite() {
        
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(400, 300));
        this.setBounds(new Rectangle(5, 5, 235, 260));
        this.setLayout(borderLayout1);
        jList2.setBackground(Color.gray);
        this.add(jLabel1, BorderLayout.NORTH);
        this.add(jList2, BorderLayout.CENTER);
        
    }
   
}
