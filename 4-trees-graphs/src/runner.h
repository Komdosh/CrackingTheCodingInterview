//
// Created by Andrey Tabakov on 31.10.2021.
//

#ifndef INC_4_TREES_GRAPHS_RUNNER_H
#define INC_4_TREES_GRAPHS_RUNNER_H

#include "RouteBetweenNode/RouteBetweenNodesNaive.h"
#include "RouteBetweenNode/RouteBetweenNodesOptimized.h"
#include "MinimalTree/MinimalTree.h"

template<class T>
concept GraphTaskRunner = requires(T runner) {
    runner.run();
};

static_assert(GraphTaskRunner<RouteBetweenNodesNaive>);
static_assert(GraphTaskRunner<RouteBetweenNodesOptimized>);
static_assert(GraphTaskRunner<MinimalTree>);

#endif //INC_4_TREES_GRAPHS_RUNNER_H
