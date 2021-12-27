#ifndef INC_4_TREES_GRAPHS_RUNNER_H
#define INC_4_TREES_GRAPHS_RUNNER_H

#include "RouteBetweenNode/RouteBetweenNodesNaive.h"
#include "RouteBetweenNode/RouteBetweenNodesOptimized.h"
#include "MinimalTree/MinimalTree.h"
#include "BSTSequences/BSTSequences.h"
#include "BuildOrder/BuildOrder.h"
#include "CheckBalanced/CheckBalanced.h"
#include "CheckSubtree/CheckSubtree.h"
#include "FirstCommonAncestor/FirstCommonAncestor.h"
#include "ListOfDepths/ListOfDepthsNaive.h"
#include "ListOfDepths/ListOfDepthsOptimized.h"
#include "PathsWithSum/PathsWithSum.h"
#include "RandomNode/RandomNode.h"
#include "Successor/Successor.h"
#include "ValidateBST/ValidateBST.h"

template<class T>
concept GraphTaskRunner = requires(T runner) {
    runner.run();
};

static_assert(GraphTaskRunner<RouteBetweenNodesNaive>);
static_assert(GraphTaskRunner<RouteBetweenNodesOptimized>);
static_assert(GraphTaskRunner<MinimalTree>);

static_assert(GraphTaskRunner<BSTSequences>);
static_assert(GraphTaskRunner<BuildOrder>);
static_assert(GraphTaskRunner<CheckBalanced>);
static_assert(GraphTaskRunner<CheckSubtree>);
static_assert(GraphTaskRunner<FirstCommonAncestor>);
static_assert(GraphTaskRunner<ListOfDepthsNaive>);
static_assert(GraphTaskRunner<ListOfDepthsOptimized>);
static_assert(GraphTaskRunner<PathsWithSum>);
static_assert(GraphTaskRunner<RandomNode>);
static_assert(GraphTaskRunner<Successor>);
static_assert(GraphTaskRunner<ValidateBST>);

#endif //INC_4_TREES_GRAPHS_RUNNER_H
