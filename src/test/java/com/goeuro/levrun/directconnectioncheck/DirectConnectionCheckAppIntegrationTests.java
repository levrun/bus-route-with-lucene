package com.goeuro.levrun.directconnectioncheck;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DirectConnectionCheckAppIntegrationTests {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testThatSimpleExampleFileParsingAndProcessedCorrectly() throws Exception {
        when().get("api/direct?dep_sid=3&arr_sid=4").then().statusCode(200);
    }

    @Test
    public void testThatWeFindDirectRoute() throws Exception {
        when().get("api/direct?dep_sid=3&arr_sid=4").then().body(containsString("true"));
    }

    @Test
    public void testThatWeDontFindDirectRoute() throws Exception {
        when().get("api/direct?dep_sid=100&arr_sid=0").then().body(containsString("false"));
    }

}
