package kr.or.ddit.validate.util;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateUtils {
	private static Validator validator;
	
	static {
		ValidatorFactory validatorFactory =
				Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	public static <T> Map<String, String> validate(T target, Class<?>...groups) {
		Set<ConstraintViolation<T>> constraintViolations 
				= validator.validate(target, groups);
		boolean valid = constraintViolations.isEmpty();
		log.trace("검증 통과 여부 : {}, {}", valid, constraintViolations.size());		
		Map<String, String> errors = constraintViolations.stream()
							.collect(Collectors.toMap(
								(cv)->cv.getPropertyPath().toString(),	
								(cv)->cv.getMessage(),	
								(v1, v2)->v1+","+v2	
							));
		errors.forEach((k,v)->log.trace("{} - {}", k, v));
		return errors;
	}
}


