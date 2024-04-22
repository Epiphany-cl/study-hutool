package com.chenlong.core.clone;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.io.Serializable;

/**
 * Hutool 中克隆的方法
 * 1. 继承 Cloneable<T> 接口；返回的是定义的泛型 T，类型转换需要自己实现
 * 2. 继承 CloneSupport<T> 接口；返回的是定义的泛型 T，且不同从写父类
 * 3. ObjectUtil.cloneByStream(obj)；利用序列化进行深拷贝
 */
public class TestHutoolClone {
    // 1. 继承 Cloneable<T> 接口 🐱
    @ToString
    @AllArgsConstructor
    static class Cat implements Cloneable<Cat> {
        private String name;
        private Integer age;

        @Override
        public Cat clone() {
            try {
                // 在 clone() 方法里进行强制转换
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void TestCloneable(){
        Cat cat = new Cat("招财", 1);
        Cat cloneCat = cat.clone();
        System.out.println("cat = " + cat);
        System.out.println("cloneCat = " + cloneCat);
    }

    // 2. 继承 CloneSupport<T> 接口 🐶
    @ToString
    @AllArgsConstructor
    static class Dog extends CloneSupport<Dog> {
        private String name;
        private Integer age;
    }

    @Test
    public void TestCloneSupport(){
        Dog dog = new Dog("进宝", 1);
        Dog cloneDog = dog.clone();
        System.out.println("dog = " + dog);
        System.out.println("cloneDog = " + cloneDog);
    }

    // 3.ObjectUtil.cloneByStream(obj)
    @ToString
    @AllArgsConstructor
    static class Bird implements Serializable {
        private String name;
        private Integer age;
    }

    @Test
    public void TestCloneByStream(){
        Bird bird = new Bird("啾啾", 1);
        Bird cloneBird = ObjectUtil.cloneByStream(bird);
        System.out.println("bird = " + bird);
        System.out.println("cloneBird = " + cloneBird);
    }

}
