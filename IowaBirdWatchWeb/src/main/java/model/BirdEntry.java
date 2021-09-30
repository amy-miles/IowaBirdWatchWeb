

package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sightings")

/**
 * This class defines the BirdEntry object. 
 * It is annotated to be persisted in the database.
 * These annotated items are created in the database by Java Persistence API
 * @author Amy Miles - almiles
 * CIS 175 - Fall 2021
 * Sep 15, 2021
 */
public class BirdEntry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "COUNTY")
	private String county;
	@Column(name = "BIRD")
	private String bird;
	
	
	/**
	 * Default no arg constructor
	 */
	public BirdEntry() {
		
	}
	
	/**
	 * BirdEntry constructor with params
	 * @param county the county to be set
	 * @param bird the bird to be set
	 */
	public BirdEntry(String county, String bird) {
		this.county = county;
		this.bird = bird;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the bird
	 */
	public String getBird() {
		return bird;
	}

	/**
	 * @param bird the bird to set
	 */
	public void setBird(String bird) {
		this.bird = bird;
	}
	/**
	 * this method returns all of the items in the database by county and bird
	 * @return String
	 */
	public String returnBirdDetails() {
		return this.county + ":" + this.bird;
	}
	
}
