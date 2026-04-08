package com.example.postmanecho.tests;

import com.example.postmanecho.models.EchoResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class RequestMethodsTests extends BaseTest {

    @Test
    public void testGetRequest() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("foo1", "bar1");
        queryParams.put("foo2", "bar2");

        Response response = given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .get("/get")
                .then()
                .log().all() // Для отладки
                .extract()
                .response();

        // Проверка статус кода
        assertThat(response.statusCode()).isEqualTo(200);

        // Проверка тела ответа
        EchoResponse echoResponse = response.as(EchoResponse.class);

        assertThat(echoResponse.getArgs())
                .containsEntry("foo1", "bar1")
                .containsEntry("foo2", "bar2");

        assertThat(echoResponse.getUrl())
                .isEqualTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2");

        // Альтернативный способ проверки с RestAssured Matchers
        given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }

    @Test
    public void testPostRequestWithJsonBody() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");
        requestBody.put("email", "john@example.com");
        requestBody.put("age", 30);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .log().all()
                .extract()
                .response();

        // Проверка статус кода
        assertThat(response.statusCode()).isEqualTo(200);

        // Проверка тела ответа
        EchoResponse echoResponse = response.as(EchoResponse.class);

        // Проверка данных
        assertThat(echoResponse.getData())
                .asString()
                .contains("\"name\":\"John Doe\"");

        // Проверка JSON
        Map<String, Object> responseJson = response.jsonPath().getMap("json");
        assertThat(responseJson)
                .containsEntry("name", "John Doe")
                .containsEntry("email", "john@example.com")
                .containsEntry("age", 30);

        assertThat(echoResponse.getUrl())
                .isEqualTo("https://postman-echo.com/post");
    }

    @Test
    public void testPostRequestWithFormData() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("name", "Jane Doe");
        formParams.put("occupation", "Developer");

        Response response = given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .post("/post")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);

        Map<String, String> responseForm = response.jsonPath().getMap("form");
        assertThat(responseForm)
                .containsEntry("name", "Jane Doe")
                .containsEntry("occupation", "Developer");
    }

    @Test
    public void testPutRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 123);
        requestBody.put("title", "Updated Title");
        requestBody.put("completed", true);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);

        Map<String, Object> responseJson = response.jsonPath().getMap("json");
        assertThat(responseJson)
                .containsEntry("id", 123)
                .containsEntry("title", "Updated Title")
                .containsEntry("completed", true);

        assertThat(response.jsonPath().getString("url"))
                .isEqualTo("https://postman-echo.com/put");
    }

    @Test
    public void testPatchRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("status", "updated");
        requestBody.put("patchField", "new value");

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);

        Map<String, Object> responseJson = response.jsonPath().getMap("json");
        assertThat(responseJson)
                .containsEntry("status", "updated")
                .containsEntry("patchField", "new value");
    }

    @Test
    public void testDeleteRequest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 456);
        requestBody.put("reason", "no longer needed");

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);

        Map<String, Object> responseJson = response.jsonPath().getMap("json");
        assertThat(responseJson)
                .containsEntry("id", 456)
                .containsEntry("reason", "no longer needed");

        assertThat(response.jsonPath().getString("url"))
                .isEqualTo("https://postman-echo.com/delete");
    }

    @Test
    public void testDeleteRequestWithoutBody() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/delete")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("url"))
                .isEqualTo("https://postman-echo.com/delete");
    }

    @Test
    public void testGetRequestWithHeaders() {
        Response response = given()
                .spec(requestSpec)
                .header("X-Custom-Header", "custom-value")
                .header("Authorization", "Bearer token123")
                .when()
                .get("/get")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);

        Map<String, String> headers = response.jsonPath().getMap("headers");
        assertThat(headers)
                .containsEntry("x-custom-header", "custom-value")
                .containsEntry("authorization", "Bearer token123");
    }

    @Test
    public void testGetRequestWithPathParams() {
        // Если бы API поддерживало path parameters
        Response response = given()
                .spec(requestSpec)
                .pathParam("id", 789)
                .when()
                .get("/get/{id}")
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode()).isEqualTo(200);
        // Дополнительные проверки...
    }
}
