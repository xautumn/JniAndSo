LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := libtvad
LOCAL_SRC_FILES := libtvad.so
LOCAL_EXPORT_C_INCLUDES := src/main/jni
include $(PREBUILT_SHARED_LIBRARY)

# 多个module需要以include$(CLEAR_VARS)开始，include结束
include $(CLEAR_VARS)
LOCAL_SHARED_LIBRARIES := tvad
LOCAL_C_INCLUDES += $(LOCAL_PATH)

#加入这句可以使得so文件打印日志
LOCAL_LDLIBS:=-L$(SYSROOT)/usr/lib -llog
# 要生成的so库的名称，但实际为libdemo.so
LOCAL_MODULE        := test
LOCAL_SRC_FILES     := test.cpp
include $(BUILD_SHARED_LIBRARY)