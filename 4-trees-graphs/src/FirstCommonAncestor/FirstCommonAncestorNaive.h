#ifndef INC_4_TREES_GRAPHS_FIRST_COMMON_ANCESTOR_NAIVE_H
#define INC_4_TREES_GRAPHS_FIRST_COMMON_ANCESTOR_NAIVE_H

#include <queue>
#include <vector>
#include <unordered_set>

class FirstCommonAncestorNaive {

public:
    void run() {
        Tree tree;
        tree.createDefiniteTree();

        int first = 3;
        int second = 6;

        tree.printTree();
    }
};


#endif //INC_4_TREES_GRAPHS_FIRST_COMMON_ANCESTOR_NAIVE_H
