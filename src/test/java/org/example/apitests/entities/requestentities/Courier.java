package org.example.apitests.entities.requestentities;


public class Courier {
    private String login;
    private String password;
    private String firstName;

    // Конструктор без параметров (по умолчанию)
    public Courier() { }

    // Конструктор с параметрами для создания курьера с логином, паролем и именем
    public Courier(String login, String password, String firstName) {
        this.login = login;          // Установка логина
        this.password = password;    // Установка пароля
        this.firstName = firstName;  // Установка имени
    }

    // Конструктор с параметрами для создания курьера с логином и паролем
    public Courier(String login, String password) {
        this.login = login;          // Установка логина
        this.password = password;     // Установка пароля
    }

    // Метод для получения логина курьера
    public String getLogin() {
        return login; // Возвращает логин
    }

    // Метод для установки логина курьера
    public void setLogin(String login) {
        this.login = login; // Устанавливает новый логин
    }

    // Метод для получения пароля курьера
    public String getPassword() {
        return password; // Возвращает пароль
    }

    // Метод для установки пароля курьера
    public void setPassword(String password) {
        this.password = password; // Устанавливает новый пароль
    }

    // Метод для получения имени курьера
    public String getFirstName() {
        return firstName; // Возвращает имя
    }

    // Метод для установки имени курьера
    public void setFirstName(String firstName) {
        this.firstName = firstName; // Устанавливает новое имя
    }
}