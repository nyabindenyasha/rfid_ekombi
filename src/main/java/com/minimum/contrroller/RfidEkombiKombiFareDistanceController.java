package com.minimum.contrroller;

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
import com.minimum.local.ActionResult;
import com.minimum.model.RfidEkombiKombiFareDistance;
import com.minimum.service.RfidEkombiKombiFareDistanceService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/kombiFareDistance")
public class RfidEkombiKombiFareDistanceController {

	@Autowired
	RfidEkombiKombiFareDistanceService kombiFareDistanceService;
	
	@ApiOperation(value = "", response = Iterable.class)
	@PostMapping()
	public ResponseEntity<ActionResult> save(@Valid @RequestBody RfidEkombiKombiFareDistance kombiFareDistance) {
		ActionResult result = new ActionResult(); 
		try {
			kombiFareDistanceService.save(kombiFareDistance);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}catch(Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<RfidEkombiKombiFareDistance>> findAll(){
		try {
			return ResponseEntity.ok().body(kombiFareDistanceService.findAll());
		}catch(Exception exception) {
			Iterable<RfidEkombiKombiFareDistance> iterable = null;
			return new ResponseEntity<Iterable<RfidEkombiKombiFareDistance>>(iterable, HttpStatus.BAD_GATEWAY);
		}	
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<RfidEkombiKombiFareDistance> findOne(@PathVariable int id) {
		RfidEkombiKombiFareDistance kombiFareDistance = kombiFareDistanceService.findOne(id);
			if(kombiFareDistance == null)return ResponseEntity.notFound().build();
			return ResponseEntity.ok().body(kombiFareDistance);
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/{id}")
	public ResponseEntity<ActionResult> update(@PathVariable int id,@Valid @RequestBody RfidEkombiKombiFareDistance kombiFareDistance) {
		ActionResult result = new ActionResult(); 
		try {
			if(id!=kombiFareDistance.getId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);	
			}
			kombiFareDistanceService.save(kombiFareDistance);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}catch(Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@DeleteMapping("{id}")
	public ResponseEntity<ActionResult> delete(@PathVariable int id) {
		ActionResult result = new ActionResult(); 
		if(kombiFareDistanceService.findOne(id)!=null) { 
			kombiFareDistanceService.delete(id);
		result.setMessage("Success");
		return ResponseEntity.ok().body(result);
		}
		result.setMessage("Cannot delete the RfidEkombiKombiFareDistance");
		return new  ResponseEntity<ActionResult>(result,HttpStatus.BAD_REQUEST);		
	}

}

