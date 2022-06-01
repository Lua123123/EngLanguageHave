package com.example.englanguage.model.login;

public class UserLogin {
    private String email;
    private String password;
    private Boolean remember_me;

    public UserLogin(String email, String password, Boolean remember_me) {
        this.email = email;
        this.password = password;
        this.remember_me = remember_me;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(Boolean remember_me) {
        this.remember_me = remember_me;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", remember_me=" + remember_me +
                '}';
    }
}
