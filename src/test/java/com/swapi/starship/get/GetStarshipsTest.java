package com.swapi.starship.get;

import com.swapi.base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetStarshipsTest extends BaseTest {

    @Test
    public void readAllStartShips() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
    }

    @Test
    public void readOneStartShips() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS + "/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
    }

}
