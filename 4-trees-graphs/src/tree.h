#ifndef INC_4_TREES_GRAPHS_TREE_H
#define INC_4_TREES_GRAPHS_TREE_H

#include <iostream>
#include <vector>
#include <queue>
#include "graph.h"

class TreeStatistics {
public:
    int height = 0;
    int maxChildrenOnLevel = 0;
    int lowestLevelChildrenCount = 0;
    int maxDigits = 0;

    int evaluateMaxDigits(int value) {
        int digitsCount = 0;
        while (value > 0) {
            ++digitsCount;
            value /= 10;
        }

        this->maxDigits = digitsCount;

        return digitsCount;
    }

    void evaluateLowesLevelChildrenCount() {
        this->lowestLevelChildrenCount = std::pow(maxChildrenOnLevel, height - 1);
    }
};

class PrintTreeStatistics : public TreeStatistics {
public:
    int width;

    int spaces = 1 * 2; // * 2 - for both sides

    PrintTreeStatistics(const int depth, const int maxAbsValue, const int maxChildrenOnLevel) {
        this->height = depth;
        this->maxChildrenOnLevel = maxChildrenOnLevel;

        evaluateMaxDigits(maxAbsValue);
        evaluateLowesLevelChildrenCount();

        this->width = this->lowestLevelChildrenCount * (maxDigits + spaces);
    }

    void print() const {
        std::cout << this->height << std::endl;
        std::cout << this->width << std::endl;
        std::cout << this->maxChildrenOnLevel << std::endl;
        std::cout << this->lowestLevelChildrenCount << std::endl;
    }

};

class TreePrinter;

class Tree : public Graph {
    PrintTreeStatistics *evaluateStatistics() const {
        int currentLevelChildren = 1;
        int nextLevelChildren = 0;
        int depth = 0;
        int maxChildrenOnLevel = 1;

        int maxAbsValue = -(1LL << 31);

        breadthFirstTraverse([&](const Node *current) {
            if (maxAbsValue < current->getId()) {
                maxAbsValue = current->getId();
            }

            --currentLevelChildren;

            const std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;

            nextLevelChildren += currentConnectedNodes->size();

            if (maxChildrenOnLevel < currentConnectedNodes->size()) {
                maxChildrenOnLevel = currentConnectedNodes->size();
            }
            if (currentLevelChildren == 0) {
                ++depth;

                currentLevelChildren = nextLevelChildren;
                nextLevelChildren = 0;
            }

            return false;
        });

        return new PrintTreeStatistics(depth, maxAbsValue, maxChildrenOnLevel);
    }

public:
    Node *root() const {
        return roots.back();
    }

    void setRoot(Node *node) {
        roots.push_back(node);
    }

    void createDefiniteTree() {
        //              0
        //      1               2
        //                  3   4   5
        //                         6  7
        const auto root = new Node(0, 0);
        roots.push_back(root);

        Node *n = new Node(1, 1);
        root->connect(n);
        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        n->connect(new Node(4, 2));

        const auto tmp = new Node(5, 2);
        n->connect(tmp);

        tmp->connect(new Node(6, 3));
        tmp->connect(new Node(7, 3));
    }


    void createBalancedTree() {

        const auto root = new Node(0, 0);
        roots.push_back(root);

        Node *n = new Node(1, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        n->connect(new Node(4, 2));

        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(5, 2));

        n->connect(new Node(6, 2));
    }


    bool findPath(
        const std::vector<Node *> &providedRoots,
        std::queue<Node *> &path,
        const int id
    ) const {
        for (const auto root: providedRoots) {
            if (root == nullptr) {
                return false;
            }
            if (root->getId() == id) {
                path.push(root);
                return true;
            }

            const bool found = findPath(root->connectedNodes, path, id);
            if (found) {
                path.push(root);
                return true;
            }
        }

        return false;
    }

    void printTree() const {
        PrintTreeStatistics *pts = evaluateStatistics();

        // TODO(AT): implement real print tree
        printGraph();
    }

