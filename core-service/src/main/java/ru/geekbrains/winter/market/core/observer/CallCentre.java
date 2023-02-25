package ru.geekbrains.winter.market.core.observer;


import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.core.email.Listner;
import java.util.ArrayList;
import java.util.List;


@Component
public class CallCentre implements Observed {

    List<Listner> observers;

    public CallCentre(List<Listner> observers) {
        this.observers = observers;
    }


    @Override
    public void addObserver(Listner o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Listner o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Listner o: observers){
            o.handleEvent();
        }
    }


}
