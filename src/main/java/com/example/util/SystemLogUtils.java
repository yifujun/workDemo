package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.owasp.esapi.ESAPI;


/**
 * 日志输出转换字符串类.
 * 对字符日志输出CWE ID 117安全的修改
 * 
 * @author yifujun
 * @date 2021-06-24
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
        if (format == null) {
            return "";
        }
        format = SystemLogUtils.parse("{", "}", format, args);
        String clean = format.replace('\n', '_').replace('\r', '_');
        if (ESAPI.securityConfiguration().getLogEncodingRequired()) {
            clean = ESAPI.encoder().encodeForHTML(format);
            if (!format.equals(clean)) {
                clean += " (Encoded)";
            }
        }
        return clean;
    }

    /**
     * 将字符串text中由openToken和closeToken组成的占位符依次替换为args数组中的值.
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

        if (text == null || text.isEmpty()) {
            return "";
        }
        char[] src = text.toCharArray();
        int offset = 0;
        // search open token
        int start = text.indexOf(openToken, offset);
        if (start == -1) {
            return text;
        }
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        int appendCount = 0;
        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                // this open token is escaped. remove the backslash and continue.
                builder.append(src, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                // found open token. let's search close token.
                if (expression == null) {
                    expression = new StringBuilder();
                } else {
                    expression.setLength(0);
                }
                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        // this close token is escaped. remove the backslash and continue.
                        expression.append(src, offset, end - offset - 1).append(closeToken);
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {
                        expression.append(src, offset, end - offset);
                        offset = end + closeToken.length();
                        break;
                    }
                }
                if (end == -1) {
                    // close token was not found.
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
                    ///////////////////////////////////////仅仅修改了该else分支下的个别行代码////////////////////////

                    String value = argsIndex <= (args.length - 1)
                            ? args[argsIndex] == null ? "" : args[argsIndex].toString() : expression.toString();
                    builder.append(value);
                    offset = end + closeToken.length();
                    argsIndex++;
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                }
            }
            start = text.indexOf(openToken, offset);
            appendCount++;
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }
        //匹配规则，如果格式不对，则自动累加
        if (appendCount < args.length) {
            for (int i = appendCount; i < args.length; i++) {
                builder.append(args[i]);
            }

        }
        return builder.toString();
    }

    /**
     * 输出Error日志.
     * @param format 输出字符
     * @param args 替换字符数组
     */
    public  static void error(String format, Object... args) {
        try {
            String str = formatLog(args);
            log.error(SystemLogUtils.removeControlCharacter(str + " - " + format, args));
        } catch (Exception e) {
            log.error(SystemLogUtils.removeControlCharacter(e.getMessage()));
        }
    }

    /**
     * 输出Info日志.
     * @param format 输出字符
     * @param args 替换字符数组
     */
    public  static void info(String format, Object... args) {
        try {
            String str = formatLog(args);
            log.info(SystemLogUtils.removeControlCharacter(str + " - " + format, args));
        } catch (Exception e) {
            log.error(SystemLogUtils.removeControlCharacter(e.getMessage()));
        }
    }

    /**
     * 组装日志输出字符串.
     * @param args 输出数组
     * @return
     */
    private static String formatLog(Object... args) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String str = stackTrace[3].getClassName();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Throwable) {
                StackTraceElement[] sts = ((Throwable) args[i]).getStackTrace();
                String meaasge = ((Throwable) args[i]).getMessage();
                StringBuffer sb = new StringBuffer(meaasge != null ? meaasge : "");
                sb.append(";");
                for (StackTraceElement st : sts) {
                    sb.append(st);
                }
                args[i] = sb;
            }
        }
        return str;
    }
}
