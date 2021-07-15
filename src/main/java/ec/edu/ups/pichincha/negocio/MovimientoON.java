package ec.edu.ups.pichincha.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.pichincha.DAO.CuentaDAO;
import ec.edu.ups.pichincha.DAO.MovimientoDAO;
import ec.edu.ups.pichincha.DAO.TransferenciaDAO;
import ec.edu.ups.pichincha.modelo.Cuenta;
import ec.edu.ups.pichincha.modelo.Movimiento;
import ec.edu.ups.pichincha.modelo.Transferencia;

@Stateless
public class MovimientoON implements MovimientoONLocal{

	@Inject
	private MovimientoDAO daoMovimiento;

	@Inject
	private CuentaDAO cuentaDAO;
	
	@Inject
	private TransferenciaDAO transferenciaDAO;

	private Cuenta cuenta = new Cuenta();

	public boolean movimiento(Movimiento movimiento) throws Exception {
		if (movimiento == null)
			throw new Exception("Error en el Objeto Movimiento");
		try {
			daoMovimiento.insertMovimiento(movimiento);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Error al Ingresar Movimiento");
		}
		return true;
	}

	public int movimientoN() throws SQLException {
		int id = daoMovimiento.contarMovimiento();
		return id;
	}
	
	public int transferenciaN() throws SQLException {
		int id = transferenciaDAO.contarTransferencia();
		return id;
	}

	public Cuenta cuenta(String nCuenta) throws SQLException {
		cuenta = cuentaDAO.buscarCuenta(nCuenta);
		System.out.println(cuenta);
		return cuenta;
	}
	
	public boolean actualizarCuenta(Cuenta cuenta) throws SQLException {
		cuentaDAO.update(cuenta);
		return true;
	}

	public boolean crearTransferencia(Transferencia transferencia) throws SQLException {
		transferenciaDAO.insertTransferencia(transferencia);
		return true;
	}
	
}
