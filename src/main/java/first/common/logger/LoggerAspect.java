package first.common.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
//action-servliet.xml과 context-aspect.xml에서 <aop:aspectj-autoproxy/>를 사용했는데, @Aspect 어노테이션을 통해서 bean을 등록시켜주는 역할을 한다.
public class LoggerAspect {
	protected Log log = LogFactory.getLog(LoggerAspect.class);
	static String name ="";
	static String type = "";

/*	1) 관점(Aspect)
	구현하고자 하는 횡단 관심사의 기능을 의미한다. 한개 이상의 포인트컷과 어드바이스의 조합으로 만들어진다.

	2) 조인포인트(Join point)
	관점(Aspect)를 삽입하여 어드바이스가 적용될 수 있는 위치를 말한다. 

	3) 어드바이스(Advice)
	관점(Aspect)의 구현체로 조인 포인트에 삽입되어 동작하는 코드이다. 


	어드바이스는 조인포인트와 결합하여 동작하는 시점에 따라 5개로 구분된다.
	Before Advice : 조인포인트 전에 실행되는 advice
	After returning advice : 조인포인트에서 성공적으로 리턴 된 후 실행되는 advice
	After throwing advice : 예외가 발생하였을 경우 실행되는 advice
	After advice : 조인포인트에서 메서드의 실행결과에 상관없이 무조건 실행되는 advice, 자바의 finally와 비슷한 역할을 한다.
	Around advice : 조인포인트의 전 과정(전, 후)에 수행되는 advice


	4) 포인트컷(PointCut)
	어드바이스를 적용할 조인 포인트를 선별하는 과정이나 그 기능을 정의한 모듈을 의미한다. 패턴매칭을 이용하여 어떤 조인포인트를 사용할 것인지 결정한다.

	5) 타겟(Target)
	어드바이스를 받을 대상, 즉 객체를 의미한다. 비지니스로직을 수행하는 클래스일수도 있지만, 프록시 객체(Object)가 될 수도 있다.
*/


	
	
//@Around 어드바이스이다. 어드바이스는 어노테이션이 붙은 메서드를 이용하여 정의한다.
	//execution은 포인트컷 표현식 부분이다.
	
/*	execution() : 가장 대표적이고 강력한 지시자로, 접근제어자, 리턴 타입, 타입 패턴, 메서드, 파라미터 타입, 예외 타입 등을 조합해서 메서드까지 선택가능한 가장 정교한 포인트컷을 만들수 있다.
	within() : 타입 패턴만을 이용하여 조인포인트를 정의한다.
	this : 빈 오브텍트의 타입의 조인토인트를 정의한다.
	target : 대상객체의 타입 비교를 이용한 조인포인트를 정의한다.
	args : 메서드의 파라미터 타입만을 이용하여 조인포인트를 정의한다.
	@target : 특정 어노테이션이 정의된 객체를 찾는 조인포인트를 정의한다.
	@args : 특정 어노테이션을 파라미터로 받는 오브젝트를 찾는 조인포인트를 정의한다. 
	@within : @target과 유사하게 특정 어노테이션이 정의된 객체를 찾는데, 선택될 조인포인트 메서드는 타겟 클래스에서 선언이 되어있어야 한다.
	@annotation : 조인 포인트 메서드에 특정 어노테이션을 찾는 조인포인트를 정의한다.*/


	
	@Around("execution(* first..controller.*Controller.*(..)) or execution(* first..service.*Impl.*(..)) or execution(* first..dao.*DAO.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable{
		type = joinPoint.getSignature().getDeclaringTypeName();
		
		if (type.indexOf("Controller") > -1) {
			name = "Controller	\t:";
		}
		else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl	\t:";
		}
		else if (type.indexOf("DAO") > -1) {
			name = "DAO	\t\t:";
		}
		log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
		return joinPoint.proceed();
		
	} 
}
