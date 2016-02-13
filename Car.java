package hibernate.working;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	//-----------------//
	// Column mappings //
	//-----------------//
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	private Owner ownerMapping;
	
	@Column(name="year")
	private String year;
	
	@Column(name="make")
	private String make;
	
	@Column(name="model")
	private String model;
	
	@Column(name="color")
	private String color;
	
	//--------------//
	// Constructors //
	//--------------//
	public Car () {
		
	}
	
	public Car (Owner anOwner, String aYear, String aMake, String aModel, String aColor) {
		this.ownerMapping = anOwner;
		this.year = aYear;
		this.make = aMake;
		this.model = aModel;
		this.color = aColor;
	}
	
	//---------------------//
	// Getters and Setters //
	//---------------------//
	public String toString() {
		return "Car: [id="+id+", ownerId="+ownerMapping.toString()+", year="+year+
				", make="+make+", model="+model+", color="+color+"]";
	}
	
	// Car ID
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer anId) {
		this.id = anId;
	}
	
	// Owner ID
	public Owner getOwnerId() {
		return this.ownerMapping;
	}
	
	public void setOwnerId(Owner anOwner) {
		this.ownerMapping = anOwner;
	}
	
	// Year
	public String getYear() {
		return this.year;
	}
	
	public void setYear(String aYear) {
		this.year = aYear;
	}
	
	// Make
	public String getMake() {
		return this.make;
	}
	
	public void setMake(String aMake) {
		this.make = aMake;
	}
	
	// Model
	public String getModel() {
		return this.model;
	}
	
	public void setModel(String aModel) {
		this.model = aModel;
	}
	
	// Color
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String aColor) {
		this.color = aColor;
	}
	
}
