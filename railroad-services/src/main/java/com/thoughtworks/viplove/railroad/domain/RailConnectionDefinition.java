package com.thoughtworks.viplove.railroad.domain;

/**
 * Basic railroad connection definition parsed from the input received.
 * @author vigujrat
 *
 */
public class RailConnectionDefinition {

    private final char sourceTown;
    private final char destinationTown;
    private final int distance;

    public RailConnectionDefinition(char sourceTown, char destinationTown, int distance) {
        this.sourceTown = sourceTown;
        this.destinationTown = destinationTown;
        this.distance = distance;
    }

    public char getSourceTown() {
        return sourceTown;
    }

    public char getDestinationTown() {
        return destinationTown;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + destinationTown;
        result = prime * result + distance;
        result = prime * result + sourceTown;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof RailConnectionDefinition))
            return false;
        RailConnectionDefinition other = (RailConnectionDefinition) obj;
        if (destinationTown != other.destinationTown)
            return false;
        if (distance != other.distance)
            return false;
        if (sourceTown != other.sourceTown)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RailConnectionDefinition [sourceTown=" + sourceTown + ", destinationTown=" + destinationTown
                + ", distance=" + distance + "]";
    }

}
