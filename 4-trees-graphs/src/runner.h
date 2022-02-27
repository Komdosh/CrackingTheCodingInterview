#ifndef INC_4_TREES_GRAPHS_RUNNER_H
#define INC_4_TREES_GRAPHS_RUNNER_H

#include "RouteBetweenNode/RouteBetweenNodesNaive.h"
#include "RouteBetweenNode/RouteBetweenNodesOptimized.h"
#include "MinimalTree/MinimalTree.h"
#include "BSTSequences/BSTSequencesNaive.h"
#include "BSTSequences/BSTSequencesOptimized.h"
#include "BuildOrder/BuildOrderNaive.h"
#include "BuildOrder/BuildOrderOptimized.h"
#include "CheckBalanced/CheckBalancedNaive.h"
#include "CheckBalanced/CheckBalancedOptimized.h"
#include "CheckSubtree/CheckSubtreeNaive.h"
#include "CheckSubtree/CheckSubtreeOptimized.h"
#include "FirstCommonAncestor/FirstCommonAncestorNaive.h"
#include "FirstCommonAncestor/FirstCommonAncestorOptimized.h"
#include "ListOfDepths/ListOfDepthsNaive.h"
#include "ListOfDepths/ListOfDepthsOptimized.h"
#include "PathsWithSum/PathsWithSumNaive.h"
#include "PathsWithSum/PathsWithSumOptimized.h"
#include "RandomNode/RandomNodeNaive.h"
#include "RandomNode/RandomNodeOptimized.h"
#include "Successor/Successor.h"
#include "ValidateBST/ValidateBSTNaive.h"
#include "ValidateBST/ValidateBSTOptimized.h"

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
static_assert(GraphTaskRunner<CheckBalancedOptimized>);

static_assert(GraphTaskRunner<BSTSequencesNaive>);
static_assert(GraphTaskRunner<BSTSequencesOptimized>);
static_assert(GraphTaskRunner<BuildOrderNaive>);
static_assert(GraphTaskRunner<BuildOrderOptimized>);
static_assert(GraphTaskRunner<CheckSubtreeNaive>);
static_assert(GraphTaskRunner<CheckSubtreeOptimized>);
static_assert(GraphTaskRunner<FirstCommonAncestorNaive>);
static_assert(GraphTaskRunner<FirstCommonAncestorOptimized>);
static_assert(GraphTaskRunner<PathsWithSumNaive>);
static_assert(GraphTaskRunner<PathsWithSumOptimized>);

static_assert(GraphTaskRunner<RandomNodeNaive>);
static_assert(GraphTaskRunner<RandomNodeOptimized>);

static_assert(GraphTaskRunner<Successor>);

static_assert(GraphTaskRunner<ValidateBSTNaive>);
static_assert(GraphTaskRunner<ValidateBSTOptimized>);

#endif //INC_4_TREES_GRAPHS_RUNNER_H
