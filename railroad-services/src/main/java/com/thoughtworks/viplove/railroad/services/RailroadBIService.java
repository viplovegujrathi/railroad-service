package com.thoughtworks.viplove.railroad.services;

import java.util.List;

import com.thoughtworks.viplove.railroad.domain.Railroad;

/**
 * Interface of a railroad business intelligence system.
 * @author vigujrat
 *
 */
public interface RailroadBIService {

    List<String> buildAnswers(Railroad railroad);

}