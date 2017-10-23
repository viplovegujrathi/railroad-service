package com.thoughtworks.viplove.railroad.services.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;
import com.thoughtworks.viplove.railroad.services.RailroadBIService;
import com.thoughtworks.viplove.railroad.services.RailroadService;
import com.thoughtworks.viplove.railroad.services.mockprovider.RailroadServiceMock;

/**
 * Unit tests of the class {@link RailroadBIServiceImpl}.
 * @author vigujrat
 *
 */
public class RailroadBIServiceImplTest {

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

        final RailroadService railroadService = new RailroadServiceMock();
        final RailroadBIService subject = new RailroadBIServiceImpl(railroadService);

        // when
        final List<String> answers = subject.buildAnswers(railroad);

        // then
        assertEquals("Output #1: 3", answers.get(0));
        assertEquals("Output #2: 2", answers.get(1));
        assertEquals("Output #3: 3", answers.get(2));
        assertEquals("Output #4: 5", answers.get(3));
        assertEquals("Output #5: 3", answers.get(4));
        assertEquals("Output #6: 1", answers.get(5));
        assertEquals("Output #7: 2", answers.get(6));
        assertEquals("Output #8: 9", answers.get(7));
        assertEquals("Output #9: 9", answers.get(8));
        assertEquals("Output #10: 3", answers.get(9));
    }

}
