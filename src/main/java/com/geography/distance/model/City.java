package com.geography.distance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geography.distance.utils.PointType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "cidade")
@TypeDefs({
	@TypeDef(name = "point", typeClass = PointType.class)
})
public class City {
	
	@Id
	private Long id;

	@Column(name = "nome")
	private String name;

	//private Long uf;

	private Integer ibge;

	/* 1st
	@Column(name = "lat_lon")
	private String geolocation;*/

	// 2nd
	@Type(type = "point")
	@Column(name = "lat_lon", updatable = false, insertable = false)
	private Point location;
	
	@ManyToOne
	@JoinColumn(name = "uf", referencedColumnName = "id")
	@JsonIgnore
	private State state;
	
}
