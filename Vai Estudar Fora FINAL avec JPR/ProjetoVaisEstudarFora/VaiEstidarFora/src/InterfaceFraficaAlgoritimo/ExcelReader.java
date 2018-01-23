package InterfaceFraficaAlgoritimo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * classe responsable pour lire un fichier excel depuis un adrresse
 */
public class ExcelReader {
    public ExcelReader() {
    }
    public ArrayList<Echanges> recuperetabechange (String adresse){
        ArrayList<Echanges> tabechange=new ArrayList<Echanges>();
       InputStream inputStream = null;
          try {
               inputStream = new FileInputStream(new File(adresse));
               Workbook workbook = WorkbookFactory.create(inputStream);
               int numberOfSheet = workbook.getNumberOfSheets();
              
           //pour recuperer les informations du fichier excel
           int i =0;
           while(workbook.getSheetAt(0).getRow(i)!=null){
                           String pays = workbook.getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
                           String ville = workbook.getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
                           String langue = workbook.getSheetAt(0).getRow(i).getCell(2).getStringCellValue();
                           String université = workbook.getSheetAt(0).getRow(i).getCell(3).getStringCellValue();
                           String departement =workbook.getSheetAt(0).getRow(i).getCell(4).getStringCellValue();
                           int durée = (int)(workbook.getSheetAt(0).getRow(i).getCell(5).getNumericCellValue());
                           double latitude = (workbook.getSheetAt(0).getRow(i).getCell(6).getNumericCellValue());
                           double longitude = (workbook.getSheetAt(0).getRow(i).getCell(7).getNumericCellValue());
           tabechange.add(new Echanges(pays, ville, langue, université,departement, durée, latitude,longitude));
            i++;
            }
    }catch (FileNotFoundException e) {
        e.printStackTrace(); 
        JOptionPane.showMessageDialog(null, "Fichier pas trouvé");
    }catch (InvalidFormatException e) {
        JOptionPane.showMessageDialog(null, "Pas le bon format");
        e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }
    return tabechange;   
    }  
}