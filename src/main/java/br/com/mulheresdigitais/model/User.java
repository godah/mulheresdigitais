package br.com.mulheresdigitais.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String name;

	private String email;

	private String pwd;

	private String description;

	@Nullable
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_user_id")
	private UserType userType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_image_id")
	private UserImage image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserImage getImage() {
		return image;
	}

	public void setImage(UserImage image) {
		this.image = image;
	}
	

}
