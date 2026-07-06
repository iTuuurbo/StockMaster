package com.stockmaster.venta;

import java.math.BigDecimal;

import com.stockmaster.producto.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne // Una venta tiene muchos DetalleVenta
	@JoinColumn(name = "venta_id", nullable = false) 
	private Venta venta;
	@ManyToOne // Un producto puede aparecer en muchos DetalleVenta
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;
	@Column(name = "cantidad", nullable = false)
	private int cantidad;   // 10 DIGITOS - // 2 DECIMALES
	@Column(name = "precio", nullable = false, precision = 10, scale = 2)
	private BigDecimal precio;
	@Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
	private BigDecimal subtotal;
	
	public DetalleVenta() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	
	
	
}
