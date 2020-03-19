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
import entities.Posterminal;
import services.PosterminalService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1.0/public/test/posterminal")
public class TestPosterminalApi {
	@Autowired
	private PosterminalService posterminalService;
	
	@RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
	public Object healthcheck() {
		return "OK";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Object getAll() {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,posterminalService.getAll());
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Object searchAll() {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,posterminalService.search(""));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
	}
	
	@RequestMapping(value = "/search/{key}", method = RequestMethod.GET)
	public Object search(@PathVariable(value="key",required = false) String key) {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,posterminalService.search(key));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public Object getDetails(@PathVariable("id") int id) {
		try {
			return new BaseResponse("00",MESSAGE.GET_SUCCESS,posterminalService.getById(id));
		} catch (Exception e) {
			return new BaseResponse("99","Ex: " + e.getMessage(),null);
		}
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object getDetails(@RequestBody Posterminal oPosterminal) {
		
		try {
			int rs = posterminalService.save(oPosterminal);
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
			int rs = posterminalService.delete(id);
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
			int rs = posterminalService.lock(id,0);
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
			int rs = posterminalService.lock(id,1);
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
