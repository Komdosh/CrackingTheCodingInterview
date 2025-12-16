#ifndef INC_4_TREES_GRAPHS_CHECK_SUBTREE_NAIVE_H
#define INC_4_TREES_GRAPHS_CHECK_SUBTREE_NAIVE_H

class CheckSubtreeNaive {
    static bool areIdentical(const BinaryTreeNode *t1, const BinaryTreeNode *t2) {
        if (!t1 && !t2) return true;
        if (!t1 || !t2) return false;

        if (t1->getId() != t2->getId()) return false;

        return areIdentical(t1->left(), t2->left()) &&
               areIdentical(t1->right(), t2->right());
    }

    // Traverse T1 and try to match T2 at each node
    static bool traverseSubtree(const BinaryTreeNode *t1, const BinaryTreeNode *t2) {
        if (!t1) return false;

        if (t1->getId() == t2->getId()) {
            if (areIdentical(t1, t2)) {
                return true;
            }
        }

        return traverseSubtree(t1->left(), t2) ||
               traverseSubtree(t1->right(), t2);
    }

public:
    void run() {
        BinaryTree t1, t2;
        t1.createDefiniteTree();
        t2.createDefiniteTree();

        std::cout << traverseSubtree(t1.root(), t2.root()) << std::endl;

        BinaryTree t3, t4;
        t3.createDefiniteTree();
        t4.createBiDirectedBinaryTree();

        std::cout << traverseSubtree(t3.root(), t4.root()) << std::endl;
    }
};


#endif //INC_4_TREES_GRAPHS_CHECK_SUBTREE_NAIVE_H
