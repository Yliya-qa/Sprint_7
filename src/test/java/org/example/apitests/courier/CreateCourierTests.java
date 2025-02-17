package org.example.apitests.courier;

import io.qameta.allure.*;
import io.qameta.allure.junit4.*;
import io.restassured.response.Response;
import org.example.resthandlers.apihandlers.CourierAPIHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * Класс CreateCourierTests содержит тесты для проверки API создания курьера.
 */
@Link(url = "https://qa-scooter.praktikum-services.ru/docs/#api-Courier-CreateCourier", name = "#api-Courier-CreateCourier")
@Tag("create-courier")
@Epic("Sprint 7. Project")
@Feature("Группа тестов для API создания курьера")
@DisplayName("1. Создание курьера")
public class CreateCourierTests extends CourierAPIHandler {
    private String login;      // Логин курьера
    private String password;   // Пароль курьера
    private String firstName;  // Имя курьера

    // Конструктор
    public CreateCourierTests() {
    }


    @Before
    @Step("Подготовка тестовых данных")
    public void prepareTestData() {
        this.login = "courier_" + UUID.randomUUID(); // Генерация уникального логина
        this.password = "pass_" + UUID.randomUUID(); // Генерация уникального пароля
        this.firstName = "name_" + UUID.randomUUID(); // Генерация уникального имени
    }


    @After
    @Step("Очистка данных после теста")
    public void cleanAfterTests() {
        if (!isCourierCreated()) return; // Проверка, был ли курьер создан

        Integer idCourier = getIdCourier(loginCourier(login, password)); // Получение ID курьера

        if (idCourier != null) {
            deleteCourier(idCourier); // Удаление курьера
        }

        setIsCreated(false); // Сбрасываем статус создания курьера
    }


    @Test
    @DisplayName("Создание нового курьера")
    @Description("Тест проверяет API создания нового курьера. Ожидаемый результат - новый курьер создан")
    public void createNewCourierIsSuccess() {
        Response response = createCourier(login, password, firstName); // Создание курьера
        setIsCreated(isCourierCreated(response, 201)); // Проверка, был ли курьер создан

        checkStatusCode(response, 201); // Проверка статус кода
        checkMessage(response, "ok", true); // Проверка сообщения
    }


    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Тест проверяет возможность создать двух одинаковых курьеров. Ожидаемый результат - одинаковых курьеров создать нельзя")
    public void createSameCouriersIsFailed(){
        // Создание первого курьера
        Response response = createCourier(login, password, firstName);
        setIsCreated(isCourierCreated(response, 201)); // Проверка создания

        checkStatusCode(response, 201); // Проверка статус кода
        checkMessage(response, "ok", true); // Проверка сообщения

        // Создание второго курьера
        response = createCourier(login, password, firstName); // Пытаемся создать второго курьера

        checkStatusCode(response, 409); // Проверка статус кода
        checkMessage(response, "message", "Этот логин уже используется"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Создание нового курьера без входных данных")
    @Description("Тест проверяет API создания нового курьера без входных данных. Ожидаемый результат - новый курьер НЕ создан")
    public void createCourierMissingAllParamsIsFailed() {
        Response response = createCourier("", "", ""); // Пытаемся создать курьера без данных
        setIsCreated(isCourierCreated(response, 201)); // Проверка создания

        checkStatusCode(response, 400); // Проверка статус кода
        checkMessage(response, "message", "Недостаточно данных для создания учетной записи"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Создание нового курьера без логина")
    @Description("Тест проверяет API создания нового курьера без логина. Ожидаемый результат - новый курьер НЕ создан")
    public void createCourierMissingLoginParamIsFailed() {
        Response response = createCourier("", password, firstName); // Пытаемся создать курьера без логина
        setIsCreated(isCourierCreated(response, 201)); // Проверка создания

        checkStatusCode(response, 400); // Проверка статус кода
        checkMessage(response, "message", "Недостаточно данных для создания учетной записи"); // Проверка сообщения об ошибке
    }


    @Test
    @DisplayName("Создание нового курьера без пароля")
    @Description("Тест проверяет API создания нового курьера без пароля. Ожидаемый результат - новый курьер НЕ создан")
    public void createCourierMissingPasswordParamIsFailed() {
        Response response = createCourier(login, "", firstName); // Пытаемся создать курьера без пароля
        setIsCreated(isCourierCreated(response, 201)); // Проверка создания

        checkStatusCode(response, 400); // Проверка статус кода
        checkMessage(response, "message", "Недостаточно данных для создания учетной записи"); // Проверка сообщения об ошибке
    }
}
