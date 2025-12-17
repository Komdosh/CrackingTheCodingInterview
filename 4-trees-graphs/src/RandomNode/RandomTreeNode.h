//
// Created by Andrey Tabakov on 17/12/2025.
//

#ifndef INC_4_TREES_GRAPHS_RANDOMTREE_H
#define INC_4_TREES_GRAPHS_RANDOMTREE_H

class RandomTreeNode {
    int data = 0;
    RandomTreeNode *left = nullptr;
    RandomTreeNode *right = nullptr;
    int size = 0;

    RandomTreeNode *getNodeByIndex(const int index) {
        const int leftSize = left != nullptr ? left->size : 0;
        if (index < leftSize) {
            if (left == nullptr) { return nullptr; }
            return left->getNodeByIndex(index);
        }
        if (index == leftSize) {
            return this;
        }
        return right->getNodeByIndex(index - (leftSize + 1));
    }

public:
    [[nodiscard]] int getData() const { return data; }

    explicit RandomTreeNode(const int data) {
        this->data = data;
        this->size = 1;
    }

    void insert(const int d) {
        if (d <= data) {
            if (left == nullptr) {
                left = new RandomTreeNode(d);
            } else {
                left->insert(d);
            }
        } else {
            if (right == nullptr) {
                right = new RandomTreeNode(d);
            } else {
                right->insert(d);
            }
        }
        size++;
    }

    RandomTreeNode *getRandomNode() {
        const int index = rand() % size;
        return getNodeByIndex(index);
    }
};

#endif //INC_4_TREES_GRAPHS_RANDOMTREE_H
