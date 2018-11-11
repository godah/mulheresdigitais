package br.com.mulheresdigitais.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lesson")
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	private String title;

	private String description;

	private String video;

	private Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rel_user_knowledge_id")
	private UserKnowledge userKnowledge;

	public UserKnowledge getUserKnowledge() {
		return userKnowledge;
	}

	public void setUserKnowledge(UserKnowledge userKnowledge) {
		this.userKnowledge = userKnowledge;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
