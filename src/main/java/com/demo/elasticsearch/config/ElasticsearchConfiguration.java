package com.demo.elasticsearch.config;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import com.demo.elasticsearch.document.UserDocument;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ElasticsearchConfiguration extends ElasticsearchConfigurationSupport {

	@Override
	public ElasticsearchCustomConversions elasticsearchCustomConversions() {

		return new ElasticsearchCustomConversions(
			Arrays.asList(new UserDocumentToMap(), new MapToUserDocument()));
	}

	@WritingConverter
	static class UserDocumentToMap implements Converter<UserDocument, Map<String, Object>> {

		@SuppressWarnings("unchecked")
		@Override
		public Map<String, Object> convert(UserDocument source) {

			ObjectMapper mapper = new ObjectMapper();

			return mapper.convertValue(source, Map.class);
		}
	}

	@ReadingConverter
	static class MapToUserDocument implements Converter<Map<String, Object>, UserDocument> {

		@Override
		public UserDocument convert(Map<String, Object> source) {

			ObjectMapper mapper = new ObjectMapper();

			return mapper.convertValue(source, UserDocument.class);
		}
	}
}
