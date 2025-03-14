package com.rejs.csvloader;

import org.springframework.boot.SpringApplication;

public class TestCsvloaderApplication {

	public static void main(String[] args) {
		SpringApplication.from(CsvloaderApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
