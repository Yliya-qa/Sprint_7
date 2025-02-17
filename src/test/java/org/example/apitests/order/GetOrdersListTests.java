package org.example.apitests.order;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.response.Response;
import org.example.resthandlers.apihandlers.OrdersAPIHandler;
import org.junit.Test;

@Link(url = "https://qa-scooter.praktikum-services.ru/docs/#api-Orders-CreateOrder", name = "#api-Orders-GetOrdersPageByPage")
@Tag("get-orders-list")
@Epic("Sprint 7. Project")
@Feature("Группа тестов для API получения списка заказа")
@DisplayName("4. Получение списка заказов")
public class GetOrdersListTests extends OrdersAPIHandler {

    // Тест на получение списка заказов
    @Test
    @DisplayName("Получение списка заказов") // Отображаемое имя теста
    @Description("Тест проверяет API получения списка заказов. Ожидаемый результат - возвращается список заказов") // Описание теста
    public void getOrderListWithoutParamsIsSuccess() {
        // Вызов метода для получения списка заказов
        Response response = getOrdersList();

        // Проверка кода статуса ответа (200 - ОК)
        checkStatusCode(response, 200);
        // Проверка, что в ответе содержится список заказов
        checkOrdersInResponse(response);
    }
}