import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class testCases extends passwordHashingAPI{
  public static   String hash="";
  private static final String log_path="src/test/resources/log4j.properties";
@Test
    public void  checkPostApiWorks() throws IOException {
    Logger log = Logger.getLogger(testCases.class);
    Properties prop = new Properties();
    prop.load(new FileInputStream(log_path));
       String response = passwordHashingAPI.postapi(hash);
       log.info("Post Api Identifier :-"+response);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertNotNull(response);
    }
@Test
    public void checkGetstatsApiWorks()
    {
        String value ="";
       List <String>response =  passwordHashingAPI.getapistats(hash,value);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertNotNull(response);

    }

    @Test
    public void checkAverageTimeIsCorrect()
    {
        String value ="";

        List <String>response =  passwordHashingAPI.getapistats(hash,value);
        SoftAssert softAssertion= new SoftAssert();
try {
   softAssertion.assertEquals(response.get(1), "5050");
    softAssertion.assertAll();
}
catch(NoSuchElementException e)
{
  e.getStackTrace();
}
    }

  @Test
  public void checkShutdownApi()
  {
int response = shutdownApi();
System.out.println(response);
      Integer response1 = new Integer(response);
      SoftAssert softAssertion= new SoftAssert();
      softAssertion.assertTrue(response1.toString().contains("200"),"API executed successfully");
      softAssertion.assertAll();

  }
  @Test
    public void checkGetsApiWorks() throws IOException {
        String value ="";
        String response =  passwordHashingAPI.getapi(hash);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertNotNull(response);
        softAssertion.assertAll();

    }

}


