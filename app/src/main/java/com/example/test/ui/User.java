package com.example.test.ui;

public class User {
    private String target;
    private String male;
    private int age;
    private float height;
    private float weight;
    private String exceps[];

    public User(String target, String male, int age, float height, float weight, String[] exceps) {
        this.target = target;
        this.male = male;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.exceps = exceps;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String[] getExceps() {
        return exceps;
    }

    public void setExceps(String[] exceps) {
        this.exceps = exceps;
    }
    @Override
    public  String toString(){
        return "Цель: " + target + " Пол: " + male + " Возраст: " + age + " Рост: " + height + " Вес: " + weight; // добавить Exceps
    }
}
