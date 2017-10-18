package InterfaceFraficaAlgoritimo;

import gov.nasa.worldwind.Configuration;

import gov.nasa.worldwind.avlist.AVKey;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Application1 {
    public Application1() {
        JFrame frame = new Framee1();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* para fazer aparecer no ponto que vc quer
        Configuration.setValue(AVKey.INITIAL_LATITUDE, 52);
                Configuration.setValue(AVKey.INITIAL_LONGITUDE, 10);
                Configuration.setValue(AVKey.INITIAL_ALTITUDE, 150e4);
        */
        new Application1();
    }
}
