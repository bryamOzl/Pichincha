package ec.edu.ups.pichincha.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.pichincha.modelo.Transferencia;

@Stateless
public class TransferenciaDAO {

	@Inject
	private EntityManager em;

	@Inject
	private Connection con;

	public boolean insertTransferencia(Transferencia transferencia) throws SQLException {
		em.persist(transferencia);
		return true;
	}
	
	public int contarTransferencia() throws SQLException {
		int s;
		String sql = "SELECT MAX (transferencia_id) FROM Transferencia";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		res.next();
		s = res.getInt(1) + 1;
		ps.execute();
		ps.close();
		return s;
	}
	
}
