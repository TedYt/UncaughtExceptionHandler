package com.ted.myuncaughtexceptionhandler;

import android.content.Context;
import android.nfc.Tag;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C) 2008 The Android Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created by Ted.Yt on 9/21/16.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() +
                    "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";


    private static CrashHandler sInstance = new CrashHandler();

    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private Context mContext;

    private CrashHandler(){

    }

    public static CrashHandler getInstance(){
        return sInstance;
    }

    public void init(Context context){
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    /**
     * 最关键的一个函数
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try{
            dumpExceptionToSDCard(ex);
        }catch (IOException e){
            e.printStackTrace();
        }

        ex.printStackTrace();
        //如果系统提供默认的一场处理器，则交给系统去结束进程，否则就由自己结束自己
        if (mDefaultCrashHandler != null){
            mDefaultCrashHandler.uncaughtException(thread,ex);
        }else{
            try{
                Thread.sleep(2000);
                Toast.makeText(mContext, "Fatal Error !!!!", Toast.LENGTH_SHORT).show();
            }catch (InterruptedException e){
                Log.d(TAG, e.getMessage());
            }
            android.os.Process.killProcess(Process.myPid());
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException{

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.e(TAG, "sdcard unmounted, skip dump exception");
            return;
        }

        File dir = new File(PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd-HH:MM:SS").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);
        if (!file.exists()){
            Log.e(TAG, file.getName() + " does not exist ! !");
        }

        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            pw.print("\n");
            ex.printStackTrace(pw);//写入异常信息
            pw.close();
            Log.e(TAG, "dump crash info success!");
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            Log.e(TAG, "dump crash info failed");
        }
    }
}
