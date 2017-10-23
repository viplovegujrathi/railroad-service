package com.thoughtworks.viplove.railroad.business;

import java.util.Set;

import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;
import com.thoughtworks.viplove.railroad.domain.Railroad;

/**
 * Interface of a builder of a {@link Railroad} instance from the {@link RailConnectionDefinition}.
 * @author vigujrat
 *
 */
public interface RailroadBuilder {

    /**
     * Once connection defination is available tis method will build the route
     * 
     * @param connectionsDefinitions
     * @return
     */
    Railroad build(Set<RailConnectionDefinition> connectionsDefinitions);

}
