package com.demo.elasticsearch.document;

import java.util.List;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user")
public class UserDocument {

	@Id
	@Field(type = FieldType.Long)
	private long id;

	@Field(type = FieldType.Boolean)
	private boolean enabled;

	@Field(type = FieldType.Keyword)
	private String username;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<String> cityNames;

	public UserDocument() {
	}

	public UserDocument(long id, String username, List<String> cityNames) {
		this.id = id;
		this.username = username;
		this.cityNames = cityNames;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getCityNames() {
		return cityNames;
	}

	public void setCityNames(List<String> cityNames) {
		this.cityNames = cityNames;
	}

	@Override
	public String toString() {
		return "UserDocument [id=" + id + ", username=" + username + ", cityNames=" + cityNames + "]";
	}
}
