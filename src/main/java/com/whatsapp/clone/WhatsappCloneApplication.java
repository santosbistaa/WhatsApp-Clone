package com.whatsapp.clone;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
/*
@SecurityScheme
This annotation tells Swagger:
“My app uses OAuth2 with KeycloakService. Use the Password Grant type,
send bearer JWT tokens in the Authorization header, and here’s the URL to get the token.”
So now, in Swagger UI, we get a green Authorize button that lets us to:
--> Enter our username and password
--> Get a token from KeycloakService
--> Automatically attach the token to API requests for testing
*/
@SecurityScheme(
		name = "keycloak",
		type = SecuritySchemeType.OAUTH2,
		bearerFormat = "JWT",
		scheme = "bearer",
		in = SecuritySchemeIn.HEADER,
		flows = @OAuthFlows(
				password = @OAuthFlow(
						authorizationUrl = "http://localhost:9090/realms/master/protocol/openid-connect/auth",
						tokenUrl = "http://localhost:9090/realms/master/protocol/openid-connect/token"
				)
		)
)
public class WhatsappCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappCloneApplication.class, args);
	}

}
