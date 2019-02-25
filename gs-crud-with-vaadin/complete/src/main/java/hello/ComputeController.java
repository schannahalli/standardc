package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ComputeController {

    Logger logger = LoggerFactory.getLogger(ComputeController.class);

    @Autowired
    private ComputeService service ;

    @Autowired
    private UserService userService ;

    /*
        Use from - to date to get all the events within this range.
        use from to get all events from date until current date
        use to to get all events upto to.
        use ondate to get all events on this date.
     */
    @GetMapping("/previous-bydate")
    @ResponseBody
    public ComputeResponse getPreviousComputationsByDate(@RequestParam(value="username",required=true)String userName,@RequestParam(value="to",required=false)String beforedateStr,@RequestParam(value="from",required=false)String afterdateStr,@RequestParam(value="ondate",required=false) String onDateStr,@RequestParam(value="limit",required=false)Integer limit){
        ComputeResponse response = new ComputeResponse() ;

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.userName = userName;

        Boolean isLoggedIn = userService.isLoggedIn(userInfoDTO);
        if(!isLoggedIn){
            response.errorMessage = String.format(Constants.USER_NOT_LOGGED_IN_EM,userName) ;
            response.errorCode = Constants.USER_NOT_LOGGED_IN_EC;
            return response ;
        }

        Date beforedate = null;
        Date afterdate = null;
        Date onDate = null;

        try {
            if (beforedateStr != null) {
                beforedate = new SimpleDateFormat("mm/dd/yyyy").parse(beforedateStr);
            }

            if (afterdateStr != null) {
                afterdate = new SimpleDateFormat("mm/dd/yyyy").parse(afterdateStr);
            }

            if (onDateStr != null) {
                onDate = new SimpleDateFormat("mm/dd/yyyy").parse(onDateStr);
            }
        }catch(Exception e) {
            response.errorCode = Constants.INVALID_DATE_EC;
            response.errorMessage = Constants.INVALID_DATE_EM;
            return response ;
        }

        try{

            List<ComputeObj> computeObjList = service.getPrevComputations(beforedate,afterdate,onDate,limit);
            response.setComputeObjList(computeObjList);
            response.errorCode = Constants.SUCCESS_EC;
            response.errorMessage=Constants.SUCCESS_EM;

        }catch(Exception e){
            response.errorMessage = Constants.UNKNOWN_ERROR_EM;
            response.errorCode = Constants.UNKNOW_ERROR_EC;
        }
        return response ;
    }



    @RequestMapping(value="previous-byrange",method=RequestMethod.GET)
    @ResponseBody
    public ComputeResponse getPreviousComputationsByRange(@RequestParam(value="username",required=true)String userName,@RequestParam(value="beforen",required=false)Long beforen,@RequestParam(value="aftern",required=false)Long aftern,@RequestParam(value="lastn",required=false) Long lastN,@RequestParam(value="limit",required=false)Integer limit){
        ComputeResponse response = new ComputeResponse() ;

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.userName = userName;

        Boolean isLoggedIn = userService.isLoggedIn(userInfoDTO);
        if(!isLoggedIn){
            response.errorMessage = String.format(Constants.USER_NOT_LOGGED_IN_EM,userName) ;
            response.errorCode = Constants.USER_NOT_LOGGED_IN_EC;
            return response ;
        }

        try {
            List<ComputeObj> computeObjList = service.getPrevComputationsByRange(beforen,aftern,lastN,limit);
            response.setComputeObjList(computeObjList);
            response.errorCode = Constants.SUCCESS_EC;
            response.errorMessage = Constants.SUCCESS_EM;
        }catch(Exception e){
            System.out.println("error");
            response.errorMessage = Constants.UNKNOWN_ERROR_EM ;
            response.errorCode = Constants.UNKNOW_ERROR_EC ;
        }
        return response ;
    }


    @PostMapping("/compute")
    @ResponseBody public Response compute(@RequestBody ComputeObj computeObj){
        Response resp = new ComputeResponse();

        try{
            service.compute(computeObj);
            resp.errorCode = Constants.SUCCESS_EC;
            resp.errorMessage = Constants.SUCCESS_EM;
        }catch(CustomException e){
            resp.errorMessage = e.getErrorMessage();
            resp.errorCode = e.getErrorCode();
        }catch(Exception e){
            resp.errorMessage = Constants.UNKNOWN_ERROR_EM;
            resp.errorCode = Constants.UNKNOW_ERROR_EC;
        }
        return resp ;
    }

}
