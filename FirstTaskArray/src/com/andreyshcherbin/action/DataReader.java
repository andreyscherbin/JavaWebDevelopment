package com.andreyshcherbin.action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.andreyshcherbin.exception.ResourceException;

public class DataReader {
	public String read(String filename) throws ResourceException {
		String data = null;
		Path path = Paths.get(filename);
		String delimeter = ",";
		if (Files.exists(path) && !Files.isDirectory(path) && Files.isReadable(path)) {
			try {
				StringBuilder builder = new StringBuilder();
				data = Files.lines(path).reduce((s1, s2) -> s1 + delimeter + s2).orElse("empty");

			} catch (IOException e) {
				throw new ResourceException(e);
			}
		}
		return data;
	}
}