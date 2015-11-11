package net.quarksoft.sonar.export;

import net.quarksoft.sonar.domain.File;
import net.quarksoft.sonar.domain.Module;
import net.quarksoft.sonar.domain.Project;
import net.quarksoft.sonar.json.JsonMetric;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Oswaldo on 11/11/15.
 */
@Component
public class ExcelExporter {
    private static final Logger log = LoggerFactory.getLogger(ExcelExporter.class);

    public void export(String fileName, List<Project> projects) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        log.info("Exporting projects...");
        exportProjectInfo(projects, workbook);

        log.info("Exporting modules...");
        exportModuletInfo(projects, workbook);

        log.info("Exporting files...");
        exportFileInfo(projects, workbook);

        log.info("Exporting metrics...");
        exportMetrics(projects, workbook);

        try (FileOutputStream out = new FileOutputStream(new java.io.File(fileName))) {
            workbook.write(out);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportProjectInfo(List<Project> projects, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Projects");
        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        headers("ProjectId|ProjectKey|ProjectName|Scope|Qualifier", row);
        for (Project p : projects) {
            row = sheet.createRow(rownum++);
            int cellnum = 0;

            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(p.getId());

            cell = row.createCell(cellnum++);
            cell.setCellValue(p.getKey());

            cell = row.createCell(cellnum++);
            cell.setCellValue(p.getName());

            cell = row.createCell(cellnum++);
            cell.setCellValue(p.getScope());

            cell = row.createCell(cellnum++);
            cell.setCellValue(p.getQualifier());
        }
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void exportModuletInfo(List<Project> projects, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Modules");
        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        headers("ProjectId|ModuleId|ModuleKey|ModuleName|Scope|Qualifier|ModuleLname|ModuleVersion|ModuleDescription", row);
        for (Project p : projects) {
            for (Module m : p.getModulos()) {
                row = sheet.createRow(rownum++);
                int cellnum = 0;

                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(p.getId());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getId());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getKey());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getName());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getScope());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getQualifier());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getLname());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getVersion());

                cell = row.createCell(cellnum++);
                cell.setCellValue(m.getDescription());
            }
        }
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void exportFileInfo(List<Project> projects, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Files");
        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        headers("ModuleId|FileId|FileKey|FileName|Scope|Qualifier|FileLname|FileLang", row);
        for (Project p : projects) {
            for (Module m : p.getModulos()) {
                for (File f : m.getFiles()) {
                    row = sheet.createRow(rownum++);
                    int cellnum = 0;

                    Cell cell = row.createCell(cellnum++);
                    cell.setCellValue(m.getId());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getId());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getKey());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getName());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getScope());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getQualifier());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getLname());

                    cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getLang());
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void exportMetrics(List<Project> projects, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet("Metrics");
        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        String metricsHeaderStr = "FileId|complexity|class_complexity|file_complexity|function_complexity|comment_lines|comment_lines_density|" +
                "public_documented_api_density|public_undocumented_api|duplicated_blocks|duplicated_files|duplicated_lines|" +
                "duplicated_lines_density|sqale_index|sqale_debt_ratio|violations|blocker_violations|critical_violations|" +
                "major_violations|minor_violations|info_violations|false_positive_issues|weighted_violations|violations_density|accessors|" +
                "classes|directories|files|lines|ncloc|functions|projects|public_api|statements|branch_coverage|coverage|line_coverage|" +
                "skipped_tests|tests|test_errors|test_failures|test_success_density";
        headers(metricsHeaderStr, row);
        for (Project p : projects) {
            for (Module m : p.getModulos()) {
                for (File f : m.getFiles()) {
                    row = sheet.createRow(rownum++);
                    int cellnum = 0;

                    Cell cell = row.createCell(cellnum++);
                    cell.setCellValue(f.getId());

                    String[] headerNames = metricsHeaderStr.split("\\|");
                    for (String header : headerNames) {
                        if (!header.equals("FileId")) {
                            cell = row.createCell(cellnum++);
                            cell.setCellValue(getMetricFromFile(header, f));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private String getMetricFromFile(String key, File f) {
        List<JsonMetric> metrics = Arrays.asList(f.getJsonMetrics());
        Optional<JsonMetric> metric = metrics.parallelStream().filter(x -> x.getKey().equals(key)).findFirst();
        return metric.isPresent() ? metric.get().getValue() : "";
    }

    private void headers(String headerStr, Row row) {
        int cellnum = 0;
        String[] values = headerStr.split("\\|");
        for (String value : values) {
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(value);
        }
    }
}
