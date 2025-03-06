package com.ohgiraffers.session.user.model.dto;

/* 데이터베이스와 완전히 1대1 매핑된다. */
public class AuthorityDTO {

    private int code;
    private String name;
    private String description;

    public AuthorityDTO() {
    }

    public AuthorityDTO(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
