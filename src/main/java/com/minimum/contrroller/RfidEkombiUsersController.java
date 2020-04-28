package com.minimum.contrroller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.uuid.Generators;
import com.minimum.local.ActionResult;
import com.minimum.local.HarshingClass;
import com.minimum.local.requests.LoginRequest;
import com.minimum.local.requests.SignUpRequest;
import com.minimum.local.requests.UpdateUserRFIDUIDRequest;
import com.minimum.local.requests.UpdateUserRequest;
import com.minimum.local.responses.LoginResponse;
import com.minimum.model.RfidEkombiAccount;
import com.minimum.model.RfidEkombiUsers;
import com.minimum.service.RfidEkombiAccountService;
import com.minimum.service.RfidEkombiUsersService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class RfidEkombiUsersController {

	@Autowired
	RfidEkombiUsersService usersService;

	@Autowired
	RfidEkombiAccountService rfidAccountService;

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<RfidEkombiUsers>> findAll() {
		try {
			return ResponseEntity.ok().body(usersService.findAll());
		} catch (Exception exception) {
			Iterable<RfidEkombiUsers> iterable = null;
			return new ResponseEntity<Iterable<RfidEkombiUsers>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<RfidEkombiUsers> findOne(@PathVariable int id) {
		RfidEkombiUsers users = usersService.findOne(id);
		if (users == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(users);
	}
	
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("/findByAccountId/{accountId}")
	public ResponseEntity<RfidEkombiUsers> findByAccountId(@PathVariable int accountId) {
		RfidEkombiUsers users = usersService.findByAccountId(accountId);
		if (users == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(users);
	}

	@ApiOperation(value = "view user by email", response = Iterable.class)
	@GetMapping("/email/{email:.+}")
	public ResponseEntity<RfidEkombiUsers> findByEmail(@PathVariable String email) {
		try {
			return ResponseEntity.ok().body(usersService.findByEmail(email));
		} catch (Exception exception) {
			return new ResponseEntity<RfidEkombiUsers>(new RfidEkombiUsers(), HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "create user / register", response = Iterable.class)
	@PostMapping("/register")
	public ResponseEntity<ActionResult> save(@RequestBody SignUpRequest signUpRequest) {
		ActionResult result = new ActionResult();
		HarshingClass harshingClass = new HarshingClass();

		try {
			RfidEkombiUsers userAlreadyInDb = usersService.findByEmail(signUpRequest.getUsername());
			if (userAlreadyInDb != null) {
				result.setMessage("Email Already exist");
				return ResponseEntity.badRequest().body(result);
			}

			RfidEkombiAccount rfidEkombiAccount = new RfidEkombiAccount();
			rfidEkombiAccount.setCardBalance(0.00);
			RfidEkombiAccount rfidEkombiAccountSaved = rfidAccountService.saveR(rfidEkombiAccount);

			RfidEkombiUsers rfidEkombiUser = new RfidEkombiUsers();
			UUID uuidSecurityStamp = Generators.nameBasedGenerator().generate(signUpRequest.getUsername());
			rfidEkombiUser.setSecurityStamp(uuidSecurityStamp.toString());
			rfidEkombiUser.setHarshedPassword(
					harshingClass.getStringFromSHA256(signUpRequest.getPassword()) + uuidSecurityStamp.toString());
			rfidEkombiUser.setAccountId(rfidEkombiAccountSaved.getId());
			rfidEkombiUser.setMobileNumber(signUpRequest.getMobileNumber());
			rfidEkombiUser.setNationalId(signUpRequest.getNationalId());
			rfidEkombiUser.setUsername(signUpRequest.getUsername());
			usersService.save(rfidEkombiUser);
			result.setMessage("Success");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<>(result, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "login CAT user", response = Iterable.class)
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		HarshingClass harshingClass = new HarshingClass();
		try {
			RfidEkombiUsers rfidEkombiUser = usersService.findByEmail(loginRequest.getUsername());
			if ((harshingClass.getStringFromSHA256(loginRequest.getPassword()) + rfidEkombiUser.getSecurityStamp())
					.equalsIgnoreCase(rfidEkombiUser.getHarshedPassword())) {
				rfidEkombiUser.setToken(
						"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
				
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setId(rfidEkombiUser.getId());
				loginResponse.setAccountId(rfidEkombiUser.getAccountId());
				loginResponse.setMobileNumber(rfidEkombiUser.getMobileNumber());
				loginResponse.setNationalId(rfidEkombiUser.getNationalId());
				loginResponse.setUsername(rfidEkombiUser.getUsername());
				loginResponse.setToken(rfidEkombiUser.getToken());
				
				return ResponseEntity.ok().body(loginResponse);
			} else
				return new ResponseEntity<LoginResponse>(new LoginResponse(), HttpStatus.BAD_GATEWAY);
		} catch (Exception exception) {
			return new ResponseEntity<LoginResponse>(new LoginResponse(), HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/{id}")
	public ResponseEntity<ActionResult> update(@PathVariable int id, @Valid @RequestBody UpdateUserRequest request) {
		ActionResult result = new ActionResult();
		try {
			if (id != request.getId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}
			RfidEkombiUsers userAlreadyInDb = usersService.findOne(id);
			userAlreadyInDb.setName(request.getName());
			userAlreadyInDb.setSurname(request.getSurname());
//			userAlreadyInDb.setRfidUniqueId(request.getRfidUniqueId());
			usersService.save(userAlreadyInDb);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/updateRfidUID/{id}")
	public ResponseEntity<ActionResult> updateRFIDUID(@PathVariable int id,
			@Valid @RequestBody UpdateUserRFIDUIDRequest request) {
		ActionResult result = new ActionResult();
		try {
			if (id != request.getId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}
			RfidEkombiUsers userAlreadyInDb = usersService.findOne(id);
			userAlreadyInDb.setRfidUniqueId(request.getRfidUniqueId());
			userAlreadyInDb.setActive(true);
			usersService.save(userAlreadyInDb);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@DeleteMapping("{id}")
	public ResponseEntity<ActionResult> delete(@PathVariable int id) {
		ActionResult result = new ActionResult();
		if (usersService.findOne(id) != null) {
			usersService.delete(id);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}
		result.setMessage("Cannot delete the RfidEkombiUsers");
		return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
	}

}
