package com.thoughtworks.viplove.railroad.business.impl;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import com.thoughtworks.viplove.railroad.business.RailroadParser;
import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;

/**
 * Unit tests of the class {@link RailroadParserImpl}.
 * @author vigujrat
 *
 */
public class RailroadParserImplTest {

    @Test
    public void shouldParseGraphCorrectly() {
        // given
        final String graphDefinition = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Set<RailConnectionDefinition> providedConnectionDefinitions = provider.buildRailConnectionDefinitions();
        final RailroadParser subject = new RailroadParserImpl();

        // when
        final Set<RailConnectionDefinition> connectionsDefinitions = subject.parse(graphDefinition);

        // then
        assertEquals(providedConnectionDefinitions, connectionsDefinitions);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldNotParseWrongFormatedGraph() {
        // given
        final String graphDefinition = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        final RailroadParser subject = new RailroadParserImpl();

        // when then
        subject.parse(graphDefinition);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldNotParseInvalidGraph() {
        // given
        final String graphDefinition = "Graph: AB5, BC4, CDD8, DC8, DE6, AD5, CE2, EB3, AE7";
        final RailroadParser subject = new RailroadParserImpl();

        // when then
        subject.parse(graphDefinition);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldNotParseEmptyGraph() {
        // given
        final String graphDefinition = "Graph: ";
        final RailroadParser subject = new RailroadParserImpl();

        // when then
        subject.parse(graphDefinition);
    }

}
