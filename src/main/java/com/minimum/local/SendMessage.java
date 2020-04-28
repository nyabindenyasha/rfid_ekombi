package com.minimum.local;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SendMessage {
	
	public ResponseEntity<ActionResult> generateSms(SmsRequest smsRequest) {
		ActionResult result = new ActionResult();
		try {
			final String uri = "http://localhost:3000/sms/sms1";
			RestTemplate restTemplate = new RestTemplate();
			ActionResult resultFrom = restTemplate.postForObject(uri, smsRequest, ActionResult.class);
			if (resultFrom.getMessage().equalsIgnoreCase("success"))
				return ResponseEntity.ok().body(resultFrom);
			else {
				result.setMessage("an internal error occured");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}
	
	public ResponseEntity<ActionResult> generateSms2(MichelleRequest smsRequest) {
		ActionResult result = new ActionResult();
		try {
			final String uri = "http://192.168.43.40:80/pantilt";
			RestTemplate restTemplate = new RestTemplate();
			ActionResult resultFrom = restTemplate.postForObject(uri, smsRequest, ActionResult.class);
			System.out.println(restTemplate);
			System.out.println(resultFrom);
			if (resultFrom.getMessage().equalsIgnoreCase("success"))
				return ResponseEntity.ok().body(resultFrom);
			else {
				result.setMessage("an internal error occured");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
			}
		} catch (Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}

}
