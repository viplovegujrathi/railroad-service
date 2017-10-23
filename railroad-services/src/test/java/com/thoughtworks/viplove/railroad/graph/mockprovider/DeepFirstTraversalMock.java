package com.thoughtworks.viplove.railroad.graph.mockprovider;

import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 * Mock implementation of the interface {@link DeepFirstTraversal}.
 * @author vigujrat
 *
 */
public class DeepFirstTraversalMock implements DeepFirstTraversal {

    @Override
    public int countTotalPathsThroughEachStep(Graph graph, Node source, Node target, int maxSteps) {
        return 1;
    }

    @Override
    public int countTotalPathsInTheLastStep(Graph graph, Node source, Node target, int maxSteps) {
        return 2;
    }

    @Override
    public int countTotalPathsWithLessThanMaxDistance(Graph graph, Node source, Node target, int maxDistance) {
        return 3;
    }

}
