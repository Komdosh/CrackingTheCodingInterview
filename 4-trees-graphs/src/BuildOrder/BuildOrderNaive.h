#ifndef INC_4_TREES_GRAPHS_BUILD_ORDER_NAIVE_H
#define INC_4_TREES_GRAPHS_BUILD_ORDER_NAIVE_H

#include <queue>
#include <vector>
#include <unordered_map>


class BuildOrderNaive {

    Node *getProjectNode(char projectName, std::unordered_map<char, Node *> projectsStore) {
        Node *projectNode;
        if (!projectsStore.contains(projectName)) {
            Node *node = new Node(projectName, 0);
            projectNode = node;
            projectsStore.insert({projectName, node});
        } else {
            projectNode = projectsStore.at(projectName);
        }
        return projectNode;
    }

public:
    void run() {
        std::vector<char> projects = {'a', 'b', 'c', 'd', 'e', 'f'};
        std::vector<std::pair<char, char>> dependencies = {
                {'a', 'd'},
                {'f', 'b'},
                {'b', 'd'},
                {'f', 'a'},
                {'d', 'c'},
        };

        std::unordered_map<char, Node *> projectsStore;

        for (auto d: dependencies) {
            Node *currentProject = getProjectNode(d.first, projectsStore);
            Node *dependantProject = getProjectNode(d.second, projectsStore);

            currentProject->connect(dependantProject);
        }


        std::vector<Node *> nodes(projectsStore.size());
        auto value_selector = [](auto pair) { return pair.second; };
        transform(projectsStore.begin(), projectsStore.end(), nodes.begin(), value_selector);

//        Graph::traverse(nodes)
    }
};


#endif //INC_4_TREES_GRAPHS_BUILD_ORDER_NAIVE_H
