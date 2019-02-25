package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class ComputeApiTests {

    @Mock
    ComputeRepository computeRepository;

    @InjectMocks
    ComputeService computeService ;

    @Before
    public void init() {

    }

    @Test
    public void basicTestForCompute() {

//        ComputeObj computeObj = new ComputeObj(1.0,1.0,1.0,1.0,0.0,0.0);
//        try {
//            computeService.compute(computeObj);
//        }catch(Exception e){
//            System.out.println("exception");
//        }
//
//        List<Compute> resultList = this.computeRepository.findAll();
//        then(resultList.size()).equals(1);
//
//        System.out.println("----"+resultList.get(0).getA());

    }

}
