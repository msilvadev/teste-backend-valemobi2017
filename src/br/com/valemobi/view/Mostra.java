package br.com.valemobi.view;

import java.sql.SQLException;
import java.util.List;

import br.com.valemobi.bean.CustomerBean;
import br.com.valemobi.dao.CustomerDao;

public class Mostra 
{

	public static void main(String[] args) 
	{
		
		CustomerBean customer = new CustomerBean();
		customer.setIdCustomer(001);
		customer.setCpfCnpj("00000000001");
		customer.setNmCustomer("Cleiton");
		customer.setActive(true);
		customer.setVlTotal(1500);
		
		CustomerBean customer1 = new CustomerBean();
		customer1.setIdCustomer(1501);
		customer1.setCpfCnpj("00000000002");
		customer1.setNmCustomer("Jhon");
		customer1.setActive(true);
		customer1.setVlTotal(657);
		
		CustomerBean customer2 = new CustomerBean();
		customer2.setIdCustomer(2699);
		customer2.setCpfCnpj("00000000003");
		customer2.setNmCustomer("Ricardo");
		customer2.setActive(true);
		customer2.setVlTotal(6590);
		
		CustomerBean customer3 = new CustomerBean();
		customer3.setIdCustomer(1954);
		customer3.setCpfCnpj("00000000004");
		customer3.setNmCustomer("Janaina");
		customer3.setActive(true);
		customer3.setVlTotal(4500);
		
		CustomerDao dao = new CustomerDao();
		dao.insereCustomer(customer);
		dao.insereCustomer(customer1);
		dao.insereCustomer(customer2);
		dao.insereCustomer(customer3);
		
		try 
		{
			double media = dao.getMediaFinal(560, 1500, 2700);
			System.out.println("Sua média é: " + media);
			List<CustomerBean> cutomer = dao.listCustomer(500, 1500, 2700);
			for(CustomerBean c : cutomer)
			{
				System.out.println("" + "id " + c.getIdCustomer() + "Nome " + c.getNmCustomer() + "CPF ou CNPJ  " + c.getCpfCnpj() + "," + (c.isActive() ? "Ativo " : "Inativo ") + "Valor Total  " + c.getVlTotal());
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
