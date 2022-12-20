package com.example.controlleur;

import java.util.HashMap;
import java.util.List;

import com.example.model.Avion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Error;
import com.example.model.Kilometrage;
import com.example.model.Token;
import com.example.repos.KilometrageRepository;
import com.example.repos.TokenRepository;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
public class KilometrageControlleur {
	@Autowired
	KilometrageRepository kr;
	@Autowired 
	TokenRepository tr;
	@GetMapping("/kilometrages/{idVehicule}")
    public HashMap<String,Object> kilometrageParVehicule(@PathVariable Long idVehicule,@RequestBody Token tok){
		Avion vec=new Avion();
		System.err.println(idVehicule);
		vec.setIdAvion(idVehicule);
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
		List<Kilometrage>li=kr.findByAvion(vec);
		System.err.println(li);
		if(li.size()>0) {
			ray.put("data",li);
			return ray;
		}
		ray.put("error", new Error("404","not found"));
        return ray;
    }
	/*@PutMapping("/kilometrages")
    public HashMap<String,Object> updateKilometrage(@RequestBody Kilometrage updtKilometrage){
		
    	
    	HashMap<String,Object> ray=new HashMap<>();
    	try {
    		kr.save(updtKilometrage);
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		
    		ray.put("error", new Error("404","not found"));
    	}
        return ray;
    }*/
	@PostMapping("/kilometrages")
    public HashMap<String,Object> insererKilometrage(@RequestBody Kilometrage newKilometrage,@RequestBody Token tok){
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
    		kr.save(newKilometrage);
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		ray.put("error", new Error("404","not found"));
    	}

        return ray;
    }
	@DeleteMapping("/kilometrages/{id}")
    public HashMap<String, Object> supprimerKilometrageVoiture(@PathVariable Long id,@RequestBody Token tok){
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
    		Avion vec=new Avion();
    		vec.setIdAvion(id);
    		kr.deleteByAvion(vec);
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		
    		ray.put("error", new Error("404","not found\n"+e.getMessage()));
    	}
        return ray;
    }
}
