package tech.criasystem.specs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import tech.criasystem.dto.filter.ScrapeFilter;


public class BotSpecs extends BaseSpecs {

	public static Specification<ScrapeFilter> specByFilter(Optional<ScrapeFilter> filter) {
		return filter.isEmpty() ? null : (root, query, builder) -> {
			Collection<Predicate> predicates = new ArrayList<>();

			predicates.add(equal(builder, root.get("search"), filter.map(ScrapeFilter::getSearch)));
			predicates.add(contains(builder, root.get("state"), filter.map(ScrapeFilter::getState)));
			predicates.add(contains(builder, root.get("region"), filter.map(ScrapeFilter::getRegion)));
			predicates.add(contains(builder, root.get("time"), filter.map(ScrapeFilter::getTime)));
			
			//Expression<String> allCols = concatAll(builder, root.get("search"), root.get("state"),
				//	root.get("region"),root.get("time"));
			//predicates.add(contains(builder, allCols, filter.map(ScrapeFilter::getAny)));
			return toAndArray(builder, predicates);
		};
	}
}