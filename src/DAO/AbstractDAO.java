package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import customExceptions.GCAException;

public abstract class AbstractDAO {

	protected OracleConnection oracleConnection = new OracleConnection();
	protected PreparedStatement ps;
	protected ResultSet rs;

	protected Connection getConnection() throws GCAException {
		Connection conn = null;
		try {
			conn = this.oracleConnection.getConnection();
		} catch (ClassNotFoundException e) {
			throw new GCAException(e.getMessage());
		} catch (IOException e) {
			throw new GCAException(e.getMessage());
		} catch (SQLException e) {
		}
		return conn;
	}

	protected void closeConnection(Connection conn) throws GCAException {
		if (!conn.equals(null)) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new GCAException(e.getMessage());
			}
		}
	}
}
