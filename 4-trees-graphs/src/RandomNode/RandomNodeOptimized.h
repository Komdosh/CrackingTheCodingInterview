#ifndef INC_4_TREES_GRAPHS_RANDOM_NODE_OPTIMIZED_H
#define INC_4_TREES_GRAPHS_RANDOM_NODE_OPTIMIZED_H

#include "RandomTreeNode.h"
#include <random>

class RandomNodeOptimized {
public:
    void run() {
        RandomTreeNode r(3);

        r.insert(5);
        r.insert(7);
        r.insert(10);
        r.insert(15);
        r.insert(17);
        r.insert(20);
        r.insert(30);
        r.insert(35);

        std::srand(777);

        std::cout << r.getRandomNode()->getData() << std::endl;
        std::cout << r.getRandomNode()->getData() << std::endl;
    }
};


#endif //INC_4_TREES_GRAPHS_RANDOM_NODE_OPTIMIZED_H
