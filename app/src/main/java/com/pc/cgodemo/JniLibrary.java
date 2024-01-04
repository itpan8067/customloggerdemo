package com.pc.cgodemo;

public class JniLibrary {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("add");
        System.loadLibrary("native-lib");
    }

    public static native void TestCustomLogger(String logPath);
}
