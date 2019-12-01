package analyzer

import java.io.File

fun main(args: Array<String>) {
    val graph = importGraphFromTextFile(args[0])

    val analyzer = BisectionBandwidthAnalyzer(graph)

    analyzer.analyze()
}

private fun importGraphFromTextFile(path: String): NetworkGraph {
    val input = File(path).bufferedReader()

    val numberOfNodes = input.readLine().toInt()

    val nodes = Array(numberOfNodes) { index -> NetworkGraph.Node(index) }

    input.forEachLine {
        val connectedNodes = it.split(", ").map { nodeString -> nodeString.toInt()}

        nodes[connectedNodes[0]].connections[nodes[connectedNodes[1]]] = connectedNodes[2]
        nodes[connectedNodes[1]].connections[nodes[connectedNodes[0]]] = connectedNodes[2]
    }

    return NetworkGraph(nodes)
}