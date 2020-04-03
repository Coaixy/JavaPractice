package io.github.yxiaoc;

public class Cat extends Animal{
    private String name;
    private int age;
    Cat(String name,int age){
        this.name = name;this.age = age;
    }
    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
