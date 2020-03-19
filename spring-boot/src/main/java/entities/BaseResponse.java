package entities;

public class BaseResponse {
	public String CODE;
	public String MESSAGE;
	public Object RESULT;
	
	public BaseResponse() {}
	
	public BaseResponse(String code, String message, Object result) {
		this.CODE = code;
		this.MESSAGE = message;
		this.RESULT = result;
	}


}
