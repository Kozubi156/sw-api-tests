package com.swapi.starship.get;

import com.swapi.base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<String> results = json.getList("results");
        assertEquals(10, results.size());
    }

    @Test
    public void readOneStartShipsWithId() {

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

    @Test
    public void readStartShipsWithName() {

        Response response = given()
                .spec(reqSpec)
                .queryParam("name", "CR90 corvette")
                .when()
                .get(BASE_URL + STARSHIPS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("CR90 corvette", json.get("name"));
    }

}
