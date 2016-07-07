/******************************************************************************
 * @File name   :      Utils.java
 *
 * @Author      :      hou
 *
 * @Date        :      2014-2-27
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 GYMBOREE, Inc. All  Rights Reserved.
 * This software is published under the terms of the GYMBOREE Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2014-2-27 涓嬪崍03:54:36        hou     1.0            Initial Version
 *****************************************************************************/
package com.TRMS.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * This class contains the common utilities methods
 */
public final class Utils {

	/**
	 * default private constructor
	 * 
	 * @Date : 2014-3-28
	 */
	private Utils() {

	}

	/**
	 * Check if the input str is null or ""
	 * 
	 * @Date : 2014-3-21
	 * @param str
	 *            - string
	 * @return true if str is null or empty; otherwise, false
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * Check if the input str is not null and ""
	 * 
	 * @Date : 2014-3-21
	 * @param str
	 *            - string
	 * @return true if str is not null and empty
	 */
	public static boolean notEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}
    /**
     * Check if the input long id is not null and 0
     * @Date        :      2014-7-19
     * @param id
     * @return
     */
	public static boolean notNullAndZero(Long id) {
		return id != null && id != 0;
	}
	
	/**
	 * Check if the input integer id is not null and 0		
	 * @Date        :      2014-7-19
	 * @param id
	 * @return
	 */
	public static boolean notNullAndZero(Integer id) {
		return id != null && id != 0;
	}
	
	/**
	 * check if the value is null and negative
	 * @Date        :      2014-11-8
	 * @param value - value
	 * @return ture/false
	 */
	public static boolean notNullAndNegative(Integer value){
	    return value != null && value > 0;
	}
	
	/**
	 * check if the value is null and negative
	 * @Date        :      2014-11-8
	 * @param value - value
	 * @return true/false
	 */
	public static boolean notNullAndNegative(Long value){
	    return value != null && value > 0;
	}
	/**
	 * Formats the date input with the given format
	 * 
	 * @Date : 2014-1-25
	 * @param format
	 *            - date format strign
	 * @param date
	 *            - instance of java.util.Date
	 * @return string representation of date
	 */
	public static String formatDate(String format, Date date) {
		if (isNullOrEmpty(format) || date == null) {
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * Transfer \\ to / in path
	 * 
	 * @Date : 2014-3-9
	 * @param path
	 *            - uri path string
	 * @return String
	 */
	public static String transferPath(String path) {
		if (path == null) {
			return null;
		}
		path = path.replace("\\", "/");
		return path;
	}

	/**
	 * 鏍煎紡鍖杝tring
	 * 
	 * @Date : 2014-6-2
	 * @param str
	 * @return
	 */
	public static String removeNull(String str) {
		if (str == null)
			str = "";
		if (str.equals("null"))
			str = "";
		return str.trim();
	}

	/**
	 * Parse the date with the given date string and date format.
	 * 
	 * @Date        :      2014-6-9
	 * @param dateStr a string of date
	 * @param format a string of date format
	 * @return an instance of Date after parsed
	 * @throws ParseException 
	 */
	public static Date parseDate(String dateStr, String format) throws ParseException {
        if (isNullOrEmpty(format) || isNullOrEmpty(dateStr)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        
        return dateFormat.parse(dateStr.trim());        	   
	}
	
	/**
	 * 寰楀埌鏈懆鐨勬槦鏈熶竴鐨勬棩鏈�
	 * @Date        :      2014-6-27
	 * @return
	 */
	public static String getMondayOfThisWeek(){
	    Calendar c = Calendar.getInstance();   
	    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
	    if(dayOfWeek == 0){
	        dayOfWeek = 7;
	    }
	    c.add(Calendar.DATE, -dayOfWeek + 1);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(c.getTime());
	}
	
	/**
     * 寰楀埌鏈懆涔嬪墠鐨勬渶鍚庝竴涓殑鏄熸湡鏃ョ殑鏃ユ湡
     * @Date        :      2014-6-27
     * @return
     */
    public static String getLastSundayBeforeThisWeek(){
        Calendar c = Calendar.getInstance();   
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
        if(dayOfWeek == 0){
            dayOfWeek = 7;
        }
        c.add(Calendar.DATE, -dayOfWeek);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }
	
	/**
	 * 
	 * @Date        :      2014-7-6
	 * @param currentContactCodeSeq
	 * @return
	 */
	public static String getNextContactCodeSeq(String dealerOrgCode,Integer contactCodeSeq){
	    
		int year = Calendar.getInstance().get(Calendar.YEAR); 
		
		contactCodeSeq = contactCodeSeq + 1;

        return dealerOrgCode + year + StringUtils.leftPad(contactCodeSeq + "", 6, "0");
   
	}
	
	/**
	 * 
	 * @Date        :      2014-7-13
	 * @param ids
	 * @return
	 */
	public static String getSplitStr(List<Long> ids){
	    StringBuilder sb = new StringBuilder();
	    if(ids != null && ids.size() >0){
	        for(int i = 0;i<ids.size();i++){
	            Long id = ids.get(i);
	            if(i == 0){
	                sb.append(id);
	            }else{
	                sb.append(","+id);
	            }
	        }
	    }
	    return sb.toString();
	}
/**
 * 灏嗗瓧绗︿覆鏁扮粍缁勫悎鎴愪互鎸囧畾瀛楃鍒嗛殧鐨勫瓧绗︿覆锛屼笉鎸囧畾瀛楃鏃朵互閫楀彿鍒嗛殧
 * @Date        :      2014-7-15
 * @param objects
 * @param splitChar
 * @return
 */
	public static String getSplitString(String[] objects,String splitChar){
	    
	    if(Utils.isNullOrEmpty(splitChar)){
	        splitChar="," ;
	    }
	    
        StringBuilder sb = new StringBuilder();
        if(objects != null && objects.length >0){
            for(int i = 0;i<objects.length;i++){
                String id = objects[i];
                if(i == 0){
                    sb.append(id);
                }else{
                    sb.append(splitChar+id);
                }
            }
        }
        return sb.toString();
	}
	/**
	 * 灏哃ong鏁扮粍缁勫悎鎴愪互鎸囧畾瀛楃鍒嗛殧鐨勫瓧绗︿覆锛屼笉鎸囧畾瀛楃鏃朵互閫楀彿鍒嗛殧
	 * @Date        :      2014-7-15
	 * @param objects
	 * @param splitChar
	 * @return
	 */
	   public static String getSplitString(Long[] objects,String splitChar){
	        
	        if(Utils.isNullOrEmpty(splitChar)){
	            splitChar="," ;
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        if(objects != null && objects.length >0){
	            for(int i = 0;i<objects.length;i++){
	                Long id = objects[i];
	                if(i == 0){
	                    sb.append(id);
	                }else{
	                    sb.append(splitChar+id);
	                }
	            }
	        }
	        return sb.toString();
	    }
	   /**
	    * 
	    * @Date        :      2014-7-22
	    * @param strs
	    * @param splitChar
	    * @return
	    */
       public static List<String> getSplitList(String strs,String splitChar){
           
           if(Utils.isNullOrEmpty(splitChar)){
               splitChar="," ;
           }
           
           List<String> list=new ArrayList<String>();
           for(String str:strs.split(splitChar)){
               list.add(str);
           }
           
           return list;
       }
       
       public static Date getDateStrByDateAndTime(String date, String time) {
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String str=date;
           if (time!=null&&!"".equals(time)) {
               str=str + " " + time + ":00";
           }else{
        	   return new Date();
           }
           try {
               return dateFormat.parse(str);
           } catch (ParseException e) {
               e.printStackTrace();
               return null;
           }
      }
       /**
        * get database column date value
        * @Date        :      2014-11-3
        * @param obj - column object
        * @return - Date
        */
       public static Date getDate(Object obj){
           if(null == obj){
               return null;
           }
           return (Date)obj;
       }
       
       /**
        * get database column long value
        * @Date        :      2014-11-3
        * @param obj - column object
        * @return - Long
        */
       public static Long getLong(Object obj){
           if(null == obj){
               return null;
           }
           return ((BigDecimal)obj).longValue();
       }

       /**
        * get database column int value
        * @Date        :      2014-11-3
        * @param obj - column object
        * @return - Integer
        */
       public static Integer getInt(Object obj){
           if(null == obj){
               return null;
           }
           return Integer.parseInt(obj.toString());
          // return ((BigDecimal)obj).intValue();
       }
       
       /**
        * get database column string value
        * @Date        :      2014-11-3
        * @param obj - column object
        * @return - String
        */
       public static String getString(Object obj){
           if(null == obj){
               return "";
           }
           return String.valueOf(obj);
       }
       
    
  
    /**
     * 
     * @Date        :      Dec 9, 2014
     * @param s    瀛楃涓�
     * @param Length   楠岃瘉闀垮害
     * @param addmunber 閬囧埌闈炶嫳鏂囧瓧绗�   闇�瑕佺疮鍔犱功
     * @return
     */
      public static boolean widthCheck(String s,int Length ,int addmunber){
    	int  count =StringLength(s,addmunber);
    	if(count<=Length){
    		return true;
    	}else{
    		return false;
    	}
    } 
    /**
     *  
     * @Date        :      Dec 9, 2014
     * @param s
     * @param addmunber
     * @return
     */
    public static int StringLength(String s,int addmunber){
    	int count =0;
    	int add =addmunber;
    	if(s==null || s.equals("")){
        	count=0;		
    	}else{
    		 char[] ch = s.toCharArray();
    		 for(int i =0 ; i< ch.length; i++){ 
    			if((ch[i]>0x0001&&ch[i]<0x007e)||(0xff60<=ch[i]&&ch[i]<=0xff9f)){
    				count++;
    			}else{
    				count+=add;
    			}
    		}
    	}
		 return  count;
     }
    /**
     *   
     * @Date        :      Dec 9, 2014
     * @param s
     * @param interPart
     * @param decimalPart
     * @return
     */
    public static boolean  decimal_16_4Validato(String s) {
    	Pattern pattern = Pattern.compile("^\\d{0,12}(\\.\\d{1,4})?$");
    	if(pattern.matcher(s).matches()){
    		return  true;
    	}
    	return  false;    	     	  
    } 
    
    /**
     * Replace event term 
     * @Date        :      2014-12-14
     * @return
     */
    public static String replaceForEventTerm(String eventTerm){
        String resultStr = eventTerm;
        if(notEmpty(resultStr)){
            resultStr = resultStr.replaceAll("\r\n", "<br/>");
            resultStr = resultStr.replaceAll("\n", "<br/>");
            resultStr = resultStr.replaceAll("\\r\\n", "<br/>");
            resultStr = resultStr.replaceAll("\\n", "<br/>");
            resultStr = resultStr.replaceAll("\\\\r\\\\n", "<br/>");
            resultStr = resultStr.replaceAll("\\\\n", "<br/>");
            resultStr = resultStr.replaceAll(" ", "&nbsp;");
        }
        return resultStr;
    }
    
    /**
     * 鍒ゆ柇srcString瀛楃涓诧紙鈥�,鈥欏垎闅旂鍒嗛殧锛変腑鏄惁鍖呭惈key瀛楃涓�
     * @Date        :      Mar 23, 2012
     * @param key
     * @param srcString
     * @return -boolean
     */
    public static boolean containsKey(String key,String srcString){
    	if(Utils.notEmpty(key)&&Utils.notEmpty(srcString)){
    		String[] temp = srcString.split(",");
    		for(int index = 0;index < temp.length; index++){
    			if(key.equals(temp[index]))return true;
    		}
    	}
    	return false;
    }
 
    /**
     * 涓嶇瓑浜� 0锛岀┖锛�-1
     * @Date        :      2012-4-16
     * @param str
     * @return
     */
   public static boolean notNullAndZeroAndNegativeOne(String str){
       
       return Utils.notEmpty(str) && !"0".equals(str) && !"-1".equals(str);
   }
   
   
   /***
	 * l == null || l.size() <= 0 杩斿洖 true
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isEmptyList(List<?> l) {
		if (l == null || l.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 鍒ゆ柇list闈炵┖
	 * @Date        :      2014-12-21
	 * @param l
	 * @return
	 */
	public static boolean isNotEmptyList(List<?> l) {
		if (l != null && l.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	* @Title: isNumeric
	* @author David.Zheng
	* @return boolean 
	* @throws
	*/
	public static boolean isNumeric(String str){ 
		for(int i=str.length();--i>=0;){ 
		int chr=str.charAt(i); 
		if(chr<48 || chr>57) 
		return false;
		} 
		return true; 
	}
    
}
