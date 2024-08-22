cmake_minimum_required(VERSION 3.9.0)
project(a1 VERSION 0.1.0 LANGUAGES C CXX)

include(CTest)
enable_testing()


set(SOURCES                                  
        ${CMAKE_SOURCE_DIR}/main.cpp     
        ${CMAKE_SOURCE_DIR}/Laplacian.cpp 
        ${CMAKE_SOURCE_DIR}/Timer.h
        ${CMAKE_SOURCE_DIR}/Laplacian.h)  

add_executable(a1 ${SOURCES})

find_package(OpenMP)
if(OpenMP_CXX_FOUND)
    target_link_libraries(a1 PUBLIC OpenMP::OpenMP_CXX)
endif()

set(CMAKE_CXX_FLAGS "-O2  -mavx512f")
set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)

