package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFileTest {

    ClassLoader cl = ZipFileTest.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {
        try ( InputStream zipFile = cl.getResourceAsStream("example/desktop.zip");
              ZipInputStream zis = new ZipInputStream(zipFile)
                ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry())!=null){
                String entryName = entry.getName();
                if (entryName.contains(".xlsx")){
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue()).contains("First Name");
                } else if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Annot_Disc_Napr_Date.pdf");
                } else if (entryName.contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)[1]).contains(" lest 1");

                }
            }
        }
//
       // try (
       //         InputStream is = cl.getResourceAsStream("example/Desktop.zip");
       //         ZipInputStream zis = new ZipInputStream(is)
       // ) {
       //     ZipEntry entry;
       //     while ((entry = zis.getNextEntry()) != null) {
       //         String entryName = entry.getName();
       //         if (entryName.contains(".xlsx")) {
       //             XLS content = new XLS(zis);
       //             assertThat(content.excel.getSheetAt(0).getRow(7).getCell(2).getStringCellValue()).contains("Филиал 1");
       //         } else if (entryName.contains(".pdf")) {
       //             PDF content = new PDF(zis);
       //             assertThat(content.text).contains("PDF");
       //         } else if (entryName.contains(".csv")) {
       //             CSVReader reader = new CSVReader(new InputStreamReader(zis));
       //             List<String[]> content = reader.readAll();
       //             assertThat(content.get(0)[1]).contains("Федоров");
       //         }
       //     }
       // }
    }
}