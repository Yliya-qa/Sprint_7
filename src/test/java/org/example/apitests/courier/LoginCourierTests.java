package org.example.apitests.courier;

import io.qameta.allure.*;
import io.qameta.allure.junit4.*;
import io.restassured.response.Response;
import org.example.resthandlers.apihandlers.CourierAPIHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;


@Link(url = "https://qa-scooter.praktikum-services.ru/docs/#api-Courier-Login", name = "#api-Courier-Login")
@Tag("login-courier")
@Epic("Sprint 7. Project")
@Feature("Группа тестов для API логина курьера")
@DisplayName("2. Логин курьера")
public class LoginCourierTests extends CourierAPIHandler {
    private String login;      // Логин курьера
    private String password;   // Пароль курьера
    private String firstName;  // Имя курьера


    @Before
    @Step("Подготовка данных для тестирования")
    public void prepareTestData() {
        this.login = "courier_" + UUID.randomUUID(); // Генерация уникального логина
        this.password = "pass_" + UUID.randomUUID(); // Генерация уникального пароля
        this.firstName = "name_" + UUID.randomUUID(); // Генерация уникального имени

        createCourier(login, password, firstName); // Создание курьера перед запуском тестов
    }


    @After
    @Step("Очистка данных после теста")
    public void clearAfterTests() {
        Integer idCourier = getIdCourier(loginCourier(login, password)); // Получение ID курьера
        if (idCourier == null) return; // Если курьер не найден, выходим

        deleteCourier(idCourier); // Удаление курьера
    }


    @Test
    @DisplayName("Логин курьера в систему")
    @Description("Тест проверяет API логина курьера. Ожидаемый результат - курьер залогинен в системе, возвращается его id")
    public void loginCourierIsSuccess() {
        Response response = loginCourier(login, password); // Логин курьера

        checkStatusCode(response, 200); // Проверка статус кода
        checkCourierIDNotNull(response); // Проверка, что ID курьера не null
    }


    @Test
    @DisplayName("Логин курьера в систему без входных данных")
    @Description("Тест проверяет API логина курьера без входных данных. Ожидаемый результат - курьер НЕ залогинен в системе")
    public void loginCourierMissingAllParamsIsFailed() {
        Response response = loginCourier("", ""); // Логин без данных

        checkStatusCode(response, 400); // Проверка статус кода
        checkMessage(response, "message", "Недостаточно данных для входа"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Логин курьера в систему без логина")
    @Description("Тест проверяет API логина курьера без логина. Ожидаемый результат - курьер НЕ залогинен в системе")
    public void loginCourierMissingLoginParamIsFailed() {
        Response response = loginCourier("", password); // Логин без логина

        checkStatusCode(response, 400); // Проверка статус кода
        checkMessage(response, "message", "Недостаточно данных для входа"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Логин курьера в систему без пароля")
    @Description("Тест проверяет API логина курьера без пароля. Ожидаемый результат - курьер НЕ залогинен в системе")
    public void loginCourierMissingPasswordParamIsFailed() {
        Response response = loginCourier(login, ""); // Логин без пароля

        checkStatusCode(response, 400); // Проверка статус кода
        checkMessage(response, "message", "Недостаточно данных для входа"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Логин курьера в систему с некорректным логином")
    @Description("Тест проверяет API логина курьера с некорректным логином. Ожидаемый результат - курьер НЕ залогинен в системе")
    public void loginCourierIncorrectLoginParamIsFailed() {
        Response response = loginCourier(login + "1", password); // Логин с некорректным логином

        checkStatusCode(response, 404); // Проверка статус кода
        checkMessage(response, "message", "Учетная запись не найдена"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Логин курьера в систему с некорректным паролем")
    @Description("Тест проверяет API логина курьера с некорректным паролем. Ожидаемый результат - курьер НЕ залогинен в системе")
    public void loginCourierIncorrectPasswordParamIsFailed() {
        Response response = loginCourier(login, password + "1"); // Логин с некорректным паролем

        checkStatusCode(response, 404); // Проверка статус кода
        checkMessage(response, "message", "Учетная запись не найдена"); // Проверка сообщения об ошибке
    }
}
