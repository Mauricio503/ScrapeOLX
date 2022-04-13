package tech.criasystem.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import tech.criasystem.model.Product;

@Service
public class WriteFileCSVProductService {

	private String CSV_FILE_NAME = "products.csv";
	
	public File filterAndCreateCSV(List<Product> products) {
		try {
			List<String[]> dataLines = new ArrayList<>();
			//columns
			dataLines.add(new String[] 
			  { "Titulo", "Link", "Endereço", "Preço","Armazenamento" });
			products.forEach(product -> {
				if(product.getPrice() != null && product.getPrice() < 3300) {
					dataLines.add(new String[] 
							  { product.getTitle(), product.getLink(), product.getAddress(), product.getPrice().toString(),
									  (product.getStorageSize() != null ? product.getStorageSize().toString():"")});
				}
			});
			return writeFile(dataLines);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}
	
	private File writeFile(List<String[]> dataLines) throws IOException {
	    File csvOutputFile = new File(CSV_FILE_NAME);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    }
	    return csvOutputFile;
	}

	private String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}
}
