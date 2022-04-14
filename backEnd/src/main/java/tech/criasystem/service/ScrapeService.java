package tech.criasystem.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import tech.criasystem.dto.req.ScrapeDTO;
import tech.criasystem.model.Product;

@Service
public class ScrapeService {

	public List<Product> executeScrape(ScrapeDTO dto) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		List<Product> products = new ArrayList<Product>();
		for(int page=1;page<=3;page++) {
			String searchUrl = "https://"+dto.getState()+".olx.com.br/"+dto.getRegion()+"?o="+page+"&q="+URLEncoder.encode(dto.getSearch(), "UTF-8")+"&sp=2";
			filter(searchUrl,client,products);
		}
		client.close();
		return products;
	}
	
	private void filter(String searchUrl,WebClient client,List<Product> products) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlPage page = client.getPage(searchUrl);
		List<HtmlElement> items = page.getByXPath("//*[@id=\"ad-list\"]//li");
		if (!items.isEmpty()) {
			items.forEach(htmlElement -> {
				Product product = new Product();
			    HtmlAnchor link = ((HtmlAnchor) htmlElement.getFirstByXPath(".//a"));
			    if(verifyIsNull(link)) {
			    	product.setLink(link.getHrefAttribute());
			    }    
			    HtmlHeading2 title = ((HtmlHeading2) htmlElement.getFirstByXPath(".//h2"));
			    if(verifyIsNull(title)) {
			    	String titleText = title.getTextContent();
			    	product.setTitle(titleText);
			    	if(titleText.contains("gb")) {
			    		int indexGb = titleText.indexOf("gb");
			    		String redux = titleText.substring(indexGb-3, indexGb).trim();
			    		if(NumberUtils.isCreatable(redux)) {
			    			product.setStorageSize(Integer.parseInt(redux));
			    		}
			    	}
			    }
			    List<HtmlSpan> spans = htmlElement.getByXPath(".//span");
			    if(verifyIsNull(spans) && !spans.isEmpty()) {
			    	spans.forEach(item -> {
				    	String label = item.getAttribute("aria-label");
				    	if(label.contains("Preço")) {
				    		String replacePrice = item.getTextContent()
									.replace("R$", "").replace(".", "").replace(",", ".");
								Double price = Double.parseDouble(replacePrice);
								product.setPrice(price);
				    	}
				    	if(label.contains("Localização")) {
							product.setAddress(item.getTextContent());
				    	}
			    	});
			    }
			    products.add(product);
			});
		}
		else {
		  System.out.println("No items found !");
		}
	}
	
	private Boolean verifyIsNull(Object obj) {
		return obj == null ?
				false :
					true;
	}
}
