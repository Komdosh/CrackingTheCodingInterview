#ifndef INC_4_TREES_GRAPHS_CHECKBALANCEDNAIVE_H
#define INC_4_TREES_GRAPHS_CHECKBALANCEDNAIVE_H

#include <queue>
#include <vector>
#include <unordered_set>

class CheckBalancedNaive {

    int maxDepth;
    int secondDepth;

public:
    void run() {
        Tree tree;
        tree.createDefiniteTree();

        tree.printTree();

        bool balanced = isBalanced(tree.root(), 0);

        std::cout << "isBalanced: " << balanced << std::endl;
    }

    bool isBalanced(Node *current, int level) {
        for (auto c: current->connectedNodes) {
            if (level > maxDepth) {
                if (abs(secondDepth - level) > 1) {
                    return false;
                }
                secondDepth = maxDepth;
                maxDepth = level;

            }
            if (!isBalanced(c, level + 1)) {
                return false;
            }
        }

        return true;
    };
};


#endif //INC_4_TREES_GRAPHS_CHECKBALANCEDNAIVE_H
