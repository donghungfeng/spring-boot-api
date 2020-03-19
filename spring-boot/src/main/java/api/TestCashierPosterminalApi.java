package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import constant.MESSAGE;
import entities.BaseResponse;
import entities.CashierPosterminal;
import services.CashierPosterminalService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1.0/public/test/cashier_posterminal")
public class TestCashierPosterminalApi {
	@Autowired
	private CashierPosterminalService cashierPosterminalService;
	
	@RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
	public Object healthcheck() {
		return "OK";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Object getAll() {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,cashierPosterminalService.getAll());
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/getbycashierid", method = RequestMethod.GET)
	public Object getByCashierid(@RequestBody CashierPosterminal cashier) {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,cashierPosterminalService.getByCashierid(cashier));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/getbypoterminalid", method = RequestMethod.GET)
	public Object getByPosterminalid(@RequestBody CashierPosterminal cashier) {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,cashierPosterminalService.getByPosterminalid(cashier));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object getDetails(@RequestBody CashierPosterminal cashier) {
		
		try {
			int rs = cashierPosterminalService.save(cashier);
			if (rs == 0) {
				return new BaseResponse("00",MESSAGE.SAVE_SUCCESS,null);
			} else {
				return new BaseResponse(String.valueOf(rs),MESSAGE.SAVE_FAILED,null);
			}
			
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.DELETE)
	public Object del(@RequestBody CashierPosterminal cashier) {
		try {
			int rs = cashierPosterminalService.delete(cashier);
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
