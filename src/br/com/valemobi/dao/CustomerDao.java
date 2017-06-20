package br.com.valemobi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.valemobi.bean.CustomerBean;



public class CustomerDao 
{
	private Connection conn;
	
	public CustomerDao ()
	{
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public void insereCustomer(CustomerBean customer)
	{
		String sql = "insert into tb_customer_account" + "(id_customer, cpf_cnpj, nm_customer, is_active, vl_total)" + "values (?, ?, ?, ?, ?)";
		
		this.conn = new ConnectionFactory().getConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);

			
			stmt.setLong(1, customer.getIdCustomer());
			stmt.setString(2, customer.getCpfCnpj());
			stmt.setString(3, customer.getNmCustomer());
			stmt.setBoolean(4, customer.isActive());
			stmt.setLong(5, customer.getVlTotal());

			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<CustomerBean> listaClientes(double valorTotal, long idInicial, long idFinal) throws SQLException {

		this.conn = new ConnectionFactory().getConnection();
		List<CustomerBean> lista = new ArrayList<>();
		
		try {
			String sql = "select * from tb_customer_account where  vl_total > ? and ( id_customer between ? and ?) order by vl_total desc";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setDouble(1, valorTotal);
			stm.setLong(2, idInicial);
			stm.setLong(3, idFinal);
			stm.execute();

			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				CustomerBean cutomer = new CustomerBean();
				cutomer.setIdCustomer(rs.getLong(1));
				cutomer.setCpfCnpj(rs.getString(2));
				cutomer.setNmCustomer(rs.getString(3));
				cutomer.setActive(rs.getBoolean(4));
				cutomer.setVlTotal(rs.getLong(5));
				lista.add(cutomer);				
			}
			rs.close();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao imprimir");
		} finally {
			conn.close();
		}
		return lista;
	}
	
	public double getMediaFinal(double valorTotal, long idInicial, long idFinal) throws SQLException {

		this.conn = new ConnectionFactory().getConnection();
		double resultado = 0.0;

		try {
			String sql = "Select avg(vl_total) from tb_customer_account where vl_total>? and id_customer between ? and ?;";
			PreparedStatement stm = conn.prepareStatement(sql);

			stm.setDouble(1, valorTotal);
			stm.setLong(2, idInicial);
			stm.setLong(3, idFinal);
			stm.execute();

			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				resultado = rs.getDouble(1);
			}

			rs.close();
			stm.close();

		} catch (SQLException e) {
			System.out.println("Erro ao fazer a pesquisa:");

		} finally {
			conn.close();
		}
		return resultado;
	}
}
