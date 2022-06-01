package com.example.englanguage.model.login

class Login(var status: Boolean, var message: String, var data: Data) {
    override fun toString(): String {
        return "Login{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}'
    }
}