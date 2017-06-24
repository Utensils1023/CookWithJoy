package com.CookWithJoy;
import java.security.Principal;
import java.util.List;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.CookWithJoy.Product.Product;
import com.CookWithJoy.Product.ProductDao;
import com.CookWithJoy.User.User;
import com.CookWithJoy.User.UserDao;


@RestController
public class SignupRestController {

	
	@Autowired
	UserDao userdao;
	
	@Autowired
	ProductDao pdao;
	

	@RequestMapping(value = "/adduser" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addUser(@RequestBody String data) throws JSONException{
		
		System.out.println("DATA : " + data);
		
		
		
      JSONParser jpar=new JSONParser();
		
		JSONObject jobj =new JSONObject(); 
		try{
		jobj=(JSONObject)jpar.parse(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		User user =new User();
		user.setUsername(jobj.get("Username").toString());
        user.setCpassword(jobj.get("ConfirmPassword").toString());
		
		user.setPassword(jobj.get("Password").toString());
		user.setPhone(jobj.get("Phone").toString());
		
		user.setUsername(jobj.get("Username").toString());
		user.setAddress(jobj.get("Address").toString());
		user.setEmail(jobj.get("Email").toString());
	//	userdao.addUser(u);
		userdao.addUser(user);
		json.put("msg", "added success");
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	

	@RequestMapping(value = "/product" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> product(@RequestBody String data) throws JSONException{
		
		System.out.println("DATA : " + data);
	
        JSONParser jpar=new JSONParser();
		
		JSONObject jobj =new JSONObject(); 
		try{
		jobj=(JSONObject)jpar.parse(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		Product p=new Product();
		p.setProductName(jobj.get("productname").toString());
        p.setProductPrice(jobj.get("price").toString());
		
		p.setProductDescription(jobj.get("description").toString());
		p.setProductCategory(jobj.get("category").toString());
		
		
		p.setProductQuantity(jobj.get("quantity").toString());
	    pdao.insert(p);
		
		json.put("msg", "added success");
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/productview" , method = RequestMethod.POST)
	public @ResponseBody JSONObject productview(@RequestBody String data) throws JSONException{
		
		System.out.println("DATA : " + data);
		
	    List list=	pdao.getAllProducts();
     	System.out.println(list);
     	JSONArray json = new JSONArray();
     	
	    return null;
	}
	
	@RequestMapping(value = "/loginuser" , method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody JSONObject data, Principal p) {
		System.out.println(data);

		JSONObject json = new JSONObject();
		
		List<User> listuser = userdao.listUser();

		boolean usermatch = false;

		for (User u : listuser) {
			if( u.getEmail() != null && u.getPassword() != null )
			if (u.getEmail().equals(data.get("Email").toString() ) && u.getPassword().equals(data.get("Password").toString()) ) {
				usermatch = true;
				break;
			}
		}
		if (usermatch == false) {
			json.put("msg", "Invalid Login");
			
		} else {
			User u = userdao.getUserByEmail(data.get("Email").toString());
			
			json.put("msg", "Login Successful");
			json.put("role", u.getRole());
			
		}
		
		System.out.println(json.toString());
		
		
		return new ResponseEntity<String>(json.toString(), HttpStatus.CREATED);
	}
	

}
