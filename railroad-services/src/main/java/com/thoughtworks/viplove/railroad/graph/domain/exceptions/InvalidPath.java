package com.thoughtworks.viplove.railroad.graph.domain.exceptions;

/**
 * Runtime exception to indicate an invalid path.
 * @author vigujrat
 *
 */
public class InvalidPath extends RuntimeException {

    private static final long serialVersionUID = 3670824471853278700L;

    public InvalidPath() {
        super();
    }

    public InvalidPath(String s) {
        super(s);
    }

    public InvalidPath(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidPath(Throwable throwable) {
        super(throwable);
    }

}
