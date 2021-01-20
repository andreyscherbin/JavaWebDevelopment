package com.andreyshcherbin.run;

import org.apache.logging.log4j.Logger;

import com.andreyshcherbin.action.DataParser;
import com.andreyshcherbin.action.DataReader;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Main {
	static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("start application");
	}
}
