#ifndef INC_4_TREES_GRAPHS_BST_SEQUENCES_NAIVE_H
#define INC_4_TREES_GRAPHS_BST_SEQUENCES_NAIVE_H

#include <queue>
#include <vector>

class BSTSequencesNaive {
public:
    void run() {
        BinaryTree tree;
        tree.createBSTree();

        const std::vector<std::deque<const BinaryTreeNode *> > result = dfs(tree.root());

        for (const std::deque<const BinaryTreeNode *> &l: result) {
            std::cout << "{ ";
            for (const BinaryTreeNode *n: l) {
                std::cout << n->getId() << ", ";
            }
            std::cout << "}" << std::endl;
        }
    }

    static std::vector<std::deque<const BinaryTreeNode *> > dfs(const BinaryTreeNode *node) {
        std::vector<std::deque<const BinaryTreeNode *> > result;

        if (node == nullptr) {
            result.emplace_back();
            return result;
        }

        std::vector<std::deque<const BinaryTreeNode *> > leftSubResult = dfs(node->left());
        std::vector<std::deque<const BinaryTreeNode *> > rightSubResult = dfs(node->right());

        std::deque<const BinaryTreeNode *> current;
        current.push_back(node);

        for (std::deque<const BinaryTreeNode *> &l: leftSubResult) {
            for (std::deque<const BinaryTreeNode *> &r: rightSubResult) {
                std::vector<std::deque<const BinaryTreeNode *> > shuffled;

                shuffle(l, r, current, shuffled); // create a shuffled list of all subbranches

                result.insert(result.end(), shuffled.begin(), shuffled.end());
            }
        }

        return result;
    }

    static void shuffle(std::deque<const BinaryTreeNode *> &l, std::deque<const BinaryTreeNode *> &r,
                        std::deque<const BinaryTreeNode *> &current,
                        std::vector<std::deque<const BinaryTreeNode *> > &results) {
        if (l.empty() || r.empty()) {
            std::deque<const BinaryTreeNode *> result;

            // all current values are start of the subsequence
            result.insert(result.end(), current.begin(), current.end());
            if (!l.empty()) {
                // add all last in the left subtree
                result.insert(result.end(), l.begin(), l.end());
            }
            if (!r.empty()) {
                // add all last in the right subtree
                result.insert(result.end(), r.begin(), r.end());
            }

            results.emplace_back(result);
            return;
        }

        // backtracking algorigthm (delete, process, recover)
        const BinaryTreeNode *leftHead = l.front();
        l.pop_front();
        current.push_back(leftHead);
        shuffle(l, r, current, results);
        current.pop_back();
        l.push_front(leftHead);

        const BinaryTreeNode *rightHead = r.front();
        r.pop_front();
        current.push_back(rightHead);
        shuffle(l, r, current, results);
        current.pop_back();
        r.push_front(rightHead);
    }
};


#endif //INC_4_TREES_GRAPHS_BST_SEQUENCES_NAIVE_H
