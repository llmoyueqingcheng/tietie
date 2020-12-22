package com.example.demo;

public class UserExt {
    private String name;
    private Integer age;
    private String ext; //success  fail

    public UserExt() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public UserExt(String name, Integer age, String ext) {
        this.name = name;
        this.age = age;
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "UserExt{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ext='" + ext + '\'' +
                '}';
    }
}
