package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class UserController {

    @Autowired CustomerRepository customerRepository ;
    @Autowired UserService userService ;

   // @PostMapping("/newsignup")
    @RequestMapping(value = "/newsignup", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody Response signup(@RequestBody UserInfoDTO userInfo){

        Response resp = new Response();

        Boolean ret = userService.signup(userInfo);

        if(ret) {
            resp.errorCode = 0L;
            resp.errorMessage = "success";
        }

        resp.errorCode = 101L ;
        resp.errorMessage = "unsuccessful signup attempt.please try again";
        return resp ;
    }

    @PostMapping("/login")
    @ResponseBody Response loginWithEmail(@RequestBody UserInfoDTO userInfoDTO){
        Response response = new Response();
        try{
            userService.loginWithEmail(userInfoDTO);
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
