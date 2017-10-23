package com.thoughtworks.viplove.railroad.graph;

import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;


/**
 * Interface of a deep first traversal computation methods.
 * @author vigujrat
 *
 */
public interface DeepFirstTraversal {

    int countTotalPathsThroughEachStep(Graph graph, Node source, Node target, int maxSteps);

    int countTotalPathsInTheLastStep(Graph graph, Node source, Node target, int maxSteps);

    int countTotalPathsWithLessThanMaxDistance(Graph graph, Node source, Node target, int maxDistance);

}
