package com.thoughtworks.viplove.railroad.services;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;

/**
 * Interface of a collection of railroad services available for invocation.
 * @author vigujrat
 *
 */
public interface RailroadService {

    int calculatePathDistance(Railroad railroad, Town... path);

    int calculateShortestRouteDistance(Railroad railroad, Town from, Town to);

    int countTotalPathsThroughEachStep(Railroad railroad, Town from, Town to, int maxStops);

    int countTotalPathsInTheLastStep(Railroad railroad, Town from, Town to, int maxStops);

    int countTotalPathsWithLessThanMaxDistance(Railroad railroad, Town from, Town to, int maxDistance);

}