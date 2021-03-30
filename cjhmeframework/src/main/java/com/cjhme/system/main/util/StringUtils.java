package com.cjhme.system.main.util;

/**
 * 
 * <p>  
 * Title: StringUtils.java 
 * </p>  
 * Description: 字符串工具
 * <p>
 * Modify histoty:
 * @author  cjh  
 * @version 1.0
 * @created Sep 14, 2014 9:47:04 AM
 */
public abstract class StringUtils {
	
	/**
	 * 判断字符串是否为空
	 * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
	 */
    public static boolean isEmpty(final CharSequence cs){
        return cs == null || cs.length() == 0;
    }
    


    /**
     * 判断字符串是否空白
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     */
    public static boolean isBlank(final CharSequence cs){
        int strLen;
        if(cs == null || (strLen = cs.length()) == 0){
            return true;
        }

        for(int i=0; i<strLen; i++){
            if(Character.isWhitespace(cs.charAt(i)) == false){
                return false;
            }
        }

        return true;
    }
}
