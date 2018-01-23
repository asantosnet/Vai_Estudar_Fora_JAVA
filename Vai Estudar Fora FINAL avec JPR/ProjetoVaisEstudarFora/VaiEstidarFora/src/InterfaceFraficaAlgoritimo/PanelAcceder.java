package InterfaceFraficaAlgoritimo;

import java.awt.BorderLayout;

import javax.swing.JPanel;
/**
 * 
 */
public class PanelAcceder extends JPanel {
    private Google google=new Google();
    private Panelgauche pgauche;
    private PanelTest globo=new PanelTest();
    public PanelAcceder(Google google,Panelgauche pgauche,PanelTest globo) {
        this.google=google;
        this.globo=globo;
        this.pgauche=pgauche;
        this.setLayout(new BorderLayout());
        this.add(pgauche, BorderLayout.WEST);
        this.add(globo, BorderLayout.CENTER);
        this.add(google, BorderLayout.NORTH);
    }
}

