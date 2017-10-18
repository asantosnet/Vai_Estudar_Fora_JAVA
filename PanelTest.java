package InterfaceFraficaAlgoritimo;


import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.placename.PlaceNameLayer;
import gov.nasa.worldwind.util.StatusBar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
/**
 * Le panel qui contient le globe
 */
public class PanelTest extends JPanel {
    private  Model m = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
    private  StatusBar statusBar;
    private  WorldWindowGLCanvas wwd = new WorldWindowGLCanvas();
    private Dimension canvasSize ;

    public StatusBar getStatusBar() {
        return statusBar;
    }
    //private LayerUniversite layeruniversite=new LayerUniversite();
    
    

    public PanelTest() { 
        //Dimension t= janelaprincipal.getJPanel1().getSize();
        this.setLayout(new BorderLayout());
        //pour afficher la terre^^
        wwd.setModel(m);
        //wwd.setPreferredSize(new Dimension(200, 200));
        this.statusBar = new StatusBar();
        this.statusBar.setEventSource(getWwd());
        this.add(wwd,BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.PAGE_END);
       
    }

    public WorldWindowGLCanvas getWwd() {
        return wwd;
    }
    /**
     *methode utilisé pour aditioner une layer à la layerlist avant le compass
     * @param wwd
     * @param layer
     */
    public static void insertBeforeCompass(WorldWindow wwd, Layer layer){
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers){
            if (l instanceof CompassLayer)
                compassPosition = layers.indexOf(l);
            }
    layers.add(compassPosition, layer);
    }
    /**
     *pour inserice un layer dans la layerlist avant le Pacenames
     * @param wwd
     * @param layer
     */
    public static void insertBeforePlacenames(WorldWindow wwd, Layer layer){
    // Insert the layer into the layer list just before the placenames.
        int compassPosition = 0;
        LayerList layers = wwd.getModel().getLayers();
        for (Layer l : layers)
        {
        if (l instanceof PlaceNameLayer)
            compassPosition = layers.indexOf(l);
        }
        layers.add(compassPosition, layer);

    }
   
    
}
   
