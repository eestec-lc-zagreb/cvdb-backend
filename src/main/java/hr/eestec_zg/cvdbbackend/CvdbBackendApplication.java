package hr.eestec_zg.cvdbbackend;

import hr.eestec_zg.cvdbbackend.domain.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackageClasses =  {
		User.class
})
public class CvdbBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvdbBackendApplication.class, args);
	}
}
