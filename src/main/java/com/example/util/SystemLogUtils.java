package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.plugin2.util.SystemUtil;


/**
 * 日志输出转换字符串类.
 * 对字符日志输出CWE ID 117安全的修改
 * @author tanq
 * @date 2020-06-10
 */
@Slf4j
public class SystemLogUtils {

    /**
     * .
     * @param format 输出字符
     * @param args 替换字符数组
     * @return
     */
    public static final String removeControlCharacter(String format, Object... args) {
        if (StringUtils.isBlank(format)) {
            return "";
        }
        
        return "";
    }

    public static void main(String[] args) {
        String text = "将字符串text中由openToken和closeToken组成的占位符依次替换为args数组中的值";
        /*char[] cha1r = text.toCharArray();
        for (char ch: cha1r) {
            System.out.println(ch);
        }*/
        int start = text.indexOf("text",0);
        System.out.println(start);
    }
    /**
     *  将字符串text中由openToken和closeToken组成的占位符依次替换为args数组中的值.
     * @param openToken 开始字符
     * @param closeToken 结束字符
     * @param text 输出字符
     * @param args 替换字符
     * @return
     */
    public static String parse(String openToken, String closeToken, String text, Object... args) {
        if (args == null || args.length <= 0) {
            return text;
        } 
        int argsIndex = 0;
        if (StringUtils.isBlank(text)) {
            return "";
        }
        // 把需要输出的字符置换成单个字符的数组
        char[] src = text.toCharArray();
        int offset = 0;
        int start = text.indexOf(openToken,offset);
        if (start == -1) {
            return text;
        }
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        int appendCount = 0;
        while (start > -1) {
             if (start > 0 && src[start-1] == '\\') {
                 
             }
        }
        return "";
        
    }
}
