package com.demo.elasticsearch.command;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import com.demo.elasticsearch.constant.Constants;
import com.demo.elasticsearch.document.UserDocument;
import com.demo.elasticsearch.entity.User;
import com.demo.elasticsearch.repository.UserRepository;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	/**
	 * Reindex all of the documents when start-up
	 */
	@Override
	public void run(String... args) throws Exception {

		List<User> users = userRepository.findAll();

		_log.info(users.toString());

		/*
		 * This delay should be replace by check health Elasticsearch service.
		 */
		_log.info("Start delay of 15 seconds");

		TimeUnit.SECONDS.sleep(15);

		reindexAll(users);

		_log.info("*****REINDEX****");

		_log.info("Finished delay of 15 seconds");
	}

	private IndexQuery indexRequest(User user) {

		UserDocument document = getUserDocument(user);

		return new IndexQueryBuilder()
			.withObject(document)
			.withId(String.valueOf(user.getId()))
			.build();
	}

	private UserDocument getUserDocument(User source) {

		UserDocument target = new UserDocument();

		BeanUtils.copyProperties(source, target, "cities");

		List<String> cityNames = source.getCities()
			.stream()
			.map(city -> city.getName())
			.collect(Collectors.toList());

		target.setCityNames(cityNames);

		return target;
	}

	public void reindexAll(List<User> users) {

		List<IndexQuery> indexQueries = users
			.stream()
			.map(user -> indexRequest(user))
			.collect(Collectors.toList());

		List<IndexedObjectInformation> informations = 
			elasticsearchRestTemplate.bulkIndex(
				indexQueries, IndexCoordinates.of(Constants.INDEX_NAME));

		_log.info("Reindex:" + informations.size() + " elements.");
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	private static final Logger _log = 
		LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

}