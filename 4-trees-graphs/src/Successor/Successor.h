#ifndef INC_4_TREES_GRAPHS_SUCCESSOR_H
#define INC_4_TREES_GRAPHS_SUCCESSOR_H

class Successor {

public:
    void run() {
        BinaryTree tree;
        tree.createBiDirectedBinaryTree();

        BiDirectedBinaryTreeNode *current = leftMostChild((BiDirectedBinaryTreeNode *) tree.root()->right());
//        current = current->parent;
//        current = (BiDirectedBinaryTreeNode *) tree.root()->right()->right()->right();

        Node *successor = inorderSuccessor(current);

        if (successor != nullptr) {
            std::cout << "Successor of " << current->getId() << " is: " << successor->getId() << std::endl;
        } else {
            std::cout << "Not found successor for " << current->getId() << std::endl;
        }
    }

    Node *inorderSuccessor(BiDirectedBinaryTreeNode *n) {
        if (n == nullptr) {
            return nullptr;
        }

        if (n->right() != nullptr) {
            return leftMostChild(n);
        }

        BiDirectedBinaryTreeNode *q = n;
        BiDirectedBinaryTreeNode *x = n->parent;
        while (x != nullptr && x->left() != q) {
            q = x;
            x = x->parent;
        }
        return x;
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


#endif //INC_4_TREES_GRAPHS_SUCCESSOR_H
