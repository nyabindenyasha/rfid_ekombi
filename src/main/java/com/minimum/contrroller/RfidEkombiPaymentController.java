package com.minimum.contrroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minimum.local.ActionResult;
import com.minimum.local.MichelleRequest;
import com.minimum.local.SendMessage;
import com.minimum.local.SmsRequest;
import com.minimum.model.RfidEkombiAccount;
import com.minimum.model.RfidEkombiKombis;
import com.minimum.model.RfidEkombiPayments;
import com.minimum.model.RfidEkombiUsers;
import com.minimum.service.RfidEkombiAccountService;
import com.minimum.service.RfidEkombiKombiFareDistanceService;
import com.minimum.service.RfidEkombiKombisService;
import com.minimum.service.RfidEkombiPaymentsService;
import com.minimum.service.RfidEkombiUsersService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class RfidEkombiPaymentController {

	@Autowired
	RfidEkombiUsersService usersService;
	
	@Autowired
	RfidEkombiPaymentsService paymentsService;

	@Autowired
	RfidEkombiAccountService rfidAccountService;
	
	@Autowired
	RfidEkombiKombisService kombiService;
	
	@Autowired
	RfidEkombiKombiFareDistanceService kombiFareDistanceService;
	
	@Autowired
	RfidEkombiAccountService accountService;
	
	@Autowired
	RfidEkombiPaymentsService paymentService;
	
	private static List<String> uIDList = new ArrayList<>();

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<RfidEkombiPayments>> findAll() {
		try {
			return ResponseEntity.ok().body(paymentService.findAll());
		} catch (Exception exception) {
			Iterable<RfidEkombiPayments> iterable = null;
			return new ResponseEntity<Iterable<RfidEkombiPayments>>(iterable, HttpStatus.BAD_GATEWAY);
		}
	}

	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<RfidEkombiPayments> findOne(@PathVariable int id) {
		RfidEkombiPayments payment = paymentService.findOne(id);
		if (payment == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(payment);
	}

	@ApiOperation(value = "view user by RFID UID", response = Iterable.class)
	@GetMapping("/rfidUniqueId/{rfidUniqueId:.+}")
	public ResponseEntity<RfidEkombiUsers> findByEmail(@PathVariable String rfidUniqueId) {
		try {
			return ResponseEntity.ok().body(usersService.findByRfidUniqueId(rfidUniqueId));
		} catch (Exception exception) {
			return new ResponseEntity<RfidEkombiUsers>(new RfidEkombiUsers(), HttpStatus.BAD_GATEWAY);
		}
	}

	@GetMapping("/querry")
	public ResponseEntity<ActionResult> addToRegister1(@RequestParam("rfidUniqueId") String rfidUniqueId, @RequestParam("kombiId") int kombiId) {
		ActionResult result = new ActionResult();
		SmsRequest smsRequest = new SmsRequest();
		SendMessage sendMessage = new SendMessage();
		uIDList.add(rfidUniqueId);
		try {
			RfidEkombiUsers usersWhoMadePayment = usersService.findByRfidUniqueId(rfidUniqueId);	
			RfidEkombiKombis kombiWhichProvidedService = kombiService.findOne(kombiId);
			RfidEkombiAccount accountOfUser = accountService.findOne(usersWhoMadePayment.getAccountId());
			
			if(usersWhoMadePayment == null || kombiWhichProvidedService == null) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
			}
			
			smsRequest.setReceiverNumber("263" + usersWhoMadePayment.getMobileNumber());
			int kombiFareDistanceId = kombiWhichProvidedService.getKombiFareDistanceId();
			double amountPaid = kombiFareDistanceService.findOne(kombiFareDistanceId).getKombiFare();
			double usersRemainingBalance = accountOfUser.getCardBalance() - amountPaid;
			
			if (usersRemainingBalance >= 0){
			accountOfUser.setCardBalance(usersRemainingBalance);
			accountService.save(accountOfUser);		
			RfidEkombiPayments ekombiPayments = new RfidEkombiPayments();
			ekombiPayments.setUserId(usersWhoMadePayment.getId());
			ekombiPayments.setKombiId(kombiId);
			ekombiPayments.setAmountPaid(amountPaid);
			ekombiPayments.setDateCreated(new Date());
			paymentService.save(ekombiPayments);
			result.setMessage("Success");
			smsRequest.setSmsBody("Transaction of $" + amountPaid + "0 successful. Remaining balance is $" + accountOfUser.getCardBalance() + "0");
			sendMessage.generateSms(smsRequest);
			}			
			else {
				result.setMessage("Insufficient Balance");		
				smsRequest.setSmsBody("Transaction of $" + amountPaid + "0 failed due to Insufficient Balance. Remaining balance is $" + accountOfUser.getCardBalance() + "0");
				sendMessage.generateSms(smsRequest);
			}
			return ResponseEntity.ok().body(result);
		}
		catch (Exception e) {
			result.setMessage(e.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
//	@GetMapping("/querry2")
//	public ResponseEntity<ActionResult> makePayment(@RequestParam("rfidUniqueId") String rfidUniqueId, @RequestParam("kombiId") int kombiId, @RequestParam("numberCount") int numberCount) {
//		System.out.println("payments");
//		SmsRequest smsRequest = new SmsRequest();
//		SendMessage sendMessage = new SendMessage();
//		ActionResult result = new ActionResult();
//		if(uIDList.contains(rfidUniqueId)) {
//			uIDList.remove(rfidUniqueId);
//				result.setMessage("EXIT"); 
//				return ResponseEntity.ok().body(result);
//		}
//		
//		else {
//		try {
//			RfidEkombiUsers usersWhoMadePayment = usersService.findByRfidUniqueId(rfidUniqueId);	
//			RfidEkombiKombis kombiWhichProvidedService = kombiService.findOne(kombiId);
//	
//			if(usersWhoMadePayment == null || kombiWhichProvidedService == null) {
//				result.setMessage("INVALID_REQUEST!"); 
//				return ResponseEntity.ok().body(result);
//			}	
//			uIDList.add(rfidUniqueId);
//			
//			RfidEkombiAccount accountOfUser = accountService.findOne(usersWhoMadePayment.getAccountId());
//			
//			smsRequest.setReceiverNumber("263" + usersWhoMadePayment.getMobileNumber());
//			
//			int kombiFareDistanceId = kombiWhichProvidedService.getKombiFareDistanceId();
//			double amount = kombiFareDistanceService.findOne(kombiFareDistanceId).getKombiFare();
//			double amountPaid = amount * numberCount;
//			double usersRemainingBalance = accountOfUser.getCardBalance() - amountPaid;			
//			if (usersRemainingBalance >= 0){
//			accountOfUser.setCardBalance(usersRemainingBalance);
//			accountService.save(accountOfUser);			
//			RfidEkombiPayments ekombiPayments = new RfidEkombiPayments();
//			ekombiPayments.setUserId(usersWhoMadePayment.getId());
//			ekombiPayments.setKombiId(kombiId);
//			ekombiPayments.setAmountPaid(amountPaid);
//			ekombiPayments.setDateCreated(new Date());
//			paymentService.save(ekombiPayments);
//			result.setMessage("SUCCESS");
//			smsRequest.setSmsBody("Transaction of $" + amountPaid + "0 successful. Remaining balance is $" + accountOfUser.getCardBalance() + "0");
//			sendMessage.generateSms(smsRequest);
//			}	
//			else {
//				result.setMessage("INSUFFICIENT");		
//				smsRequest.setSmsBody("Transaction of $" + amountPaid + "0 failed due to Insufficient Balance. Remaining balance is $" + accountOfUser.getCardBalance() + "0");
//				sendMessage.generateSms(smsRequest);
//			}
//			return ResponseEntity.ok().body(result);
//		}
//		catch (Exception e) {
//			result.setMessage("EXCEPTION");
//			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);
//		}
//	}
//	}
	
	@GetMapping("/querry2")
	public String makePayment(@RequestParam("rfidUniqueId") String rfidUniqueId, @RequestParam("kombiId") int kombiId, @RequestParam("numberCount") int numberCount) {
		System.out.println("payments");
		SmsRequest smsRequest = new SmsRequest();
		SendMessage sendMessage = new SendMessage();
		ActionResult result = new ActionResult();
		if(uIDList.contains(rfidUniqueId)) {
			uIDList.remove(rfidUniqueId);
				return "EXIT";
		}
		
		else {
		try {
			RfidEkombiUsers usersWhoMadePayment = usersService.findByRfidUniqueId(rfidUniqueId);	
			RfidEkombiKombis kombiWhichProvidedService = kombiService.findOne(kombiId);
	
			if(usersWhoMadePayment == null || kombiWhichProvidedService == null) {
				return "INVALID_REQUEST!";
			}	
			
			RfidEkombiAccount accountOfUser = accountService.findOne(usersWhoMadePayment.getAccountId());
			smsRequest.setReceiverNumber("263" + usersWhoMadePayment.getMobileNumber());
			int kombiFareDistanceId = kombiWhichProvidedService.getKombiFareDistanceId();
			double amount = kombiFareDistanceService.findOne(kombiFareDistanceId).getKombiFare();
			double amountPaid = amount * numberCount;
			double usersRemainingBalance = accountOfUser.getCardBalance() - amountPaid;			
			if (usersRemainingBalance >= 0){
			accountOfUser.setCardBalance(usersRemainingBalance);
			accountService.save(accountOfUser);			
			RfidEkombiPayments ekombiPayments = new RfidEkombiPayments();
			ekombiPayments.setUserId(usersWhoMadePayment.getId());
			ekombiPayments.setKombiId(kombiId);
			ekombiPayments.setAmountPaid(amountPaid);
			ekombiPayments.setDateCreated(new Date());
			paymentService.save(ekombiPayments);
			result.setMessage("SUCCESS");
			smsRequest.setSmsBody("Transaction of $" + amountPaid + "0 successful. Remaining balance is $" + accountOfUser.getCardBalance() + "0");
//			sendMessage.generateSms(smsRequest);
			MichelleRequest r = new MichelleRequest("80000", "100");
			sendMessage.generateSms2(r);
			uIDList.add(rfidUniqueId);
			}	
			else {
				result.setMessage("INSUFFICIENT");		
				smsRequest.setSmsBody("Transaction of $" + amountPaid + "0 failed due to Insufficient Balance. Remaining balance is $" + accountOfUser.getCardBalance() + "0");
//				sendMessage.generateSms(smsRequest);
				MichelleRequest r = new MichelleRequest();
				r.setMeter_number("70000");
				r.setBalance("100");
				sendMessage.generateSms2(r);
			}
			return result.getMessage();
		}
		catch (Exception e) {
			return "EXCEPTION";
		}
	}
	}

}
