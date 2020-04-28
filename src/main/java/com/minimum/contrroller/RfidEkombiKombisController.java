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
import com.minimum.local.enums.KombiDistances;
import com.minimum.local.requests.KombiRequest;
import com.minimum.local.requests.UpdateKombiRequest;
import com.minimum.model.RfidEkombiKombis;
import com.minimum.repo.RfidEkombiKombiFareDistanceRepo;
import com.minimum.service.RfidEkombiKombiTrackerService;
import com.minimum.service.RfidEkombiKombisService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
@RequestMapping("/kombis")
public class RfidEkombiKombisController {

	@Autowired
	RfidEkombiKombisService rfidEkombiKombisService;
	
	@Autowired
	RfidEkombiKombiTrackerService kombiTrackerService;
	
	@Autowired
	RfidEkombiKombiFareDistanceRepo kombiFareDistanceRepo;
	
	@ApiOperation(value = "", response = Iterable.class)
	@PostMapping()
	public ResponseEntity<ActionResult> save(@Valid @RequestBody KombiRequest request) {
		ActionResult result = new ActionResult(); 
		try {
			RfidEkombiKombis rfidEkombiKombi = new RfidEkombiKombis();
			rfidEkombiKombi.setFromSource(request.getFromSource());
			rfidEkombiKombi.setToDestination(request.getToDestination());
			
			if(request.getDistance() < KombiDistances.Distance1.getKombiDistance()) 
				rfidEkombiKombi.setKombiFareDistanceId(kombiFareDistanceRepo.findByDistance(KombiDistances.Distance1.getKombiDistance()).getId());
			
			else if(request.getDistance() < KombiDistances.Distance2.getKombiDistance()) 
				rfidEkombiKombi.setKombiFareDistanceId(kombiFareDistanceRepo.findByDistance(KombiDistances.Distance2.getKombiDistance()).getId());
			
			else if(request.getDistance() < KombiDistances.Distance3.getKombiDistance()) 
				rfidEkombiKombi.setKombiFareDistanceId(kombiFareDistanceRepo.findByDistance(KombiDistances.Distance3.getKombiDistance()).getId());			
				
			rfidEkombiKombisService.save(rfidEkombiKombi);
			result.setMessage("Success");
			return ResponseEntity.ok().body(result);
		}catch(Exception exception) {
			result.setMessage(exception.getMessage());
			return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping()
	public ResponseEntity<Iterable<RfidEkombiKombis>> findAll(){
		try {
			return ResponseEntity.ok().body(rfidEkombiKombisService.findAll());
		}catch(Exception exception) {
			Iterable<RfidEkombiKombis> iterable = null;
			return new ResponseEntity<Iterable<RfidEkombiKombis>>(iterable, HttpStatus.BAD_GATEWAY);
		}	
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@GetMapping("{id}")
	public ResponseEntity<RfidEkombiKombis> findOne(@PathVariable int id) {
		RfidEkombiKombis rfidEkombiKombis = rfidEkombiKombisService.findOne(id);
			if(rfidEkombiKombis == null)return ResponseEntity.notFound().build();
			return ResponseEntity.ok().body(rfidEkombiKombis);
	}
	
	@ApiOperation(value = "", response = Iterable.class)
	@PutMapping("/{id}")
	public ResponseEntity<ActionResult> update(@PathVariable int id,@Valid @RequestBody UpdateKombiRequest request) {
		ActionResult result = new ActionResult(); 
		try {
			if(id != request.getId()) {
				result.setMessage("Invalid request.");
				return new ResponseEntity<ActionResult>(result, HttpStatus.BAD_REQUEST);	
			}
			
			RfidEkombiKombis rfidEkombiKombiAlreadyInDb = rfidEkombiKombisService.findOne(id);
			
			rfidEkombiKombiAlreadyInDb.setFromSource(request.getFromSource());
			rfidEkombiKombiAlreadyInDb.setToDestination(request.getToDestination());
			
			if(request.getDistance() < KombiDistances.Distance1.getKombiDistance()) 
				rfidEkombiKombiAlreadyInDb.setKombiFareDistanceId(kombiFareDistanceRepo.findByDistance(KombiDistances.Distance1.getKombiDistance()).getId());
			
			else if(request.getDistance() < KombiDistances.Distance2.getKombiDistance()) 
				rfidEkombiKombiAlreadyInDb.setKombiFareDistanceId(kombiFareDistanceRepo.findByDistance(KombiDistances.Distance2.getKombiDistance()).getId());
			
			else if(request.getDistance() < KombiDistances.Distance3.getKombiDistance()) 
				rfidEkombiKombiAlreadyInDb.setKombiFareDistanceId(kombiFareDistanceRepo.findByDistance(KombiDistances.Distance3.getKombiDistance()).getId());	
					
			rfidEkombiKombisService.save(rfidEkombiKombiAlreadyInDb);
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
		if(rfidEkombiKombisService.findOne(id)!=null) { 
			rfidEkombiKombisService.delete(id);
		result.setMessage("Success");
		return ResponseEntity.ok().body(result);
		}
		result.setMessage("Cannot delete the RfidEkombiKombis");
		return new  ResponseEntity<ActionResult>(result,HttpStatus.BAD_REQUEST);		
	}

}

