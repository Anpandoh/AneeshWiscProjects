cmake_minimum_required(VERSION 3.5)
#make sure you set the source env 
set(CMAKE_C_COMPILER "icc")
set(CMAKE_CXX_COMPILER "icpc")
project(Homework3 VERSION 0.1.0 LANGUAGES C CXX)
include(CTest)
enable_testing()


set(SOURCES                                 
       ${CMAKE_SOURCE_DIR}/ConjugateGradients.cpp
       ${CMAKE_SOURCE_DIR}/ConjugateGradients.h
       ${CMAKE_SOURCE_DIR}/CSRMatrix.h
       ${CMAKE_SOURCE_DIR}/CSRMatrixHelper.h
       ${CMAKE_SOURCE_DIR}/Laplacian.cpp
       ${CMAKE_SOURCE_DIR}/Laplacian.h
       ${CMAKE_SOURCE_DIR}/main.cpp    
       ${CMAKE_SOURCE_DIR}/MatVecMultiply.cpp
       ${CMAKE_SOURCE_DIR}/Parameters.h
       ${CMAKE_SOURCE_DIR}/PointwiseOps.cpp
       ${CMAKE_SOURCE_DIR}/PointwiseOps.h
       ${CMAKE_SOURCE_DIR}/Reductions.cpp
       ${CMAKE_SOURCE_DIR}/Reductions.h
       ${CMAKE_SOURCE_DIR}/Timer.h
       ${CMAKE_SOURCE_DIR}/Utilities.cpp
       ${CMAKE_SOURCE_DIR}/Utilities.h
)


add_executable(Homework3 ${SOURCES})
set(CMAKE_CXX_FLAGS "-O3 -mavx512f -mkl")

find_package(OpenMP)

if(OpenMP_CXX_FOUND)
    target_link_libraries(Homework3 PUBLIC OpenMP::OpenMP_CXX
    )
endif()


set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})

include(CPack)
