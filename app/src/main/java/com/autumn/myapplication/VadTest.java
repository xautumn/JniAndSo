package com.autumn.myapplication;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wuqi on 2018/10/29.
 */
public class VadTest {

    static {
        System.loadLibrary("test");//导入生成的链接库文件
        System.loadLibrary("tvad");//导入生成的链接库文件
    }

    private native String jniGetName();

    private native int jniGetAge();

    private native boolean jniIsMale();

    private native int jniGetAdditionResult(int x, int y);

    private native void jniShowSomething();

    private native long jniTestLong(String path);

    private native String jniGetVersion(long address);

    private native int jniInputVoiceData(long address, String inputData, int len);

    public void getName() {
        String s = jniGetName();
        Log.i("wq", "getName = " + s);
    }

    public void showSomething() {
        jniShowSomething();
    }

    public long testLong() {
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.i("wq", "sdcardPath = " + sdcardPath);
        String s = sdcardPath + "/vad";
        File file = new File(s);
        Log.i("wq", "testLong = " + file.exists());
        long l = jniTestLong(s);
        Log.i("wq", "testLong = " + l);
        if (l != 0) {
            getVersion(l);
        }
        return l;
    }

    public void getVersion(long address) {
        String l = jniGetVersion(address);
        Log.i("wq", "getVersion = " + l);
    }


    public void testVoiceVad(long address) {
        Log.i("wq", "testVoiceVad= " + address);
        FileInputStream in = null;
        FileOutputStream out = null;
        String sdPath1 = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = sdPath1 + "/FinalAudio.wav";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);

            byte[] b = new byte[1000];//把所有的数据读取到这个字节当中

            int i = 0;
            //定义一个记录索引的变量
            int index = 0;
            //循环读取每个数据
            while ((i = fileInputStream.read(b)) != -1) {//把读取的数据放到i中
                int result = jniInputVoiceData(address, b.toString(), i);
                index++;
                Log.i("wq", "vad result = " + result + "--length = " + i+ "--index = " + index);
            }
            //把字节数组转成字符串

            System.out.println(new String(b));
            //关闭流
            fileInputStream.close();
        } catch (IOException e) {
            Log.i("wq", "error= " + e);
            e.printStackTrace();
        }


    }
}
