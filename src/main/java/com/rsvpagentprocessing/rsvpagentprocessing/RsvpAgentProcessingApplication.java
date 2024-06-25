package com.rsvpagentprocessing.rsvpagentprocessing;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class RsvpAgentProcessingApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(RsvpAgentProcessingApplication.class, args);
	}
}
