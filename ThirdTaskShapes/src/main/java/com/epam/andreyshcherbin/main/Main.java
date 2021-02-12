package com.epam.andreyshcherbin.main;

import java.util.List;
import com.epam.andreyshcherbin.entity.Sphere;
import com.epam.andreyshcherbin.exception.ResourceException;
import com.epam.andreyshcherbin.parser.DataParser;
import com.epam.andreyshcherbin.reader.DataReader;
import com.epam.andreyshcherbin.validation.DataFilter;

public class Main {

	public static void main(String[] args) throws ResourceException {
		DataReader reader = new DataReader();
		String data = reader.read("data\\spheres.txt");
		DataFilter filter = new DataFilter();
		List<String> stringSpheres = filter.filterDouble(data);
		DataParser parser = new DataParser();
		List<Sphere> spheres = parser.parseData(stringSpheres);
		for (Sphere sp : spheres) {
			System.out.println(sp);
		}
	}
}
