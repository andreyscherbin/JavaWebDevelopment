package com.epam.andreyshcherbin.observer;

public interface Observable {	
	void attach(ShapeObserver observer);
	void detach(ShapeObserver observer);
	void notifyObservers();
}
