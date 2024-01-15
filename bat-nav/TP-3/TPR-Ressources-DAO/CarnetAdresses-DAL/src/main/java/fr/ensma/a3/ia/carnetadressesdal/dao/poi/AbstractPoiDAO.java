package fr.ensma.a3.ia.carnetadressesdal.dao.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.ensma.a3.ia.carnetadressesdal.dao.IDao;

/**
 * DAO abstrait pour la source Excel : propose les opérations d'ouverture et de
 * lecture/écriture dans la base.
 *
 * @author Mikky Richard
 *
 * @param <T>
 */
public abstract class AbstractPoiDAO<T> implements IDao<T> {

    private static final String FILE__NAME = System.getProperty("user.dir")
            + "/CarnetAdresses-DAL/carnetadr_bdd/BDD.xlsx";
    private String FILE__NAME_PROP;

    public AbstractPoiDAO() {
        Properties dbprops = new Properties();
        try {
            dbprops.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            FILE__NAME_PROP = System.getProperty("user.dir") + dbprops.getProperty("excelpath");
            //System.out.println(FILE__NAME_PROP);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected XSSFWorkbook openBase() {
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE__NAME_PROP));
            return new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    protected void writeBase(XSSFWorkbook bdd) {
        try {
            FileOutputStream output = new FileOutputStream(FILE__NAME);
            bdd.write(output);
            bdd.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected static void removeRow(Sheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
        if (rowIndex == lastRowNum) {
            Row removingRow = sheet.getRow(rowIndex);
            if (removingRow != null) {
                sheet.removeRow(removingRow);
            }
        }
    }

    protected void closeBase(XSSFWorkbook bdd) {
        if (bdd != null) {
            try {
                bdd.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
