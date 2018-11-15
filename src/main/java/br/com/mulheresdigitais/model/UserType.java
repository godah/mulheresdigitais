package br.com.mulheresdigitais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_user")
public class UserType {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String typeuserdescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeuserdescription() {
		return typeuserdescription;
	}

	public void setTypeuserdescription(String typeuserdescription) {
		this.typeuserdescription = typeuserdescription;
	}

}
