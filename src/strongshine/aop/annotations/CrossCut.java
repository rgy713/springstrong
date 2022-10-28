
package strongshine.aop.annotations;

import com.xspeeder.ws.common.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CrossCut {


	boolean bCheckLogin() 		default false;
	
	
	boolean setLanguage() 			default false;

	
	String respType() 			default MediaType.APPLICATION_JSON;
}
