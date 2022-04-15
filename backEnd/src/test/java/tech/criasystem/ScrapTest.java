package tech.criasystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import tech.criasystem.dto.req.ScrapeDTO;
import tech.criasystem.model.Product;
import tech.criasystem.service.ScrapeService;
import tech.criasystem.service.WriteFileCSVProductService;
import tech.criasystem.telegram.TelegramBot;

@WebAppConfiguration
@SpringBootTest
public class ScrapTest {


	@Test
	void TestExecuteEcrapSite() {
		ScrapeService scrapeService = new ScrapeService();
		ScrapeDTO dto = new ScrapeDTO("Iphone 11", "go", "grande-goiania-e-anapolis", 0);
		assertDoesNotThrow(() -> scrapeService.executeScrape(dto));
	}
	
	@Test
	void TestGenerateFileCsv() {
		WriteFileCSVProductService writeFile = new WriteFileCSVProductService();
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("Iphone 11", "olx.com.br/test", "test", 10.0, 2));
		assertDoesNotThrow(() -> writeFile.filterAndCreateCSV(products));
	}
	
	@Test
	void TestConnectionTelegram() {
		TelegramBot telegramBot = new TelegramBot();
		assertDoesNotThrow(() -> telegramBot.getInfoWebhook());
	}
}
