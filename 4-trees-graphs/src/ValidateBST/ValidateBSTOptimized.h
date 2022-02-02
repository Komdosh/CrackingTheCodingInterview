#ifndef INC_4_TREES_GRAPHS_VALIDATEBST_OPTIMIZED_H
#define INC_4_TREES_GRAPHS_VALIDATEBST_OPTIMIZED_H

#include <queue>
#include <vector>
#include <unordered_set>

class ValidateBSTOptimized {
    int *lastValue = nullptr;
public:
    void run() {
        BinaryTree tree;
        tree.createBSTree();
        lastValue = nullptr;
        std::cout << "Is binary search tree: " << checkBST(tree.root()) << " should be 1" << std::endl;
        std::cout << "Is binary search tree: " << checkBSTMinMax(tree.root()) << " should be 1" << std::endl;
        std::cout << "Is binary search tree: " << checkBSTWithArray(tree.root()) << " should be 1" << std::endl;

        BinaryTree nonBST;
        nonBST.createDefiniteTree();
        lastValue = nullptr;
        std::cout << "Is binary search tree: " << checkBST(nonBST.root()) << " should be 0" << std::endl;
        std::cout << "Is binary search tree: " << checkBSTMinMax(nonBST.root()) << " should be 0" << std::endl;
        std::cout << "Is binary search tree: " << checkBSTWithArray(nonBST.root()) << " should be 0" << std::endl;
    }

    bool checkBST(BinaryTreeNode *n) {
        if (n == nullptr) return true;

        if (!checkBST(n->left())) {
            return false;
        }

        if (lastValue != nullptr && (*lastValue) > n->getId()) {
            return false;
        }
        int data = n->getId();
        lastValue = &data;

        if (!checkBST(n->right())) {
            return false;
        }

        return true;
    }

    bool checkBSTMinMax(BinaryTreeNode *n) {
        return checkBSTMinMax(n, nullptr, nullptr);
    }

    bool checkBSTMinMax(BinaryTreeNode *n, int *min, int *max) {
        if (n == nullptr) {
            return true;
        }
        if ((min != nullptr && n->getId() <= (*min)) || (max != nullptr && n->getId() > (*max))) {
            return false;
        }

        int data = n->getId();
        if (!checkBSTMinMax(n->left(), min, &data) || !checkBSTMinMax(n->right(), &data, max)) {
            return false;
        }
        return true;
    }

    bool checkBSTWithArray(BinaryTreeNode *node) {
        std::vector<int> array;
        copyBST(node, &array);
        if (array.empty()) {
            return false;
        }
        for (int i = 1; i < array.size(); ++i) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    void copyBST(BinaryTreeNode *node, std::vector<int> *array) {
        if (node == nullptr) {
            return;
        }

        copyBST(node->left(), array);
        array->push_back(node->getId());
        copyBST(node->right(), array);
    }
};


#endif //INC_4_TREES_GRAPHS_VALIDATEBST_OPTIMIZED_H
