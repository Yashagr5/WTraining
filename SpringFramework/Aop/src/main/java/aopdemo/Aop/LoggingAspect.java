package aopdemo.Aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	@Before("execution(* aopdemo.Aop.ShoppingCart.checkout(..))")
	public void beforeLogger(JoinPoint jb)
	{
		System.out.println("Loggers "+jb.getArgs());
	}
	
	@Around("execution(* aopdemo.Aop.*.checkout(..))")
	public void afterLogger(JoinPoint jb)
	{String arg=jb.getArgs()[0].toString();
	System.out.println("after checkout is working  "+arg);
	}
	

//	@AfterReturning(pointcut="execution(* checkout())", returning="returnString")
//	public void getNameReturningAdvice(String returnString){
//		System.out.println("getNameReturningAdvice executed. Returned String = "
//				+ "}
	
	@AfterThrowing(pointcut = "execution(* aopdemo.Aop.*.checkout(..))", throwing = "ex")
    public void handleException(Throwable ex) {
        System.out.println("AfterThrowing - Exception: " + ex.getMessage());
    }
	
	@AfterReturning(pointcut = "execution(* aopdemo.Aop.*.checkout(..))", returning = "retVal")
    public void doAfterReturningTask(Object retVal) {
        System.out.println("AfterReturning - Returned value: " + retVal);
    }
}
