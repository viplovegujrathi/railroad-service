package com.thoughtworks.viplove.railroad.graph.mockprovider;

import com.thoughtworks.viplove.railroad.graph.PathDistanceCalculator;
import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 * Mock implementation of the interface {@link PathDistanceCalculator}.
 * @author vigujrat
 *
 */
public class PathDistanceCalculatorMock implements PathDistanceCalculator {

    @Override
    public int calculate(Graph graph, Node... nodes) {

        return nodes.length;
    }

}
