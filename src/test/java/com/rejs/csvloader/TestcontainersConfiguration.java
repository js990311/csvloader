package com.rejs.csvloader;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	MariaDBContainer<?> mariaDbContainer() {
		return new MariaDBContainer<>(DockerImageName.parse("mariadb:latest"))
				.withExposedPorts(3306)
				.withDatabaseName("mydatabase")
				.withUsername("myuser")
				.withPassword("secret")
		;
	}

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
				.withExposedPorts(5432)
				.withDatabaseName("mydatabase")
				.withUsername("myuser")
				.withPassword("secret")
		;
	}

}
