#include "native-lib.h"
#include "libadd.h"
#include "rtccallback.h"

void OnLog(const char* msg) {
    LOGE("OnLog : %s", msg);
}

JNIEXPORT void JNICALL
Java_com_pc_cgodemo_JniLibrary_TestCustomLogger(JNIEnv *env, jclass clazz, jstring logPath) {
    LOGE("Java_com_pc_cgodemo_JniLibrary_GoSendText");
    const char *log_char = (*env)->GetStringUTFChars(env, logPath, 0);

    TestCustomLogger(log_char);

    LOGE("Java_com_pc_cgodemo_JniLibrary_GoSendText Finished.");
}