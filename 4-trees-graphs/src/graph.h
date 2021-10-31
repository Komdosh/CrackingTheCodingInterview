#ifndef INC_4_TREES_GRAPHS_GRAPH_H
#define INC_4_TREES_GRAPHS_GRAPH_H

#include <iostream>
#include <vector>
#include <unordered_set>
#include <iomanip>

class Node {
    int id = 0;
    int level = 0;
public:

    std::vector<Node *> connectedNodes;

    Node(int id, int level) {
        this->id = id;
        this->level = level;
    }

    void connect(Node *node) {
        connectedNodes.push_back(node);
    }

    void connect(std::vector<Node *> &nodes) {
        connectedNodes.insert(connectedNodes.end(), nodes.begin(), nodes.end());
    }

    int getId() {
        return id;
    }

};

class Graph {
    Node *root;

    int maxLevel = 3;
public:
    void createDefiniteTree() {
        root = new Node(0, 0);

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

    void createDefiniteConnectedGraph() {
        root = new Node(0, 0);

        Node *n = new Node(1, 1);
        root->connect(n);
        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        n->connect(root);
        n->connect(new Node(4, 2));

        auto tmp = new Node(5, 2);
        n->connect(tmp);

        tmp->connect(new Node(6, 3));
        tmp->connect(new Node(7, 3));

        tmp->connect(root);
    }

    void createDefiniteUnConnectedGraph() {
        root = new Node(0, 0);

        Node *n = new Node(1, 1);
        root->connect(n);
        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        auto unconnectedNode = new Node(5, 2);

        unconnectedNode->connect(new Node(6, 3));
        unconnectedNode->connect(new Node(7, 3));
    }

    void printGraph() {
        Node *current = root;

        std::vector<Node *> nodes;
        std::vector<Node *> *currentConnectedNodes;
        std::unordered_set<Node *> visitedNodes;

        visitedNodes.insert(root);
        while (current != nullptr) {
            currentConnectedNodes = &current->connectedNodes;

            std::copy_if(currentConnectedNodes->begin(), currentConnectedNodes->end(),
                         std::back_inserter(nodes),
                         [&visitedNodes](Node *node) { return !visitedNodes.contains(node); });

            visitedNodes.insert(currentConnectedNodes->begin(), currentConnectedNodes->end());

            if (!currentConnectedNodes->empty()) {
                std::cout << "--------" << std::endl;
                std::cout << "NodeId: " << current->getId() << std::endl;
                std::cout << "Connected nodes:" << std::endl;
            }
            std::sort(currentConnectedNodes->begin(), currentConnectedNodes->end(),
                      [](Node *first, Node *second) { return first->getId() < second->getId(); });
            for (Node *c: *currentConnectedNodes) {
                std::cout << " - " << c->getId() << std::endl;
            }

            if (!nodes.empty()) {
                current = nodes.back();
                nodes.pop_back();
            } else {
                current = nullptr;
            }
        }
    }

    Node* getNodeById(int id){
        Node *current = root;

        std::vector<Node *> nodes;
        std::vector<Node *> *currentConnectedNodes;
        std::unordered_set<Node *> visitedNodes;

        visitedNodes.insert(root);
        while (current != nullptr){
            if(current->getId() == id){
                return current;
            }
            currentConnectedNodes = &current->connectedNodes;

            std::copy_if(currentConnectedNodes->begin(), currentConnectedNodes->end(),
                         std::back_inserter(nodes),
                         [&visitedNodes](Node *node) { return !visitedNodes.contains(node); });

            visitedNodes.insert(currentConnectedNodes->begin(), currentConnectedNodes->end());


            if (!nodes.empty()) {
                current = nodes.back();
                nodes.pop_back();
            } else {
                current = nullptr;
            }
        }

        return nullptr;
    }
};

#endif //INC_4_TREES_GRAPHS_GRAPH_H