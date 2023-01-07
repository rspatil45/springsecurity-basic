package springsecurity.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springsecurity.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveUser(SignupDTO signupDTO) {
		
		String sql = "insert into users values (?,?,?)";
		String sql2 = "insert into authorities values (?,?)";
		
		jdbcTemplate.update(sql, signupDTO.getUsername(), signupDTO.getPassword(),1);
		jdbcTemplate.update(sql2,signupDTO.getUsername(),"USER");

	}

}
