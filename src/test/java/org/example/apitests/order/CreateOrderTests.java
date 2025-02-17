package org.example.apitests.order;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.response.Response;
import org.example.resthandlers.apihandlers.OrdersAPIHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
@Link(url = "https://qa-scooter.praktikum-services.ru/docs/#api-Orders-CreateOrder", name = "#api-Orders-CreateOrder") // Ссылка на документацию API
@Tag("create-order")
@Epic("Sprint 7. Project")
@Feature("Группа тестов для API создания заказа")
@DisplayName("3. Создание заказа")
public class CreateOrderTests extends OrdersAPIHandler {
    // Поля для хранения данных заказа
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private final List<String> scooterColor;
    private Integer trackId;

    // Конструктор, принимающий список цветов самоката
    public CreateOrderTests(List<String> scooterColor) {
        this.scooterColor = scooterColor;
    }

    // Параметры для тестов, определяющие различные цвета самоката
    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    public static Object[][] initParamsForTest() {
        return new Object[][] {
                {List.of()},                     // Без цвета
                {List.of("BLACK")},             // Черный цвет
                {List.of("GREY")},              // Серый цвет
                {List.of("BLACK", "GREY")},     // Черный и серый цвета
        };
    }

    // Метод, выполняемый перед каждым тестом, для подготовки данных
    @Before
    @Step("Подготовка тестовых данных") // Шаг для Allure
    public void prepareTestData() {
        this.firstName = "testName";
        this.lastName = "testLastName";
        this.address = "Москва, Тестовая ул., д. 123";
        this.phone = "+7 (901) 234-56-78";
        this.rentTime = "3";
        this.deliveryDate = "2023-07-24";
        this.comment = "Some comment";
    }

    // Метод, выполняемый после каждого теста, для очистки данных
    @After
    @Step("Очистка данных после теста") // Шаг для Allure
    public void clearAfterTests() {
        if (trackId == null) return; // Если трек-номер не установлен, ничего не делать

        deleteOrder(trackId); // Удаление заказа по трек-номеру
    }

    // Тест на создание заказа
    @Test
    @DisplayName("Создание заказа") // Отображаемое имя теста
    @Description("Тест проверяет API создания заказа. Ожидаемый результат - заказ создан, возвращается его track-номер") // Описание теста
    public void createOrderIsSuccess() {
        Allure.parameter("Цвет самоката", scooterColor); // Логирование параметра для Allure

        // Создание заказа с заданными параметрами
        Response response = createOrder(firstName, lastName, address, phone, rentTime, deliveryDate, comment, scooterColor);
        checkStatusCode(response, 201); // Проверка кода статуса ответа (201 - создано)
        checkResponseParamNotNull(response, "track"); // Проверка, что параметр "track" не равен null

        this.trackId = getTrack(response); // Получение трек-номера из ответа
    }
}