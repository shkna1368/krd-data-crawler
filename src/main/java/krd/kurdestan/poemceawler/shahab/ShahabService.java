/*
package krd.kurdestan.poemceawler.shahab;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Component
public class ShahabService implements CommandLineRunner {

    private final WebClient.Builder webClientBuilder;

    public ShahabService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public void run(String... args)  {


List<Double> numbers=readExcel();

int index=0;
for (Double number:numbers) {

    String url="https://club.tara-club.ir/club/api/v1/profiles/"+number.longValue()+"/accounts";






    try {

        List<Map<String , String>> result = webClientBuilder.build()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyNzA3NDUiLCJpYXQiOjE3MTM2MTcyNTMsImV4cCI6MTcxNDQ4MTI1MywiZGV2aWNlIjoiYWhtYWRpX2FkbWluX2JhY2tvZmZpY2UiLCJyb2xlIjoiUk9MRV9CQUNLT0ZGSUNFIn0.PsHLknuZa6SRVXzRvjNAk2-f27YyWdMUYkaDKeTacuRjVUgQUWwnEu1-vtJ2HMiWqEQhI6fWd2vQx0uy9YdbEg")
                .retrieve()
                .bodyToMono(List.class)
                .block();


        System.out.println("Success:"+number.longValue());
        // return imageData;
    } catch (Exception e) {

        System.out.println("Fail:"+number.longValue());
        System.out.println("FailCause:"+e.toString());
       // throw new RuntimeException("Huggingface api call failed");
    }

    System.out.println(index+"/"+numbers.size());
    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {

    }
    index++;
}


        System.out.println("Finished");


    }


    public List<Double> readExcel() {
        FileInputStream file = null;

        List<Double > list=new ArrayList<>();
        try {
            file = new FileInputStream(new File("D:\\profileIds.xlsx"));


//Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

//Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

//Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    double num=  cell.getNumericCellValue();
list.add(num);
                    //Check the cell type and format accordingly

                }
            }
            System.out.println("");
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());

        }



return list;
    }

    public void writeExcel(Map<Long,String> data)  {

   */
/*     XSSFWorkbook workbook = new XSSFWorkbook();

//Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

//Prepare data to be written as an Object[]
        Map<String, String> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[] {1, "Amit", "Shukla"});
        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
        data.put("4", new Object[] {3, "John", "Adwards"});
        data.put("5", new Object[] {4, "Brian", "Schultz"});

//Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {

            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

//Write the workbook in file system
        try {
            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }*//*



    }


}
*/
