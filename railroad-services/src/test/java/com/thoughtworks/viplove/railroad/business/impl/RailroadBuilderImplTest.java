package com.thoughtworks.viplove.railroad.business.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.thoughtworks.viplove.railroad.business.RailroadBuilder;
import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;
import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.graph.mockprovider.RailroadInstanceProvider;

/**
 * Unit tests of the class {@link RailroadBuilderImpl}.
 * @author vigujrat
 *
 */
public class RailroadBuilderImplTest {

    @Test
    public void shouldBuildGraphCorrectly() {
        // given
        final RailroadInstanceProvider provider = new RailroadInstanceProvider();
        final Set<RailConnectionDefinition> providedConnectionDefinitions = provider.buildRailConnectionDefinitions();
        final RailroadBuilder subject = new RailroadBuilderImpl();

        // when
        final Railroad railroad = subject.build(providedConnectionDefinitions);

        // then
        assertEquals(provider.buildTestRailRoad(), railroad);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldNotBuildGraphWithEmptyDefinitions() {
        // given
        final RailroadBuilder subject = new RailroadBuilderImpl();

        // when then
        subject.build(new HashSet<RailConnectionDefinition>());
    }

}
