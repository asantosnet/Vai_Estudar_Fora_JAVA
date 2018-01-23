package InterfaceFraficaAlgoritimo;


import gov.nasa.worldwind.render.PointPlacemark;

import java.util.ArrayList;

public class Marker {
    private PointPlacemark marker;
    private ArrayList<String> departementlist = new ArrayList<String>();
    private ArrayList<String> dur�elist=new ArrayList<String>();
    private String langue;
    private String pays;
    public void setMarker(PointPlacemark marker) {
        this.marker = marker;
    }

    public PointPlacemark getMarker() {
        return marker;
    }

    public void setDepartementlist(ArrayList<String> departementlist) {
        this.departementlist = departementlist;
    }

    public ArrayList<String> getDepartementlist() {
        return departementlist;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getLangue() {
        return langue;
    }

    public String getPays() {
        return pays;
    }

    public ArrayList<String> getDur�elist() {
        return dur�elist;
    }

    public void setDur�elist(ArrayList<String> dur�elist) {
        this.dur�elist = dur�elist;
    }

    /**
     *quand on veut le relier a un echange sans savoir son pays
     * @param marker
     */
    public Marker(PointPlacemark marker) {
        this.marker = marker;
    }
    /**
     *quand on veut le relier a un pays o� a un echange et on veut savoir le pays
     * @param marker
     * @param pays
     */
    public Marker(PointPlacemark marker,String pays){
        this.marker=marker;
        this.pays=pays;
    }
}