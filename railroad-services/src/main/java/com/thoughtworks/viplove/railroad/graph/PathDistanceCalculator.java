package com.thoughtworks.viplove.railroad.graph;

import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;


/**
 * Interface of a simple walker and accumulator of path distances.
 * @author vigujrat
 *
 */
public interface PathDistanceCalculator {

    int calculate(Graph graph, Node... nodes);

}
