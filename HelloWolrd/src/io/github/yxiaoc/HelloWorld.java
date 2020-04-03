package io.github.yxiaoc;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        Dog dog = new Dog("BlueDog",3);
        System.out.println(dog.toString());
        Cat cat = new Cat("Doreamon",3);
        System.out.println(cat.toString());
        Blue b = new Blue ("test",1);
        b.eat();
    }
}
