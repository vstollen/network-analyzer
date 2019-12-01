# network-analyzer
Tool to calculate formal network metrics.

# Supported Metrics
- Bisection Bandwidth

# Usage
```
network-analyzer <PATH-TO-GRAPH-FILE>
```

# Graph Files
Graphs can be specified in `.txt` files. The files have to start with the number of nodes, followed by the edges.

An edge is defined in the form of `NODE-ID, NODE-ID, CONNECTION-BANDWIDTH`.

A resulting network file might be:
```
4
0, 1, 20
1, 3, 20
0, 2, 20
2, 3, 20
```

Representing 4 Nodes, with these connections: 0-1, 1-3, 0-2 and 2-3, each with a connection bandwidth of 20.
