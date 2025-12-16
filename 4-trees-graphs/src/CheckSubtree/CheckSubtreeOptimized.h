#ifndef INC_4_TREES_GRAPHS_CHECK_SUBTREE_OPTIMIZED_H
#define INC_4_TREES_GRAPHS_CHECK_SUBTREE_OPTIMIZED_H

#include <unordered_set>

class CheckSubtreeOptimized {

    static constexpr uint64_t NULL_HASH = 0x9e3779b97f4a7c15ULL;
    static constexpr uint64_t FNV = 1099511628211ULL; // Fowler–Noll–Vo hash for 64 bits
    static constexpr uint64_t FNV_OFFSET = 1469598103934665603ULL;

    static uint64_t calculate_hash(const uint64_t left, const uint64_t right, const int id) {
        uint64_t h = FNV_OFFSET;
        h ^= static_cast<uint64_t>(id) + NULL_HASH;
        h *= FNV;

        h ^= left;
        h *= FNV;

        h ^= right;
        h *= FNV;
        return h;
    }

    static uint64_t hashNode(
        const BinaryTreeNode* node,
        std::unordered_set<uint64_t>& hashes
    ) {
        if (!node) return NULL_HASH;

        const uint64_t left  = hashNode(node->left(), hashes);
        const uint64_t right = hashNode(node->right(), hashes);

        const uint64_t h = calculate_hash(left, right, node->getId());

        hashes.insert(h);
        return h;
    }

    static uint64_t hashTree(const BinaryTreeNode* node) {
        if (!node) return NULL_HASH;

        const uint64_t left  = hashTree(node->left());
        const uint64_t right = hashTree(node->right());

        return calculate_hash(left, right, node->getId());
    }

    static bool isSubtree(const BinaryTreeNode* T1, const BinaryTreeNode* T2) {
        if (!T2) return true;
        if (!T1) return false;

        std::unordered_set<uint64_t> subtreeHashes;
        hashNode(T1, subtreeHashes);

        const uint64_t t2Hash = hashTree(T2);

        return subtreeHashes.contains(t2Hash);
    }

public:
    void run() {
        BinaryTree t1, t2;
        t1.createDefiniteTree();
        t2.createDefiniteTree();

        std::cout << isSubtree(t1.root(), t2.root()) << std::endl;

        BinaryTree t3, t4;
        t3.createDefiniteTree();
        t4.createBiDirectedBinaryTree();

        std::cout << isSubtree(t3.root(), t4.root()) << std::endl;
    }
};


#endif //INC_4_TREES_GRAPHS_CHECK_SUBTREE_OPTIMIZED_H
