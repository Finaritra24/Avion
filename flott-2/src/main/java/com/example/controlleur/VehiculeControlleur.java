package com.example.controlleur;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.example.model.Avion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.repos.TokenRepository;
import com.example.repos.AvionRepository;

import com.example.model.Token;

import com.example.model.Error;
@CrossOrigin(origins= {"*"},maxAge = 4800, allowCredentials = "false" )
@RestController
public class VehiculeControlleur {
	@Autowired
	AvionRepository vr;
	@Autowired 
	TokenRepository tr;
	@GetMapping(value = "/avions")
	public HashMap<String,Object> listAvion(){
		HashMap<String,Object> ray=new HashMap<>();
		ray.put("data",vr.findAll());
		return ray;
	}
	@PostMapping(value="/vehiculees")
    public HashMap<String,Object> listVehicule(@RequestBody Token tok){
		List<Avion> ls=vr.findAll();
		HashMap<String,Object> ray=new HashMap<>();
		if(tok!=null){
			Token vao2=new Token();
			if(tok.getToken().startsWith("Bearer ")){
				vao2=tr.findByToken(tok.getToken().substring(7, tok.getToken().length()));;
			}else{
				ray.put("error", new Error("404","not bearer"));
				return ray;
			}
			
			if(Token.checkIfEndSession(vao2)){
				ray.put("error", new Error("404","end session"));
				return ray;
			}
		}
		if(ls.size()>0) {
			ray.put("data",ls);
			return ray;
		}
		 ray.put("error", new Error("404","not found"));
        return ray;
    }
	@GetMapping("/vehicules/{id}")
    public HashMap<String, Object> avoirVehicule(@PathVariable Long id,@RequestBody Token tok){
		Avion vec=vr.findByIdAvion(id);
		HashMap<String,Object> ray=new HashMap<>();
		if(tok!=null){
			if(tok!=null){
				Token vao2=new Token();
				if(tok.getToken().startsWith("Bearer ")){
					vao2=tr.findByToken(tok.getToken().substring(7, tok.getToken().length()));;
				}else{
					ray.put("error", new Error("404","not bearer"));
					return ray;
				}
				
				if(Token.checkIfEndSession(vao2)){
					ray.put("error", new Error("404","end session"));
					return ray;
				}
			}
		}
		if(vec!=null) {
			
			ray.put("data",vec);
			return ray;
		}
		ray.put("error", new Error("404","not found"));
		return ray;
    }
    @DeleteMapping("/vehicules/{id}")
    public HashMap<String, Object> supprimerVehicule(@PathVariable Long id,@RequestBody Token tok){
    	HashMap<String,Object> ray=new HashMap<>();
		if(tok!=null){
			if(tok!=null){
				Token vao2=new Token();
				if(tok.getToken().startsWith("Bearer ")){
					vao2=tr.findByToken(tok.getToken().substring(7, tok.getToken().length()));;
				}else{
					ray.put("error", new Error("404","not bearer"));
					return ray;
				}
				
				if(Token.checkIfEndSession(vao2)){
					ray.put("error", new Error("404","end session"));
					return ray;
				}
			}
		}
    	try {
    		vr.deleteById(id);
    		LocalDate ra;
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		
    		ray.put("error", new Error("404","not found"));
    	}
        return ray;
    }
    @PutMapping("/vehicules")
    public HashMap<String,Object> updateVehicule(@RequestBody Avion updtVehicule,@RequestBody Token tok){
    	
    	HashMap<String,Object> ray=new HashMap<>();
			if(tok!=null){
				if(tok!=null){
					Token vao2=new Token();
					if(tok.getToken().startsWith("Bearer ")){
						vao2=tr.findByToken(tok.getToken().substring(7, tok.getToken().length()));;
					}else{
						ray.put("error", new Error("404","not bearer"));
						return ray;
					}
					
					if(Token.checkIfEndSession(vao2)){
						ray.put("error", new Error("404","end session"));
						return ray;
					}
				}
			}
    	try {
    		vr.save(updtVehicule);
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		
    		ray.put("error", new Error("404","not found"));
    	}
        return ray;
    }
    @PostMapping("/vehicules")
    public HashMap<String,Object> insererVehicule(@RequestBody Avion newVehicule,@RequestBody Token tok){
    	HashMap<String,Object> ray=new HashMap<>();
		if(tok!=null){
			if(tok!=null){
				Token vao2=new Token();
				if(tok.getToken().startsWith("Bearer ")){
					vao2=tr.findByToken(tok.getToken().substring(7, tok.getToken().length()));;
				}else{
					ray.put("error", new Error("404","not bearer"));
					return ray;
				}
				
				if(Token.checkIfEndSession(vao2)){
					ray.put("error", new Error("404","end session"));
					return ray;
				}
			}
		}
    	try {
    		vr.save(newVehicule);
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		ray.put("error", new Error("404","not found"));
    	}

        return ray;
    }
	
}
