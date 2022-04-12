package tech.criasystem.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void store(/*@Valid @RequestBody PostReqDTO dto*/) {
		try {
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);

			// Set up the URL with the search term and send the request
			String searchUrl = "https://go.olx.com.br/grande-goiania-e-anapolis?q=Iphone%2011&sp=2";
			HtmlPage page = client.getPage(searchUrl);
			List<HtmlElement> items = page.getByXPath("//*[@id=\"ad-list\"]");
			if (!items.isEmpty()) {
			  // Iterate over all elements
			  for (HtmlElement item : items) {

			   System.out.println(item.getTextContent());

			  }
			}
			else {
			  System.out.println("No items found !");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
