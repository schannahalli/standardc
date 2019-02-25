package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ComputeService {

    @Autowired
    private ComputeRepository computeRepository ;

    public List<ComputeObj> getPrevComputations(Date beforeDate,Date afterDate,Date onDate,Integer limit) throws CustomException{

        List<ComputeObj> computeObjList = new ArrayList<ComputeObj>();

        List<Compute> computeList = null;
        if(beforeDate != null || afterDate != null) {
            beforeDate = beforeDate == null ? new Date(): beforeDate ;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                //Assume a historical date
                Date d = sdf.parse("01/01/1971");
                afterDate = afterDate == null ? d : afterDate;
            }catch(Exception e){
                throw new CustomException(Constants.UNKNOW_ERROR_EC,Constants.UNKNOWN_ERROR_EM);
            }
            computeList = computeRepository.findAllByEventDateBetween(afterDate, beforeDate);
        }else if(onDate != null){
            computeList = computeRepository.findAllByEventDate(onDate);
        }

        int maxElements = 0 ;
        for(Compute c : computeList){
            ComputeObj obj = new ComputeObj(c.getA(),c.getB(),c.getC(),c.getX(),c.getY1(),c.getY2() );
            computeObjList.add(obj);
            if(limit !=null && !(limit <= 0) && ++maxElements == limit){
                break ;
            }
        }

        return computeObjList ;
    }

    public List<ComputeObj>  getPrevComputationsByRange(Long beforen,Long aftern,Long lastN,Integer limit) throws CustomException{
        List<ComputeObj> computeObjList = new ArrayList<ComputeObj>();
        List<Compute> computeList = null;
        if(beforen == null || beforen <=0) {
            computeList = computeRepository.findAllByIdBetween(aftern,Long.MAX_VALUE);
        }else if(aftern == null || aftern < 0){
            computeList = computeRepository.findAllByIdBetween(0L,beforen);
        }else if(lastN != null && lastN >0){

        }else if(beforen > 0 && aftern > 0){
            computeList = computeRepository.findAllByIdBetween(beforen,aftern);
        }

        int maxElements = 0 ;
        for(Compute c : computeList){
            ComputeObj obj = new ComputeObj(c.getA(),c.getB(),c.getC(),c.getX(),c.getY1(),c.getY2() );
            computeObjList.add(obj);
            if(limit != null && !(limit <= 0) && ++maxElements == limit){
                break ;
            }
        }

        return computeObjList ;
    }

    public void compute(ComputeObj obj) throws CustomException{
        Double y1 = obj.getA() * Math.pow(obj.getX(),2)+ obj.getB() * obj.getX() + obj.getC() ;
        Double y2 = obj.getA() * Math.sin(obj.getX());
        Double ratio = 0D ;
        if(y2 != 0 )
            ratio = y1/y2 ;
        Compute c = new Compute();
        c.setA(obj.getA());
        c.setB(obj.getB());
        c.setC(obj.getC());
        c.setX(obj.getX());
        c.setY1(y1);
        c.setY2(y2);
        c.setEventDate(new Date());
        c.setRatio(ratio);

        Compute resp = computeRepository.save(c);
        if(resp == null){
            throw new CustomException(Constants.UNKNOW_ERROR_EC,Constants.UNKNOWN_ERROR_EM);
        }
    }
}
