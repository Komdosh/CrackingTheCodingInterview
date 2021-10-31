//
// Created by Andrey Tabakov on 31.10.2021.
//

#ifndef INC_4_TREES_GRAPHS_RUNNER_H
#define INC_4_TREES_GRAPHS_RUNNER_H

#include "RouteBetweenNode/naive/RouteBetweenNodeNaive.h"

template <class T>
concept GraphTaskRunner = requires (T t) {
    { t.run() };
};
static_assert(GraphTaskRunner<RouteBetweenNodeNaive>);

#endif //INC_4_TREES_GRAPHS_RUNNER_H
