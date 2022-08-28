package adapters;

import static io.restassured.RestAssured.given;

public abstract class BaseAdapter {

    protected final static String  BASE_URL = "https://api.qase.io/v1/";
    private final static String ACCESS_TOKEN = "5e45a78915c94cf5cafca98a33aacbe792b5d34f";

    public String get(String endpoint, int statusCode){
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .when()
                .log().all()
                .get(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();

    }

    public String post(String endpoint, int statusCode, String requestBody){
        return given()
                .header("Token",ACCESS_TOKEN)
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body(requestBody)
                .when()
                .log().all()
                .post(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();

    }

    public String delete(String endpoint, int statusCode){
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .when()
                .log().all()
                .delete(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();

    }

    public String update(String endpoint, int statusCode){
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .when()
                .log().all()
                .patch(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();

    }

}
