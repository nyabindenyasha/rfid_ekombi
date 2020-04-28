package com.minimum.contrroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minimum.local.ActionResult;
import com.minimum.local.SendMessage;
import com.minimum.local.SmsRequest;
import com.minimum.local.requests.TransferToCardRequest;
import com.minimum.local.requests.UpdateUserAccountRequest;
import com.minimum.model.RfidEkombiAccount;
import com.minimum.model.RfidEkombiUsers;
import com.minimum.service.RfidEkombiAccountService;
import com.minimum.service.RfidEkombiUsersService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class RfidEkombiAccountController {

	@Autowired
	RfidEkombiAccountService rfidAccountService;

	@Autowired
	RfidEkombiUsersService usersService;

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<RfidEkombiAccount>> findAll() {
		try {
			return ResponseEntity.ok().body(rfidAccountService.findAll());
		} catch (Exception exception) {
			Iterable<RfidEkombiAccount> iterable = null;
			return new ResponseEntity<Iterable<RfidEkombiAccount>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<RfidEkombiAccount> findOne(@PathVariable int id) {
		RfidEkombiAccount rfidEkombiAccount = rfidAccountService.findOne(id);
		if (rfidEkombiAccount == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(rfidEkombiAccount);
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/transferFromBankToCard/{userId}/{accountId}")
	public ResponseEntity<ActionResult> transferFromBankToCard(@PathVariable int userId, @PathVariable int accountId,
			@Valid @RequestBody TransferToCardRequest request) {
		ActionResult result = new ActionResult();
		SmsRequest smsRequest = new SmsRequest();
		SendMessage sendMessage = new SendMessage();
		
		try {
			if (userId != request.getUserId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}

			RfidEkombiUsers userAlreadyInDb = usersService.findOne(userId);

			if (accountId != userAlreadyInDb.getAccountId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}
			
			smsRequest.setReceiverNumber("263" + userAlreadyInDb.getMobileNumber());
			RfidEkombiAccount rfidEkombiAccountAlreadyInDb = rfidAccountService.findOne(accountId);
			rfidEkombiAccountAlreadyInDb
					.setBankBalance(rfidEkombiAccountAlreadyInDb.getBankBalance() - request.getAmount());
			rfidEkombiAccountAlreadyInDb
					.setCardBalance(rfidEkombiAccountAlreadyInDb.getCardBalance() + request.getAmount());
			rfidAccountService.save(rfidEkombiAccountAlreadyInDb);
			result.setMessage("Success");
			
			smsRequest.setSmsBody("Transfer of " + request.getAmount() + "0 from bank to card successful. Your card balance is $" + rfidEkombiAccountAlreadyInDb.getCardBalance() + "0");
			sendMessage.generateSms(smsRequest);
			
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/transferFromEcocashToCard/{userId}/{accountId}")
	public ResponseEntity<ActionResult> transferFromEcocashToCard(@PathVariable int userId, @PathVariable int accountId,
			@Valid @RequestBody TransferToCardRequest request) {
		ActionResult result = new ActionResult();
		SmsRequest smsRequest = new SmsRequest();
		SendMessage sendMessage = new SendMessage();
		
		try {
			if (userId != request.getUserId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}

			RfidEkombiUsers userAlreadyInDb = usersService.findOne(userId);

			if (accountId != userAlreadyInDb.getAccountId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}
			
			smsRequest.setReceiverNumber("263" + userAlreadyInDb.getMobileNumber());
			RfidEkombiAccount rfidEkombiAccountAlreadyInDb = rfidAccountService.findOne(accountId);
			rfidEkombiAccountAlreadyInDb
					.setEcocashBalance(rfidEkombiAccountAlreadyInDb.getEcocashBalance() - request.getAmount());
			rfidEkombiAccountAlreadyInDb
					.setCardBalance(rfidEkombiAccountAlreadyInDb.getCardBalance() + request.getAmount());
			rfidAccountService.save(rfidEkombiAccountAlreadyInDb);
			result.setMessage("Success");
			
			smsRequest.setSmsBody("Transfer of " + request.getAmount() + "0 from ecocash to card successful. Your card balance is $" + rfidEkombiAccountAlreadyInDb.getCardBalance() + "0");
			sendMessage.generateSms(smsRequest);
			
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/updateUserAccountBalance/{userId}/{accountId}")
	public ResponseEntity<ActionResult> updateUserAcountBalance(@PathVariable int userId, @PathVariable int accountId,
			@Valid @RequestBody UpdateUserAccountRequest request) {
		ActionResult result = new ActionResult();
		try {
			if (userId != request.getUserId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}

			RfidEkombiUsers userAlreadyInDb = usersService.findOne(userId);

			if (accountId != userAlreadyInDb.getAccountId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}

			RfidEkombiAccount rfidEkombiAccountAlreadyInDb = rfidAccountService.findOne(accountId);
			rfidEkombiAccountAlreadyInDb.setBankBalance(request.getBankBalance());
			rfidEkombiAccountAlreadyInDb.setEcocashBalance(request.getEcocashBalance());
			rfidAccountService.save(rfidEkombiAccountAlreadyInDb);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

}
