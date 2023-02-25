package ru.geekbrains.winter.market.core.email;

import org.springframework.stereotype.Service;

@Service
public class Test implements Listner{


    @Override
    public void handleEvent() {
        System.out.println("Test!!!");
    }
}
