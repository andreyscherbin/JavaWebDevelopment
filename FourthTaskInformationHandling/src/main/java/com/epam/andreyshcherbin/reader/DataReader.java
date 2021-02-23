package com.epam.andreyshcherbin.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.andreyshcherbin.exception.TextException;

public class DataReader {

	private static Logger logger = LogManager.getLogger();
	private static final String DELIMETER = "\n";

	public String read(String filename) throws TextException {
		Path path = Paths.get(filename);
		String data;
		if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
			try (Stream<String> streamLines = Files.lines(path)) {
				data = streamLines.reduce((s1, s2) -> s1 + DELIMETER + s2).orElse("empty");
			} catch (IOException e) {
				logger.error("ioexception exception file {} {}", filename, e.getMessage());
				throw new TextException(e);
			}
		} else {
			logger.error("resource exception {}", filename);
			throw new TextException("resource exception: " + filename);
		}
		return data;
	}
}