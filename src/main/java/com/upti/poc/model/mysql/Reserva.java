package com.upti.poc.model.mysql;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.upti.poc.model.postgres.Cliente;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idProduto")
	private Produto produto;

	@Column(nullable = false, length = 4)
	private Integer qtde;

	@Column(nullable = false)
	private Date dataReserva;

	@Column(nullable = false)
	private Date dataPrevisaoRetirada;

}
