package org.launchcode;

import org.launchcode.controllers.FileUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LiftoffAppApplication {

	public static void main(String[] args) {

		new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(LiftoffAppApplication.class, args);
	}

}
