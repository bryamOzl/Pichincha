package ec.edu.ups.pichincha.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.pichincha.modelo.Movimiento;


@Stateless
public class MovimientoDAO {

	
	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	public boolean insertMovimiento(Movimiento movimiento) throws SQLException {
		/*String sql = "UPDATE Cuenta SET saldo = ? WHERE  cuenta_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, movimiento.getSaldo());
		ps.setInt(2, movimiento.getCuenta().getCuentaId());
		ps.executeUpdate();
		ps.close();*/
		em.persist(movimiento);
		return true;
	}
	
	public int contarMovimiento() throws SQLException {
		int s;
		String sql = "SELECT MAX (movimiento_id) FROM Movimiento";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		res.next();
		s = res.getInt(1) + 1;
		ps.execute();
		ps.close();
		return s;
	}
}
