package InterfaceFraficaAlgoritimo;


import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.GridLayout;
;

import java.awt.Insets;

import java.awt.RenderingHints;

import java.awt.image.BufferedImage;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;



import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * Panel qui contient les informations sur l'echange plus le globe et les photos
 */
public class PanelInfoUnivglobe extends JPanel {
    private Google google=new Google();
    private PanelTest globe=new PanelTest();
    //le panel où les fotos serton afiches
    private JPanel fotos=new JPanel();
    //le scrollpane où les panel fotos sera mis
    private JScrollPane fotosscroll=new JScrollPane();
    private String université=new String();
    private Marker marker;
    private ArrayList<Pays> payslist=new ArrayList<Pays>();
    private ArrayList<Université> universitelist=new ArrayList<Université>();
    private PanelInfoUniv panelinfouniv;
    //on initialise un arraylist avec les buttons, car on ne sais pas la quantite de imgens qu'on aurra
    //on vas creer une arraylist de arraylist pour enregistrer l'image dans sont format initial
    private ArrayList<ArrayList<JButton>> arraybutton=new ArrayList<ArrayList<JButton>>();
    //pour avoir le echange qu'on vas utiliser
    public PanelInfoUniv getPanelinfouniv() {
        return panelinfouniv;
    }

    public Google getGoogle() {
        return google;
    }

    public PanelTest getGlobe() {
        return globe;
    }

    public JPanel getFotos() {
        return fotos;
    }


    public void setPanelinfouniv(PanelInfoUniv panelinfouniv) {
        this.panelinfouniv = panelinfouniv;
    }


    public PanelInfoUnivglobe(PanelTest globe,Google google,String université,Marker marker,ArrayList<Pays> payslist,ArrayList<Université> universitelist) {
        //initialisation 
        this.payslist=payslist;
        this.universitelist=universitelist;
        this.globe=globe;
        this.google=google;
        this.université=université;
        this.marker=marker;
        setPanelinfouniv(new PanelInfoUniv(université,marker,payslist,universitelist));
        //pour la partie d'interface
        //un jpanel pour mettre le globe e ces information ensemble
        JPanel globeinfo=new JPanel();
        globeinfo.setLayout(new GridLayout(0,2));
        globeinfo.add(panelinfouniv);
        globeinfo.add(globe);
        //pour mettre le globeinfo, le google et le pohotos, le 
        this.setLayout(new GridBagLayout());
         
        GridBagConstraints a = new GridBagConstraints();
        GridBagConstraints  b= new GridBagConstraints();
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();
        GridBagConstraints e = new GridBagConstraints();
        
        //on aditiones laes arrays
        ArrayList<JButton> buttonarrayum=new ArrayList<JButton>();
        ArrayList<JButton> buttonarraydois=new ArrayList<JButton>();
        arraybutton.add(buttonarrayum);
        arraybutton.add(buttonarraydois);
        
       
       
       
        fotos.setBackground(Color.BLACK);
        this.fotosscroll=new JScrollPane(fotos);
        this.fotosscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.fotosscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       
    
         //pour creer la arraylist de boutons avec les images
        this.ajouterphotos(this.recupererurls());
        fotos.setLayout(new GridLayout(0,arraybutton.get(1).size()));
        
        //mnt que la array est cree,pour créer la quantité de cases nescessaires;
        //pour ajouter les photos au panel
        for(int i=0;i<arraybutton.get(1).size();i++){
            arraybutton.get(1).get(i).setBackground(Color.black);        //je vas aussi add un listener a chaque boutton
            arraybutton.get(1).get(i).addActionListener(new ButtonImage(arraybutton.get(0).get(i)));
            //j'adition les bouttons a au panel
            fotos.add(arraybutton.get(1).get(i));
        }
        fotos.repaint();
        //pour aditioner le fotos au scrollpane
        this.fotosscroll=new JScrollPane(fotos);
        this.fotosscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.fotosscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
        a.fill=GridBagConstraints.BOTH;
        a.weighty=0.2;
        a.gridwidth = 2;
        a.gridx = 0;
        a.gridy = 0;
        this.add(google, a);
        b.fill=GridBagConstraints.BOTH;
        b.weighty=3;
        b.weightx=2;
        b.ipadx=400;
        b.gridwidth = 1;
        b.gridx = 0;
        b.gridy = 1;
        this.add(globeinfo, b);
        d.fill=GridBagConstraints.BOTH;
        d.weighty=1;
        d.weightx=1;
        d.gridwidth = 2;
        d.gridx = 0;
        d.gridy = 2;
        this.add(fotosscroll, d);
        
    }
    /**
     * methode responsable pour recuperer les urls
     */
    public ArrayList<String>   recupererurls(){
        //pour recuperer le nom de la universite
        String[]nomtab=new String[5];//ne serait jamais plus que 5
        nomtab=université.split(",");
        String nomuni=nomtab[1];
        
        //pour recuperer les URLs associés
        boolean pastrouve=true;
        int i=0;
        ArrayList<String> urls=new ArrayList<String>();
        while(pastrouve&&i<universitelist.size()){
            if(nomuni.equals(universitelist.get(i).getUniversité())){
                //les urls sont separes par ; donc
               String[] urlstab=universitelist.get(i).getPhoto().split(";");
               //pour creer la arraylist
               for(int b=0;b<urlstab.length;b++){
                    urls.add(urlstab[b]);
                }
               pastrouve=false;    
            }    
            i++;
        }
        
        return urls;
        
    }
    /**
     *methode utilise pour ajouter les images dans les Jbuttons, 
     * @param urls
     */
    public void ajouterphotos(ArrayList<String> urls){
        int size=0;
        for(int i=0;i<urls.size();i++){
            try {
                if(urls.get(i)!=null){
                JButton button=new JButton();
                button.setMargin(new Insets(10, 10, 10, 10));
                button.setRolloverEnabled(true);
                button.setForeground(Color.black);
                JButton buttonurl=new JButton();
                //on recupere les imgaens
                java.net.URL localization = new URL(urls.get(i));
                ImageIcon imageicon = new ImageIcon(localization);
                //pour ajouter l'imgae avec la taille changé
                button.setIcon(new ImageIcon(this.chagertaille(imageicon,150,150)));
                buttonurl.setIcon(imageicon);
                //finalement, on ajoute le button a l'array
                arraybutton.get(0).add(buttonurl);
                arraybutton.get(1).add(button);
                size++;
                }else{
                }
            } catch (MalformedURLException e) {
                System.out.println("l'adresse de l'image est mauvaise");
            }catch (IOException ex) {
                System.out.println("IOException");
            }
        }
        if(size<4){
            for(int b=0;b<(4-size);b++){
                JButton button=new JButton();
                button.setLabel("Imagen non existante");
                arraybutton.get(0).add(button);
                arraybutton.get(1).add(button);
            }
        }
    }
    
    /**
     *methode fait pour changer la taille de l'image
     * @param ii
     * @return
     */
    public BufferedImage chagertaille(ImageIcon ii,int altura,int largura){
        BufferedImage bi = null;
            try {
                bi = new BufferedImage(altura, largura, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = (Graphics2D) bi.createGraphics();
                g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
                g2d.drawImage(ii.getImage(), 0, 0, altura, largura, null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return bi;
    }
}