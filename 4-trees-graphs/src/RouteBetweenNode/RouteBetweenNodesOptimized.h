//
// Created by Andrey Tabakov on 31.10.2021.
//

#ifndef INC_4_TREES_GRAPHS_ROUTEBETWEENNODEOPTIMIZED_H
#define INC_4_TREES_GRAPHS_ROUTEBETWEENNODEOPTIMIZED_H

#include <queue>
#include <vector>
#include <unordered_set>


class RouteBetweenNodesOptimized {

public:
    void run() {

        Graph connectedGraph;
        connectedGraph.createDefiniteConnectedGraph();
        connectedGraph.printGraph();

        std::cout << exists(connectedGraph.getNodeById(0), connectedGraph.getNodeById(3)) << " should be 1"
                  << std::endl;
        std::cout << exists(connectedGraph.getNodeById(3), connectedGraph.getNodeById(0)) << " should be 0"
                  << std::endl;

        Graph unConnectedGraph;
        unConnectedGraph.createDefiniteUnConnectedGraph();
        unConnectedGraph.printGraph();
        std::cout << exists(unConnectedGraph.getNodeById(0), unConnectedGraph.getNodeById(5)) << " should be 0"
                  << std::endl;
    }

    bool exists(Node *startNode, Node *finishNode) {
        if (startNode == finishNode) {
            return true;
        }

        std::queue<Node *> q;
        std::unordered_set<Node *> visitedNodes;

        visitedNodes.insert(startNode);
        q.push(startNode);

        Node *current;
        std::vector<Node *> *currentConnectedNodes;

        while (!q.empty()) {
            current = q.front();
            q.pop();

            if (current != nullptr) {
                currentConnectedNodes = &current->connectedNodes;

                for (auto node : *currentConnectedNodes){
                    if (!visitedNodes.contains(node)) {
                        if (node == finishNode) {
                            return true;
                        } else {
                            visitedNodes.insert(node);
                            q.push(node);
                        }
                    }
                }

                visitedNodes.insert(current);
            }
        }

        return false;
    }
};


#endif //INC_4_TREES_GRAPHS_ROUTEBETWEENNODEOPTIMIZED_H
