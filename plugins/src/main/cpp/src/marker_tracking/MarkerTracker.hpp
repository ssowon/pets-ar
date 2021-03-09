//
//  MarkerTracker.hpp
//  WikitudeSDKTestsHost
//
//  Created by Andreas Schacherbauer on 30.11.18.
//  Copyright © 2018 Wikitude. All rights reserved.
//

#ifndef MarkerTracker_hpp
#define MarkerTracker_hpp

#include <string>
#include <vector>
#include <unordered_map>

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wsign-conversion"
#pragma clang diagnostic ignored "-Wdocumentation"
#pragma clang diagnostic ignored "-Wnon-virtual-dtor"
#pragma clang diagnostic ignored "-Wextra-semi"
#pragma clang diagnostic ignored "-Wunused-parameter"
#pragma clang diagnostic ignored "-Wconversion"
#pragma clang diagnostic ignored "-Wdynamic-exception-spec"
#include <aruco.h>
#pragma clang diagnostic pop

#ifdef INCLUDE_WIKITUDE_AS_USER_HEADER
    #include "Matrix4.hpp"
#else
    #import <WikitudeSDK/Matrix4.hpp>
#endif

#include "Marker.hpp"


namespace wikitude::sdk {
    class ManagedCameraFrame;
    class RuntimeParameters;
}

class MarkerTracker {
public:
    MarkerTracker(wikitude::sdk::RuntimeParameters* runtimeParameters_);

    void processCameraFrame(wikitude::sdk::ManagedCameraFrame& managedCameraFrame_, std::function<void(int markerId_, wikitude::sdk::Matrix4& matrix_)> markerConversionHandler_);

protected:
    aruco::MarkerDetector _detector;
    
    wikitude::sdk::RuntimeParameters*    _runtimeParameters;
};

#endif /* MarkerTracker_hpp */
