# cmake_minimum_required(VERSION <specify CMake version here>)
cmake_minimum_required(VERSION 3.30.5)

project(4_trees_graphs)

set(CMAKE_CXX_STANDARD 26)

add_executable(4_trees_graphs src/main.cpp src/runner.h src/graph.h)