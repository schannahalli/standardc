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

    @GetMapping("/previous-bydate")
    @ResponseBody
    public ComputeResponse getPreviousComputationsByDate(@PathVariable("beforedate")String beforedateStr,@PathVariable("afterdate")String afterdateStr,@PathVariable("ondate") String onDateStr,@PathVariable("limit")int limit){
        ComputeResponse response = new ComputeResponse() ;
        try {
            Date beforedate = null;
            if(beforedateStr != null){
                beforedate = new SimpleDateFormat("MM/dd/YYYY").parse(beforedateStr);
            }

            Date afterdate = null;
            if(afterdateStr != null){
                afterdate = new SimpleDateFormat("MM/dd/YYYY").parse(afterdateStr);
            }

            Date onDate = null;
            if(onDateStr != null){
                onDate = new SimpleDateFormat("MM/dd/YYYY").parse(onDateStr);
            }


            List<ComputeObj> computeObjList = service.getPrevComputations(beforedate,afterdate,onDate,limit);
            response.setComputeObjList(computeObjList);
        }catch(Exception e){
            System.out.println("error");
        }
        return response ;
    }



    @RequestMapping(value="previous-byrange",method=RequestMethod.GET)
    @ResponseBody
    public ComputeResponse getPreviousComputationsByRange(@RequestParam(value="beforen",required=false)Long beforen,@RequestParam(value="aftern",required=false)Long aftern,@RequestParam(value="lastn",required=false) Long lastN,@RequestParam(value="limit",required=false)Integer limit){
        ComputeResponse response = new ComputeResponse() ;

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
