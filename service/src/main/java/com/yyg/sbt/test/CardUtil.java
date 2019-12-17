package com.yyg.sbt.test;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: yyg
 * @Date: 2019/12/9 14:58
 * @Description: 身份证信息计算
 */
public class CardUtil {

    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证
     *
     * @return Map<String, Object>  年龄和性别
     * @throws Exception
     */
    public static Map<String, Object> getCarInfo(String CardCode)
            throws Exception {
        Map<String, Object> map = new HashMap<>();
        String year = CardCode.substring(6).substring(0, 4);// 得到年份
        String month = CardCode.substring(10).substring(0, 2);// 得到月份
        // String day=CardCode.substring(12).substring(0,2);//得到日
        String sex;
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女";
        } else {
            sex = "男";
        }
        map.put("sex", sex);
        map.put("age",  getAge(year,month));
        return map;
    }
    /**
     * 15位身份证的验证
     *
     * @param card 身份证信息
     * @throws Exception
     */
    public static Map<String, Object> getCarInfo15W(String card)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String uyear = "19" + card.substring(6, 8);// 年份
        String umonth = card.substring(8, 10);// 月份
        // String uday=card.substring(10, 12);//日
        String usex = card.substring(14, 15);// 用户的性别
        String sex;
        if (Integer.parseInt(usex) % 2 == 0) {
            sex = "女";
        } else {
            sex = "男";
        }
        map.put("sex", sex);
        map.put("age", getAge(uyear,umonth));
        return map;
    }

    /**
     * @Description: 根据身份证号获取年龄
     * @param year 年份
     * @param month 月份
     * @return: int
     * @auther: yyg
     * @date: 2019/12/9 15:01
     */
    public static int getAge(String year,String month){
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fmonth = format.format(date).substring(5, 7);// 月份
        // String fday=format.format(date).substring(8,10);
        int age = 0;
        if (Integer.parseInt(month) <= Integer.parseInt(fmonth)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
        } else {// 当前用户还没过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year);
        }
        return age;
    }

    /**
     * @Description:  身份证号脱敏处理 显示前6后3位数字 其他使用*号代替
     * @param idNumber  身份证号
     * @return: java.lang.String
     * @auther: yyg
     * @date: 2019/12/9 15:12
     */
    private static String desensitizedIdNumber(String idNumber){
        if (!Strings.isNullOrEmpty(idNumber)) {
            if (idNumber.length() == 15){
                idNumber = idNumber.replaceAll("(\\w{6})\\w*(\\w{3})", "$1******$2");
            }
            if (idNumber.length() == 18){
                idNumber = idNumber.replaceAll("(\\w{6})\\w*(\\w{3})", "$1*********$2");
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
        if (!Strings.isNullOrEmpty(fullName)) {
            name = StringUtils.left(fullName, 1);
            return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
        }
        return name;
    }

    /**
     * @Description:  手机号脱敏
     * @param phoneNumber 手机号
     * @return:  String
     * @auther: yyg
     * @date: 2019/12/9 15:44
     */
    private static String desensitizedPhoneNumber(String phoneNumber){
        if(StringUtils.isNotEmpty(phoneNumber)){
            phoneNumber = phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
        }
        return phoneNumber;
    }

    public static void main(String[] args) {
        String idNumber = "412724199504292923";
        String name = "姚伊歌";
        String name1 = "熊超";
        String phoneNumber = "17610468295";
        System.out.println("身份证信息:"+desensitizedIdNumber(idNumber));
        System.out.println("名字:"+desensitizedName(name1));
        System.out.println("手机号:"+desensitizedPhoneNumber(phoneNumber));
    }
}
