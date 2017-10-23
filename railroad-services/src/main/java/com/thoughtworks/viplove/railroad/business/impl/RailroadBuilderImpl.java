package com.thoughtworks.viplove.railroad.business.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.viplove.railroad.business.RailroadBuilder;
import com.thoughtworks.viplove.railroad.domain.RailConnection;
import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;
import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.domain.Edge;
import com.thoughtworks.viplove.railroad.graph.domain.Node;

/**
 * Builder of a {@link Railroad} instance from the {@link RailConnectionDefinition} received from the input.
 * 
 * @author vigujrat
 */
public class RailroadBuilderImpl implements RailroadBuilder {

    /* (non-Javadoc)
     * @see com.thoughtworks.viplove.railroad.business.RailroadBuilder#build(java.util.Set)
     */
    @Override
    public Railroad build(final Set<RailConnectionDefinition> connectionsDefinitions) {

        if ((connectionsDefinitions == null) || (connectionsDefinitions.isEmpty())) {
            throw new IllegalArgumentException("Connections definitions not provided !");
        }
        final Map<Edge, Integer> distances = new HashMap<Edge, Integer>();
        final Map<Node, Set<Node>> adjacents = new HashMap<Node, Set<Node>>();
        final Set<Node> nodes = new HashSet<Node>();

        for (final RailConnectionDefinition connectionDefinition : connectionsDefinitions) {

            final Node sourceTown = new Town(String.valueOf(connectionDefinition.getSourceTown()));
            final Node destinationTown = new Town(String.valueOf(connectionDefinition.getDestinationTown()));

            nodes.add(sourceTown);
            nodes.add(destinationTown);

            final Edge townsConnection = new RailConnection(sourceTown, destinationTown);

            distances.put(townsConnection, Integer.valueOf(connectionDefinition.getDistance()));

            Set<Node> sourceTownAdjacents = adjacents.get(sourceTown);

            if (sourceTownAdjacents == null) {
                sourceTownAdjacents = new HashSet<Node>();
            }
            sourceTownAdjacents.add(destinationTown);
            adjacents.put(sourceTown, sourceTownAdjacents);
        }
        return new Railroad(distances, adjacents, nodes);
    }

}
