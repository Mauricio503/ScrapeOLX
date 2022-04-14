package tech.criasystem.dto.req;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

@EntityListeners(AuditingEntityListener.class)
public class ScrapeDTO implements Serializable {
	
	private static final long serialVersionUID = 7533362762167398104L;

	@NotBlank
	private String search;
	@NotBlank
	private String state;
	@NotBlank
	private String region;
	@NotNull
	private Integer time;

	public ScrapeDTO() {
	}

	public ScrapeDTO(String search, String state, String region, Integer time) {
		super();
		this.search = search;
		this.state = state;
		this.region = region;
		this.time = time;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}