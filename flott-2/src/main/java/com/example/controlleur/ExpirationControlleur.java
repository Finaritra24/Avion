package com.example.controlleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.example.model.Expiration;
import com.example.model.Kilometrage;
import com.example.model.Token;
import com.example.repos.ExpirationRepository;
import com.example.repos.KilometrageRepository;
import com.example.repos.TokenRepository;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
public class ExpirationControlleur {
	@Autowired
	ExpirationRepository er;
	@Autowired 
	TokenRepository tr;
	@GetMapping("/Expiration/{mois}")
    public HashMap<String,Object> ParVehicule(@PathVariable int mois,@RequestBody Token tok){
        List<Expiration>lm=er.findAll();
            ArrayList<Expiration>expi=Expiration.expireAt(lm, mois);
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
		if(expi.size()>0) {
			ray.put("data",expi);
			return ray;
		}
		ray.put("error", new Error("404","not found"));
        return ray;
    }
    @PostMapping("/Expirations")
    public HashMap<String,Object> insererExpiration(@RequestBody Expiration newKilometrage,@RequestBody Token tok){
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
    		er.save(newKilometrage);
    		ray.put("data","nety e");
    	}catch(Exception e) {
    		ray.put("error", new Error("404","not found"));
    	}

        return ray;
    }
	
}
