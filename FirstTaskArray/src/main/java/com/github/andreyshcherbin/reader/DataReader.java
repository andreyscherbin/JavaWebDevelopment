package com.github.andreyshcherbin.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.andreyshcherbin.exception.ResourceException;

public class DataReader {

	static Logger logger = LogManager.getLogger();
	static final String DELIMETER = ",";

	public String read(String filename) throws ResourceException {
		String dataFromFile = null;
		Path path = Paths.get(filename);		
		if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
			try {
				dataFromFile = Files.lines(path)
						            .reduce((s1, s2) -> s1 + DELIMETER + s2)
						            .orElse("empty");

			} catch (IOException e) {
				logger.error("ioexception exception {} {}", e.getClass(), e.getMessage());
				throw new ResourceException(e);
			}
		} else {
			logger.error("resource exception {}",filename);
			throw new ResourceException("resource exception: " + filename);
		}
		return dataFromFile;
	}
}