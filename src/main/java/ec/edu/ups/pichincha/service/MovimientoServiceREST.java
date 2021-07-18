package ec.edu.ups.pichincha.service;

import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.pichincha.modelo.Cuenta;
import ec.edu.ups.pichincha.modelo.Movimiento;
import ec.edu.ups.pichincha.negocio.MovimientoONLocal;

@Path("movimientos")
public class MovimientoServiceREST {

	@Inject
	private MovimientoONLocal onMovimiento;

	private Cuenta cuenta = new Cuenta();
	@GET
	@Produces("application/json")
	@Path("cuenta")
	public Cuenta buscarCuenta(@QueryParam("nCuenta") String nCuenta) {
		Cuenta cue = null;
		try {
			cue = onMovimiento.cuenta(nCuenta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cue = null;
			cuenta = new Cuenta();
		}
		if (cue == null) {
			System.out.println("No se encontro la cuenta");
			cuenta = new Cuenta();
		} else {
			cuenta = cue;
		}
		return cuenta;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("deposito")
	public Mensaje insertDeposito(Movimiento movimiento) {
		Mensaje mensaje = new Mensaje();
		cuenta = new Cuenta();
		try {
			Date fecha;
			movimiento.setMovimientoId(onMovimiento.movimientoN());
			movimiento.setTipoMovimiento("DEPOSITO");
			movimiento.setFecha(fecha = new Date());

			cuenta = onMovimiento.cuenta(movimiento.getCuenta().getNumeroCuenta());
			cuenta.setSaldo((cuenta.getSaldo() + movimiento.getCantidad()));
			
			movimiento.setSaldo(cuenta.getSaldo());
			movimiento.setCuenta(cuenta);
			
			onMovimiento.movimiento(movimiento);
			onMovimiento.actualizarCuenta(cuenta);

			mensaje.setCode("OK");
			mensaje.setMessage("Deposito realizado");
			return mensaje;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mensaje.setCode("ERROR");
			mensaje.setMessage(e.getMessage());
			return mensaje;
		}
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("retiro")
	public Mensaje insertRetiro(Movimiento movimiento) {
		Mensaje mensaje = new Mensaje();
		cuenta = new Cuenta();
		try {
			Date fecha;
			movimiento.setMovimientoId(onMovimiento.movimientoN());
			movimiento.setTipoMovimiento("RETIRO");
			movimiento.setFecha(fecha = new Date());

			cuenta = onMovimiento.cuenta(movimiento.getCuenta().getNumeroCuenta());
			cuenta.setSaldo((cuenta.getSaldo() - movimiento.getCantidad()));
			
			movimiento.setSaldo(cuenta.getSaldo());
			movimiento.setCuenta(cuenta);
			
			onMovimiento.actualizarCuenta(cuenta);
			onMovimiento.movimiento(movimiento);
			
			mensaje.setCode("OK");
			mensaje.setMessage("Retiro realizado");
			return mensaje;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mensaje.setCode("ERROR");
			mensaje.setMessage(e.getMessage());
			return mensaje;
		}
	}
}
