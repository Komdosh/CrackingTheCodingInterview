#ifndef INC_4_TREES_GRAPHS_GRAPH_H
#define INC_4_TREES_GRAPHS_GRAPH_H

#include <iostream>
#include <vector>
#include <unordered_set>
#include <iomanip>
#include <queue>
#include <stack>
#include <algorithm>
#include <functional>

class Node {
    int id = 0;
    int level = 0;

public:
    std::vector<Node *> connectedNodes;

    Node(const int id, const int level) {
        this->id = id;
        this->level = level;
    }

    bool alreadyConnected(const Node *node) const {
        if (node != nullptr) {
            for (Node *c: connectedNodes) {
                if (c->id == node->id) {
                    return true;
                }
            }
        }
        return false;
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

    int getId() const {
        return id;
    }

    void setId(int v) {
        this->id = v;
    }

    int getLevel() const {
        return level;
    }
};

class BinaryTreeNode : public Node {
public:
    BinaryTreeNode(int id, int level) : Node(id, level) {
    }

    BinaryTreeNode *left() const {
        if (!connectedNodes.empty()) {
            return (BinaryTreeNode *) connectedNodes[0];
        }
        return nullptr;
    }

    BinaryTreeNode *right() const {
        if (connectedNodes.size() > 1) {
            return (BinaryTreeNode *) connectedNodes[1];
        }
        return nullptr;
    }
};

class BiDirectedBinaryTreeNode : public BinaryTreeNode {
public:
    BiDirectedBinaryTreeNode *parent;

    BiDirectedBinaryTreeNode(int id, int level, BiDirectedBinaryTreeNode *parent) : BinaryTreeNode(id, level) {
        this->parent = parent;
    }

    BiDirectedBinaryTreeNode *left() {
        if (!connectedNodes.empty()) {
            return (BiDirectedBinaryTreeNode *) connectedNodes[0];
        }
        return nullptr;
    }

    BiDirectedBinaryTreeNode *right() {
        if (connectedNodes.size() > 1) {
            return (BiDirectedBinaryTreeNode *) connectedNodes[1];
        }
        return nullptr;
    }
};

class Graph {
public:
    std::vector<Node *> roots;

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

    void createDefiniteConnectedGraph() {
        const auto root = new Node(0, 0);
        roots.push_back(root);

        Node *n = new Node(1, 1);
        root->connect(n);
        n = new Node(2, 1);
        root->connect(n);

        n->connect(new Node(3, 2));

        n->connect(root);
        n->connect(new Node(4, 2));

        const auto tmp = new Node(5, 2);
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

        const auto unconnectedNode = new Node(5, 2);
        roots.push_back(unconnectedNode);
        unconnectedNode->connect(new Node(6, 3));
        unconnectedNode->connect(new Node(7, 3));
    }

    template<class container>
    static void traverse(
        const std::vector<Node *> &providedRoots,
        container &nodes,
        const std::function<Node *(container)> &fetch,
        const std::function<bool(Node *)> &action
    ) {
        for (auto root: providedRoots) {
            Node *current = root;

            std::unordered_set<Node *> visitedNodes;

            while (current != nullptr) {
                visitedNodes.insert(current);

                std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;

                if (!currentConnectedNodes->empty()) {
                    for (auto node: *currentConnectedNodes) {
                        if (!visitedNodes.contains(node)) {
                            nodes.push(node);
                        }
                    }
                }

                const bool finished = action(current);
                if (finished) {
                    return;
                }

                if (!nodes.empty()) {
                    current = fetch(nodes);
                    nodes.pop();
                } else {
                    current = nullptr;
                }
            }
        }
    }

    void depthFirstTraverse(const std::function<bool(Node *)> &action) const {
        std::stack<Node *> nodes;
        traverse<std::stack<Node *> >(roots, nodes, [](std::stack<Node *> nodes) { return nodes.top(); }, action);
    }

    void breadthFirstTraverse(const std::function<bool(Node *)> &action) const {
        std::queue<Node *> nodes;
        traverse<std::queue<Node *> >(roots, nodes, [](std::queue<Node *> nodes) { return nodes.front(); }, action);
    }

    void printGraph() const {
        std::cout << "DEEP" << std::endl;
        depthFirstTraverse([this](Node *current) {
            printNode(current);

            return false;
        });

        std::cout << "BREADTH" << std::endl;

        breadthFirstTraverse([this](Node *current) {
            printNode(current);

            return false;
        });
    }

    virtual void printNode(Node *current) const {
        std::vector<Node *> *currentConnectedNodes = &current->connectedNodes;
        if (!currentConnectedNodes->empty()) {
            std::cout << "--------" << std::endl;

            std::cout << "NodeId: " << current->getId() << std::endl;

            std::cout << "Connected nodes:" << std::endl;

            std::ranges::sort(*currentConnectedNodes,
                              [](const Node *first, const Node *second) {
                                  return first->getId() < second->getId();
                              });
        }

        for (const Node *c: *currentConnectedNodes) {
            std::cout << " - " << c->getId() << std::endl;
        }
    }

    Node *getNodeById(const int id) const {
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
