package InterfaceFraficaAlgoritimo;


import java.awt.Dimension;

import java.awt.GridLayout;


import java.util.ArrayList;

import javax.swing.JPanel;
/**
 * Le panel avec les options que l'utilisateur peut choisir
 */
public class Panelgauche extends JPanel{
    private PanelNumUniversite panelbas =new PanelNumUniversite();
    private ArrayList<String> duréelist=new ArrayList<String>();
    private ArrayList<String> languelist=new ArrayList<String>();
    private Painelinfo panelhaut=new Painelinfo(duréelist,languelist);
    public PanelNumUniversite getPanelbas() {
        return panelbas;
    }

    public Painelinfo getPanelhaut() {
        return panelhaut;
    }

    public void setPanelhaut(Painelinfo panelhaut) {
        this.panelhaut = panelhaut;
    }

    public Panelgauche(ArrayList<String> duréelist,ArrayList<String> languelist) {
        this.duréelist=duréelist;
        this.languelist=languelist;
        setPanelhaut(new Painelinfo(duréelist,languelist));
        this.setSize(new Dimension(300,100));
        this.setLayout(new GridLayout(2,1));
        this.add(panelhaut);
        this.add(panelbas);
    }
    
}
