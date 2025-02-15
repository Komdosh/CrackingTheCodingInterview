#ifndef INC_4_TREES_GRAPHS_LIST_OF_DEPTHS_H
#define INC_4_TREES_GRAPHS_LIST_OF_DEPTHS_H

#include <queue>
#include <vector>

class ListOfDepthsNaive {
public:
    static void run() {
        Tree tree;
        tree.createDefiniteTree();

        for (const auto levelsList = levelsAsList(tree); const auto &lld: levelsList) {
            for (const auto n: lld) {
                std::cout << n->getId() << " ";
            }
            std::cout << std::endl;
        }
    }

    static std::vector<std::vector<Node *> > levelsAsList(Tree tree) {
        if (tree.root() == nullptr) {
            return {};
        }
        std::queue<Node *> nodes;

        std::vector<std::vector<Node *> > levelsList;
        std::vector<Node *> level;

        unsigned long currentLevelChildren = 1;
        unsigned long nextLevelChildren = 0;
        int depth = 0;

        levelsList.emplace_back();
        tree.breadthFirstTraverse([&](Node *current) {
            --currentLevelChildren;

            levelsList.at(depth).push_back(current);
            const std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;

            nextLevelChildren += currentConnectedNodes->size();

            if (currentLevelChildren == 0) {
                ++depth;
                levelsList.emplace_back();
                currentLevelChildren = nextLevelChildren;
                nextLevelChildren = 0;
            }

            return false;
        });

        return levelsList;
    }
};


#endif //INC_4_TREES_GRAPHS_LIST_OF_DEPTHS_H
