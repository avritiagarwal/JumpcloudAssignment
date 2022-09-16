//import groovy.transform.ASTTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;






public class passwordHashingAPI {
   public static  String password = "Test@@123";
   public  static String baseUrl = "http://127.0.0.1:8088";



    public static String postapi(String Hash) throws IOException {

        String filepath = "src/test/resources/jsonFiles/postAPIJson.json";
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        File jsonFile = new File(filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        String dataJsonObj = objectMapper.readTree(jsonFile).toString();
        dataJsonObj=  dataJsonObj.replace("pass-word",password);
        request.header("Content-Type", "application/json");
        Response response = request.body(dataJsonObj)
                .post("/hash");

        Assert.assertEquals(response.getStatusCode(), 200);
        return (response.body().asString());
    }

    public static String getapi(String Hash) throws IOException {
        String hash ="";
        String  responsePost =postapi(hash);

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
System.out.println(responsePost);
        Response response =
                request.get("/hash/"+responsePost);
        Assert.assertEquals(response.getStatusCode(), 200);

        return (response.body().asString());
    }

    public static List<String> getapistats(String hash, String value) {

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();

        Response response =
                request.get("/stats");
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonNode jsonNode = (response.body().as(JsonNode.class, ObjectMapperType.JACKSON_2));
         hash=jsonNode.get("TotalRequests").asText();
         value = jsonNode.get("AverageTime").asText();
        return Arrays.asList(hash,value);

    }

public int shutdownApi()
{
    RestAssured.baseURI = baseUrl;
    RequestSpecification request = RestAssured.given();
    Response response = request.body("shutdown")
            .post("/hash");
    Assert.assertEquals(response.getStatusCode(), 200);
    return(response.getStatusCode());

}
}

