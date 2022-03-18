package validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import command.RegistCommand;

public class RegistCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return RegistCommand.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) { //여기서 자동으로 업캐스팅 되기 때문에
		RegistCommand rc = (RegistCommand) target; //다운캐스팅으로 다시 넣어줌
		if(rc.getImg().isEmpty()) {
			errors.rejectValue("img", "required"); //("input태그의 요소", "라벨에 정의된 메세지 이름")
		}
		
		//요소에 값이 없을 경우 라벨에 정의된 메세지 호출하기
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publisher", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "introduction", "required");
	}
}