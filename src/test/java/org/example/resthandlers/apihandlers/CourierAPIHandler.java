package org.example.resthandlers.apihandlers;

import org.example.apitests.entities.requestentities.Courier;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.*;
import org.example.apitests.entities.responseentities.CourierResponse;
import org.example.resthandlers.httpclients.CourierHTTPClient;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class CourierAPIHandler {

    private final CourierHTTPClient courierHTTPClient = new CourierHTTPClient(); // Инициализация HTTP-клиента для курьеров

    private boolean isCreated = false; // Флаг для отслеживания, создан ли курьер

    // Метод для отправки запроса на создание курьера
    @Step("Отправка запроса на создание курьера") // Шаг для Allure
    public Response createCourier(String login, String password, String firstName) {
        // Создание нового курьера и отправка запроса на создание
        return courierHTTPClient.createCourier(new Courier(login, password, firstName));
    }

    // Метод для отправки запроса на логин курьера
    @Step("Отправка запроса на логин курьера") // Шаг для Allure
    public Response loginCourier(String login, String password) {
        // Отправка запроса на логин с использованием данных курьера
        return courierHTTPClient.loginCourier(new Courier(login, password));
    }

    // Метод для получения ID курьера из ответа
    @Step("Получение ID курьера") // Шаг для Allure
    public Integer getIdCourier(Response response) {
        // Преобразование тела ответа в объект CourierResponse и получение ID
        return response.body().as(CourierResponse.class).getId();
    }

    // Метод для отправки запроса на удаление курьера
    @Step("Отправка запроса на удаление курьера") // Шаг для Allure
    public Response deleteCourier(Integer idCourier) {
        // Отправка запроса на удаление курьера по его ID
        return courierHTTPClient.deleteCourier(idCourier);
    }

    // Метод для проверки кода ответа
    @Step("Проверка кода ответа") // Шаг для Allure
    public void checkStatusCode(Response response, int code) {
        Allure.addAttachment("Ответ", response.getStatusLine()); // Присоединение информации о статусе ответа к отчету Allure
        response.then().statusCode(code); // Проверка, что код ответа соответствует ожидаемому
    }

    // Метод для проверки тела ответа на соответствие ожидаемым данным
    @Step("Проверка тела ответа") // Шаг для Allure
    public void checkMessage(Response response, String label, Object body) {
        Allure.addAttachment("Ответ", response.getBody().asInputStream()); // Присоединение тела ответа к отчету Allure
        response.then().assertThat().body(label, equalTo(body)); // Проверка, что указанное поле в теле ответа соответствует ожидаемому значению
    }

    // Метод для проверки, что ID курьера вернулся в ответе
    @Step("Проверка, что ID курьера вернулся") // Шаг для Allure
    public void checkCourierIDNotNull(Response response) {
        Allure.addAttachment("Ответ", response.getBody().asInputStream()); // Присоединение тела ответа к отчету Allure
        response.then().assertThat().body("id", notNullValue()); // Проверка, что поле "id" в теле ответа не равно null
    }

    // Метод для проверки, был ли курьер успешно создан
    public boolean isCourierCreated(Response response, int code) {
        if (response.getStatusCode() != code) return false; // Если код ответа не соответствует ожидаемому, вернуть false

        this.isCreated = true; // Установка флага, что курьер создан
        return true; // Возвращаем true, если курьер успешно создан
    }

    // Метод для установки флага о создании курьера
    public void setIsCreated(boolean isCreated) {
        this.isCreated = isCreated; // Установка значения флага
    }

    // Метод для получения значения флага о создании курьера
    public boolean isCourierCreated() {
        return this.isCreated; // Возвращение текущего значения флага
    }
}
