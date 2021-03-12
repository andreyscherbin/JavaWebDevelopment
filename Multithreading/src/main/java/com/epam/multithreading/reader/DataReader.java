package com.epam.multithreading.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.multithreading.exception.ResourceException;

public class DataReader {

	private static Logger logger = LogManager.getLogger();

	public List<String> read(String filename) throws ResourceException {
		Path path = Paths.get(filename);
		List<String> data;
		if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
			try (Stream<String> streamLines = Files.lines(path)) {
				data = streamLines.collect(Collectors.toList());
			} catch (IOException e) {
				logger.error("ioexception exception file {} {}", filename, e.getMessage());
				throw new ResourceException(e);
			}
		} else {
			logger.error("resource exception {}", filename);
			throw new ResourceException("resource exception: " + filename);
		}
		return data;
	}
}