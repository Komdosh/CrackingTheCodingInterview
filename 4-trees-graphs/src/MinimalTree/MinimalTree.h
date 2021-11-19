#ifndef INC_4_TREES_GRAPHS_MINIMALTREE_H
#define INC_4_TREES_GRAPHS_MINIMALTREE_H

#include <queue>
#include <vector>
#include <unordered_set>

class MinimalTree {

public:

    void run() {
        Graph connectedGraph;

        std::cout << "Even array" << std::endl;
        int *ordered = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        Node *node = createMinimalBST(ordered, 0, 9, 0);
        connectedGraph.addRoot(node);

        connectedGraph.printGraph();

        int *ordered2 = new int[]{8, 12, 23, 2392, 3291, 29922, 30000, 39191, 49919, 59234, 128122, 943119, 992821};

        std::cout << "Odd array" << std::endl;
        Graph connectedGraph2;
        Node *node2 = createMinimalBST(ordered2, 0, 10, 0);
        connectedGraph2.addRoot(node2);

        connectedGraph2.printGraph();
    }

    Node *createMinimalBST(int *orderedNumbers, int start, int end, int level) {
        if (level > 9999) {
            throw "Too deep, consider to use stack instead of recursion";
        }

        if (end < start) {
            return nullptr;
        }

        int middleId = (end + start) / 2;

        Node *node = new Node(orderedNumbers[middleId], level);
        Node *left = createMinimalBST(orderedNumbers, start, middleId - 1, level + 1);
        if (left != nullptr) {
            node->connect(left);
        }
        Node *right = createMinimalBST(orderedNumbers, middleId + 1, end, level + 1);
        if (right != nullptr) {
            node->connect(right);
        }
        return node;
    }
};


#endif //INC_4_TREES_GRAPHS_MINIMALTREE_H
