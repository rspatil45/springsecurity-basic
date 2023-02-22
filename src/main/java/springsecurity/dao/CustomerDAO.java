package springsecurity.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import springsecurity.dto.Customer;

@Service
public interface CustomerDAO {

	List<Customer> loadCustomerByCustomerName(String name);
}
