package com.thoughtworks.viplove.railroad.graph.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.PathDistanceCalculator;
import com.thoughtworks.viplove.railroad.graph.domain.Node;
import com.thoughtworks.viplove.railroad.graph.domain.exceptions.InvalidPath;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;

/**
 * Unit test class of the {@link PathDistanceCalculatorImpl}.
 * @author vigujrat
 *
 */
public class PathDistanceCalculatorImplTest {

    @Test
    public void shouldCalculateRouteCosts() {
        // given
        final Node nodeA = new Town("A");
        final Node nodeB = new Town("B");
        final Node nodeC = new Town("C");
        final Node nodeD = new Town("D");
        final Node nodeE = new Town("E");

        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Railroad railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
        final PathDistanceCalculator subject = new PathDistanceCalculatorImpl();

        // when then
        assertEquals(9, subject.calculate(railroad, nodeA, nodeB, nodeC));
        assertEquals(5, subject.calculate(railroad, nodeA, nodeD));
        assertEquals(13, subject.calculate(railroad, nodeA, nodeD, nodeC));
        assertEquals(22, subject.calculate(railroad, nodeA, nodeE, nodeB, nodeC, nodeD));
    }

    @Test(expected=InvalidPath.class)
    public void shouldDetectInvalidRoute() {
        // given
        final Node nodeA = new Town("A");
        final Node nodeB = new Town("B");
        final Node nodeC = new Town("C");
        final Node nodeD = new Town("D");
        final Node nodeE = new Town("E");

        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Railroad railroad = provider.buildTestRailRoad(nodeA, nodeB, nodeC, nodeD, nodeE);
        final PathDistanceCalculator subject = new PathDistanceCalculatorImpl();

        // when then
        subject.calculate(railroad, nodeA, nodeE, nodeD);
    }
}
