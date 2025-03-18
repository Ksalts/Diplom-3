package user;
import constants.Constants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import constants.Constants;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserClient {
    @Step("Логин пользователя")
    public  ValidatableResponse login (User user){
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URI)
                .body(user)
                .when()
                .post(Constants.ROOT + "login")
                .then()
                .log()
                .all();

    }

    @Step("Удаление пользователя")
    public void delete (String accessToken) {
        given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URI)
                .header("Authorization", accessToken)
                .when()
                .delete(Constants.ROOT + "user")
                .then()
                .log()
                .all();
    }
    @Step("Ответ на успешное удаление пользователя")
    public void userDeleted(ValidatableResponse response){
        response
                .assertThat()
                .body("message", equalTo("User successfully removed"))
                .statusCode(HTTP_ACCEPTED);
    }

    @Step("Регистрация пользователя")
    public  ValidatableResponse register(CreateUser createUser) {
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .baseUri(Constants.BASE_URI)
                .body(createUser)
                .when()
                .post(Constants.ROOT + "register")
                .then()
                .log()
                .all();
    }
    @Step("Получить токен")
    public String getToken(ValidatableResponse response){
        return response
                .assertThat()
                .body("success", equalTo(true))
                .statusCode(HTTP_OK)
                .extract()
                .body()
                .path("accessToken");
    }


}

