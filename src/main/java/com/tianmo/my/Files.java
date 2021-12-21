package com.tianmo.my;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;

public class Files {
    FileImageInputStream input = null;
    FileImageOutputStream imageOutput=null;
//Java中将图片转为二进制流只需要使用FileImageInputStream取得图片文件，
//然后使用ByteArrayOutputStream 写入到二进制流中即可，下面是详细代码：
    //图片到byte数组
    public byte[] image2byte(String author){
        String path="D:/敏/化工/IDEA/WorkSpace/TianMo2/src/main/webapp/avatar/"+author+".jpg";
        byte[] data = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
    //如果需要将byte[]存回图片或转为String，则：
    //byte数组到图片
    public void byte2image(byte[] data,String author){
        String path="D:/敏/化工/IDEA/WorkSpace/TianMo2/src/main/webapp/avatar/"+author+".jpg";
        if(data.length<3||path.equals("")) return;
        try{
            imageOutput= new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
    //byte数组到16进制字符串
    public String byte2string(byte[] data){
        if(data==null||data.length<=1) return "0x";
        if(data.length>200000) return "0x";
        StringBuffer sb = new StringBuffer();
        int buf[] = new int[data.length];
        //byte数组转化成十进制
        for(int k=0;k<data.length;k++){
            buf[k] = data[k]<0?(data[k]+256):(data[k]);
        }
        //十进制转化成十六进制
        for(int k=0;k<buf.length;k++){
            if(buf[k]<16) sb.append("0"+Integer.toHexString(buf[k]));
            else sb.append(Integer.toHexString(buf[k]));
        }
        return "0x"+sb.toString().toUpperCase();
    }

}
