#ifndef INC_4_TREES_GRAPHS_RUNNER_H
#define INC_4_TREES_GRAPHS_RUNNER_H

#include "RouteBetweenNode/RouteBetweenNodesNaive.h"
#include "RouteBetweenNode/RouteBetweenNodesOptimized.h"
#include "MinimalTree/MinimalTree.h"
#include "BSTSequences/BSTSequencesNaive.h"
#include "BuildOrder/BuildOrderNaive.h"
#include "CheckBalanced/CheckBalancedNaive.h"
#include "CheckSubtree/CheckSubtreeNaive.h"
#include "FirstCommonAncestor/FirstCommonAncestorNaive.h"
#include "ListOfDepths/ListOfDepthsNaive.h"
#include "ListOfDepths/ListOfDepthsOptimized.h"
#include "PathsWithSum/PathsWithSumNaive.h"
#include "RandomNode/RandomNodeNaive.h"
#include "Successor/SuccessorNaive.h"
#include "ValidateBST/ValidateBSTNaive.h"

template<class T>
concept GraphTaskRunner = requires(T runner) {
    runner.run();
};

static_assert(GraphTaskRunner<RouteBetweenNodesNaive>);
static_assert(GraphTaskRunner<RouteBetweenNodesOptimized>);
static_assert(GraphTaskRunner<MinimalTree>);
static_assert(GraphTaskRunner<ListOfDepthsNaive>);
static_assert(GraphTaskRunner<ListOfDepthsOptimized>);
static_assert(GraphTaskRunner<CheckBalancedNaive>);

static_assert(GraphTaskRunner<BSTSequencesNaive>);
static_assert(GraphTaskRunner<BuildOrderNaive>);
static_assert(GraphTaskRunner<CheckSubtreeNaive>);
static_assert(GraphTaskRunner<FirstCommonAncestorNaive>);
static_assert(GraphTaskRunner<PathsWithSumNaive>);
static_assert(GraphTaskRunner<RandomNodeNaive>);
static_assert(GraphTaskRunner<SuccessorNaive>);
static_assert(GraphTaskRunner<ValidateBSTNaive>);

#endif //INC_4_TREES_GRAPHS_RUNNER_H
