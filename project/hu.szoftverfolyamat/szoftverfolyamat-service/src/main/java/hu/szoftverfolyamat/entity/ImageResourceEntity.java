package hu.szoftverfolyamat.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageResourceEntity {

	private Long id;
	private byte[] image;

	@Id
	@GeneratedValue
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
