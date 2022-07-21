cmake_minimum_required(VERSION 3.20)
project(11_String)

set(CMAKE_CXX_STANDARD 14)

add_executable(11_String main.cpp src/str_match.cpp src/str_match.h src/str_KMPmatch.cpp src/str_KMPmatch.h)
