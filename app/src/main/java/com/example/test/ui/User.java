package com.example.test.ui;

public class User {
    private int target; // 0 - сброс, 1 - поддержка, 2 - набор
    private int male; // 0 - м, 1 - ж
    private int age;
    private float height;
    private float weight;
    private int activity; // 0 - минимальная, 1 - низкая, 2 - средняя, 3 - высокая, 4 - очень высокая
    private float budget; // 0 - не вводил, 1 - вводил
    private String exceps[];

    public User(int target, int male, int age, float height, float weight, int activity, float budget, String[] exceps) {
        this.target = target;
        this.male = male;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.budget = budget;
        this.exceps = exceps;
    }

    public boolean isHasbudget() {
        return budget > 100;
    }

    public void setbudget(float budget) {
        this.budget = budget;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
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
