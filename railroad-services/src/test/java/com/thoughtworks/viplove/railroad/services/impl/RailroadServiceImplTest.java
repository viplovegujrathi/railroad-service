package com.thoughtworks.viplove.railroad.services.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.DijkstraCalculator;
import com.thoughtworks.viplove.railroad.graph.PathDistanceCalculator;
import com.thoughtworks.viplove.railroad.graph.mockprovider.DeepFirstTraversalMock;
import com.thoughtworks.viplove.railroad.graph.mockprovider.DijkstraCalculatorMock;
import com.thoughtworks.viplove.railroad.graph.mockprovider.PathDistanceCalculatorMock;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;
import com.thoughtworks.viplove.railroad.services.RailroadService;

/**
 * Unit tests of the class {@link RailroadServiceImpl}.
 * @author vigujrat
 *
 */
public class RailroadServiceImplTest {

    @Test
    public void shouldInvokeDependenciesCorrectly() {
        // given
        final Town nodeA = new Town("A");
        final Town nodeB = new Town("B");
        final Town nodeC = new Town("C");
        final Town nodeD = new Town("D");
        final Town nodeE = new Town("E");

        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Railroad railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);

        final RailroadService subject = buildRailroadServiceSubjectWithMockedDeps();

        // when then
        assertEquals(3, subject.calculatePathDistance(railroad, nodeA, nodeB, nodeC));
        assertEquals(4, subject.calculateShortestRouteDistance(railroad, nodeC, nodeD));
        assertEquals(1, subject.countTotalPathsThroughEachStep(railroad, nodeA, nodeB, 4));
        assertEquals(2, subject.countTotalPathsInTheLastStep(railroad, nodeC, nodeD, 4));
        assertEquals(3, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeE, nodeA, 4));
    }

    private RailroadService buildRailroadServiceSubjectWithMockedDeps() {

        final DeepFirstTraversal deepFirstTraversal = new DeepFirstTraversalMock();
        final DijkstraCalculator dijkstraCalculator = new DijkstraCalculatorMock();
        final PathDistanceCalculator pathDistanceCalculator = new PathDistanceCalculatorMock();

        final RailroadService subject = new RailroadServiceImpl(
                deepFirstTraversal,
                dijkstraCalculator,
                pathDistanceCalculator);

        return subject;
    }

}
