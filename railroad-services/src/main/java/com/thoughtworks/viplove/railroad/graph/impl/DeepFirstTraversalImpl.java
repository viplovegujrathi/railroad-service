package com.thoughtworks.viplove.railroad.graph.impl;

import java.util.Set;

import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 * Implementation of the interface of the deep first traversal computation methods.
 * @author vigujrat
 *
 */
public class DeepFirstTraversalImpl implements DeepFirstTraversal {


    @Override
    public int countTotalPathsThroughEachStep(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxSteps) {

        int countAccumulator = 0;

        for (int step = 1; step <= maxSteps; step++) {
            countAccumulator += countPaths(graph, source, target, step, 0);
        }
        return countAccumulator;
    }

    @Override
    public int countTotalPathsInTheLastStep(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxSteps) {

        return countPaths(graph, source, target, maxSteps, 0);
    }

    private int countPaths(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxSteps,
            final int currentStep) {

        int countAccumulator = 0;

        if ((maxSteps == currentStep) && (source.equals(target)))
            return 1;

        if ((maxSteps == currentStep) && (! source.equals(target)))
            return 0;

        final Set<Node> neighbors = graph.neighbors(source);

        if ((neighbors == null) || (neighbors.isEmpty()))
            return 0;

        for (final Node node : neighbors)
            countAccumulator += countPaths(graph, node, target, maxSteps, currentStep +1);

        return countAccumulator;
    }

    @Override
    public int countTotalPathsWithLessThanMaxDistance(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxDistance) {

        return countPathsUntilMaxDistance(graph, source, target, maxDistance, 0);
    }

    private int countPathsUntilMaxDistance(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxDistance,
            final int currentDistance) {

        int countAccumulator = 0;

        if (currentDistance >= maxDistance)
            return 0;

        if ((currentDistance > 0) && (currentDistance < maxDistance) && (source.equals(target)))
            countAccumulator++;

        final Set<Node> neighbors = graph.neighbors(source);
        if ((neighbors == null) || (neighbors.isEmpty()))
            return countAccumulator;

        for (final Node neighbor : neighbors) {
            final int neighborAccumulatedDistance = currentDistance + graph.distance(source, neighbor);

            countAccumulator += countPathsUntilMaxDistance(
                    graph,
                    neighbor,
                    target,
                    maxDistance,
                    neighborAccumulatedDistance);
        }
        return countAccumulator;
    }

}
