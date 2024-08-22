cmake_minimum_required(VERSION 3.5)
#make sure you set the source env 
set(CMAKE_C_COMPILER "icc")
set(CMAKE_CXX_COMPILER "icpc")
project(hw5 VERSION 0.1.0 LANGUAGES C CXX)
include(CTest)
enable_testing()


set(SOURCES                                 
       ${CMAKE_SOURCE_DIR}/CSRMatrix.h
       ${CMAKE_SOURCE_DIR}/CSRMatrixHelper.h
       ${CMAKE_SOURCE_DIR}/Laplacian.cpp
       ${CMAKE_SOURCE_DIR}/Laplacian.h
       ${CMAKE_SOURCE_DIR}/main.cpp    
       ${CMAKE_SOURCE_DIR}/DirectSolver.cpp
       ${CMAKE_SOURCE_DIR}/DirectSolver.h
       ${CMAKE_SOURCE_DIR}/Parameters.h
       ${CMAKE_SOURCE_DIR}/Timer.h
       ${CMAKE_SOURCE_DIR}/Utilities.cpp
       ${CMAKE_SOURCE_DIR}/Utilities.h
)


add_executable(hw5 ${SOURCES})
set(CMAKE_CXX_FLAGS "-O3 -mavx512f -mkl -g")

find_package(OpenMP)

if(OpenMP_CXX_FOUND)
    target_link_libraries(hw5 PUBLIC OpenMP::OpenMP_CXX
    )
endif()


set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})

include(CPack)