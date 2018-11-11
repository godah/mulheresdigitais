package br.com.mulheresdigitais.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timeline_image")
public class TimeLineImage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "image", columnDefinition = "mediumtext")
	private String image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
