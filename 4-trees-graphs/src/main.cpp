#include "graph.h"
#include "runner.h"
#include <typeinfo>

void runTest(GraphTaskRunner auto &runner) {
    std::cout << "Run: " << (std::string) typeid(runner).name() << std::endl;
    std::cout << "========" << std::endl;
    runner.run();

    std::cout << "========" << std::endl << std::endl;
}

int main() {
//    runTest(*new RouteBetweenNodesNaive());
//    runTest(*new RouteBetweenNodesOptimized());

    runTest(*new MinimalTree());
    return 0;
}


