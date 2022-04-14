package tech.criasystem.controller;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import org.springframework.web.bind.annotation.RequestBody;
import tech.criasystem.dto.req.ScrapeDTO;
import tech.criasystem.model.Product;
import tech.criasystem.service.ScrapeService;
import tech.criasystem.service.TelegramService;
import tech.criasystem.service.WriteFileCSVProductService;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {
	
	@Autowired
	private ScrapeService scrapeService;
	
	@Autowired
	private WriteFileCSVProductService writeFileCSVProductService;
	
	@Autowired
	private TelegramService telegramService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void index(@Valid @RequestBody ScrapeDTO dto) throws TelegramApiException, FailingHttpStatusCodeException, MalformedURLException, IOException {
		List<Product> products = scrapeService.executeScrape(dto);
		File file = writeFileCSVProductService.filterAndCreateCSV(products);	
		telegramService.sendFile(file);
	}

}
