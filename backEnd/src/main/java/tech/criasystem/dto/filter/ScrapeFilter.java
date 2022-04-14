package tech.criasystem.dto.filter;

public class ScrapeFilter {

		private String search;
		private String state;
		private String region;
		private String time;

		public ScrapeFilter(String search, String state, String region, String time) {
			super();
			this.search = search;
			this.state = state;
			this.region = region;
			this.time = time;
		}

		public ScrapeFilter() {
			super();
			// TODO Auto-generated constructor stub
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
