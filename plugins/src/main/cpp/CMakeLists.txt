cmake_minimum_required(VERSION 3.6)

add_library(lib_aruco STATIC IMPORTED)
set_target_properties(lib_aruco PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libaruco.a)

add_library(lib_iconv STATIC IMPORTED)
set_target_properties(lib_iconv PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libiconv.a)

add_library(lib_zbar STATIC IMPORTED)
set_target_properties(lib_zbar PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libzbar.a)

add_library(lib_opencv_calib3d SHARED IMPORTED)
set_target_properties(lib_opencv_calib3d PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libopencv_calib3d.so)

add_library(lib_opencv_core SHARED IMPORTED)
set_target_properties(lib_opencv_core PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libopencv_core.so)

add_library(lib_opencv_features2d SHARED IMPORTED)
set_target_properties(lib_opencv_features2d PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libopencv_features2d.so)

add_library(lib_opencv_imgproc SHARED IMPORTED)
set_target_properties(lib_opencv_imgproc PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libopencv_imgproc.so)

add_library(lib_opencv_objdetect SHARED IMPORTED)
set_target_properties(lib_opencv_objdetect PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libopencv_objdetect.so)

add_library(lib_tbb SHARED IMPORTED)
set_target_properties(lib_tbb PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/lib/${ANDROID_ABI}/libtbb.so)

add_library(lib_architectSDK SHARED IMPORTED)
set_target_properties(lib_architectSDK PROPERTIES IMPORTED_LOCATION ${WIKITUDE_NATIVE_PATH}/${ANDROID_ABI}/libarchitect.so)

add_definitions(-DINCLUDE_WIKITUDE_AS_USER_HEADER)

add_library(wikitudePlugins SHARED
    ${CMAKE_CURRENT_LIST_DIR}/src/JniHelper.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/JniRegistration.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/barcode/BarcodePlugin.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/barcode/BarcodeScanner.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/barcode/BarcodeScannerJavaScriptPluginModule.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection/FaceTracker.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection/FaceTrackerJavaScriptPluginModule.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection/FaceTrackerOpenGLESRenderPluginModule.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection/FaceTrackerPlugin.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection/Renderables/StrokedRectangle.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/marker_tracking/Marker.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/marker_tracking/MarkerTrackable.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/marker_tracking/MarkerTracker.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/marker_tracking/MarkerTrackerJavaScriptPluginModule.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/marker_tracking/MarkerTrackerPlugin.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/simple_input_plugin/SimpleInputPlugin.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/custom_camera/OpenGLESScanningEffectRenderingPluginModule.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/custom_camera/YUVFrameInputPlugin.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/custom_camera/YUVFrameShaderSourceObject.cpp
    ${CMAKE_CURRENT_LIST_DIR}/src/FrameInputPluginModule.cpp
)

set_target_properties(wikitudePlugins PROPERTIES LIBRARY_OUTPUT_DIRECTORY "${PLUGIN_OUTPUT_PATH}/${ANDROID_ABI}")

target_include_directories(wikitudePlugins
    PRIVATE
    ${CMAKE_CURRENT_LIST_DIR}/include
    ${CMAKE_CURRENT_LIST_DIR}/include/aruco
    ${CMAKE_CURRENT_LIST_DIR}/include/nlohmann
    ${CMAKE_CURRENT_LIST_DIR}/include/opencv2
    ${CMAKE_CURRENT_LIST_DIR}/include/opencv2/core
    ${CMAKE_CURRENT_LIST_DIR}/include/opencv2/imgproc
    ${CMAKE_CURRENT_LIST_DIR}/include/opencv2/objdetect
    ${CMAKE_CURRENT_LIST_DIR}/include/wikitude
    ${CMAKE_CURRENT_LIST_DIR}/include/zbar

    ${CMAKE_CURRENT_LIST_DIR}/src
    ${CMAKE_CURRENT_LIST_DIR}/src/barcode
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection
    ${CMAKE_CURRENT_LIST_DIR}/src/face_detection/renderables
    ${CMAKE_CURRENT_LIST_DIR}/src/marker_tracking
    ${CMAKE_CURRENT_LIST_DIR}/src/simple_input_plugin
    ${CMAKE_CURRENT_LIST_DIR}/src/custom_camera
)

target_link_libraries(wikitudePlugins
    lib_architectSDK
    lib_aruco
    lib_zbar
    lib_iconv
    lib_opencv_objdetect
    lib_opencv_imgproc
    lib_opencv_features2d
    lib_opencv_calib3d
    lib_opencv_core
    lib_tbb
    log
    GLESv2
    z
    android
)

add_custom_command(TARGET wikitudePlugins PRE_BUILD
    COMMAND "${CMAKE_COMMAND}" -E
    copy
    "${ANDROID_NDK}/sources/cxx-stl/llvm-libc++/libs/${ANDROID_ABI}/libc++_shared.so"
    "${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libc++_shared.so"
)
