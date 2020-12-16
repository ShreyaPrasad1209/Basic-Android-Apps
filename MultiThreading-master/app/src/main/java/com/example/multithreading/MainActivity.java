package com.example.multithreading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Activity mActivity;
    TextView t1,t2,t3;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView)findViewById(R.id.textView1);
        t2 = (TextView)findViewById(R.id.textView2);
        t3 = (TextView)findViewById(R.id.textView3);
        mActivity=this;
    }
    public void startThread(final TextView t){
        new Thread(new Runnable() {
            @Override
            public void run () {
                try {
                    Thread.sleep(10000);
                    for(int i = 5; i > 0; i--) {
                        changeView(t,i+"");
                        Thread.sleep(10000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeView(t,"exiting");
            }
            }).start();
    }

    public void changeView(final TextView t, final String text) {
        handler.post(new Runnable() {
            @Override
            public void run () {
                t.setText(text);
            }
        });
    }

    public void MultiThreading(View view) throws InterruptedException{
        t1.setText("Thread Started");
        t2.setText("Thread Started");
        t3.setText("Thread Started");
        startThread(t1);
        startThread(t2);
        startThread(t3);
    }
}