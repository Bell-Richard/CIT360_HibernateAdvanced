package hibernate.working;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner {

	//-----------------//
	// Column mappings //
	//-----------------//
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@OneToMany(mappedBy="ownerMapping")
	private Set<Car> cars;
	
	//--------------//
	// Constructors //
	//--------------//
	public Owner () {
		
	}
	
	public Owner (String aFirstName, String aLastName, String aGender) {
		this.firstName = aFirstName;
		this.lastName = aLastName;
		this.gender = aGender;
	}
	
	//---------------------//
	// Getters and Setters //
	//---------------------//
	public String toString() {
		return "Owner: [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + "]";
	}
	
	// Owner ID
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer anId) {
		this.id = anId;
	}
	
	// First Name
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String aName) {
		this.firstName = aName;
	}
	
	// Last Name
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String aName) {
		this.lastName = aName;
	}
	
	// Gender
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(String aGender) {
		this.gender = aGender;
	}
	
	// Cars
	public Set<Car> getCars() {
		return this.cars;
	}
	
	public void setCars(Set<Car> someCars) {
		this.cars = someCars;
	}
	
	// Full name getter
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
}
