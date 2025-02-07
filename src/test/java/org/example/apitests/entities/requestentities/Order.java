package org.example.apitests.entities.requestentities;

import java.util.List;


public class Order {
    private String firstName;         // Имя заказчика
    private String lastName;          // Фамилия заказчика
    private String address;           // Адрес доставки
    private String phone;             // Телефон заказчика
    private String rentTime;          // Время аренды
    private String deliveryDate;      // Дата доставки
    private String comment;           // Комментарий к заказу
    private List<String> color;       // Список цветов (например, для товара)

    // Конструктор без параметров (по умолчанию)
    public Order() {
    }

    // Конструктор с параметрами для создания заказа с указанными данными
    public Order(String firstName, String lastName, String address, String phone, String rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;        // Установка имени заказчика
        this.lastName = lastName;          // Установка фамилии заказчика
        this.address = address;            // Установка адреса доставки
        this.phone = phone;                // Установка телефона заказчика
        this.rentTime = rentTime;          // Установка времени аренды
        this.deliveryDate = deliveryDate;  // Установка даты доставки
        this.comment = comment;            // Установка комментария к заказу
        this.color = color;                // Установка списка цветов
    }

    // Метод для получения имени заказчика
    public String getFirstName() {
        return firstName; // Возвращает имя заказчика
    }

    // Метод для установки имени заказчика
    public void setFirstName(String firstName) {
        this.firstName = firstName; // Устанавливает новое имя
    }

    // Метод для получения фамилии заказчика
    public String getLastName() {
        return lastName; // Возвращает фамилию заказчика
    }

    // Метод для установки фамилии заказчика
    public void setLastName(String lastName) {
        this.lastName = lastName; // Устанавливает новую фамилию
    }

    // Метод для получения адреса доставки
    public String getAddress() {
        return address; // Возвращает адрес доставки
    }

    // Метод для установки адреса доставки
    public void setAddress(String address) {
        this.address = address; // Устанавливает новый адрес
    }

    // Метод для получения телефона заказчика
    public String getPhone() {
        return phone; // Возвращает телефон заказчика
    }

    // Метод для установки телефона заказчика
    public void setPhone(String phone) {
        this.phone = phone; // Устанавливает новый телефон
    }

    // Метод для получения времени аренды
    public String getRentTime() {
        return rentTime; // Возвращает время аренды
    }

    // Метод для установки времени аренды
    public void setRentTime(String rentTime) {
        this.rentTime = rentTime; // Устанавливает новое время аренды
    }

    // Метод для получения даты доставки
    public String getDeliveryDate() {
        return deliveryDate; // Возвращает дату доставки
    }

    // Метод для установки даты доставки
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate; // Устанавливает новую дату доставки
    }

    // Метод для получения комментария к заказу
    public String getComment() {
        return comment; // Возвращает комментарий к заказу
    }

    // Метод для установки комментария к заказу
    public void setComment(String comment) {
        this.comment = comment; // Устанавливает новый комментарий
    }

    // Метод для получения списка цветов
    public List<String> getColor() {
        return color; // Возвращает список цветов
    }

    // Метод для установки списка цветов
    public void setColor(List<String> color) {
        this.color = color; // Устанавливает новый список цветов
    }
}