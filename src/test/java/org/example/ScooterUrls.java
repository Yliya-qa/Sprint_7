package org.example;


public class ScooterUrls {
    // Основной адрес хоста API
    public static final String HOST_NAME = "http://qa-scooter.praktikum-services.ru";

    // URL для создания курьера
    public static final String CREATE_COURIER = "/api/v1/courier";

    // URL для входа курьера
    public static final String LOGIN_COURIER = "/api/v1/courier/login";

    // URL для удаления курьера
    public static final String DELETE_COURIER = "/api/v1/courier/";

    // URL для создания заказа
    public static final String CREATE_ORDER = "/api/v1/orders";

    // URL для отмены заказа
    public static final String DELETE_ORDER = "/api/v1/orders/cancel";

    // URL для получения списка заказов
    public static final String GET_ORDERS_LIST = "/api/v1/orders";
}
