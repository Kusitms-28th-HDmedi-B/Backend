package kusitms.hdmedi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(servers = {@Server(url = "https://hdmedi.site")})
@SpringBootApplication
public class HdmediApplication {

	public static void main(String[] args) {
		SpringApplication.run(HdmediApplication.class, args);
	}

}
