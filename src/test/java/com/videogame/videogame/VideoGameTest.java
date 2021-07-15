package com.videogame.videogame;

import com.videogame.VideoPojo;
import com.videogame.testbase.Testbase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends Testbase {

    public Random randomGenerator = new Random();
    public int randomInt = randomGenerator.nextInt(1000);
    @Test
    public void getAllVideoGamesInfo() {
        Response response =
                given()
                        .accept("application/json")
                        .when()
                        .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void addNewVideoGames() {
        VideoPojo videoPojo = new VideoPojo();

        videoPojo.setId(randomInt);
        videoPojo.setName("mario");
        videoPojo.setReleaseDate("2021-07-15T19:08:27.609Z");
        videoPojo.setReviewScore(10);
        videoPojo.setCategory("A");
        videoPojo.setRating("10");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(videoPojo).accept("application/json")
                .when()
                .post();
        response.then().statusCode(200);
        response.prettyPrint();


    }

    @Test
    public void deleteVideoGames() {
        Response response =
                given()
                        .accept("application/json")
                        .when()
                        . delete("/10");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void getVideoGameById() {
        Response response =
                given()
                        .accept("application/json")
                        .pathParam("id", "1")
                        .when()
                        .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void updateVideoGame() {
        VideoPojo videoPojo = new VideoPojo();

        videoPojo.setId(6);
        videoPojo.setName("batman");
        videoPojo.setReleaseDate("2021-07-15T19:08:27.609Z");
        videoPojo.setReviewScore(90);
        videoPojo.setCategory("bc");
        videoPojo.setRating("universal");
        Response response = given()
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(videoPojo)
                .when()
                .put("/6");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
