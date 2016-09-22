package com.ted.uncaughtexceptionhandler;

import android.app.Application;

import com.ted.myuncaughtexceptionhandler.CrashHandler;
import com.tencent.bugly.crashreport.CrashReport;

//import com.ted.myuncaughtexceptionhandler.CrashHandler;

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
public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        //CrashHandler crashHandler = CrashHandler.getInstance();
        //crashHandler.init(this);

        CrashReport.initCrashReport(getApplicationContext(),"900054674", true);
    }

    public static MyApplication getInstance(){
        return sInstance;
    }
}
