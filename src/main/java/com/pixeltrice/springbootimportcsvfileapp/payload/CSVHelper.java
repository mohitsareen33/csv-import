package com.pixeltrice.springbootimportcsvfileapp.payload;

import com.pixeltrice.springbootimportcsvfileapp.entity.DeveloperTutorial;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";

    // To check if the file is in csv format or not
    public static boolean hasCSVFormat(MultipartFile file){
        if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")){
            return true;
        }
        return false;
    }

    public static List<DeveloperTutorial> csvToTutorials(InputStream is){
        try(
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<DeveloperTutorial> developerTutorialList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords){
                DeveloperTutorial developerTutorial = new DeveloperTutorial(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Title"),
                        csvRecord.get("Description"),
                        Boolean.parseBoolean(csvRecord.get("Published"))
                );

                developerTutorialList.add(developerTutorial);
            }
            return developerTutorialList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<DeveloperTutorial> developerTutorialList){
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);){

            for (DeveloperTutorial developerTutorial: developerTutorialList){
                List<String> data = Arrays.asList(
                        String.valueOf(developerTutorial.getId()),
                        developerTutorial.getTitle(),
                        developerTutorial.getDescription(),
                        String.valueOf(developerTutorial.isPublished())
                );
                csvPrinter.printRecord(data);
//                printRecord will convert data from mysql db which is table form to csv format
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}










