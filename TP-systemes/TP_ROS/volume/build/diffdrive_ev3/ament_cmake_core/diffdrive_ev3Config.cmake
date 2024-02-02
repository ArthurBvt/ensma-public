# generated from ament/cmake/core/templates/nameConfig.cmake.in

# prevent multiple inclusion
if(_diffdrive_ev3_CONFIG_INCLUDED)
  # ensure to keep the found flag the same
  if(NOT DEFINED diffdrive_ev3_FOUND)
    # explicitly set it to FALSE, otherwise CMake will set it to TRUE
    set(diffdrive_ev3_FOUND FALSE)
  elseif(NOT diffdrive_ev3_FOUND)
    # use separate condition to avoid uninitialized variable warning
    set(diffdrive_ev3_FOUND FALSE)
  endif()
  return()
endif()
set(_diffdrive_ev3_CONFIG_INCLUDED TRUE)

# output package information
if(NOT diffdrive_ev3_FIND_QUIETLY)
  message(STATUS "Found diffdrive_ev3: 0.0.1 (${diffdrive_ev3_DIR})")
endif()

# warn when using a deprecated package
if(NOT "" STREQUAL "")
  set(_msg "Package 'diffdrive_ev3' is deprecated")
  # append custom deprecation text if available
  if(NOT "" STREQUAL "TRUE")
    set(_msg "${_msg} ()")
  endif()
  # optionally quiet the deprecation message
  if(NOT ${diffdrive_ev3_DEPRECATED_QUIET})
    message(DEPRECATION "${_msg}")
  endif()
endif()

# flag package as ament-based to distinguish it after being find_package()-ed
set(diffdrive_ev3_FOUND_AMENT_PACKAGE TRUE)

# include all config extra files
set(_extras "ament_cmake_export_libraries-extras.cmake;ament_cmake_export_dependencies-extras.cmake")
foreach(_extra ${_extras})
  include("${diffdrive_ev3_DIR}/${_extra}")
endforeach()
