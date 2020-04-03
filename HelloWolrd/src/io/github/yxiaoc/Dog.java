package io.github.yxiaoc;

public class Dog extends Animal{
    private String name;
    private int age;
    Dog(String name,int age){
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Dog{" +
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
