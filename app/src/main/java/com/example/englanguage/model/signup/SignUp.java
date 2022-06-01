package com.example.englanguage.model.signup;

import com.example.englanguage.model.login.Data;

public class SignUp {
    private Boolean status;
    private String message;
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SignUp{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
