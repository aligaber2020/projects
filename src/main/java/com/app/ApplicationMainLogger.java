package com.app;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationMainLogger
{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMainLogger.class);


    @Before("execution(* com.app.controller..*(..)) || " + "execution(* com.app.service..*(..)) || "
        + "execution(* com.app.dataaccessobject..*(..))")
    public void logStartOfMethod(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        String logMsg = "Class:" + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", Method:" + joinPoint.getSignature().getName()
            + ", Params:";
        try
        {
            for (Object arg : args)
                logMsg += ("," + arg.toString());
        }
        catch (Exception ex)
        {
            logMsg = ",No Params ";
        }
        logger.info(logMsg + Arrays.deepToString(args));
    }


    @AfterThrowing(pointcut = "execution(* com.app.controller..*(..)) || "
        + "execution(* com.app.service..*(..)) || " + "execution(* com.app.dataaccessobject..*(..)) ", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception)
    {
        String logMsg = "Class:" + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", Failed Method : "
            + joinPoint.getSignature().getName() + ", Casue :" + exception.getMessage();
        logger.error(logMsg, exception);
    }


    @AfterReturning(pointcut = "execution(* com.app.controller..*(..)) || "
        + "execution(* com.app.service..*(..)) || " + "execution(* om.app.dataaccessobject..*(..))", returning = "response")
    public void afterReturning(JoinPoint joinPoint)
    {
        String logMsg = "";
        try
        {
            logMsg = "Class:" + joinPoint.getSignature().getDeclaringType().getSimpleName() + ", Method : " + joinPoint.getSignature().getName();
        }
        catch (Exception ex)
        {
            logMsg = "no return ";
        }
        logger.info(logMsg);
    }
}
