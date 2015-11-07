package net.quarksoft.sonar;

import net.quarksoft.sonar.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SonarExporterApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SonarExporterApplication.class);

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
        Project[] proyectos = restTemplate.getForObject("http://localhost:9000/api/projects", Project[].class);
        for (Project proyecto : proyectos) {
            log.info(proyecto.toString());
        }
    }
}
