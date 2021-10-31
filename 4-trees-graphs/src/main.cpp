#include "graph.h"
#include "runner.h"

void runTest(GraphTaskRunner auto &runner) {
    runner.run();
}

int main() {
    runTest(*new RouteBetweenNodeNaive());
    return 0;
}


