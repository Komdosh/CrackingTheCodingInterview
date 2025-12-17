#ifndef INC_4_TREES_GRAPHS_BUILD_ORDER_NAIVE_H
#define INC_4_TREES_GRAPHS_BUILD_ORDER_NAIVE_H

#include <vector>
#include <unordered_map>


class BuildOrder {
    static Node *getProjectNode(char projectName, std::unordered_map<char, Node *> *projectsStore) {
        Node *projectNode;
        if (!projectsStore->contains(projectName)) {
            auto node = new Node(projectName, 0);
            projectNode = node;
            projectsStore->insert({projectName, node});
        } else {
            projectNode = projectsStore->at(projectName);
        }
        return projectNode;
    }

public:
    void run() {
        buildDeps();
    }

    void buildDeps() {
        std::vector projects = {'a', 'b', 'c', 'd', 'e', 'f'};
        std::unordered_set tops(projects.begin(), projects.end());
        std::vector<std::pair<char, char> > dependencies = {
            {'a', 'd'},
            {'f', 'b'},
            {'b', 'd'},
            {'f', 'a'},
            {'d', 'c'},
        };

        std::unordered_map<char, Node *> projectsWithDependants;

        for (auto [in, out]: dependencies) {
            Node *currentProject = getProjectNode(in, &projectsWithDependants);
            Node *dependantProject = getProjectNode(out, &projectsWithDependants);

            tops.erase(out);

            currentProject->connect(dependantProject);
        }

        std::vector<Node *> nodes(projectsWithDependants.size());
        auto value_selector = [](auto pair) { return pair.second; };
        std::ranges::transform(projectsWithDependants, nodes.begin(), value_selector);

        std::unordered_set<char> visited;

        for (const auto top: tops) {
            if (!projectsWithDependants.contains(top)) {
                visited.insert(top);
                std::cout << top << " ";
            }
        }

        for (const auto top: tops) {
            if (projectsWithDependants.contains(top) && !visited.contains(top)) {
                std::cout << top << " ";
                const auto node = projectsWithDependants.at(top);
                traverse(node, visited);
                visited.insert(top);
            }
        }

        std::cout << std::endl;
    }

    // e, f, (a/b), (b/a), d, c
    void traverse(const Node *node, std::unordered_set<char> &visited) {
        for (const auto connected: node->connectedNodes) {
            if (!visited.contains(static_cast<char>(connected->getId()))) {
                std::cout << static_cast<char>(connected->getId()) << " ";
            }
        }

        for (const auto connected: node->connectedNodes) {
            traverse(connected, visited);
            visited.insert(static_cast<char>(connected->getId()));
        }
    }
};


#endif //INC_4_TREES_GRAPHS_BUILD_ORDER_NAIVE_H
