package com.chenlong.core.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试 传统clone方法
 */
public class TestOriginalClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        //
        OriginalCat originalCat = new OriginalCat("招财", 1);
        Object cloneCat = originalCat.clone();
        System.out.println(originalCat);
        System.out.println(cloneCat);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class OriginalCat implements Cloneable{
        private String name;
        private Integer age;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}

