package hr.eestec_zg.cvdbbackend;

import hr.eestec_zg.cvdbbackend.domain.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackageClasses = {
        User.class
})
public class CvdbBackendApplication {

	private void runSpring(String[] args) {
		SpringApplication springApplication = new SpringApplication(CvdbBackendApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	public static void main(String[] args) {
		CvdbBackendApplication application = new CvdbBackendApplication();
		application.runSpring(args);
	}
}
