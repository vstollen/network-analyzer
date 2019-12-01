package analyzer

class NetworkGraph(val nodes: Array<Node>) {

    class Node(val id: Int) {
        val connections = HashMap<Node, Int>()

        override fun toString(): String {
            return id.toString()
        }
    }
}