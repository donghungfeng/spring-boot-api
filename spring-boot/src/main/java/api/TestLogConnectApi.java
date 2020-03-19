package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import constant.MESSAGE;
import entities.BaseResponse;
import entities.LogConnect;
import services.LogConnectService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1.0/public/test/logconnect")
public class TestLogConnectApi {
	
	
	@Autowired private LogConnectService logconnectService;
	 
	
	@RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
	public Object healthcheck() {
		return "OK";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Object getAll() {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,logconnectService.getAll());
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public Object getDetails(@PathVariable("id") int id) {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,logconnectService.getById(id));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object getDetails(@RequestBody LogConnect oLogConnect) {
		
		try {
			int rs = logconnectService.save(oLogConnect);
			if (rs == 0) {
				return new BaseResponse("00",MESSAGE.SAVE_SUCCESS,null);
			} else {
				return new BaseResponse(String.valueOf(rs),MESSAGE.SAVE_FAILED,null);
			}
			
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	public Object del(@PathVariable("id") int id) {
		try {
			int rs = logconnectService.delete(id);
			if (rs == 0) {
				return new BaseResponse("00",MESSAGE.DEL_SUCCESS,null);
			} else {
				return new BaseResponse(String.valueOf(rs),MESSAGE.DEL_FAILED,null);
			}
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
}
