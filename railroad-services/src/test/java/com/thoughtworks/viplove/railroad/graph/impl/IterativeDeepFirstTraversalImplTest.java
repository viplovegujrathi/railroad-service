package com.thoughtworks.viplove.railroad.graph.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.domain.Node;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;

/**
 * Unit test class of the {@link IterativeDeepFirstTraversalImpl}.
 * @author vigujrat
 *
 */
public class IterativeDeepFirstTraversalImplTest {


    @Test
    public void shouldCountTotalPathsThroughEachStep() {
        // given
        final Node nodeA = new Town("A");
        final Node nodeB = new Town("B");
        final Node nodeC = new Town("C");
        final Node nodeD = new Town("D");
        final Node nodeE = new Town("E");

        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Railroad railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
        final DeepFirstTraversal subject = new IterativeDeepFirstTraversalImpl();

        // when then
        assertEquals(2, subject.countTotalPathsThroughEachStep(railroad, nodeC, nodeC, 3));
        assertEquals(3, subject.countTotalPathsThroughEachStep(railroad, nodeC, nodeD, 4));
        assertEquals(4, subject.countTotalPathsThroughEachStep(railroad, nodeC, nodeC, 4));
    }

    @Test
    public void shouldCountTotalPathsInTheLastStep() {
        // given
        final Node nodeA = new Town("A");
        final Node nodeB = new Town("B");
        final Node nodeC = new Town("C");
        final Node nodeD = new Town("D");
        final Node nodeE = new Town("E");

        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Railroad railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
        final DeepFirstTraversal subject = new IterativeDeepFirstTraversalImpl();

        // when then
        assertEquals(3, subject.countTotalPathsInTheLastStep(railroad, nodeA, nodeC, 4));
        assertEquals(4, subject.countTotalPathsInTheLastStep(railroad, nodeA, nodeE, 5));
    }

    @Test
    public void shouldCountPathsUntilMaxDistance() {
        // given
        final Node nodeA = new Town("A");
        final Node nodeB = new Town("B");
        final Node nodeC = new Town("C");
        final Node nodeD = new Town("D");
        final Node nodeE = new Town("E");

        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Railroad railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
        final DeepFirstTraversal subject = new IterativeDeepFirstTraversalImpl();

        // when then
        assertEquals(7, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeC, nodeC, 30));
        assertEquals(7, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeB, nodeE, 30));
        assertEquals(3, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeB, nodeD, 30));
    }

}
