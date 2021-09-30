import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class Main
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestByOrder.class);
        for(Failure failure : result.getFailures())
        {
            System.out.println("test failed");
        }
        if(result.wasSuccessful())
        {
            System.out.println("test was succesfull");
        }
    }
}

