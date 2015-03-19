package com.tridentqa.webdriver.appium;

import java.io.File;
import java.io.IOException;

// TODO Since launching appium server is very slow,
// you should launch appium server only once,
// and try to restart only when the server is hung up.
public class AppiumLauncher {
    private Process process;
    
    public void launch() {
        File classpathRoot = new File(System.getProperty("user.dir"));
        Runtime runtime = Runtime.getRuntime();
        // TODO fixed log path
        File appiumLog = new File(classpathRoot, "appium.log");
        // TODO fixed appium path
        try {
            process = runtime.exec(new String[]{
                    "/usr/local/bin/node",
                    "/usr/local/lib/node_modules/appium/lib/server/main.js", 
                    "--log", 
                    appiumLog.getAbsolutePath()});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (process == null) {
            throw new RuntimeException("fail to launch appium server");
        }

        // wait for appium server start
        // TODO should not use fixed value
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void stop() {
        if (process != null) {
            process.destroy();
        }
    }
}
