package InterfaceFraficaAlgoritimo;

import java.util.ArrayList;
import java.util.List;

public class Info {
    
    private ArrayList<String> stringListdepartement = new ArrayList<String>();

    public ArrayList<String> getStringListdepartement() {
        return stringListdepartement;
    }

    /**
     * elle contient  les departements,
     */
    public Info() {
        //les departements de l'INSA
        stringListdepartement.add("Depart");
        stringListdepartement.add("GCU"); 
        stringListdepartement.add("GMC"); 
        stringListdepartement.add("GMD");
        stringListdepartement.add("GMPP");
        stringListdepartement.add("GEN"); 
        stringListdepartement.add("BIM"); 
        stringListdepartement.add("SGM"); 
        stringListdepartement.add("GE"); 
        stringListdepartement.add("IF");
        stringListdepartement.add("BC");
        stringListdepartement.add("GI");                                
    }
}
