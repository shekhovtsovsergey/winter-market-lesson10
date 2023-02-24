package ru.geekbrains.winter.market.core.observer;

import ru.geekbrains.winter.market.core.email.Listner;

public interface Observed {

    void addObserver(Listner o);
    void removeObserver(Listner o);
    void notifyObservers();

}
