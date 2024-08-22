cmake_minimum_required(VERSION 3.9.0)
project(ConjGrad VERSION 0.1.0 LANGUAGES C CXX)
include(CTest)
enable_testing()


set(SOURCES                                 
       ${CMAKE_SOURCE_DIR}/main.cpp    
       ${CMAKE_SOURCE_DIR}/ConjugateGradients.cpp
       ${CMAKE_SOURCE_DIR}/Timer.h
       ${CMAKE_SOURCE_DIR}/ConjugateGradients.h
       ${CMAKE_SOURCE_DIR}/Laplacian.cpp
       ${CMAKE_SOURCE_DIR}/Laplacian.h
       ${CMAKE_SOURCE_DIR}/Parameters.h
       ${CMAKE_SOURCE_DIR}/PointwiseOps.cpp
       ${CMAKE_SOURCE_DIR}/PointwiseOps.h
       ${CMAKE_SOURCE_DIR}/Reductions.cpp
       ${CMAKE_SOURCE_DIR}/Reductions.h
       ${CMAKE_SOURCE_DIR}/Utilities.cpp
       ${CMAKE_SOURCE_DIR}/Utilities.h
       ) 
add_executable(ConjGrad ${SOURCES})


find_package(OpenMP)
if(OpenMP_CXX_FOUND)
   target_link_libraries(ConjGrad PUBLIC OpenMP::OpenMP_CXX)
endif()
set(CMAKE_CXX_FLAGS "-O3 -mavx512f")
set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)