# cmake_minimum_required(VERSION <specify CMake version here>)
project(4_trees_graphs)

set(CMAKE_CXX_STANDARD 20)

add_executable(4_trees_graphs src/main.cpp src/runner.h)
add_executable(4_trees_graphs src/RouteBetweenNode/naive/RouteBetweenNodesNaive.h)