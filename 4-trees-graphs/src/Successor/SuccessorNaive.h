#ifndef INC_4_TREES_GRAPHS_SUCCESSOR_NAIVE_H
#define INC_4_TREES_GRAPHS_SUCCESSOR_NAIVE_H

#include <queue>
#include <vector>
#include <unordered_set>

class SuccessorNaive {

public:
    void run() {
        BinaryTree tree;
        tree.createBiDirectedBinaryTree();

        BiDirectedBinaryTreeNode *leftLast = (BiDirectedBinaryTreeNode *) tree.root()->right();
        while (leftLast->left() != nullptr) {
            leftLast = leftLast->left();
        }
//        leftLast = leftLast->parent;
        leftLast = (BiDirectedBinaryTreeNode *) tree.root()->right()->right()->right();

        Node *successor = findSuccessor(leftLast);

        if (successor != nullptr) {
            std::cout << "Successor of " << leftLast->getId() << " is: " << successor->getId() << std::endl;
        } else {
            std::cout << "Not found successor for " << leftLast->getId() << std::endl;
        }
    }

    Node *findSuccessor(BiDirectedBinaryTreeNode *current) {
        if (current == nullptr) {
            return nullptr;
        }
        if (current->right() != nullptr) {
            return leftMostChild(current->right());
        }

        while (current->parent != nullptr) {
            auto right = current->parent->right();
            if (right != nullptr && right != current) {
                return leftMostChild(right);
            }
            current = current->parent;
        }

        return nullptr;
    }

    BiDirectedBinaryTreeNode *leftMostChild(BiDirectedBinaryTreeNode *node) {
        if (node == nullptr) {
            return nullptr;
        }
        auto current = node;
        while (current->left() != nullptr) {
            current = current->left();
        }
        return current;
    }
};


#endif //INC_4_TREES_GRAPHS_SUCCESSOR_NAIVE_H
