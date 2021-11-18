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
        if (node != nullptr) {
            connectedNodes.push_back(node);
        }
    }

    void connect(std::vector<Node *> &nodes) {
        if (!nodes.empty()) {
            connectedNodes.insert(connectedNodes.end(), nodes.begin(), nodes.end());
        }
    }

    int getId() {
        return id;
    }

    void setId(int v) {
        this->id = v;
    }

};

class Graph {
    std::vector<Node *> roots;

    int maxLevel = 3;
public:
    Node *createRoot() {
        Node *root = new Node(0, 0);
        roots.push_back(root);
        return root;
    }

    void addRoot(Node *root) {
        if (root != nullptr) {
            roots.push_back(root);
        }
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

    void createDefiniteConnectedGraph() {
        auto root = new Node(0, 0);
        roots.push_back(root);

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
        auto root = new Node(0, 0);
        roots.push_back(root);
        root = new Node(0, 0);

        Node *n = new Node(1, 1);
        root->connect(n);
        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        auto unconnectedNode = new Node(5, 2);
        roots.push_back(unconnectedNode);
        unconnectedNode->connect(new Node(6, 3));
        unconnectedNode->connect(new Node(7, 3));
    }

    void depthFirstTraverse(std::function<bool(Node *)> action) {
        for (auto root: roots) {
            Node *current = root;

            std::vector<Node *> nodes;
            std::vector<Node *> *currentConnectedNodes;
            std::unordered_set<Node *> visitedNodes;

            while (current != nullptr) {
                visitedNodes.insert(current);

                currentConnectedNodes = &current->connectedNodes;

                if (!currentConnectedNodes->empty()) {
                    std::copy_if(currentConnectedNodes->begin(), currentConnectedNodes->end(),
                                 std::back_inserter(nodes),
                                 [&visitedNodes](Node *node) { return !visitedNodes.contains(node); });
                }

                bool finished = action(current);
                if (finished) {
                    return;
                }

                if (!nodes.empty()) {
                    current = nodes.back();
                    nodes.pop_back();
                } else {
                    current = nullptr;
                }
            }
        }
    }

    void printGraph() {
        depthFirstTraverse([](Node *current) {
            std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;

            if (!currentConnectedNodes->empty()) {
                std::cout << "--------" << std::endl;
                std::cout << "NodeId: " << current->getId() << std::endl;
                std::cout << "Connected nodes:" << std::endl;

                std::sort(currentConnectedNodes->begin(), currentConnectedNodes->end(),
                          [](Node *first, Node *second) { return first->getId() < second->getId(); });
            }

            for (Node *c: *currentConnectedNodes) {
                std::cout << " - " << c->getId() << std::endl;
            }

            return false;
        });
    }

    Node *getNodeById(int id) {
        Node *n = nullptr;
        depthFirstTraverse([=, &n](Node *current) {
            if (current->getId() == id) {
                n = current;
                return true;
            }

            return false;
        });

        return n;
    }
};

#endif //INC_4_TREES_GRAPHS_GRAPH_H