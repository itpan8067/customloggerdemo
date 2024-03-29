# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("cgodemo")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/)

file(GLOB JNI_SRC ${CMAKE_CURRENT_SOURCE_DIR}/*.c ${CMAKE_CURRENT_SOURCE_DIR}/*.h) # 扫描jni目录下的头文件和库文件, 并设置给JNI_SRC

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
        ${JNI_SRC})

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
            std-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              android )
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

message("@@@@@@@@@@@@ PROJECT_SOURCE_DIR is: ${CMAKE_CURRENT_SOURCE_DIR}")

file(GLOB OUTPUT_LIBRARY ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/${CMAKE_ANDROID_ARCH_ABI}/*.so) # 找到对应cpu的so库文件
message("OUTPUT_LIBRARY: ${OUTPUT_LIBRARY}")

#add_library(add SHARED IMPORTED)
#set_target_properties(add PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/libs/${CMAKE_ANDROID_ARCH_ABI}/libadd.so)


target_link_libraries( # Specifies the target library.
                       native-lib

                    ${log-lib}
                    ${std-lib}

                       # Links the target library to the log library
                       # included in the NDK.
        #add # 连接给jni库
        ${OUTPUT_LIBRARY}
        )