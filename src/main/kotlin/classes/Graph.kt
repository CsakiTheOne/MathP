package classes

import java.util.*
import kotlin.collections.ArrayList


internal class Graph<T>() {
    // Adjacency List as ArrayList of ArrayList's
    //private val adj: ArrayList<ArrayList<Int>> = ArrayList(vertices)
    private val vertices: MutableSet<T> = mutableSetOf()
    private val adj: MutableList<Pair<T, T>> = mutableListOf()

    // Function to add an edge into the graph
    fun addEdge(v: T, w: T) {
        vertices.add(v)
        vertices.add(w)
        adj.add(v to w)
    }

    fun addAllEdge(edges: List<Pair<T, T>>) {
        for (edge in edges) {
            vertices.add(edge.first)
            vertices.add(edge.second)
            adj.add(edge.first to edge.second)
        }
    }

    // A recursive function used by topologicalSort
    private fun topologicalSortUtil(
        v: T, visited: MutableSet<T>,
        stack: Stack<T>
    ) {
        // Mark the current node as visited.
        visited.add(v)
        var i: T

        // Recur for all the vertices adjacent
        // to thisvertex
        //val it: Iterator<T> = adj[v].iterator()
        val it: Iterator<T> = adj.filter { it.first == v }.map { it.second }.iterator()
        while (it.hasNext()) {
            i = it.next()
            if (!visited.contains(i)) topologicalSortUtil(i, visited, stack)
        }

        // Push current vertex to stack
        // which stores result
        stack.push(v)
    }

    // The function to do Topological Sort.
    // It uses recursive topologicalSortUtil()
    fun topologicalSort(): List<T> {
        val stack = Stack<T>()

        // Mark all the vertices as not visited
        val visited = mutableSetOf<T>()

        // Call the recursive helper
        // function to store
        // Topological Sort starting
        // from all vertices one by one
        for (v in vertices) if (!visited.contains(v)) topologicalSortUtil(v, visited, stack)

        return stack.toList().reversed()
    }
}