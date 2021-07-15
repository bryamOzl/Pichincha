package ec.edu.ups.pichincha.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.pichincha.modelo.Cuenta;


@Stateless
public class CuentaDAO {

	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;

	public Cuenta buscarCuenta(String numero_cuenta) throws SQLException {
		Cuenta cuenta = new Cuenta();
		String sql = "SELECT c.cuenta_id, c.fecha_apertura, c.saldo, c.entidad_financiera, c.numero_cuenta FROM Cuenta c WHERE  c.numero_cuenta=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, numero_cuenta);
		ResultSet res = ps.executeQuery();
		res.next();
		cuenta.setCuentaId(res.getInt("cuenta_id"));
		cuenta.setFechaApertura(res.getDate("fecha_apertura"));
		cuenta.setSaldo(res.getDouble("saldo"));
		cuenta.setEntidadFinanciera(res.getString("entidad_financiera"));
		cuenta.setNumeroCuenta(res.getString("numero_cuenta"));
		ps.execute();
		ps.close();
		return cuenta;
	}
	
	public boolean update(Cuenta cuenta) throws SQLException {
		em.merge(cuenta);
		return true;
	}
}
