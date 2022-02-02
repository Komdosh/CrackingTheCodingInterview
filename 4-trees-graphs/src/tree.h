#ifndef INC_4_TREES_GRAPHS_TREE_H
#define INC_4_TREES_GRAPHS_TREE_H

#include <iostream>
#include <vector>
#include <unordered_set>
#include <iomanip>
#include <queue>
#include <stack>
#include "graph.h"

class TreeStatistics {
public:
    int height;
    int maxChildrenOnLevel;
    int lowestLevelChildrenCount;
    int maxDigits;

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

    PrintTreeStatistics(int depth, int maxAbsValue, int maxChildrenOnLevel) {
        this->height = depth;
        this->maxChildrenOnLevel = maxChildrenOnLevel;

        evaluateMaxDigits(maxAbsValue);
        evaluateLowesLevelChildrenCount();

        this->width = this->lowestLevelChildrenCount * (maxDigits + spaces);
    }

    void print() {
        std::cout << this->height << std::endl;
        std::cout << this->width << std::endl;
        std::cout << this->maxChildrenOnLevel << std::endl;
        std::cout << this->lowestLevelChildrenCount << std::endl;
    }

};

class TreePrinter;

class Tree : public Graph {
    PrintTreeStatistics *evaluateStatistics() {
        int currentLevelChildren = 1;
        int nextLevelChildren = 0;
        int depth = 0;
        int maxChildrenOnLevel = 1;

        int maxAbsValue = -(1LL << 31);

        breadthFirstTraverse([&](Node *current) {
            if (maxAbsValue < current->getId()) {
                maxAbsValue = current->getId();
            }

            --currentLevelChildren;

            std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;

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
    Node *root() {
        return roots.back();
    }

    void setRoot(Node *node) {
        roots.push_back(node);
    }

    void createDefiniteTree() {
        auto root = new Node(0, 0);
        roots.push_back(root);

        Node *n = new Node(1, 1);
        root->connect(n);
        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        n->connect(new Node(4, 2));

        auto tmp = new Node(5, 2);
        n->connect(tmp);

        tmp->connect(new Node(6, 3));
        tmp->connect(new Node(7, 3));
    }


    void createBalancedTree() {
        auto root = new Node(0, 0);
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

    void printTree() {
        PrintTreeStatistics *pts = evaluateStatistics();

        // TODO(AT): implement real print tree
        printGraph();
    }

    void printNumberAlign(int value, int spaceSize) {
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
        // assumption: id is values
        auto root = new BinaryTreeNode(6, 0);
        roots.push_back(root);

        BinaryTreeNode *n = new BinaryTreeNode(3, 1);
        root->connect(n);

        n->connect(new BinaryTreeNode(1, 2));

        n->connect(new BinaryTreeNode(5, 2));

        n = new BinaryTreeNode(9, 1);
        root->connect(n);

        n->connect(new BinaryTreeNode(7, 2));

        auto additionalNode = new BinaryTreeNode(10, 2);
        n->connect(additionalNode);

        additionalNode->connect(new BinaryTreeNode(9, 2));
    }

    void createDefiniteTree() {
        auto root = new BinaryTreeNode(0, 0);
        roots.push_back(root);

        BinaryTreeNode *n = new BinaryTreeNode(1, 1);
        root->connect(n);
        n = new BinaryTreeNode(2, 1);
        root->connect(n);

        n->connect(new BinaryTreeNode(3, 2));

        n->connect(new BinaryTreeNode(4, 2));

        auto tmp = new BinaryTreeNode(5, 2);
        n->connect(tmp);

        tmp->connect(new BinaryTreeNode(6, 3));
        tmp->connect(new BinaryTreeNode(7, 3));
    }
};

#endif //INC_4_TREES_GRAPHS_TREE_H