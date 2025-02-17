package org.example.apitests.entities.responseentities;


public class CourierResponse {
    private Integer id;        // Идентификатор курьера
    private boolean ok;        // Статус успешности запроса (true, если успешно)
    private String message;    // Сообщение, связанное с ответом

    // Конструктор без параметров (по умолчанию)
    public CourierResponse() {
    }

    // Конструктор с параметрами для создания ответа с указанными данными
    public CourierResponse(Integer id, boolean ok, String message) {
        this.id = id;              // Установка идентификатора курьера
        this.ok = ok;              // Установка статуса успешности запроса
        this.message = message;    // Установка сообщения ответа
    }

    // Метод для получения идентификатора курьера
    public Integer getId() {
        return id; // Возвращает идентификатор курьера
    }

    // Метод для установки идентификатора курьера
    public void setId(Integer id) {
        this.id = id; // Устанавливает новый идентификатор
    }

    // Метод для получения статуса успешности запроса
    public boolean getOk() {
        return ok; // Возвращает статус успешности запроса
    }

    // Метод для установки статуса успешности запроса
    public void setOk(Boolean ok) {
        this.ok = ok; // Устанавливает новый статус
    }

    // Метод для получения сообщения ответа
    public String getMessage() {
        return message; // Возвращает сообщение ответа
    }

    // Метод для установки сообщения ответа
    public void setMessage(String message) {
        this.message = message; // Устанавливает новое сообщение
    }
}
