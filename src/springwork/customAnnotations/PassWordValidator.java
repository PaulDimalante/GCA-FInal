package springwork.customAnnotations;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

public class PassWordValidator implements ConstraintValidator<PassWordConstraint, String> {
	public static final Pattern VALID_PASSWORD_REGEX = 
//	Pattern.compile("^+[A-Z]+[0-9]+[!@#\\$%\\^&\\*].*$");
	Pattern.compile("^.*$");
	
	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(emailStr);
		return matcher.find();
	}

	@Override
	public void initialize(PassWordConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return (validate(arg0) && (arg0.length() > 1) && (arg0.length() < 14));
	}



}
