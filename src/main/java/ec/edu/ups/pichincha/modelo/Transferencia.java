package ec.edu.ups.pichincha.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transferencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transferencia_Id")
	private int transferenciaId;
	@Column(name = "fecha_Transferencia")
	private Date fechaTransferencia;
	private Double monto;

	@ManyToOne
	@JoinColumn(name = "cuenta_Origen")
	private Cuenta cuentaOrigen;

	@ManyToOne
	@JoinColumn(name = "cuenta_Destino")
	private Cuenta cuentaDestino;

	public int getTransferenciaId() {
		return transferenciaId;
	}

	public void setTransferenciaId(int transferenciaId) {
		this.transferenciaId = transferenciaId;
	}

	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	@Override
	public String toString() {
		return "Transferencia [transferenciaId=" + transferenciaId + ", fechaTransferencia=" + fechaTransferencia
				+ ", monto=" + monto + ", cuentaOrigen=" + cuentaOrigen + ", cuentaDestino=" + cuentaDestino + "]";
	}

}
