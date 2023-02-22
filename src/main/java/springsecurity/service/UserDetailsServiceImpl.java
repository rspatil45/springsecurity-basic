package springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springsecurity.dao.CustomerDAO;
import springsecurity.dto.Customer;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<Customer> customers = customerDAO.loadCustomerByCustomerName(username);
		if(customers.isEmpty())
		{
			throw new UsernameNotFoundException(username + " this user not found");
		}
//		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//		SimpleGrantedAuthority simpg = new SimpleGrantedAuthority(customer.getAuthority());
//		authorities.add(simpg);
//		User user = new User(customer.getUsername(), customer.getPassword(), authorities);  		
		
		// below code explantion : User is outer class, User.withUsername function can create object of UserBuilder class 
		// and set the value for username, then password method is called to set value for password and so rles 
		// at last build method is called which return object of UserDetails(it convert UserBuilder to UserDetails)
		// here UserBuilder class is static so we can create its object without creating object of outer class(User)
		UserDetails userDetails = User.withUsername(customers.get(0).getUsername())
				.password(customers.get(0).getPassword())
				.roles(customers.get(0).getAuthority(),"other")
				.build();
				
		return userDetails;
	}

}
