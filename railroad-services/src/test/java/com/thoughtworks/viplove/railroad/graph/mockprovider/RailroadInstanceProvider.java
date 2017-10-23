package com.thoughtworks.viplove.railroad.graph.mockprovider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.viplove.railroad.domain.RailConnection;
import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;
import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.domain.Edge;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 * Provider of a test instance of the {@link Railroad}.
 * @author vigujrat
 *
 */
public class RailroadInstanceProvider {

    public Set<RailConnectionDefinition> buildRailConnectionDefinitions() {
        final Set<RailConnectionDefinition> definitions = new HashSet<RailConnectionDefinition>();

        definitions.add(new RailConnectionDefinition('A', 'B', 5));
        definitions.add(new RailConnectionDefinition('B', 'C', 4));
        definitions.add(new RailConnectionDefinition('C', 'D', 8));
        definitions.add(new RailConnectionDefinition('D', 'C', 8));
        definitions.add(new RailConnectionDefinition('D', 'E', 6));
        definitions.add(new RailConnectionDefinition('A', 'D', 5));
        definitions.add(new RailConnectionDefinition('C', 'E', 2));
        definitions.add(new RailConnectionDefinition('E', 'B', 3));
        definitions.add(new RailConnectionDefinition('A', 'E', 7));

        return definitions;
    }

    public Railroad buildTestRailRoad() {
        final Node nodeA = new Town("A");
        final Node nodeB = new Town("B");
        final Node nodeC = new Town("C");
        final Node nodeD = new Town("D");
        final Node nodeE = new Town("E");

        return buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
    }

    public Railroad buildTestRailRoad(
            final Node nodeA,
            final Node nodeB,
            final Node nodeC,
            final Node nodeD,
            final Node nodeE) {

        final Map<Edge, Integer> distances = new HashMap<Edge, Integer>();
        final Map<Node, Set<Node>> adjacents = new HashMap<Node, Set<Node>>();

        final Set<Node> nodeAAdjacents = new HashSet<Node>();
        final Set<Node> nodeBAdjacents = new HashSet<Node>();
        final Set<Node> nodeCAdjacents = new HashSet<Node>();
        final Set<Node> nodeDAdjacents = new HashSet<Node>();
        final Set<Node> nodeEAdjacents = new HashSet<Node>();

        nodeAAdjacents.add(nodeB);
        nodeAAdjacents.add(nodeD);
        nodeAAdjacents.add(nodeE);

        nodeBAdjacents.add(nodeC);

        nodeCAdjacents.add(nodeD);
        nodeCAdjacents.add(nodeE);

        nodeDAdjacents.add(nodeC);
        nodeDAdjacents.add(nodeE);

        nodeEAdjacents.add(nodeB);

        adjacents.put(nodeA, nodeAAdjacents);
        adjacents.put(nodeB, nodeBAdjacents);
        adjacents.put(nodeC, nodeCAdjacents);
        adjacents.put(nodeD, nodeDAdjacents);
        adjacents.put(nodeE, nodeEAdjacents);

        distances.put(new RailConnection(nodeA, nodeB), Integer.valueOf(5));
        distances.put(new RailConnection(nodeA, nodeD), Integer.valueOf(5));
        distances.put(new RailConnection(nodeA, nodeE), Integer.valueOf(7));

        distances.put(new RailConnection(nodeB, nodeC), Integer.valueOf(4));

        distances.put(new RailConnection(nodeC, nodeD), Integer.valueOf(8));
        distances.put(new RailConnection(nodeC, nodeE), Integer.valueOf(2));

        distances.put(new RailConnection(nodeD, nodeC), Integer.valueOf(8));
        distances.put(new RailConnection(nodeD, nodeE), Integer.valueOf(6));

        distances.put(new RailConnection(nodeE, nodeB), Integer.valueOf(3));

        final Set<Node> nodes = new HashSet<Node>();

        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);
        nodes.add(nodeD);
        nodes.add(nodeE);

        final Railroad railroad = new Railroad(distances, adjacents, nodes);

        return railroad;
    }

}
