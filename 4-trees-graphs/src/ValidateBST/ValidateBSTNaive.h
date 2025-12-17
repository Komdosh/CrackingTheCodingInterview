#ifndef INC_4_TREES_GRAPHS_VALIDATEBST_NAIVE_H
#define INC_4_TREES_GRAPHS_VALIDATEBST_NAIVE_H

class ValidateBSTNaive {

public:
    void run() {
        BinaryTree tree;
        tree.createBSTree();

        std::cout << "Is binary search tree: " << isBinarySearchTree(tree.root()) << " should be 1" << std::endl;

        BinaryTree nonBST;
        nonBST.createDefiniteTree();
        std::cout << "Is binary search tree: " << isBinarySearchTree(nonBST.root()) << " should be 0" << std::endl;
    }

    static bool isBinarySearchTree(const BinaryTreeNode *node) {
        if (node == nullptr) {
            return true;
        }

        if (node->left() != nullptr) {
            if (node->left()->getId() > node->getId()) {
                return false;
            }
        }

        if (node->right() != nullptr) {
            if (node->right()->getId() <= node->getId()) {
                return false;
            }
        }

        bool good = isBinarySearchTree(node->left());
        if (!good) {
            return false;
        }
        good = isBinarySearchTree(node->right());

        return good;
    }
};


#endif //INC_4_TREES_GRAPHS_VALIDATEBST_NAIVE_H
