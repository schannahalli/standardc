package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    CustomerRepository repo ;

    Boolean signup(UserInfoDTO userInfo){

        Customer customer = new Customer();
        customer.setUserName(userInfo.userName);
        customer.setEmailId(userInfo.emailId);
        customer.setPassword(userInfo.password);

        Customer r = repo.save(customer);
        if(r != null)
            return true ;

        return false ;
    }

    void loginWithEmail(UserInfoDTO userInfoDTO) throws CustomException{
        Customer customer = repo.findCustomerByEmailIdAndPassword(userInfoDTO.emailId,userInfoDTO.password);
        if(customer != null){
            customer.setLoggedIn(true);
            customer.setLoginDate(new Date());
            return ;
        }

        throw new CustomException(Constants.LOGIN_FAILED_PASSWORD_INCORRECT_EC,Constants.LOGIN_FAILED_PASSWORD_INCORRECT_EM);

    }

    void loginWithUserName(UserInfoDTO userInfoDTO){
        Customer customer = repo.findCustomerByUserNameAndPassword(userInfoDTO.userName,userInfoDTO.password);
        if(customer != null){
            customer.setLoggedIn(true);
            customer.setLoginDate(new Date());
            return ;
        }

        throw new CustomException(Constants.LOGIN_FAILED_PASSWORD_INCORRECT_EC,Constants.LOGIN_FAILED_PASSWORD_INCORRECT_EM);

    }

    Boolean isLoggedIn(UserInfoDTO userInfoDTO){
        Customer customer = repo.findCustomerByUserNameOrEmailIdAndIsLoggedIn(userInfoDTO.userName,userInfoDTO.emailId,true);
        if(customer != null){
            customer.setLoggedIn(true);
            customer.setLoginDate(new Date());
            return true;
        }
        return false ;
    }
}
