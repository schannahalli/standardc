package hello;

import java.util.List;

public class ComputeResponse  extends Response{

    private List<ComputeObj> computeObjList ;

    public void setComputeObjList(List<ComputeObj> computeObjList) {
        this.computeObjList = computeObjList;
    }

    public List<ComputeObj> getComputeObjList() {
        return computeObjList;
    }

}
