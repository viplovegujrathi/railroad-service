package com.thoughtworks.viplove.railroad.business.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import com.thoughtworks.viplove.railroad.business.RailroadParser;
import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;

/**
 * Parser of the string containing the graph definition of the railroad.
 * 
 * @author vigujrat
 */
public class RailroadParserImpl implements RailroadParser {

    /*
     * (non-Javadoc)
     * @see com.thoughtworks.viplove.railroad.business.RailroadParser#parse(java.lang.String)
     */
    @Override
    public Set<RailConnectionDefinition> parse(final String graphDefinition) {

        validateGraphDefinition(graphDefinition);

        final String edgesDefinition = graphDefinition.replaceFirst("Graph:", "");
        final StringTokenizer edgesTokenizer = new StringTokenizer(edgesDefinition, ",");

        if (0 == edgesTokenizer.countTokens()) {
            throw new IllegalArgumentException("No edges found in the graph definition received.");
        }

        final Set<RailConnectionDefinition> connectionsDefinitions = new HashSet<RailConnectionDefinition>();

        while (edgesTokenizer.hasMoreTokens()) {

            final RailConnectionDefinition connectionDefinition =
                    parseRailConnectionDefinition(edgesTokenizer.nextToken().trim());
            connectionsDefinitions.add(connectionDefinition);
        }
        return connectionsDefinitions;
    }

    /**
     * @param edgeDefinition
     * @return
     */
    private RailConnectionDefinition parseRailConnectionDefinition(final String edgeDefinition) {

        if (edgeDefinition.length() < 3) {
            throw new IllegalArgumentException("Invalid edge definition received.");
        }

        final char sourceTown = edgeDefinition.charAt(0);
        final char destinationTown = edgeDefinition.charAt(1);
        final int distance = Integer.parseInt(edgeDefinition.substring(2));

        return new RailConnectionDefinition(sourceTown, destinationTown, distance);
    }

    /**
     * Validates the input
     * 
     * @param graphDefinition
     */
    private void validateGraphDefinition(final String graphDefinition) {

        if ((null == graphDefinition) || (graphDefinition.isEmpty())) {
            throw new IllegalArgumentException("Empty graph definition received.");
        } else if (!graphDefinition.startsWith("Graph:", 0)) {
            throw new IllegalArgumentException("Wrong format of graph definition received.");
        }
    }

}
