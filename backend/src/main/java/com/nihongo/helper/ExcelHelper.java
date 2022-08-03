package com.nihongo.helper;

import com.nihongo.entity.Vocabulary;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String level;
    public static String cateVocabulary;


    static String[] HEADERs = {"Id", "Từ vựng", "Hán tự", "Hiragana", "Ý nghĩa", "Ví dụ", "Audio"};
    static String SHEET = "Vocabularies";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Vocabulary> excelToVocabularies(InputStream is) throws IOException {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                int rowNum = currentRow.getRowNum();
                if (rowNumber == 0) {
                    cateVocabulary = currentRow.getCell(1).getStringCellValue();
                    level = currentRow.getCell(3).getStringCellValue();
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                int cellIdx = 0;

                if (rowNum >= 2) {
                    Vocabulary vocabulary = new Vocabulary();
                    vocabulary.setLevel(level);
                    vocabulary.setCategoryVocabulary(cateVocabulary);
                    while (cellsInRow.hasNext()) {
                        Cell currentCell = cellsInRow.next();
                        switch (cellIdx) {
                            case 0:
                                vocabulary.setVocabulary_id((long) currentCell.getNumericCellValue());
                                break;
                            case 1:
                                vocabulary.setWord(currentCell.getStringCellValue());
                                break;
                            case 2:
                               vocabulary.setKanJ(currentCell.getStringCellValue());
                                break;
                            case 3:
                                vocabulary.setRead(currentCell.getStringCellValue());
                                break;
                            case 4:
                                vocabulary.setMean(currentCell.getStringCellValue());
                                break;
                            case 5:
                                vocabulary.setExample(currentCell.getStringCellValue());
                                break;
                            case 6:
                                vocabulary.setAudio(currentCell.getStringCellValue());
                                break;
                            case 7:
                                vocabulary.setExample1(currentCell.getStringCellValue());
                                break;

                            default:
                                break;
                        }
                        cellIdx++;
                    }
                    vocabularies.add(vocabulary);
                }
            }
            workbook.close();
            return vocabularies;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}
