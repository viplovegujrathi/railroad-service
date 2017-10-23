package com.thoughtworks.viplove.railroad.domain;

import com.thoughtworks.viplove.railroad.graph.domain.Node;



/**
 * Entity that represents a town connected in the railroad, a node in the graph.
 * @author vigujrat
 *
 */
public class Town implements Node {

    private final String name;

    public Town(final String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Town))
            return false;
        Town other = (Town) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Town [name=" + name + "]";
    }

}
