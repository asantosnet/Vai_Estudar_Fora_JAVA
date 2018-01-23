package InterfaceFraficaAlgoritimo;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;



/**
 * classe responsable pour le panel qui contient le text a changer.
 * Ici on a aussi les listenerss pour le button de ce panel
 */
public class ModifierBaseDeDon�esListener extends JPanel implements ActionListener{
    private String nompays="";
    private String nomuniversit�="";
    private String description="";
    private String url="";
    private JTextPane textpanedesc=new JTextPane();
    private JPanel paneldesc=new JPanel();
    private JButton valider=new JButton();
    /**
     *si on veut modifie un pays, on doit dire que unoniversit�==null et url==null
     *                  un pays, on dit que nonpays==null et ou descripion==null ou url==null
     * @param nompays
     * @param nomuniversit�
     * @param description
     * @param url
     */
    public ModifierBaseDeDon�esListener(String nompays,String nomuniversit�,String description,String url) {  
        this.nompays=nompays;
        this.nomuniversit�=nomuniversit�;
        this.description=description;
        this.url=url;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        paneldesc.setPreferredSize(new Dimension(600,600));
        paneldesc.setLayout(null);
        textpanedesc.setBounds(new Rectangle(580,530,10,10));
        valider.setPreferredSize(new Dimension(50,50));
        valider.setText("Valider");
        JScrollPane panedescscroll=new JScrollPane(textpanedesc);
        
        panedescscroll.setBounds(new Rectangle(10,10,580,520));
        valider.setBounds(new Rectangle(250,550,100,50));
        
        paneldesc.add(panedescscroll);
        paneldesc.add(valider);
        
        //pour savoir s'il a le droit de changer la basededon�e
        boolean codevraie=false;
        if(JOptionPane.showInputDialog("Tapez le code").equals("ThomasBoloss")){
            codevraie=true;
        }
        //pour pouvoir savoir quel don�e il veut changer 
        if(this.nompays==null&&codevraie&&this.url==null){
            textpanedesc.setText(description);
            textpanedesc.repaint();
            //listener responsable pour effectuer l'update de la basededon�e
            valider.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changerdescuniversit�listener(e);
                }    
            });
            
            JOptionPane.showMessageDialog(null,paneldesc,"Vous pouvez mnt changer les informations sur le universit�",JOptionPane.INFORMATION_MESSAGE);
        }else if(this.nomuniversit�==null&&codevraie&&this.url==null){
            textpanedesc.setText(description);
            //listener responsable pour effectuer l'update de la basededon�e
            valider.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changerdescppayslistener(e);
                }    
            });
            
            JOptionPane.showMessageDialog(null,paneldesc,"Vous pouvez mnt changer les informations sur le pays",JOptionPane.INFORMATION_MESSAGE);
        }else if(codevraie&&this.nompays==null){
            textpanedesc.setText(url);
            //listener responsable pour effectuer l'update de la basededon�e
            valider.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    changerurllistener(e);
                }});
            
            JOptionPane.showMessageDialog(null,paneldesc,"Vous pouvez mnt changer les urls",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de changer ses informations");
        }
    }   
    /**
     *listener responsable pour la modification de description universit�
     * @param e
     */
    public void changerdescuniversit�listener(ActionEvent e){
        AdditionerBaseDeDon�es add=new AdditionerBaseDeDon�es();
        add.changerdecripitionuniversit�(this.nomuniversit�,this.textpanedesc.getText());
    }   
    /**
     *methode responsable pour la modification de la descrption du pays
     * @param e
     */
    public void changerdescppayslistener(ActionEvent e){
        AdditionerBaseDeDon�es add=new AdditionerBaseDeDon�es();
        System.out.println(this.textpanedesc.getText());
        add.changerdescriptionpays(this.nompays,this.textpanedesc.getText());
    }
    /**
     *methode responsable pour la modification des urls associ�es a l'universit�
     * @param e
     */
    public void changerurllistener(ActionEvent e){
        AdditionerBaseDeDon�es add=new AdditionerBaseDeDon�es();
        add.changerurl(this.textpanedesc.getText(),this.nomuniversit�);
    }
}
