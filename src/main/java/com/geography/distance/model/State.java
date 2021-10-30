package com.geography.distance.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity(name = "State")
@Table(name = "estado")
@TypeDefs({
	@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class State {
	
	@Id
	private Long id;

	@Column(name = "nome")
	private String name;
	
	private String uf;

	private Integer ibge;

	@ManyToOne
	@JoinColumn(name = "pais", referencedColumnName = "id")
	private Country country;

	@Type(type = "jsonb")
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "ddd", columnDefinition = "jsonb")
	private List<Integer> ddd;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	private List<City> cities;

}
