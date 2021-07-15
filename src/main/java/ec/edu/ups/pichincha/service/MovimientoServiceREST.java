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
import ec.edu.ups.pichincha.modelo.Transferencia;
import ec.edu.ups.pichincha.negocio.MovimientoONLocal;

@Path("movimientos")
public class MovimientoServiceREST {

	@Inject
	private MovimientoONLocal onMovimiento;

	private Cuenta cuenta = new Cuenta();
	private Cuenta cuentaO = new Cuenta();
	private Cuenta cuentaD = new Cuenta();
	private Movimiento deposito = new Movimiento();
	private Movimiento retiro = new Movimiento();

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
	@Path("transferencia")
	public Mensaje transferencia(Transferencia transferencia) {
		Mensaje mensaje = new Mensaje();
		cuentaO = new Cuenta();
		cuentaD = new Cuenta();
		deposito = new Movimiento();
		retiro = new Movimiento();

		try {
			Date fecha;
			transferencia.setTransferenciaId(onMovimiento.transferenciaN());
			transferencia.setFechaTransferencia(fecha = new Date());
			
			cuentaO = onMovimiento.cuenta(transferencia.getCuentaOrigen().getNumeroCuenta());
			cuentaD = onMovimiento.cuenta(transferencia.getCuentaDestino().getNumeroCuenta());
			
			cuentaD.setSaldo((cuentaD.getSaldo() + transferencia.getMonto()));
			cuentaO.setSaldo((cuentaO.getSaldo() - transferencia.getMonto()));
			
			deposito.setMovimientoId(onMovimiento.movimientoN());
			deposito.setTipoMovimiento("DEPOSITO");
			deposito.setCantidad(transferencia.getMonto());
			deposito.setFecha(fecha =  new Date());
			deposito.setSaldo(cuentaD.getSaldo());
			deposito.setCuenta(onMovimiento.cuenta(transferencia.getCuentaDestino().getNumeroCuenta()));
			onMovimiento.movimiento(deposito);
			
			retiro.setMovimientoId(onMovimiento.movimientoN());
			retiro.setTipoMovimiento("RETIRO");
			retiro.setCantidad(transferencia.getMonto());
			retiro.setFecha(fecha =  new Date());
			retiro.setSaldo(cuentaO.getSaldo());
			retiro.setCuenta(onMovimiento.cuenta(transferencia.getCuentaOrigen().getNumeroCuenta()));
			onMovimiento.movimiento(retiro);

			
			onMovimiento.actualizarCuenta(cuentaD);
			onMovimiento.actualizarCuenta(cuentaO);
			
			transferencia.setCuentaDestino(cuentaD);
			transferencia.setCuentaOrigen(cuentaO);
			
			onMovimiento.crearTransferencia(transferencia);

			mensaje.setCode("OK");
			mensaje.setMessage("Transferencia realizado");
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
