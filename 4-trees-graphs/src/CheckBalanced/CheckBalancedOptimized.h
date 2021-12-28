#ifndef INC_4_TREES_GRAPHS_CHECK_BALANCED_OPTIMIZED_H
#define INC_4_TREES_GRAPHS_CHECK_BALANCED_OPTIMIZED_H

#include <queue>
#include <vector>
#include <unordered_set>

class CheckBalancedOptimized {
public:
    void run() {
        Tree tree;
        tree.createDefiniteTree();
//        tree.createBalancedTree();

        tree.printTree();

        bool balanced = isBalanced(tree.root());

        std::cout << "isBalanced: " << balanced << std::endl;
    }

    int checkHeight(Node *root) {
        if (root == nullptr) return -1;

        int max = -1;
        int min = INT_MAX;
        for (auto c: root->connectedNodes) {
            int value = checkHeight(c);

            if (value == INT_MIN) {
                return INT_MIN;
            }

            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        if (abs(max - min) > 1) {
            return INT_MIN;
        }

        return max + 1;
    }

    bool isBalanced(Node *root) {
        return checkHeight(root) != INT_MIN;
    }
};


#endif //INC_4_TREES_GRAPHS_CHECK_BALANCED_OPTIMIZED_H
