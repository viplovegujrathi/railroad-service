package com.thoughtworks.viplove.railroad.graph.impl;

import com.thoughtworks.viplove.railroad.graph.PathDistanceCalculator;
import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;
import com.thoughtworks.viplove.railroad.graph.domain.exceptions.InvalidPath;

/**
 * Calculator of total distance of a given path.
 * @author vigujrat
 *
 */
public class PathDistanceCalculatorImpl implements PathDistanceCalculator {

    @Override
    public int calculate(final Graph graph, final Node ... nodes) {

        if ((graph == null) || (nodes == null) || (nodes.length == 0))
            throw new IllegalArgumentException("Invalid arguments for the algorithm.");

        int distanceAccumulator = 0;
        Node lastNode = null;

        for (final Node node : nodes) {
            if (lastNode != null) {
                final Integer distanceFromLast = graph.distance(lastNode, node);

                if (distanceFromLast == null)
                    throw new InvalidPath("Given path is not a valid one.");

                distanceAccumulator += distanceFromLast.intValue();
            }
            lastNode = node;
        }
        return distanceAccumulator;
    }

}
