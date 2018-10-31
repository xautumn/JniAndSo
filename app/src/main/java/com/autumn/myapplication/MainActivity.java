package com.autumn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


   /* static {
        System.loadLibrary("test");//导入生成的链接库文件
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VadTest vadTest = new VadTest();
        vadTest.getName();
        vadTest.showSomething();
        long address = vadTest.testLong();
        vadTest.testVoiceVad(address);
    }
}
