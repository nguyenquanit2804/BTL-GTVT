package com.gtvt.backendcustomermanagement.utils;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author quanNA
 */
public class StringUtil {
    /***
     * Kiem tra chuoi co ky tu dac biet
     *
     * @param inputStr
     * @return boolean
     */
    public static boolean isContainSpecialCharacter(String inputStr) {

        if (inputStr == null) {
            return false;
        }
        inputStr = inputStr.replace(" ", "");
        if ("".equals(inputStr)) {
            return false;
        }

        Pattern p = Pattern.compile("[~!@#$%^&*()-=+_?><,|]");
        Matcher m = p.matcher(inputStr);

        return m.find();
    }

    public static String valueOfTimestamp(Date date, String format) {

        DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(date);

    }

    public static String replaceAllSpaces(String str, String replaceBy) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        return str.replaceAll("\\s+", replaceBy);
    }

    public static boolean isNullOrEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    public static boolean isNotNullOrEmpty(String str) {
        return (!(isNullOrEmpty(str)));
    }

    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; ++i) {
            if (!(Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return (!(isBlank(str)));
    }

    public static boolean equals(String str1, String str2) {
        return (str1 != null && (str2 == null || str1.equals(str2)));
    }

    public static boolean checkRegexStr(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean checkLength(String str, int min, int max) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        } else
            return str.length() >= min && str.length() <= max;
    }

    public static boolean checkMinLength(String str, int min) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        } else
            return str.length() >= min;
    }

    public static boolean checkMaxLength(String str, int max) {
        if ((str == null) || (str.length() == 0)) {
            return true;
        } else
            return str.length() <= max;
    }

    public static String replace(String value, String oldPart, String newPart) {
        if (value == null || value.length() == 0 || oldPart == null || oldPart.length() == 0)
            return value;
        //
        int oldPartLength = oldPart.length();
        String oldValue = value;
        StringBuilder retValue = new StringBuilder();
        int pos = oldValue.indexOf(oldPart);
        while (pos != -1) {
            retValue.append(oldValue, 0, pos);
            if (newPart != null && newPart.length() > 0)
                retValue.append(newPart);
            oldValue = oldValue.substring(pos + oldPartLength);
            pos = oldValue.indexOf(oldPart);
        }
        retValue.append(oldValue);
        return retValue.toString();
    }

    public static boolean isHasWhiteSpaceBeginEnd(String str) {
        if ((str == null) || (str.length() == 0))
            return false;
        return (str.endsWith(" ") || str.startsWith(" "));
    }

    public static boolean isHasWhiteSpace(String str) {
        if ((str == null) || (str.length() == 0))
            return false;
        return (str.contains(" "));
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static String valueOfTimestamp(Date timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String result = "";
        if (timestamp != null) {
            result = dateFormat.format(new Date(timestamp.getTime()));
        }
        return result;
    }

    public static String valueOfTimestamp(Timestamp timestamp, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(timestamp.getTime()));
    }

    public static String doubleFormat(double value) {
        String result = "";
        NumberFormat formatter = new DecimalFormat("#0.00");
        result = formatter.format(value);
        return result;
    }

    public static String doubleFormat(double value, String format) {
        String result = "";
        NumberFormat formatter = new DecimalFormat(format);
        result = formatter.format(value);
        return result;
    }

    public static String priceWithoutDecimal(double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public static String trim(String stringToTrim, String stringToRemove) {
        String answer = stringToTrim;

        while (answer.startsWith(stringToRemove)) {
            answer = answer.substring(stringToRemove.length());
        }

        while (answer.endsWith(stringToRemove)) {
            answer = answer.substring(0, answer.lastIndexOf(stringToRemove));
        }

        return answer;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String removeLastCharOptional(String s) {
        return Optional.ofNullable(s).filter(str -> str.length() != 0).map(str -> str.substring(0, str.length() - 1))
                .orElse(s);
    }

    public static String removeLastChar(String s) {
        return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }

}
