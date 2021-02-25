/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csv;

/**
 *
 * @author Kowalewski
 */
public class CSVLoader {
    public static CSVData CSVData;
    public static CSVFile CSVFile;
    public static String CSVFilename;
    
    /**
     * @param args the command line arguments
     */
    public static void loadFromFile() {
        CSVFile _c = new CSVFile(CSVLoader.CSVFilename, ",");
        CSVLoader.CSVData = _c.getCSVData();
        CSVLoader.CSVFile = _c;
    }
    
    public static void saveToFile(int CountRows, int CountFields) {
        CSVFile _c = new CSVFile(CSVLoader.CSVData, CountRows, CountFields, CSVLoader.CSVFilename, ",");
    }
    
    public static void main(String[] args) {
        CSVLoader.CSVFilename = "test1.csv";
        CSVLoader.loadFromFile();
        System.out.println(CSVLoader.CSVFile.toString());
        System.out.println(CSVLoader.CSVFile.getCell(2,1));
        CSVLoader.CSVFile.setCell(2, 1, "23");
        System.out.println(CSVLoader.CSVFile.toString());
        
        CSVLoader.CSVData = CSVLoader.CSVFile.getCSVData();
        CSVLoader.CSVFilename = "test1_1.csv";
        CSVLoader.saveToFile(CSVLoader.CSVData.getCountRows(), CSVLoader.CSVData.getCountFields());
    }
}