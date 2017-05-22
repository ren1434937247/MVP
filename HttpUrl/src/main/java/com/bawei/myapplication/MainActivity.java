package com.bawei.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private StringBuffer strBuf;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadFile("renxintong","renxintong.png");
            }
        });

    }
   public void upLoadFile(String filename,String filepath){
       HttpURLConnection connection=null;
       /// boundary就是request头和上传文件内容的分隔符(可自定义任意一组字符串)
       String BOUNDARY = "******";
       // 用来标识payLoad+文件流的起始位置和终止位置(相当于一个协议,告诉你从哪开始,从哪结束)
       String  preFix = ("\r\n--" + BOUNDARY + "--\r\n");

       try {
           URL url=new URL("http://169.254.159.111:8080/ssm/user/photoUpload");
           connection= (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("POST");
           connection.setReadTimeout(5000);
           connection.setConnectTimeout(5000);
           connection.setDoInput(true);
           connection.setDoOutput(true);
           connection.setUseCaches(false);


           // 设置header
           connection.setRequestProperty("Accept","*/*");
           connection.setRequestProperty("Connection", "keep-alive");
           connection.setRequestProperty("Content-Type",
                   "multipart/form-data; boundary=" + BOUNDARY);
           // 获取写输入流
           OutputStream out = new DataOutputStream(connection.getOutputStream());
           // 获取上传文件
           File file = new File("/renxintong.png");

           // 要上传的数据
           StringBuffer strBuf = new StringBuffer();

           // 标识payLoad + 文件流的起始位置
           strBuf.append(preFix);
           strBuf.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + "/renxintong.png"+  "\"\r\n");
           strBuf.append("Content-Type: image/jpeg"  + "\r\n\r\n");
           out.write(strBuf.toString().getBytes());

           FileInputStream inputStream = new FileInputStream(file);
           DataInputStream dinputStream = new DataInputStream(inputStream);

           int bytes = 0;
           // 计算上传进度
           float count = 0;
           // 获取文件总大小
           int fileSize = inputStream.available();
           // 每次上传的大小
           byte[] bufferOut = new byte[1024];
           // 上传文件
           while ((bytes = dinputStream.read(bufferOut)) != -1) {
               // 上传文件(一份)
               out.write(bufferOut, 0, bytes);
               // 计算当前已上传的大小
//               count += bytes;
//               // 打印上传文件进度(已上传除以总大*100就是进度)
//               utils.logD("progress:" +(count / fileSize * 100) +"%");
           }
           dinputStream.close();
           // 标识payLoad + 文件流的结尾位置
           out.write(preFix.getBytes());
           out.flush();
           out.close();

           strBuf = new StringBuffer();
           // 打开输入流 , 读取服务器返回的数据
           BufferedReader buffer = new BufferedReader
                   (new InputStreamReader(connection.getInputStream()));


           String line;
           while ((line=buffer.readLine())!=null){
               strBuf.append(line).append("\n");
           }
           buffer.close();
         Log.e("上传成功=============>" , strBuf.toString());

       } catch (IOException e) {
           Log.e("上传图片出错-------------->",e.toString());
       }finally {
           if (connection!=null){
               connection.disconnect();
           }
       }


   }
}
