package tech.criasystem.dto.req;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
public class ScrapeDTO implements Serializable {
	
	private static final long serialVersionUID = 7533362762167398104L;

	@NotBlank
	private String search;
	@NotBlank
	private String state;
	@NotBlank
	private String region;
	@NotBlank
	private String time;

	public ScrapeDTO() {
	}

	public ScrapeDTO(String search, String state, String region, String time) {
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}