package pl.jakpoliczyc.aspect;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ExecutionTimeLoggingAOP implements MethodBeforeAdvice, AfterReturningAdvice {

    private long startTime = 0;

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        startTime = System.nanoTime();
    }

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        long elapsedTime = System.nanoTime() - startTime;
        String className = target.getClass().getCanonicalName();
        String methodName = method.getName();
        System.out.printf("Execution of %s#%s ended in %d\n", className, methodName, TimeUnit.NANOSECONDS.toMillis(elapsedTime));
    }

}
