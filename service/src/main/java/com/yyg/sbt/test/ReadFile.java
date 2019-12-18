package com.yyg.sbt.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: yyg
 * @Date: 2019/12/17 17:08
 * @Description:
 */
public class ReadFile {

public ReadFile() {

}

        public static ArrayList<String> getFiles(String filepath){
                ArrayList<String> files = new ArrayList<String>();
                File file = new File(filepath);
                File[] tempLists = file.listFiles();
            for (int i = 0; i < tempLists.length; i ++) {
                if (tempLists[i].isFile()) {
                    files.add(tempLists[i].toString());
            }
        }
            System.out.println("所有的txt文件和压缩文件：");
            for (int i = 0; i < files.size(); i++) {
                String[] split = files.get(i).split("\\.");
                if(split[split.length-1].equals("txt")||split[split.length-1].equals("rar")||split[split.length-1].equals("zip")) {
                    System.out.println(files.get(i));
                }

                }

            System.out.println("------------------------------------");
            System.out.println("txt文件和内容：");
            for (int i = 0; i < files.size(); i++) {
                String[] split = files.get(i).split("\\.");
                if(split[split.length-1].equals("txt")) {
                    System.out.println(files.get(i));
                    readTxtFile(files.get(i));
                }

            }

           Map<String,Object> map = new HashMap<>();

            //所有文件名和重复次数
            System.out.println("------------------------------");
            System.out.println("所有文件名和重复次数：");
            for (int i = 0; i < files.size(); i++) {
                    if(map.containsKey(files.get(i))){
                        int o = (int)map.get(files.get(i));
                        o++;
                        map.putIfAbsent(files.get(i),o);
                    }else{
                        map.put(files.get(i),1);
                    }
            }

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }

                return files;
            }

            //读取txt的文件内容
    public static void readTxtFile(String filePath){
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

            //添加文件路径
         getFiles("C:\\yingyong\\安装软件包\\");
        }
}
