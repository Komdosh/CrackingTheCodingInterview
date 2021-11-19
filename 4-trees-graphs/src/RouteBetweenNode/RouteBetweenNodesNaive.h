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

        std::cout << search(connectedGraph.getNodeById(0), connectedGraph.getNodeById(3)) << " should be 1" << std::endl;
        std::cout << search(connectedGraph.getNodeById(3), connectedGraph.getNodeById(0)) << " should be 0" << std::endl;

        Graph unConnectedGraph;
        unConnectedGraph.createDefiniteUnConnectedGraph();
        unConnectedGraph.printGraph();
        std::cout << search(unConnectedGraph.getNodeById(0), unConnectedGraph.getNodeById(5)) << " should be 0" << std::endl;
    }

    bool search(Node *startNode, Node *finishNode) {
        if(startNode == finishNode){
            return true;
        }

        std::queue<Node *> q;
        std::unordered_set<Node *> visitedNodes;
        std::vector<Node *> *currentConnectedNodes;

        Node *current = startNode;
        while (current != nullptr) {
            if (current == finishNode) {
                return true;
            }

            currentConnectedNodes = &current->connectedNodes;

            for (auto node : *currentConnectedNodes){
                if (!visitedNodes.contains(node)) {
                    q.push(node);
                }
            }

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
