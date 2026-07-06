package com.stockmaster.categoria;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stockmaster.producto.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nombre", nullable = false, unique = true, length = 50)
	private String nombre;
	@Column(name = "estado", nullable = false, length = 20)
	private String estado;
	// Crear lista de Productos
	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	private List<Producto> productos;
	
	public Categoria() {
		
	}

	

	



	public Long getId() {
		return id;
	}







	public void setId(Long id) {
		this.id = id;
	}







	public List<Producto> getProductos() {
		return productos;
	}







	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}







	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
