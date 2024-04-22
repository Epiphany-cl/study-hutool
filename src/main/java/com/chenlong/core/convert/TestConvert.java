package com.chenlong.core.convert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 类型转换工具类-Convert
 * 解决常见转换问题，避免在业务处写大量转换代码，减少臃肿
 * 1. Java常见类型转换 toXXX()
 * 2. 其它类型转换 Convert.convert(Class<T>, Object)
 * 3. 半角和全角转换
 * 4. 16进制（Hex）
 * 5. Unicode和字符串转换
 * 6. 编码转换
 * 7. 时间单位转换
 * 8. 金额大小写转换
 * 9. 数字转换
 * 10. 原始类和包装类转换
 */
public class TestConvert {
    // 1. Java常见类型转换
    @Test
    public void TestCommon() {
        // a. toStr()
        String intString = Convert.toStr(1);
        String arrString = Convert.toStr(new int[]{1, 2, 3, 4, 5});
        System.out.println("intString = " + intString);
        System.out.println("arrString = " + arrString);

        // b. toXXXArray(): toIntArray()、toByteArray()、toBooleanArray···
        Character[] charArray = Convert.toCharArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("charArray = " + Arrays.toString(charArray));

        // c. toDate 好像没啥用，效果和 new Date("2024/1/1") 一样
        Date date = Convert.toDate("2024/1/1");
        System.out.println("date = " + date);

        // d. toList()
        List<?> list = Convert.toList(new int[]{1, 2, 3, 4, 5});
        System.out.println("list = " + list);
        List<Integer> integerList = Convert.toList(Integer.class, new int[]{1, 2, 3, 4, 5});
        System.out.println("integerList = " + integerList);
    }

    // 2. 其它类型转换
    @Test
    public void OtherConvert() {
        // a. 标准类型
        String convert = Convert.convert(String.class, new int[]{1, 2, 3, 4, 5});
        System.out.println("convert = " + convert);

        // b. 泛型类型
        List<String> convertList = Convert.convert(
                new TypeReference<List<String>>() {
                },
                new int[]{1, 2, 3, 4, 5});
        System.out.println("convertList = " + convertList);
    }

    // 3. 半角和全角转换 toSBC()、toDBC()
    @Test
    public void TestSBC() {
        String str = "123456789";
        String dbc = Convert.toDBC(str);
        String sbc = Convert.toSBC(dbc);
        System.out.println("dbc = " + dbc);
        System.out.println("sbc = " + sbc);
    }

    // 4. 16进制（Hex）toHex()、hexToStr()
    @Test
    public void TestHex() {
        // a. 转化为 16 进制 toHex()
        String str = "hello word";
        String hex = Convert.toHex(str, CharsetUtil.CHARSET_UTF_8);
        System.out.println("hex = " + hex);

        // b. 将16进制（Hex）字符串转为普通字符串 hexToStr()
        String hexToStr = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("hexToStr = " + hexToStr);
    }

    // 5. Unicode和字符串转换 strToUnicode()、unicodeToStr()
    @Test
    public void TestUnicode() {
        String str = "你好，世界";
        String unicode = Convert.strToUnicode(str);
        String unicodeToStr = Convert.unicodeToStr(unicode);
        System.out.println("unicode = " + unicode);
        System.out.println("unicodeToStr = " + unicodeToStr);
    }

    // 6. 编码转换 convertCharset() 把乱码转为正确的编码方式
    @Test
    public void TestConvertCharSet() {
        String str = "你好，世界";
        String iosStr = Convert.convertCharset(str, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String gbkStr = Convert.convertCharset(iosStr, CharsetUtil.ISO_8859_1, CharsetUtil.GBK);
        String utfStr = Convert.convertCharset(gbkStr, CharsetUtil.GBK, CharsetUtil.UTF_8);
        System.out.println("iosStr = " + iosStr);
        System.out.println("gbkStr = " + gbkStr);
        System.out.println("utfStr = " + utfStr); // 输出：你好，世�? 原因应该是 “界” 在 ISO_8859_1 转 GBK 时丢失了
    }


}