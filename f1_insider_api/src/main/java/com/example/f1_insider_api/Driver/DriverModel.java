package com.example.f1_insider_api.Driver;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "drivers")
public class DriverModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long driverId;

	private int driver_number;
	private String broadcast_name;
	private String full_name;
	private String name_acronym;
	private String team_name;
	private String team_colour;
	private String first_name;
	private String last_name;
	private String headshot_url;
	private String country_code;
	private int session_key;
	private int meeting_key;
	
	public DriverModel() {
        // Deja este constructor vacío o inicializa las variables en valores predeterminados si es necesario
    }

	public DriverModel(int driver_number, String broadcast_name, String full_name, String name_acronym,
			String team_name, String team_colour, String first_name, String last_name, String headshot_url,
			String country_code, int session_key, int meeting_key) {
		this.driver_number = driver_number;
		this.broadcast_name = broadcast_name;
		this.full_name = full_name;
		this.name_acronym = name_acronym;
		this.team_name = team_name;
		this.team_colour = team_colour;
		this.first_name = first_name;
		this.last_name = last_name;
		this.headshot_url = headshot_url;
		this.country_code = country_code;
		this.session_key = session_key;
		this.meeting_key = meeting_key;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public int getDriver_number() {
		return driver_number;
	}

	public void setDriver_number(int driver_number) {
		this.driver_number = driver_number;
	}

	public String getBroadcast_name() {
		return broadcast_name;
	}

	public void setBroadcast_name(String broadcast_name) {
		this.broadcast_name = broadcast_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getName_acronym() {
		return name_acronym;
	}

	public void setName_acronym(String name_acronym) {
		this.name_acronym = name_acronym;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_colour() {
		return team_colour;
	}

	public void setTeam_colour(String team_colour) {
		this.team_colour = team_colour;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getHeadshot_url() {
		return headshot_url;
	}

	public void setHeadshot_url(String headshot_url) {
		this.headshot_url = headshot_url;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public int getSession_key() {
		return session_key;
	}

	public void setSession_key(int session_key) {
		this.session_key = session_key;
	}

	public int getMeeting_key() {
		return meeting_key;
	}

	public void setMeeting_key(int meeting_key) {
		this.meeting_key = meeting_key;
	}
	
	
}
