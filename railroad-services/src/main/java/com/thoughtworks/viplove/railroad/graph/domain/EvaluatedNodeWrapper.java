package com.thoughtworks.viplove.railroad.graph.domain;


/**
 * A simple wrapper to add distance information to a {@link Node} object.
 *
 * NOTE that the attribute distance is not used in the 'equals' and in
 * the 'hashCode' methods, only in the 'compareTo' method.
 *
 * @author vigujrat
 *
 */
public class EvaluatedNodeWrapper implements Node, Comparable<EvaluatedNodeWrapper> {

    private final Node node;
    private final int distance;

    public EvaluatedNodeWrapper(final Node node, final int distance) {
        this.node = node;
        this.distance = distance;
    }

    @Override
    public String name() {
        return node.name();
    }

    public Node node() {
        return node;
    }

    public int distance() {
        return distance;
    }

    @Override
    public int compareTo(final EvaluatedNodeWrapper other) {

        if (this.node.equals(other.node))
            return 0;

        if (other.distance() > this.distance())
            return -1;

        return 1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EvaluatedNodeWrapper other = (EvaluatedNodeWrapper) obj;
        if (node == null) {
            if (other.node != null)
                return false;
        } else if (!node.equals(other.node))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EvaluatedNodeWrapper [node=" + node + ", distance=" + distance + "]";
    }

}
