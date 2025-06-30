package br.com.globalmotors.cars_service.entities;


@Entity
@Table (name = "car_images")
public class ImagesEntity {
	@Column (name = "car")
	private String car;
	@Id
	@GeneratedValue
	private Long id;
	@Column (name = "image")
	private String image;
	
	//Getters and Setters
	
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
