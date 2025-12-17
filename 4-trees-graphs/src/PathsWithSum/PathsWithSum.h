#ifndef INC_4_TREES_GRAPHS_PATHSWITHSUM_NAIVE_H
#define INC_4_TREES_GRAPHS_PATHSWITHSUM_NAIVE_H

#include <unordered_map>

class PathsWithSum {
    static int pathsWithSumCount(const BinaryTreeNode *node, const int target) {
        std::unordered_map<int, int> visited;
        return nodePathsWithSumCount(node, target, 0, visited);
    }

    static int nodePathsWithSumCount(const BinaryTreeNode *node, const int target, const int currentSum,
                                     std::unordered_map<int, int> &visited) {
        if (node == nullptr) return 0;

        const int currentValue = currentSum + node->getId();
        const int sum = currentValue - target;
        int total = visited.contains(sum) ? visited.at(sum) : 0;

        if (currentValue == target) total++;

        updateCount(visited, currentValue, 1);
        total += nodePathsWithSumCount(node->left(), target, currentValue, visited);
        total += nodePathsWithSumCount(node->right(), target, currentValue, visited);
        updateCount(visited, currentValue, -1);

        return total;
    }

    static void updateCount(std::unordered_map<int, int> &visited, const int key, const int delta) {
        const int count = (visited.contains(key) ? visited.at(key) : 0) + delta;
        if (count == 0) {
            visited.erase(key);
        } else {
            visited[key] = count;
        }
    }

public:
    static void run() {
        BinaryTree t;
        t.createBSTree();

        std::cout << pathsWithSumCount(t.root(), 6) << std::endl;
    }
};


#endif //INC_4_TREES_GRAPHS_PATHSWITHSUM_NAIVE_H
