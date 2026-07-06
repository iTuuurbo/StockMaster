package com.stockmaster.usuario;

import java.util.List;

import com.stockmaster.rol.Rol;
import com.stockmaster.venta.Venta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	@Column(name = "apellido", nullable = false, length = 50)
	private String apellido;
	@Column(name = "correo", nullable = false, unique = true, length = 100)
	private String correo;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "estado", nullable = false, length = 20)
	private String estado;
	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false)
	private Rol rol;
	@OneToMany(mappedBy = "usuario")
	private List<Venta> ventas;
	
	public Usuario() {
		
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}



	public List<Venta> getVentas() {
		return ventas;
	}



	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	
	

}
