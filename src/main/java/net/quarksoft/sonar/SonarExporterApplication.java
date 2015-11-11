package net.quarksoft.sonar;

import net.quarksoft.sonar.domain.File;
import net.quarksoft.sonar.domain.Module;
import net.quarksoft.sonar.domain.Project;
import net.quarksoft.sonar.export.ExcelExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SonarExporterApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SonarExporterApplication.class);

    @Value("${sonar.host}")
    private String host;
    @Value("${sonar.port}")
    private String port;

    @Autowired
    ExcelExporter excelExporter;

    public static void main(String[] args) {
        SpringApplication.run(SonarExporterApplication.class, args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        List<Project> projectsList;

        Project[] projects = restTemplate.getForObject("http://" + host + ":" + port + "/api/projects", Project[].class);
        projectsList = Arrays.asList(projects);
        for (Project project : projectsList) {

            // get modules (model-common, data-access, etc...)
            Module[] modules = restTemplate.getForObject("http://" + host + ":" + port + "/api/resources/index?qualifiers=BRC", Module[].class);
            project.setModulos(Arrays.asList(modules));
            for (Module module : modules) {
                // get module elements
                log.debug("getting info for module key '{}'", module.getKey());
                File[] files = restTemplate.getForObject("http://" + host + ":" + port + "/api/resources/index?qualifiers=FIL" +
                        "&resource=" + module.getKey() +
                        "&depth=-1" +
                        "&metrics=complexity,class_complexity,file_complexity,function_complexity,comment_lines,comment_lines_density," +
                        "public_documented_api_density,public_undocumented_api,duplicated_blocks,duplicated_files,duplicated_lines," +
                        "duplicated_lines_density,sqale_index,sqale_debt_ratio,violations,blocker_violations,critical_violations," +
                        "major_violations,minor_violations,info_violations,false_positive_issues,weighted_violations,violations_density,accessors," +
                        "classes,directories,files,lines,ncloc,functions,projects,public_api,statements,branch_coverage,coverage,line_coverage," +
                        "skipped_tests,tests,test_errors,test_failures,test_success_density", File[].class);
                module.setFiles(Arrays.asList(files));
            }
        }

        log.info("Statistics:");
        for (Project project : projectsList) {
            log.info("\tProject '{}'", project.getName());
            for (Module module : project.getModulos()) {
                log.info("\t\tModule '{}': {} files", module.getName(), module.getFiles().size());
            }
        }

        excelExporter.export("sonar_export.xlsx", projectsList);

        log.info("Export successful");
        System.exit(0);
    }


}
