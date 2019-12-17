package com.yyg.sbt.test;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: duanjinlong
 * @Date: 2019/12/11 15:01
 * @Description:
 */
public class DateUtil {

    //大部分敏感词汇在10个以内，直接返回缓存的字符串
    public static String[] starArr={"*","**","***","****","*****","******","*******","********","*********","**********"};

    /**
     * 生成n个星号的字符串
     * @param length
     * @return
     */
    private static String getStarChar(int length) {
        if (length <= 0) {
            return "";
        }
        //大部分敏感词汇在10个以内，直接返回缓存的字符串
        if (length <= 10) {
            return starArr[length - 1];
        }

        //生成n个星号的字符串
        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            arr[i] = '*';
        }
        return new String(arr);
    }

    private static String desensitizedIdNumber(String idNumber){
        if (!Strings.isNullOrEmpty(idNumber)) {
            if (idNumber.length() == 15){//123456789876543
                idNumber = idNumber.replaceAll("(\\w{3})\\w*(\\w{2})", "$1**********$2");
            }
            if (idNumber.length() == 18){ //123456789786754632
                idNumber = idNumber.replaceAll("(\\w{3})\\w*(\\w{2})", "$1*************$2");
            }
        }
        return idNumber;
    }


    /**
     * @Description: 对名称进行脱敏
     * @param fullName 名字
     * @return: java.lang.String
     * @auther: yyg
     * @date: 2019/12/9 15:45
     */
    private static String desensitizedName(String fullName){
        String name = "";
        if (StringUtils.isNotBlank(fullName)) {
            name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
        return name;
    }

    public  static String getNums(String num){
        String nums="";
        if(StringUtils.isNotBlank(num)){
            //得到左边的3个字段
            String left = StringUtils.left(num, 3);
            String right = StringUtils.right(num,2);
            //剩余的字符串长度
           int index = num.length()-left.length()-right.length();
            String s = num.substring(3,3+index);
            String replace = s.replace(s, getStarChar(index));
            nums  = left+replace+right;
            System.out.println("有多少长度的字符串："+nums);
            return nums;
        }
        return nums ;
    }

    public  static String getNames(String name){
        String nums="";
        if(StringUtils.isNotBlank(name)){
            //得到左边的3个字段
            String left = StringUtils.left(name, 2);
            String right = StringUtils.right(name,4);
            //剩余的字符串长度
            int index = name.length()-left.length()-right.length();
            String s = name.substring(2,2+index);
            String replace = s.replace(s, getStarChar(index));
            nums  = left+replace+right;
            System.out.println("有多少长度的字符串："+nums);
            return nums;
        }
        return nums ;
    }

    public static void main(String[] args) {
        String num ="北京yirenkefhfhdk有限公司";
        getNames(num);
    }

  /*  public static void main(String[] args) {
       *//* String id = "123456789876543";
        String ida = "412724199504292923";
        String card = "23270019961016682X";
        String name = "";
        String num = "23270019961016682X00";
        System.out.println(desensitizedIdNumber(id));
        System.out.println(desensitizedIdNumber(ida));
        System.out.println(desensitizedName(name));
        System.out.println(desensitizedIdNumber(card));
        System.out.println(desensitizedIdNumber(num));
        String s ="12345678910";
        getStarChar(s.length());
        System.out.println("几个长度的*:"+ getStarChar(s.length()));
        System.out.println(s.replace(s,getStarChar(s.length())));*//*
    }*/
}
