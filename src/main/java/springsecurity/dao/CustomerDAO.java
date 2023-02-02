package springsecurity.dao;

import org.springframework.stereotype.Service;

import springsecurity.dto.Customer;

@Service
public interface CustomerDAO {

	Customer loadCustomerByCustomerName(String name);
}
