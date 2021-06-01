package com.demo.elasticsearch.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "user_")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private boolean enabled;
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "user_city",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "city_id")
	)
	private List<City> cities;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", enabled=" + enabled + ", username=" + username + ", password=" + password
				+ ", cities=" + cities + "]";
	}

}
