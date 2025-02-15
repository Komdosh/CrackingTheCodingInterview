#ifndef INC_4_TREES_GRAPHS_ROUTEBETWEENNODE_OPTIMIZED_H
#define INC_4_TREES_GRAPHS_ROUTEBETWEENNODE_OPTIMIZED_H

#include <queue>
#include <vector>
#include <unordered_set>


class RouteBetweenNodesOptimized {
public:
    void run() {
        Graph connectedGraph;
        connectedGraph.createDefiniteConnectedGraph();
        connectedGraph.printGraph();

        std::cout << search(connectedGraph.getNodeById(0), connectedGraph.getNodeById(3)) << " should be 1"
                << std::endl;
        std::cout << search(connectedGraph.getNodeById(3), connectedGraph.getNodeById(0)) << " should be 0"
                << std::endl;

        Graph unConnectedGraph;
        unConnectedGraph.createDefiniteUnConnectedGraph();
        unConnectedGraph.printGraph();
        std::cout << search(unConnectedGraph.getNodeById(0), unConnectedGraph.getNodeById(5)) << " should be 0"
                << std::endl;
    }

    bool search(Node *startNode, const Node *finishNode) {
        if (startNode == finishNode) {
            return true;
        }

        std::queue<Node *> q;
        std::unordered_set<Node *> visitedNodes;

        visitedNodes.insert(startNode);
        q.push(startNode);

        while (!q.empty()) {
            Node *current = q.front();
            q.pop();

            if (current != nullptr) {
                std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;

                for (auto node: *currentConnectedNodes) {
                    if (!visitedNodes.contains(node)) {
                        if (node == finishNode) {
                            return true;
                        }
                        visitedNodes.insert(node);
                        q.push(node);
                    }
                }

                visitedNodes.insert(current);
            }
        }

        return false;
    }
};


#endif //INC_4_TREES_GRAPHS_ROUTEBETWEENNODE_OPTIMIZED_H
