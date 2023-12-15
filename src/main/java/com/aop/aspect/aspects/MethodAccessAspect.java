package com.aop.aspect.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aop.aspect.dtos.CourseCreateDTO;
import com.aop.aspect.utils.JsonUtils;

@Aspect
@Component
public class MethodAccessAspect {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* com.aop.aspect.api.*.*(..))")
	private void courseControllerMethods() {
	}

	@Before("execution(* com.aop.aspect.api.*.*(..))")
	public void beforeExecution(JoinPoint joinpoint) {
		logger.info(joinpoint.getSignature().getName() + " Request...");
	}

	@Before("execution(* com.aop.aspect.api.*.*(..)) && args(courseDTO)")
	public void beforeExecutionWithArg(CourseCreateDTO courseDTO) {
		logger.info("Create Value for method: {}", JsonUtils.prettyJSON(courseDTO));
	}

	@After("courseControllerMethods()")
	public void afterExecution(JoinPoint joinpoint) {
		logger.info(joinpoint.getSignature().getName() + " Response...");
	}

	@AfterReturning("execution(* com.aop.aspect.api.*.*(..))")
	public void afterReturningExecution(JoinPoint joinpoint) {
		logger.info(joinpoint.getSignature().getName() + " Result.....");
	}

	@AfterThrowing("execution(* com.aop.aspect.api.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) {
		logger.info("Invalid Argument: " + joinPoint.getArgs());
		throw new RuntimeException("Fail to " + joinPoint.getSignature().getName());
	}

	@Around("execution(* com.aop.aspect.api.*.*(..))")
	public Object aroundInvocation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		try {

			// Before execution
			logger.info("Before Api");

			Object result = proceedingJoinPoint.proceed();
			logger.info("Proceed Method Result: {}", JsonUtils.prettyJSON(result));
			logger.info("After Api Returning");

			// After execution

			return result;
		} catch (Exception e) {
			// After throwing
			logger.info("After Api Throwing Exception.");
			throw new RuntimeException(e);

		} finally {
			logger.info("After All Done");
			// After finally
		}
	}

}
