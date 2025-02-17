package org.example.resthandlers.apihandlers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.apitests.entities.requestentities.Order;
import org.example.resthandlers.httpclients.OrdersHTTPClient;
import org.example.apitests.entities.responseentities.OrderResponse;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersAPIHandler {

    private final OrdersHTTPClient ordersHTTPClient = new OrdersHTTPClient(); // Инициализация HTTP-клиента для заказов

    // Метод для отправки запроса на создание заказа
    @Step("Отправка запроса на создание заказа") // Шаг для Allure
    public Response createOrder(String firstName, String lastName, String address, String phone,
                                String rentTime, String deliveryDate, String comment, List<String> scooterColor) {
        // Создание нового заказа и отправка запроса
        return ordersHTTPClient.createOrder(new Order(firstName, lastName, address, phone,
                rentTime, deliveryDate, comment, scooterColor));
    }

    // Метод для отправки запроса на удаление заказа
    @Step("Отправка запроса на удаление заказа") // Шаг для Allure
    public Response deleteOrder(Integer trackId) {
        // Отправка запроса на удаление заказа по его трек-номеру
        return ordersHTTPClient.deleteOrder(trackId);
    }

    // Метод для проверки кода ответа
    @Step("Проверка кода ответа") // Шаг для Allure
    public void checkStatusCode(Response response, int code) {
        Allure.addAttachment("Ответ", response.getStatusLine()); // Присоединение информации о статусе ответа к отчету Allure
        response.then().statusCode(code); // Проверка, что код ответа соответствует ожидаемому
    }

    // Метод для проверки, что параметр track-номер заказа не равен null
    @Step("Проверка, что track-номер заказа вернулся") // Шаг для Allure
    public void checkResponseParamNotNull(Response response, String label) {
        Allure.addAttachment("Ответ", response.getBody().asInputStream()); // Присоединение тела ответа к отчету Allure
        response.then().assertThat().body(label, notNullValue()); // Проверка, что указанный параметр в теле ответа не равен null
    }

    // Метод для получения трек-номера заказа из ответа
    @Step("Получение track-номера заказа") // Шаг для Allure
    public Integer getTrack(Response response) {
        // Преобразование тела ответа в объект OrderResponse и получение track-номера
        return response.body().as(OrderResponse.class).getTrack();
    }

    // Метод для отправки запроса на получение списка заказов
    @Step("Отправка запроса на получение списка заказов") // Шаг для Allure
    public Response getOrdersList() {
        // Отправка запроса на получение списка заказов
        return ordersHTTPClient.getOrdersList();
    }

    // Метод для проверки наличия заказов в теле ответа
    @Step("Проверка наличия заказов в теле ответа") // Шаг для Allure
    public void checkOrdersInResponse(Response response) {
        Allure.addAttachment("Список заказов", response.getBody().asInputStream()); // Присоединение тела ответа к отчету Allure
        response.then().assertThat().body("orders", notNullValue()); // Проверка, что поле "orders" в теле ответа не равно null
    }
}