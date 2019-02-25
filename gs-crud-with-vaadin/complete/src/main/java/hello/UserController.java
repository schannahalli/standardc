package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired CustomerRepository customerRepository ;
    @Autowired UserService userService ;

   // @PostMapping("/newsignup")
    /*
        Signup.
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody Response signup(@RequestBody UserInfoDTO userInfo){

        Response resp = new Response();

        Boolean isUserInfoValid = true ;
        if(userInfo.getEmailId() == null || userInfo.getPassword() == null || userInfo.getUserName() == null){
            isUserInfoValid = false ;
        }

        if(userInfo.getUserName().length() == 0 || userInfo.getPassword().length() == 0 || userInfo.getEmailId().length() == 0){
            isUserInfoValid = false ;
        }

        //Check if valid email
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(userInfo.getEmailId());

        if(!mat.matches()){
            isUserInfoValid = false ;
        }

        if(!isUserInfoValid) {
            resp.errorMessage = Constants.INCORRECT_USER_INFO;
            resp.errorCode = Constants.INCORRECT_USER_INFO_EC;
            return resp;
        }

        Boolean ret = userService.signup(userInfo);

        if(ret) {
            resp.errorCode = 0L;
            resp.errorMessage = "success";
            return resp ;
        }

        resp.errorCode = 101L ;
        resp.errorMessage = "unsuccessful signup attempt.please try again";
        return resp ;
    }

    @PostMapping("/login")
    public @ResponseBody Response login(@RequestBody UserInfoDTO userInfoDTO){
        Response response = new Response();
        Boolean isValidUserInfo = true ;
        if((userInfoDTO.getEmailId() == null || userInfoDTO.getEmailId().length() == 0)
                && ((userInfoDTO.getUserName() == null || userInfoDTO.getUserName().length() == 0))){
            isValidUserInfo = false ;
        }
        if(userInfoDTO.getPassword() == null || userInfoDTO.getPassword().length() == 0){
            isValidUserInfo = false ;
        }

        if(!isValidUserInfo){
            response.errorMessage = Constants.INCORRECT_USER_INFO;
            response.errorCode = Constants.INCORRECT_USER_INFO_EC;
            return response;
        }

        try{
            if(userInfoDTO.getUserName()==null || userInfoDTO.getUserName().length()==0) {
                userService.loginWithEmail(userInfoDTO);
            }else{
                userService.loginWithUserName(userInfoDTO);
            }
        }catch(CustomException e){
            response.errorCode = e.getErrorCode() ;
            response.errorMessage = e.getErrorMessage();
        }catch(Exception e){
            response.errorCode = Constants.UNKNOW_ERROR_EC ;
            response.errorMessage = Constants.UNKNOWN_ERROR_EM;
        }

        response.errorCode = Constants.SUCCESS_EC ;
        response.errorMessage = Constants.SUCCESS_EM;
        return response;
    }

}
