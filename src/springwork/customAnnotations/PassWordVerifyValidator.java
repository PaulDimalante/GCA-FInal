package springwork.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.validation.BindingResult;

import springwork.model.EnrollForm;

public class PassWordVerifyValidator implements ConstraintValidator<PassWordVerifyConstraint, EnrollForm>{
	public static boolean validate(EnrollForm enrollForm, BindingResult result) {
		return enrollForm.getMemberPassWord().equals(enrollForm.getMemberPassWordVerify());
	}

	public void initialize(PassWordConstraint arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isValid(EnrollForm enrollForm, 
			//ConstraintValidatorContext arg1
			BindingResult result
			) {
		return validate(enrollForm, result);
	}

	@Override
	public void initialize(PassWordVerifyConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(EnrollForm value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
