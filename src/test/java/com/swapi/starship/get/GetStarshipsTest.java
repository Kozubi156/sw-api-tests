package com.swapi.starship.get;

import com.swapi.base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
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
    public void readStartShipsById() {

        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS + "/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("CR90 corvette", json.get("name"));
        assertEquals("CR90 corvette", json.get("model"));
        assertEquals("Corellian Engineering Corporation", json.get("manufacturer"));

    }

    @Test
    public void readStartShipsByName() {

        Response response = given()
                .spec(reqSpec)
                .queryParam("search", "TIE Advanced x1")
                .when()
                .get(BASE_URL + STARSHIPS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("TIE Advanced x1", json.get("results[0].name"));
    }

}
