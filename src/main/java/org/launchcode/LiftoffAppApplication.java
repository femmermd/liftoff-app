package org.launchcode;

import org.launchcode.controllers.FileController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LiftoffAppApplication {

	public static void main(String[] args) {

		new File(FileController.uploadDirectory).mkdir();
		SpringApplication.run(LiftoffAppApplication.class, args);
	}

}
