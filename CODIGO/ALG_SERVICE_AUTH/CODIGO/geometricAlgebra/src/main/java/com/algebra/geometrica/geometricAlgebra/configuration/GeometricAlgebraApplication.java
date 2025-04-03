package com.algebra.geometrica.geometricAlgebra.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * Se Ajusta el inicio de la aplicación para su inicio y por medio de anotaciones el manejo de entidades,
 * repositorios, paquetes y interfaces
 *
 * @author esteban
 */

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.algebra.geometrica.geometricAlgebra.repository"})
@SpringBootApplication(scanBasePackages = {"com.algebra.geometrica.geometricAlgebra"})
@EntityScan(basePackages = {"com.algebra.geometrica.geometricAlgebra.entity"})
@ComponentScan(basePackages = {"com.algebra.geometrica.geometricAlgebra"})
public class GeometricAlgebraApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeometricAlgebraApplication.class, args);
	}

}
