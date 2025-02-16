#ifndef INC_4_TREES_GRAPHS_FIRST_COMMON_ANCESTOR_NAIVE_H
#define INC_4_TREES_GRAPHS_FIRST_COMMON_ANCESTOR_NAIVE_H

#include <queue>

class FirstCommonAncestorNaive {
public:
    void run() {
        Tree tree;
        tree.createDefiniteTree();

        constexpr int first = 3;
        constexpr int second = 6;

        auto firstNodePath = path(tree, first);
        auto secondNodePath = path(tree, second);

        while (firstNodePath.size() > secondNodePath.size()) {
            firstNodePath.pop();
        }

        while (secondNodePath.size() > firstNodePath.size()) {
            secondNodePath.pop();
        }

        while (!firstNodePath.empty() && !secondNodePath.empty() && firstNodePath.front()->getId() != secondNodePath.front()->getId()) {
            firstNodePath.pop();
            secondNodePath.pop();
        }

        if (firstNodePath.empty()) { // anyway
            std::cout << "Can't find first common ancestor looks like an error" << std::endl;
        } else {
            std::cout << "First common ancestor is : " << firstNodePath.front()->getId() << std::endl;
        }
    }

    std::queue<Node *> path(const Tree &tree, const int value) {
        std::queue<Node *> nodePath;

        tree.findPath(tree.roots, nodePath, value);

        return nodePath;
    }
};


#endif //INC_4_TREES_GRAPHS_FIRST_COMMON_ANCESTOR_NAIVE_H
