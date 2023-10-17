package com.mysite.model;

import javax.persistence.*;

@Entity
@Table(name = "users") // Если таблица называется по-другому, измените имя здесь
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Конструкторы
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // hashCode, equals и toString (необязательно, но полезно)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' + // В реальных приложениях пароль обычно не выводят в toString!
                '}';
    }
}

