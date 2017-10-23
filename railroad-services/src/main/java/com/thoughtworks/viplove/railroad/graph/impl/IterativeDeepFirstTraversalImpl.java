package com.thoughtworks.viplove.railroad.graph.impl;

import java.util.Set;
import java.util.Stack;

import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.domain.Graph;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 * Implementation of the interface of the deep first traversal computation methods.
 * @author vigujrat
 *
 */
public class IterativeDeepFirstTraversalImpl implements DeepFirstTraversal {

    @Override
    public int countTotalPathsThroughEachStep(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxSteps) {

        int countAccumulator = 0;

        final TraversalStep firstStep = new TraversalStep(source, 0);
        final Stack<TraversalStep> stack = new Stack<TraversalStep>();

        stack.push(firstStep);

        while (! stack.isEmpty()) {
            final TraversalStep step = stack.pop();

            if ((step.getNode().equals(target)) && (step.getStepAccumulator() > 0))
                countAccumulator++;

            if (step.getStepAccumulator() >= maxSteps)
                continue;

            final Set<Node> neighbors = graph.neighbors(step.getNode());
            if ((neighbors == null) || (neighbors.isEmpty()))
                continue;

            for (final Node neighbor : neighbors)
                stack.push(new TraversalStep(neighbor, step.getStepAccumulator() +1));
        }
        return countAccumulator;
    }

    @Override
    public int countTotalPathsInTheLastStep(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxSteps) {

        int countAccumulator = 0;

        final TraversalStep firstStep = new TraversalStep(source, 0);
        final Stack<TraversalStep> stack = new Stack<TraversalStep>();

        stack.push(firstStep);

        while (! stack.isEmpty()) {
            final TraversalStep step = stack.pop();

            if (step.getStepAccumulator() == maxSteps) {
                if (step.getNode().equals(target))
                    countAccumulator++;
                continue;
            }

            final Set<Node> neighbors = graph.neighbors(step.getNode());
            if ((neighbors == null) || (neighbors.isEmpty()))
                continue;

            for (final Node neighbor : neighbors)
                stack.push(new TraversalStep(neighbor, step.getStepAccumulator() +1));
        }
        return countAccumulator;
    }

    @Override
    public int countTotalPathsWithLessThanMaxDistance(
            final Graph graph,
            final Node source,
            final Node target,
            final int maxDistance) {

        int countAccumulator = 0;

        final TraversalStep firstStep = new TraversalStep(source, 0);
        final Stack<TraversalStep> stack = new Stack<TraversalStep>();

        stack.push(firstStep);

        while (! stack.isEmpty()) {
            final TraversalStep step = stack.pop();

            if (step.getStepAccumulator() >= maxDistance)
                continue;

            if ((step.getNode().equals(target)) && (step.getStepAccumulator() > 0))
                countAccumulator++;

            final Set<Node> neighbors = graph.neighbors(step.getNode());
            if ((neighbors == null) || (neighbors.isEmpty()))
                continue;

            for (final Node neighbor : neighbors) {
                final int neighborAccDistance = step.getStepAccumulator() + graph.distance(step.getNode(), neighbor);

                stack.push(new TraversalStep(neighbor, neighborAccDistance));
            }
        }
        return countAccumulator;
    }

    class TraversalStep {

        private final Node node;
        private final int stepAccumulator;

        public TraversalStep(Node node, int stepAccumulator) {
            this.node = node;
            this.stepAccumulator = stepAccumulator;
        }

        public Node getNode() {
            return node;
        }

        public int getStepAccumulator() {
            return stepAccumulator;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((node == null) ? 0 : node.hashCode());
            result = prime * result + stepAccumulator;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof TraversalStep))
                return false;
            TraversalStep other = (TraversalStep) obj;
            if (node == null) {
                if (other.node != null)
                    return false;
            } else if (!node.equals(other.node))
                return false;
            if (stepAccumulator != other.stepAccumulator)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "TraversalStep [node=" + node + ", stepAccumulator=" + stepAccumulator + "]";
        }

    }

}
