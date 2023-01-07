package springsecurity.dao;

import org.springframework.stereotype.Service;

import springsecurity.dto.SignupDTO;

@Service
public interface SignupDAO {

	void saveUser(SignupDTO signupDTO);
	
}
