package pro.atabakov.pro.atabakov.babynames

class GraphNode(
    val name: String,
    val frequency: Int
) {
    private val neighbors: MutableList<GraphNode> = mutableListOf()
    var visited: Boolean = false

    fun addNeighbor(node: GraphNode) {
        neighbors.add(node)
    }

    fun getNeighbors(): List<GraphNode> = neighbors
}

class Graph {

    private val nodes: MutableMap<String, GraphNode> = mutableMapOf()

    fun createNode(name: String, frequency: Int) {
        if (!nodes.containsKey(name)) {
            nodes[name] = GraphNode(name, frequency)
        }
    }

    fun addEdge(name1: String, name2: String) {
        val node1 = nodes[name1]
        val node2 = nodes[name2]

        if (node1 != null && node2 != null) {
            node1.addNeighbor(node2)
            node2.addNeighbor(node1)
        }
    }

    fun getNodes(): Collection<GraphNode> = nodes.values
}

fun getComponentFrequency(node: GraphNode): Int {
    if (node.visited) return 0

    node.visited = true

    var sum = node.frequency
    for (child in node.getNeighbors()) {
        sum += getComponentFrequency(child)
    }

    return sum
}

fun getTrueFrequencies(graph: Graph): Map<String, Int> {
    val rootNames = mutableMapOf<String, Int>()

    for (node in graph.getNodes()) {
        if (!node.visited) {
            val frequency = getComponentFrequency(node)
            val name = node.name
            rootNames[name] = frequency
        }
    }

    return rootNames
}

fun connectEdges(
    graph: Graph,
    synonyms: Array<Pair<String, String>>
) {
    for ((name1, name2) in synonyms) {
        graph.addEdge(name1, name2)
    }
}

fun constructGraph(names: Map<String, Int>): Graph {
    val graph = Graph()
    for ((name, frequency) in names) {
        graph.createNode(name, frequency)
    }
    return graph
}

fun main() {
    val names = mapOf(
        "John" to 15,
        "Jon" to 12,
        "Chris" to 13,
        "Kris" to 4,
        "Christopher" to 19
    )

    val synonyms = arrayOf(
        "Jon" to "John",
        "John" to "Johnny",
        "Chris" to "Kris",
        "Chris" to "Christopher",
    )

    val graph = constructGraph(names)
    connectEdges(graph, synonyms)

    val result = getTrueFrequencies(graph)

    println(result)
}