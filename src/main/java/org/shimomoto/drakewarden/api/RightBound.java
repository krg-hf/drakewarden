package org.shimomoto.drakewarden.api;

public interface RightBound<T extends Comparable<T>> extends Range<T> {
	// one comment
	T getRight();

	boolean isRightClosed();
	default boolean isRightOpen(){return !isRightClosed();}
}
