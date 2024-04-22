package com.chenlong.core.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试 传统 clone 方法
 */
public class TestJavaClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Java 的克隆方法，返回的是 Object 需要强制转化才能得到被克隆的对象
        Cat cat = new Cat("招财", 1);
        Cat cloneCat = (Cat) cat.clone();
        System.out.println("cat = " + cat);
        System.out.println("cloneCat = " + cloneCat);
    }

    // 继承 java.lang.Cloneable 接口的传统猫猫类🐱
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Cat implements java.lang.Cloneable {
        private String name;
        private Integer age;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}

