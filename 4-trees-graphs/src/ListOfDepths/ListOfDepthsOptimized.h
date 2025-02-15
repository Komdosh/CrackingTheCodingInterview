#ifndef INC_4_TREES_GRAPHS_LIST_OF_DEPTHS_OPTIMIZED_H
#define INC_4_TREES_GRAPHS_LIST_OF_DEPTHS_OPTIMIZED_H

#include <queue>
#include <vector>
#include <unordered_set>

class ListOfDepthsOptimized {

public:
    void run() {
        Tree tree;
        tree.createDefiniteTree();

        std::vector<std::vector<Node *> *> levelsList = std::vector<std::vector<Node *> *>();
        createLevelLinkedListDepth(tree.root(), &levelsList, 0);
        levelsList = *createLevelLinkedListBreadth(tree.root());

        for (auto lld: levelsList) {
            for (auto n: *lld) {
                std::cout << n->getId() << " ";
            }
            std::cout << std::endl;
        }
    }

    void createLevelLinkedListDepth(Node *root, std::vector<std::vector<Node *> *> *lists, int level) {
        if (root == nullptr) {
            return;
        }

        if (lists->size() == level) {
            lists->push_back(new std::vector<Node *>());
        }
        auto list = (*lists)[level];

        list->push_back(root);

        for (Node *child: root->connectedNodes) {
            createLevelLinkedListDepth(child, lists, level + 1);
        }
    }

    std::vector<std::vector<Node *> *> *createLevelLinkedListBreadth(Node *root) {
        std::vector<std::vector<Node *> *> *result = new std::vector<std::vector<Node *> *>();
        std::vector<Node *> *current = new std::vector<Node *>();
        if (root != nullptr) {
            current->push_back(root);
        }

        while (current->size() > 0) {
            result->push_back(current);
            const std::vector<Node *> *parents = current;
            current = new std::vector<Node *>();
            for (Node *parent: *parents) {
                for (Node *child: parent->connectedNodes) {
                    current->push_back(child);
                }
            }
        }

        return result;
    }
};


#endif //INC_4_TREES_GRAPHS_LIST_OF_DEPTHS_OPTIMIZED_H
