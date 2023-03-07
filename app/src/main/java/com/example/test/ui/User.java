package com.example.test.ui;

public class User {
    private int target; // 0 - сброс, 1 - поддержка, 2 - набор
    private int male; // 0 - м, 1 - ж
    private int age;
    private double height;
    private double weight;
    private int calories;
    private int activity; // 0 - минимальная, 1 - низкая, 2 - средняя, 3 - высокая, 4 - очень высокая
    private double proteins;
    private double fats;
    private double carbs;

    private double currentProteins;
    private double currentFats;
    private double currentCarbs;

    private double[] activityValues = new double[]{1.2, 1.37, 1.55, 1.7, 1.9};
    private double[][] PFCC = new double[4][4];
    public User(int target, int male, int age, float height, float weight,
                int activity) {
        this.target = target;
        this.male = male;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public void setPFCC(){
        if (male == 0) {
            this.calories = (int) caloriesCalc(250, 5);
        } else {
            this.calories = (int) caloriesCalc(125, -161);
        }
        this.proteins = calories * 0.3;
        this.fats = calories * 0.3;
        this.carbs = calories * 0.4;

        PFCC[0][0] = calories * 0.36;
        PFCC[0][1] = proteins * 0.28;
        PFCC[0][2] = fats * 0.43;
        PFCC[0][3] = carbs * 0.38;
        PFCC[1][0] = calories * 0.38;
        PFCC[1][1] = proteins * 0.33;
        PFCC[1][2] = fats * 0.43;
        PFCC[1][3] = carbs * 0.38;
        PFCC[2][0] = calories * 0.20;
        PFCC[2][1] = proteins * 0.33;
        PFCC[2][2] = fats * 0.08;
        PFCC[2][3] = carbs * 0.18;
        PFCC[3][0] = calories * 0.06;
        PFCC[3][1] = proteins * 0.06;
        PFCC[3][2] = fats * 0.06;
        PFCC[3][3] = carbs * 0.06;
    }
    private double caloriesCalc(int x, int diff) {
        double calories = (int) ((9.99 * weight + 6.25 * height - 4.92 * age + diff) *
                activityValues[activity]);
        if (target == 0) {
            calories = calories - x;
        } else if (target == 1) {
            calories = calories;
        } else {
            calories = calories + x;
        }
        return calories;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Цель: " + target + " Пол: " + male + " Возраст: " + age + " Рост: " + height + " Вес: " + weight; // добавить Exceps
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbs() {
        return carbs;
    }
    public double[] getBreakfastStats(){
        return PFCC[0];
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getCurrentProteins() {
        return currentProteins;
    }

    public double getCurrentFats() {
        return currentFats;
    }

    public double getCurrentCarbs() {
        return currentCarbs;
    }
}
