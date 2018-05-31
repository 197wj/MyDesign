package com.design.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符转换
 */
public class StringUtil {

	/**
	 * 判断字符串是空
	 */
	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断字符串不是空
	 */
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 判断某一个字符串数组中是否含有某一字符串
	 */
	public static boolean existStrArr(String str,String []strArr){
		for(int i=0;i<strArr.length;i++){
			if(strArr[i].equals(str)){
				return true;
			}
		}
		return false;
	}

	/**
     * 判断某一个字符串数组中是否含有某一字符串
     */
	public static String join(String join,String[] strAry){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<strAry.length;i++){
             if(i==(strAry.length-1)){
                 sb.append(strAry[i]);
             }else{
                 sb.append(strAry[i]).append(join);
             }
        }
        return new String(sb);
    }

	/**
	 * 字符串首字母转大写
	 * @param str
	 * @return
	 */
	public static String toUpperFirstChar(String str) {
	    char[] cs=str.toCharArray();
	    cs[0]-=32;
	    return String.valueOf(cs);
	}
	/**
	 * 
	  * 查找字符串中是否有特殊字符
	  * @param str
	  * @return true为有 false为无 
	  * @throws
	 */
	public static boolean checkSpecial(String str){
		  String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		  Pattern p = Pattern.compile(regEx);
		  Matcher m = p.matcher(str);
		return m.find();
	}
}
