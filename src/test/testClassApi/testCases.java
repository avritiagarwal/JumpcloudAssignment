import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.After;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class testCases extends passwordHashingAPI{
    private static final String log_path="src/test/resources/log4j.properties";
  public static   String hash="";
    Logger log = Logger.getLogger(testCases.class);
    Properties prop = new Properties();

  //To Check Post API works fine
  @Test(priority = 1 )
    public void  checkPostApiWorks() throws IOException {
    Logger log = Logger.getLogger(testCases.class);
      prop.load(new FileInputStream(log_path));
       String response = passwordHashingAPI.postapi(hash);
       log.debug("Post Api Identifier :-"+response);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertNotNull(response);
    }
    //To Check Get Stats API works fine

@Test(priority = 2)
    public void checkGetstatsApiWorks()
    {
        String value ="";
       List <String>response =  passwordHashingAPI.getapistats(hash,value);
       log.info("GET API STATS"+response);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertNotNull(response);

    }
    //To Check Average TIme returned by GET API is Correct
    @Test(priority = 3)
    public void checkAverageTimeIsCorrect() throws IOException {
        String value = "";

        List<String> response = passwordHashingAPI.getapistats(hash, value);
        long response1 =passwordHashingAPI.postapiGetTime(hash);
        System.out.println(response1);
        System.out.println(response.get(0));
        System.out.println(response.get(1));
        SoftAssert softAssertion = new SoftAssert();
        try {
            softAssertion.assertEquals(response.get(1), response1);
            prop.load(new FileInputStream(log_path));
            log.info("Average Time" + response.get(1));
            softAssertion.assertAll();
        } catch (NoSuchElementException | FileNotFoundException e) {
            e.getStackTrace();

        }
    }
    

  // To check Get API works
  @Test(priority = 4)
    public void checkGetsApiWorks() throws IOException {
        String value ="";
        String response =  passwordHashingAPI.getapi(hash);
        SoftAssert softAssertion= new SoftAssert();
        log.info("Get API returns response "+response);
        softAssertion.assertNotNull(response);
        softAssertion.assertAll();

    }

    @Test(priority = 5)
    public void checkpostAPIResponseTime() throws IOException {
        String hash ="";
        long response =  passwordHashingAPI.postapiGetTime(hash);
        SoftAssert softAssertion= new SoftAssert();
        log.info("Get API returns response "+response);
        softAssertion.assertEquals(response,"2000");
        softAssertion.assertAll();

    }

    @Test(priority = 6)
    public void checkencodedpassword () throws IOException {
        String hash ="";
        String password_Sha_base64 ="ZjM5NGY1OTE4NjEyY2Q1MjM4YzQ2YmZkZTI1NjVhZTlhNjdiMzhjNg==";
        String getResponse =  passwordHashingAPI.getapi(hash);
        SoftAssert softAssertion= new SoftAssert();
        log.info("Get API returns response "+getResponse);
        softAssertion.assertEquals(getResponse,password_Sha_base64);
        softAssertion.assertAll();

    }

    // To chek Shutdown API Works
@AfterClass
    public void checkShutdownApi() throws IOException {
        int response = shutdownApi();
        prop.load(new FileInputStream(log_path));
        log.info("Shutdown done properly with status"+response);
        Integer response1 = new Integer(response);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertTrue(response1.toString().contains("200"),"API executed successfully");
        softAssertion.assertAll();

    }

    @Test(priority = 7)
    public void check2SimultaneousPostApi() throws IOException {
        String hash ="";
        String postApiFirst= passwordHashingAPI.postapi(hash);
        String postApiSecond= passwordHashingAPI.postapi(hash);
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertNotNull(postApiFirst);
        softAssertion.assertNotNull(postApiSecond);
        softAssertion.assertAll();
        log.info("Simultaneous requests are successful"+postApiFirst+postApiSecond);
    }



}


