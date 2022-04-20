package com.nihongo.admin.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "level")
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 40, nullable = false, unique = true)
	private String level;





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
