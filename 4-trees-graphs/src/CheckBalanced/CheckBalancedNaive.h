#ifndef INC_4_TREES_GRAPHS_CHECK_BALANCED_NAIVE_H
#define INC_4_TREES_GRAPHS_CHECK_BALANCED_NAIVE_H

#include <queue>
#include <vector>
#include <unordered_set>

class CheckBalancedNaive {

    int maxDepth = -1;
    int secondDepth = 0;

public:
    void run() {
        Tree tree;
        tree.createDefiniteTree();
//        tree.createBalancedTree();

        tree.printTree();

        const bool balanced = isBalanced(tree.root(), 0);
//        bool balanced = isBalancedAlternative(tree.root());

        std::cout << "isBalanced: " << balanced << std::endl;
    }

    bool isBalanced(const Node *current, const int level) {
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
    }

    int getHeight(const Node *root) {
        if (root == nullptr) return -1;

        int max = -1;
        for (auto c: root->connectedNodes) {
            max = getHeight(c) + 1;
        }
        return max;
    }

    bool isBalancedAlternative(const Node *root) {
        int max = -1;
        int min = INT_MAX;
        for (auto c: root->connectedNodes) {
            int value = getHeight(c) + 1;
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        if (abs(max - min) > 1) {
            return false;
        }
        for (const auto c: root->connectedNodes) {
            if (!isBalancedAlternative(c)) {
                return false;
            }
        }
        return true;
    };
};


#endif //INC_4_TREES_GRAPHS_CHECK_BALANCED_NAIVE_H
