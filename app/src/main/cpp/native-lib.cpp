
#include "native-lib.h"

extern "C" JNIEXPORT jstring JNICALL


Java_com_videoplayer_fastplayer_gdvideoplayer_Retrofit_APIClient_stringFromJNI(
        JNIEnv *env, //default env parameter.
        jobject /* this, activity context. */) {
    std::string hello = "http://139.59.80.183:1225/";
    return env->NewStringUTF(hello.c_str());
}



