package com.demo.elasticsearch.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elasticsearch.constant.Constants;
import com.demo.elasticsearch.document.UserDocument;

@RestController
@RequestMapping("/")
public class UserController {

	@GetMapping
	public List<UserDocument> getUser() {

		Query query = new NativeSearchQueryBuilder()
			.addAggregation(
				AggregationBuilders.terms("enabledTerm").field("enabled"))
			.withFilter(
				QueryBuilders.prefixQuery("username", "user"))
			.build();

		SearchHits<UserDocument> hits = 
			elasticsearchRestTemplate.search(
				query, UserDocument.class, IndexCoordinates.of(Constants.INDEX_NAME));

		_log.info("*******Search Result*****");
		_log.info("Total:" + hits.getTotalHits());
		_log.info("*************************");

		hits.toList()
			.forEach(hit -> _log.info(hit.getContent().toString()));

		Map<String, Aggregation> aggregations = hits.getAggregations().asMap();

		ParsedTerms terms = (ParsedTerms) aggregations.get("enabledTerm");

		_log.info("*******User enabled stats:*****");

		terms.getBuckets().forEach(bucket -> {

			_log.info(bucket.getKeyAsString() + " : " + bucket.getDocCount());
		});

		return hits
			.stream()
			.map(item -> item.getContent())
			.collect(Collectors.toList());
	}

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	private static final Logger _log = 
		LoggerFactory.getLogger(UserController.class);

}
