package com.thoughtworks.viplove.railroad.business;

import java.util.Set;

import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;

/**
 * Interface of a parser of the string containing the graph definition of the railroad.
 * @author vigujrat
 *
 */
public interface RailroadParser {

    /**
     * This method will parse recieved input.
     * @param graphDefinition
     * @return
     */
    Set<RailConnectionDefinition> parse(String graphDefinition);

}
