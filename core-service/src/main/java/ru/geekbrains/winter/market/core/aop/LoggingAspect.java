package ru.geekbrains.winter.market.core.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* ru.geekbrains.winter.market.core..*(..))") public void logBefore(JoinPoint joinPoint) {
        System.out.println(
                "Вызов метода : " + joinPoint.getSignature().getName()
        ); }
}
