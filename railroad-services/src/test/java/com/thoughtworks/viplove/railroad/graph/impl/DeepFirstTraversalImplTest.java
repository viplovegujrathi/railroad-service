package com.thoughtworks.viplove.railroad.graph.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.domain.Node;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;

/**
 * Unit test class of {@link DeepFirstTraversalImpl}.
 * 
 * @author vigujrat
 */
public class DeepFirstTraversalImplTest {

    private Node nodeA;

    private Node nodeB;

    private Node nodeC;

    private Node nodeD;

    private Node nodeE;

    private RailroadInstanceProvider provider;

    private Railroad railroad;

    private DeepFirstTraversal subject;

    @Before
    public void beforeTest() {
        nodeA = new Town("A");
        nodeB = new Town("B");
        nodeC = new Town("C");
        nodeD = new Town("D");
        nodeE = new Town("E");
        provider = new RailroadInstanceProvider();
        railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
        subject = new DeepFirstTraversalImpl();
    }

    @Test
    public void shouldCountTotalPathsThroughEachStep() {
        assertEquals(2, subject.countTotalPathsThroughEachStep(railroad, nodeC, nodeC, 3));
        assertEquals(3, subject.countTotalPathsThroughEachStep(railroad, nodeC, nodeD, 4));
        assertEquals(4, subject.countTotalPathsThroughEachStep(railroad, nodeC, nodeC, 4));
    }

    @Test
    public void shouldCountTotalPathsInTheLastStep() {
        assertEquals(3, subject.countTotalPathsInTheLastStep(railroad, nodeA, nodeC, 4));
        assertEquals(4, subject.countTotalPathsInTheLastStep(railroad, nodeA, nodeE, 5));
    }

    @Test
    public void shouldCountPathsUntilMaxDistance() {
        assertEquals(7, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeC, nodeC, 30));
        assertEquals(7, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeB, nodeE, 30));
        assertEquals(3, subject.countTotalPathsWithLessThanMaxDistance(railroad, nodeB, nodeD, 30));
    }
}
