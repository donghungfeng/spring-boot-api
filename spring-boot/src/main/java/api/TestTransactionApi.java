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
import entities.Transaction;
import services.TransactionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1.0/public/test/transaction")
public class TestTransactionApi {
	
	
	@Autowired private TransactionService transactionService;
	 
	
	@RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
	public Object healthcheck() {
		return "OK";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Object getAll() {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,transactionService.getAll());
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public Object getDetails(@PathVariable("id") int id) {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,transactionService.getById(id));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object getDetails(@RequestBody Transaction oTransaction) {
		
		try {
			int rs = transactionService.save(oTransaction);
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
			int rs = transactionService.delete(id);
			if (rs == 0) {
				return new BaseResponse("00",MESSAGE.DEL_SUCCESS,null);
			} else {
				return new BaseResponse(String.valueOf(rs),MESSAGE.DEL_FAILED,null);
			}
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/lock/{id}", method = RequestMethod.PUT)
	public Object lock(@PathVariable("id") int id) {
		try {
			int rs = transactionService.lock(id,0);
			if (rs == 0) {
				return new BaseResponse("00",MESSAGE.DEL_SUCCESS,null);
			} else {
				return new BaseResponse(String.valueOf(rs),MESSAGE.DEL_FAILED,null);
			}
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
	}
	
	@RequestMapping(value = "/unlock/{id}", method = RequestMethod.PUT)
	public Object unlock(@PathVariable("id") int id) {
		try {
			int rs = transactionService.lock(id,1);
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
