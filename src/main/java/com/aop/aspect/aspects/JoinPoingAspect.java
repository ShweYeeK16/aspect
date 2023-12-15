package com.aop.aspect.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.aop.aspect.domain.Courses;

@Aspect
public class JoinPoingAspect {

	@Before("execution(public * com.example.myapp.service.*.*(..))")
	public void beforeMethodExecution() {
		System.out.println("Before method execution");
	}

	@Before("execution(* new com.example.myapp.service.UserService())")
	public void beforeObjectCreation() {
		System.out.println("Before object creation");
	}

	@Before("execution(void com.example.myapp.service.UserService.init())")
	public void beforeObjectInitialization() {
		System.out.println("Before object initialization");
	}

	@Before("execution(static com.example.myapp.service.*.*(..))")
	public void beforeStaticInitializer() {
		System.out.println("Before static initializer");
	}

	@Before("execution(* com.example.myapp.service.UserService.get*(..))")
	public void beforeFieldAccess() {
		System.out.println("Before field access");
	}

	@Before("execution(* com.example.myapp.service.UserService.throwException())")
	public void beforeExceptionHandling() {
		System.out.println("Before exception handling");
	}

	@Before("execution(* com.example.myapp.service.UserService.*(..)) && this(obj)")
	public void beforeAdviceExecution(Object obj) {
		System.out.println("Before advice execution on object: " + obj);
	}

	@Pointcut("execution(public * com.example.myapp.service.*.*(..))")
	private void serviceMethods() {
	}

	@Pointcut("execution(* com.example.myapp.service.UserService.createUser(..))")
	private void createUserMethod() {
	}

	@Pointcut("execution(* com.example.myapp.service.UserService.*(..))")
	private void allUserServiceMethods() {
	}

	@Pointcut("within(com.example.myapp.service.*)")
	private void withinServicePackage() {
	}

	@Pointcut("bean(user*)")
	private void userBeans() {
	}

	@Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
	private void transactionalMethods() {
	}

	@Pointcut("@annotation(com.example.myapp.annotations.CustomAnnotation)")
	private void customAnnotatedMethods() {
	}

	@Before("execution(* com.example.service.UserService.*(..)) && args(user)")
	public void beforeMethodsWithUserArgument(Courses courses) {
		System.out.println("Aspect applied to methods with User argument: " + courses.getDescription());
	}

	@Pointcut("serviceMethods() || createUserMethod()")
	private void combinedPointcut() {
	}

	@Before("execution(* (@com.example.annotations.TransactionalService *).*(..))")
	public void beforeMethodsInTransactionalServices() {
		System.out.println("Aspect applied to methods in classes with @TransactionalService annotation.");
	}

	@Pointcut("execution(* *.*(..)) && !execution(* com.example.myapp.service.UserService.createUser(..))")
	private void nonCreateUserMethods() {
	}

}
