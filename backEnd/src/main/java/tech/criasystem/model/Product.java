package tech.criasystem.model;

import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
public class Product {
	
	private String title;
	private String link;
	private String address;
	private Double price;
	private Integer storageSize;
	
	public Product() {
	}

	public Product(String title, String link, String address, Double price, Integer storageSize) {
		super();
		this.title = title;
		this.link = link;
		this.address = address;
		this.price = price;
		this.storageSize = storageSize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStorageSize() {
		return storageSize;
	}

	public void setStorageSize(Integer storageSize) {
		this.storageSize = storageSize;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}