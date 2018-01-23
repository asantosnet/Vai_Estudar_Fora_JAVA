package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.layers.RenderableLayer;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe responsable pour charge la layer avec les markers des pays qui respectent les conditions choisis par le utilisateur
 */
public class SelectionerPais extends JFrame implements ActionListener {
    private PanelTest globo=new PanelTest();
    private LayorPais layerpais;
    private RenderableLayer layer=new RenderableLayer();
    private Panelgauche panelgauche;
    private ArrayList<Echanges> tabechanges=new ArrayList<Echanges>();
    private ArrayList<Pays> listpays=new ArrayList<Pays>();
    public RenderableLayer getLayer() {
        return layer;
    }
    /**
     *pour le button Valider
     * @param globo
     * @param gauche
     * @param tabechanges
     * @param listpays
     */
    public SelectionerPais(PanelTest globo,Panelgauche gauche,ArrayList<Echanges> tabechanges,ArrayList<Pays> listpays) {
        this.tabechanges=tabechanges;
        this.listpays=listpays;
        this.globo=globo;
        this.panelgauche=gauche;
        
    }

    public void actionPerformed(ActionEvent actionEvent) {
        //pour pouvoir refaire la recherche, C-T-D , pour enlever les markers
        layer.removeAllRenderables();
        if(actionEvent.getSource().equals(panelgauche.getPanelhaut().getJButton1())){
            layerpais=new LayorPais(globo,panelgauche,tabechanges,listpays);
           
            //pour aditioner les marker a la layer, de plus, j'ajout un evenemet à chaque marker
                for(int i=0;i<layerpais.getListmarkers().size();i++){
                    layer.addRenderable(layerpais.getListmarkers().get(i).getMarker());
                    globo.getWwd().addSelectListener(new RoolOverMarker(layerpais.getListmarkers(),layerpais.getListmarkers().get(i).getMarker(),globo,tabechanges));
                }
            
           
            //dans le cas où on n'a pas trouve d'echange
            if(layer.getNumRenderables()==0){
                JOptionPane.showMessageDialog(null,"Le departement n'a pas nous fourni les echanges où il n'y a pas d'echange avec cette combinaison");
            }
            
            //dans le cas où tous les pays sont affiches
            if(this.tousechangesaffihces()){
                JOptionPane.showMessageDialog(null,"tous les pays où il y a echange sont affiches");
            }
            
            //pour qu'on ne le voit plus a 1000m
            layer.setMinActiveAltitude(1165e3);
            globo.insertBeforeCompass(globo.getWwd(),layer);
        }else if(actionEvent.getSource().equals(panelgauche.getPanelhaut().getRedemarrer())){
            JOptionPane.showMessageDialog(null,"Appuier sur valider pour refaire la recherche aprés avoir choisir les critiéres");
        }
    }
    /**
     *pour quand tous les echanges sont afihces
     * @return
     */
    public boolean tousechangesaffihces(){
        boolean tous= false;
        if(panelgauche.getPanelhaut().getJComboBox1().getSelectedItem().equals("Depart")&&panelgauche.getPanelhaut().getJComboBox2().getSelectedItem().equals("Langue")&&panelgauche.getPanelhaut().getJComboBox3().getSelectedItem().equals("Durée en semaine")){
            tous=true;
        }
        return tous;
    }

}

