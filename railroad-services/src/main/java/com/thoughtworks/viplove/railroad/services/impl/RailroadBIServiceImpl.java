package com.thoughtworks.viplove.railroad.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.domain.Town;
import com.thoughtworks.viplove.railroad.graph.domain.exceptions.InvalidPath;
import com.thoughtworks.viplove.railroad.services.RailroadBIService;
import com.thoughtworks.viplove.railroad.services.RailroadService;

/**
 * Business intelligence service of a railroad. Designed to answer some predefined questions.
 * @author vigujrat
 *
 */
public class RailroadBIServiceImpl implements RailroadBIService {

    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    private static final Town TOWN_A = new Town("A");
    private static final Town TOWN_B = new Town("B");
    private static final Town TOWN_C = new Town("C");
    private static final Town TOWN_D = new Town("D");
    private static final Town TOWN_E = new Town("E");


    private final RailroadService railroadService;

    public RailroadBIServiceImpl(RailroadService railroadService) {
        this.railroadService = railroadService;
    }

    @Override
    public List<String> buildAnswers(final Railroad railroad) {
        final List<String> answers = new ArrayList<String>();

        answers.add("Output #1: " + calculateDistanceRouteABC(railroad));
        answers.add("Output #2: " + calulateDistanceRouteAD(railroad));
        answers.add("Output #3: " + calulateDistanceRouteADC(railroad));
        answers.add("Output #4: " + calulateDistanceRouteAEBCD(railroad));
        answers.add("Output #5: " + calulateDistanceRouteAED(railroad));
        answers.add("Output #6: " + calculateNumberTripsFromCToCWithMax3Stops(railroad));
        answers.add("Output #7: " + calculateNumberTripFromAToCWith4Stops(railroad));
        answers.add("Output #8: " + calculateLengthShortestRouteFromAToC(railroad));
        answers.add("Output #9: " + calculateLengthShortestRouteFromBToB(railroad));
        answers.add("Output #10: " + calculateNumberDifferentRoutesFromCToCWithLessThan30Distance(railroad));

        return answers;
    }

    private String calculateDistanceRouteABC(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculatePathDistance(railroad, TOWN_A, TOWN_B, TOWN_C);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calulateDistanceRouteAD(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculatePathDistance(railroad, TOWN_A, TOWN_D);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calulateDistanceRouteADC(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculatePathDistance(railroad, TOWN_A, TOWN_D, TOWN_C);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calulateDistanceRouteAEBCD(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculatePathDistance(railroad, TOWN_A, TOWN_E, TOWN_B, TOWN_C, TOWN_D);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calulateDistanceRouteAED(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculatePathDistance(railroad, TOWN_A, TOWN_E, TOWN_D);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calculateNumberTripsFromCToCWithMax3Stops(final Railroad railroad) {

        final int paths = this.railroadService.countTotalPathsThroughEachStep(railroad, TOWN_C, TOWN_C, 3);

        return String.valueOf(paths);
    }

    private String calculateNumberTripFromAToCWith4Stops(final Railroad railroad) {

        final int paths = this.railroadService.countTotalPathsInTheLastStep(railroad, TOWN_A, TOWN_C, 4);

        return String.valueOf(paths);
    }

    private String calculateLengthShortestRouteFromAToC(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculateShortestRouteDistance(railroad, TOWN_A, TOWN_C);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calculateLengthShortestRouteFromBToB(final Railroad railroad) {

        try {
            final int distance = this.railroadService.calculateShortestRouteDistance(railroad, TOWN_B, TOWN_B);

            return String.valueOf(distance);

        } catch(InvalidPath exception) {
            return NO_SUCH_ROUTE;
        }
    }

    private String calculateNumberDifferentRoutesFromCToCWithLessThan30Distance(final Railroad railroad) {

        final int paths = this.railroadService.countTotalPathsWithLessThanMaxDistance(railroad, TOWN_C, TOWN_C, 30);

        return String.valueOf(paths);
    }
}
