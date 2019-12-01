package analyzer

class BisectionBandwidthAnalyzer(private val graph: NetworkGraph, private var threshold: Int = Int.MAX_VALUE) {

    private val nodes = graph.nodes

    private val firstGroup = ArrayList<NetworkGraph.Node>()

    private var aggregatedBandwidth = 0

    fun analyze() {

        for (i in 0 until nodes.size / 2) {
            addToGroup(nodes[i])
        }

        var finished = false

        while (!finished) {

            checkCurrentSolution()

            var lastNode = removeLastFromGroup()
            var droppedNodes = 1

            while (lastNode + droppedNodes == nodes.size) {
                if (droppedNodes == nodes.size / 2) {
                    finished = true
                    break
                }

                lastNode = removeLastFromGroup()
                droppedNodes++
            }

            if (finished) break

            for (i in 0 until droppedNodes) {
                lastNode++

                if (lastNode == nodes.size) {
                    finished = true
                    break
                }
                addToGroup(nodes[lastNode])

                while (aggregatedBandwidth >= threshold) {
                    removeLastFromGroup()
                    lastNode++

                    if (lastNode == nodes.size) {
                        finished = true
                        break
                    }
                    addToGroup(nodes[lastNode])
                }

                if (finished) break
            }
        }
    }

    private fun addToGroup(node: NetworkGraph.Node) {

        for (connection in node.connections) {
            if (firstGroup.contains(connection.key)) {
                aggregatedBandwidth -= connection.value
            } else {
                aggregatedBandwidth += connection.value
            }
        }

        firstGroup.add(node);
    }

    private fun removeLastFromGroup(): Int {
        val lastNode = firstGroup.last()
        firstGroup.remove(lastNode)

        for (connection in lastNode.connections) {
            if (firstGroup.contains(connection.key)) {
                aggregatedBandwidth += connection.value
            } else {
                aggregatedBandwidth -= connection.value
            }
        }

        return lastNode.id
    }

    private fun checkCurrentSolution() {
        if (aggregatedBandwidth < threshold) {
            println("New minimal Bandwidth: $aggregatedBandwidth")
            println("First group: ${firstGroup.toString()}")
            threshold = aggregatedBandwidth
        }
    }
}