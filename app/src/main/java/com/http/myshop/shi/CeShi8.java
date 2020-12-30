package com.http.myshop.shi;

public class CeShi8 {
    private String name;
    private int age;

    public CeShi8(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CeShi8{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
