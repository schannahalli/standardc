package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
	Customer findCustomerByEmailIdAndPassword(String emailId,String password);
	Customer findCustomerByUserNameAndPassword(String userName,String password);
	Customer findCustomerByUserNameOrEmailIdAndIsLoggedIn(String userName,String emailId,Boolean isLoggedIn);
}
