package com.thoughtworks.viplove.railroad.graph;

import java.util.Map;

import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;


/**
 * Interface of a Dijkstra algorithm.
 * @author vigujrat
 *
 */
public interface DijkstraCalculator {

    static Integer INFINITE_DISTANCE = Integer.valueOf(Integer.MAX_VALUE);
    static Integer NO_DISTANCE = Integer.valueOf(0);

    Map<Node, Integer> calculate(Graph graph, Node source);

}
