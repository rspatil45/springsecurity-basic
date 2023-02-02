package springsecurity.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springsecurity.dto.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Customer loadCustomerByCustomerName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from customers where username = ?";
		Customer customer = jdbcTemplate.queryForObject(sql, new Object[] {name}, new BeanPropertyRowMapper<Customer>(Customer.class));
		return customer;
	}

}
