cmake_minimum_required(VERSION 3.6)
project(stats)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES stats.c stats.h)

add_executable(stats ${SOURCE_FILES})