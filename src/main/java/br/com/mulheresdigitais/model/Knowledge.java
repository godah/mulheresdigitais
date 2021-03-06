package br.com.mulheresdigitais.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "knowledge")
public class Knowledge {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String knowledgedescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKnowledgedescription() {
		return knowledgedescription;
	}

	public void setKnowledgedescription(String knowledgedescription) {
		this.knowledgedescription = knowledgedescription;
	}

}
