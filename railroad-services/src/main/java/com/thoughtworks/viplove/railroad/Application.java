package com.thoughtworks.viplove.railroad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.thoughtworks.viplove.railroad.business.RailroadBuilder;
import com.thoughtworks.viplove.railroad.business.RailroadParser;
import com.thoughtworks.viplove.railroad.business.impl.RailroadBuilderImpl;
import com.thoughtworks.viplove.railroad.business.impl.RailroadParserImpl;
import com.thoughtworks.viplove.railroad.domain.RailConnectionDefinition;
import com.thoughtworks.viplove.railroad.domain.Railroad;
import com.thoughtworks.viplove.railroad.graph.DeepFirstTraversal;
import com.thoughtworks.viplove.railroad.graph.DijkstraCalculator;
import com.thoughtworks.viplove.railroad.graph.PathDistanceCalculator;
import com.thoughtworks.viplove.railroad.graph.impl.DijkstraCalculatorImpl;
import com.thoughtworks.viplove.railroad.graph.impl.IterativeDeepFirstTraversalImpl;
import com.thoughtworks.viplove.railroad.graph.impl.PathDistanceCalculatorImpl;
import com.thoughtworks.viplove.railroad.services.RailroadBIService;
import com.thoughtworks.viplove.railroad.services.RailroadService;
import com.thoughtworks.viplove.railroad.services.impl.RailroadBIServiceImpl;
import com.thoughtworks.viplove.railroad.services.impl.RailroadServiceImpl;

/**
 * Main class responsible to handle direct invocations to the services provided.
 * 
 * @author vigujrat
 */
public class Application {

    /**
     * This method will initiate the program
     * 
     * @param args
     */
    public static void main(final String args[]) {
        final Application railroadMainService = new Application();
        final List<String> answers = railroadMainService.processInput(args);
        for (final String answer : answers) {
            System.out.println(answer);
        }
    }

    /**
     * This method will process received input
     * 
     * @param args
     * @return
     */
    protected List<String> processInput(final String args[]) {

        final Scanner inputScanner = scaneInput(args);
        final String graphDefinition = inputScanner.nextLine();
        inputScanner.close();

        final RailroadParser railroadParser = new RailroadParserImpl();
        final Set<RailConnectionDefinition> railConnectionDefinitions = railroadParser.parse(graphDefinition);

        final RailroadBuilder railroadBuilder = new RailroadBuilderImpl();
        final Railroad railroad = railroadBuilder.build(railConnectionDefinitions);

        final RailroadBIService railroadBIService = buildRailroadBIService();

        final List<String> answers = railroadBIService.buildAnswers(railroad);

        return answers;
    }

    /**
     * This method will calculate and will build Rail Route
     * 
     * @return
     */
    private RailroadBIService buildRailroadBIService() {
        final DeepFirstTraversal deepFirstTraversal = new IterativeDeepFirstTraversalImpl();
        final DijkstraCalculator dijkstraCalculator = new DijkstraCalculatorImpl();
        final PathDistanceCalculator pathDistanceCalculator = new PathDistanceCalculatorImpl();

        return new RailroadBIServiceImpl(new RailroadServiceImpl(deepFirstTraversal, dijkstraCalculator,
                pathDistanceCalculator));
    }

    /**
     * This method will validate received input
     * 
     * @param args
     * @return
     */
    private Scanner scaneInput(final String args[]) {

        if (null == args) {
            throw new IllegalArgumentException("No input found");
        } else if (args.length > 1) {
            throw new IllegalArgumentException(
                    "Too many parameters. We need a single file path or none (input through 'system in').");
        } else if ((null !=args) && (1==args.length)) {

            try {
                return new Scanner(new FileInputStream(args[0]));

            } catch (FileNotFoundException exception) {
                throw new IllegalArgumentException("The parameter received does not correspond to a valid file path.");
            }
        }
        return new Scanner(System.in);
    }

}
