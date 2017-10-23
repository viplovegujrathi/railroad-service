package com.thoughtworks.viplove.railroad.graph.domain;

import java.util.Set;


/**
 * Interface that defines a weighted directed graph object.
 * @author vigujrat
 *
 */
public interface Graph {

    Set<Node> nodes();

    Set<Node> neighbors(Node node);

    Integer distance(Node source, Node target);

}
