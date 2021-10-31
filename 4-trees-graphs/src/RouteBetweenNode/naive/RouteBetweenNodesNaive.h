//
// Created by Andrey Tabakov on 31.10.2021.
//

#ifndef INC_4_TREES_GRAPHS_ROUTEBETWEENNODENAIVE_H
#define INC_4_TREES_GRAPHS_ROUTEBETWEENNODENAIVE_H

#include <queue>
#include <vector>
#include <unordered_set>

class RouteBetweenNodesNaive {

public:
    void run() {
        Graph connectedGraph;
        connectedGraph.createDefiniteConnectedGraph();
        connectedGraph.printGraph();

        std::cout << exists(connectedGraph.getNodeById(0), connectedGraph.getNodeById(3)) << " should be 1" << std::endl;

        Graph unConnectedGraph;
        unConnectedGraph.createDefiniteUnConnectedGraph();
        unConnectedGraph.printGraph();
        std::cout << exists(unConnectedGraph.getNodeById(0), unConnectedGraph.getNodeById(5)) << " should be 0" << std::endl;
    }

    bool exists(Node *startNode, Node *finishNode) {
        std::queue<Node *> q;
        std::unordered_set<Node *> visitedNodes;
        std::vector<Node *> *currentConnectedNodes;

        Node *current = startNode;
        while (current != nullptr) {
            if (current == finishNode) {
                return true;
            }

            currentConnectedNodes = &current->connectedNodes;

            std::for_each(currentConnectedNodes->begin(), currentConnectedNodes->end(), [&q, &visitedNodes](auto data) {
                if (!visitedNodes.contains(data)) {
                    q.push(data);
                }
            });

            visitedNodes.insert(currentConnectedNodes->begin(), currentConnectedNodes->end());

            if (!q.empty()) {
                current = q.front();
                q.pop();
            } else {
                current = nullptr;
            }
        }

        return false;
    }
};


#endif //INC_4_TREES_GRAPHS_ROUTEBETWEENNODENAIVE_H
