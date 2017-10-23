package com.thoughtworks.viplove.railroad.graph.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.thoughtworks.viplove.railroad.graph.DijkstraCalculator;
import com.thoughtworks.viplove.railroad.graph.domain.EvaluatedNodeWrapper;
import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 *  Dijkstra's algorithm implementation using a priority queue based on
 *  https://en.wikipedia.org/wiki/Dijkstra's_algorithm
 * @author vigujrat
 *
 */
public class DijkstraCalculatorImpl implements DijkstraCalculator {

    @Override
    public Map<Node, Integer> calculate(final Graph graph, final Node source) {

        if ((graph == null) || (source == null))
            throw new IllegalArgumentException("Invalid null arguments for the algorithm.");

        final Map<Node, Integer> shortestDistances = new HashMap<Node, Integer>();
        final Map<Node, EvaluatedNodeWrapper> evaluatedNodesMap = new HashMap<Node, EvaluatedNodeWrapper>();
        final PriorityQueue<EvaluatedNodeWrapper> priorityQueue = new PriorityQueue<EvaluatedNodeWrapper>();

        shortestDistances.put(source, NO_DISTANCE);

        final EvaluatedNodeWrapper evaluatedSourceNode = new EvaluatedNodeWrapper(source, NO_DISTANCE);
        evaluatedNodesMap.put(source, evaluatedSourceNode);
        priorityQueue.add(evaluatedSourceNode);

        for (final Node node : graph.nodes()) {
            if (! node.equals(source)) {

                final EvaluatedNodeWrapper evaluatedNode = new EvaluatedNodeWrapper(node, INFINITE_DISTANCE);

                evaluatedNodesMap.put(node, evaluatedNode);
                shortestDistances.put(node, INFINITE_DISTANCE);
                priorityQueue.add(evaluatedNode);
            }
        }

        while (! priorityQueue.isEmpty()) {

            final EvaluatedNodeWrapper leastDistanceNode = priorityQueue.poll();

            if (leastDistanceNode.distance() == INFINITE_DISTANCE)
                break;

            final Set<Node> neighbors = graph.neighbors(leastDistanceNode.node());
            if ((neighbors == null) || (neighbors.isEmpty()))
                continue;

            for (final Node neighbor : neighbors) {

                final int currentNeighborDistance =  graph.distance(leastDistanceNode.node(), neighbor);
                final int newNeighborDistance = leastDistanceNode.distance() + currentNeighborDistance;

                final EvaluatedNodeWrapper evaluatedNeighborNode = evaluatedNodesMap.get(neighbor);

                if ((newNeighborDistance < evaluatedNeighborNode.distance())
                        || (evaluatedNeighborNode.distance() == NO_DISTANCE)) {

                    final EvaluatedNodeWrapper newNeighborEvaluation = new EvaluatedNodeWrapper(neighbor, newNeighborDistance);
                    evaluatedNodesMap.put(neighbor, newNeighborEvaluation);

                    final boolean neighborRemoved = priorityQueue.remove(evaluatedNeighborNode);
                    if (neighborRemoved)
                        priorityQueue.add(newNeighborEvaluation);

                    shortestDistances.put(neighbor, Integer.valueOf(newNeighborDistance));
                }
            }
        }
        return shortestDistances;
    }

}
