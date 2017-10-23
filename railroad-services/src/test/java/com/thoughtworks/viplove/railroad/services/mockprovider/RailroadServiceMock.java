package com.thoughtworks.viplove.railroad.services.mockprovider;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.services.RailroadService;

/**
 * Mock implementation of the interface {@link RailroadService}.
 * @author vigujrat
 *
 */
public class RailroadServiceMock implements RailroadService {

    @Override
    public int calculatePathDistance(Railroad railroad, Town... path) {
        return path.length;
    }

    @Override
    public int calculateShortestRouteDistance(Railroad railroad, Town from, Town to) {
        return 9;
    }

    @Override
    public int countTotalPathsThroughEachStep(Railroad railroad, Town from, Town to, int maxStops) {
        return 1;
    }

    @Override
    public int countTotalPathsInTheLastStep(Railroad railroad, Town from, Town to, int maxStops) {
        return 2;
    }

    @Override
    public int countTotalPathsWithLessThanMaxDistance(Railroad railroad, Town from, Town to, int maxDistance) {
        return 3;
    }

}
