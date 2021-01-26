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

	public String read(String filename) throws ResourceException {
		String data = null;
		Path path = Paths.get(filename);
		String delimeter = ",";
		if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
			try {
				data = Files.lines(path).reduce((s1, s2) -> s1 + delimeter + s2).orElse("empty");

			} catch (IOException e) {
				logger.error("ioexception exception {} {}", e.getClass(), e.getMessage());
				throw new ResourceException(e);
			}
		} else {
			logger.error("resource exception");
			throw new ResourceException("resource exception");
		}
		return data;
	}
}