package com.thoughtworks.viplove.railroad.graph.domain;

/**
 * Interface of a directed edge between two nodes
 * @author vigujrat
 *
 */
public interface Edge {

    Node from();

    Node to();

}
