package ec.edu.ups.pichincha.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cuenta_id")
	private int cuentaId;
	@Column(name = "numero_Cuenta")
	private String numeroCuenta;
	@Column(name = "entidad_Financiera")
	private String entidadFinanciera;
	@Column(name = "fecha_Apertura")
	private Date fechaApertura;
	@Column(name = "saldo")
	private Double saldo;

	public int getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getEntidadFinanciera() {
		return entidadFinanciera;
	}

	public void setEntidadFinanciera(String entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [cuentaId=" + cuentaId + ", numeroCuenta=" + numeroCuenta + ", entidadFinanciera="
				+ entidadFinanciera + ", fechaApertura=" + fechaApertura + ", saldo=" + saldo + "]";
	}

}
