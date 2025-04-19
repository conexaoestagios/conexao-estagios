package br.com.conexaoestagios.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Conexão Estágios AI")
                        .description("API para testes de endpoints das entidades")
                        .version("1.0")
                        .license(new License()
                                .name("Conexão Estágios AI © 2025 - Todos os direitos reservados."))
                        .contact(new Contact()
                                .name("Suporte")
                                .email("doug.candido2704@gmail.com"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
