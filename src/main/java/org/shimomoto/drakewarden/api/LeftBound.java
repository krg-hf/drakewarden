package org.shimomoto.drakewarden.api;

public interface LeftBound<T extends Comparable<T>> extends Range<T> {
	// comment
	T getLeft();
	boolean isLeftClosed();
	default boolean isLeftOpen(){return !isLeftClosed();}
}