    void printNode(Node *current) const {
        std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;
        if (!currentConnectedNodes->empty()) {
            std::cout << "--------" << std::endl;

            std::cout << "NodeId: " << current->getId() << " (Level: " << current->getLevel() << ")" << std::endl;

            std::cout << "Connected nodes:" << std::endl;

            std::ranges::sort(*currentConnectedNodes,
                              [](const Node *first, const Node *second) {
                                  return first->getId() < second->getId();
                              });
        }

        for (const Node *c: *currentConnectedNodes) {
            std::cout << " - " << c->getId() << std::endl;
        }
    }

    void printNumberAlign(const int value, int spaceSize) {
        int valueSize = value == 0 ? 1 : 0;
        int tmpValue = value;
        while (tmpValue > 0) {
            ++valueSize;
            tmpValue /= 10;
        }

        int spaces = (spaceSize + 1 - valueSize) / 2;
        printSpaces(spaces);
        std::cout << value;
        printSpaces(spaces);
    };

    void printSpaces(int spaces) {
        for (int i = 0; i < spaces; ++i) {
            std::cout << " ";
        }
    }
};

class BinaryTree : public Tree {
public:
    BinaryTreeNode *root() {
        return (BinaryTreeNode *) roots.back();
    }

    void createBSTree() {
        //                  6
        //      3                     8
        //   3     5             7        10
        //                              9
        // assumption: id is value
        const auto root = new BinaryTreeNode(6, 0);
        roots.push_back(root);

        BinaryTreeNode *nId3 = new BinaryTreeNode(3, 1);
        root->connect(nId3);

        nId3->connect(new BinaryTreeNode(3, 2));

        nId3->connect(new BinaryTreeNode(5, 2));

        BinaryTreeNode *nId8 = new BinaryTreeNode(8, 1);
        root->connect(nId8);

        nId8->connect(new BinaryTreeNode(7, 2));

        auto nId10 = new BinaryTreeNode(10, 2);
        nId8->connect(nId10);

        nId10->connect(new BinaryTreeNode(9, 3));
    }

    void createDefiniteTree() {
        //                  0
        //      1                     2
        //                      3           4
        //                    5
        //                  6   7
        const auto root = new BinaryTreeNode(0, 0);
        roots.push_back(root);

        root->connect(new BinaryTreeNode(1, 1));
        BinaryTreeNode *nId2 = new BinaryTreeNode(2, 1);
        root->connect(nId2);

        const auto nId3 = new BinaryTreeNode(3, 2);
        nId2->connect(nId3);

        nId2->connect(new BinaryTreeNode(4, 2));

        const auto tmp = new BinaryTreeNode(5, 3);
        nId3->connect(tmp);

        tmp->connect(new BinaryTreeNode(6, 4));
        tmp->connect(new BinaryTreeNode(7, 4));
    }

    void createBiDirectedBinaryTree() {
        //                  0
        //      1                     2
        //                      3           4
        //                    5           7   8
        //                  6
        const auto root = new BiDirectedBinaryTreeNode(0, 0, nullptr);
        roots.push_back(root);

        root->connect(new BiDirectedBinaryTreeNode(1, 1, root));
        BiDirectedBinaryTreeNode *nId2 = new BiDirectedBinaryTreeNode(2, 1, root);
        root->connect(nId2);

        const auto nId3 = new BiDirectedBinaryTreeNode(3, 2, nId2);
        nId2->connect(nId3);

        const auto nId4 = new BiDirectedBinaryTreeNode(4, 2, nId2);
        nId2->connect(nId4);

        const auto nId5 = new BiDirectedBinaryTreeNode(5, 3, nId3);
        nId3->connect(nId5);

        nId5->connect(new BiDirectedBinaryTreeNode(6, 4, nId5));

        nId4->connect(new BiDirectedBinaryTreeNode(7, 3, nId4));
        nId4->connect(new BiDirectedBinaryTreeNode(8, 3, nId4));
    }
};

#endif //INC_4_TREES_GRAPHS_TREE_H