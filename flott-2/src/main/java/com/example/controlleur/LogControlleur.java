package com.example.controlleur;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.*;
import com.example.model.Token;
import com.example.repos.LogRepository;
import com.example.repos.TokenRepository;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
public class LogControlleur {
    @Autowired
    LogRepository lr;
    @Autowired
    TokenRepository tr;
	/**
	 * @param log
	 * @return
	 */
	@PostMapping(value = "/signin")
    public  HashMap<String,Object> conn(@RequestBody Log log){
        HashMap<String,Object> ray=new HashMap<>();
        Log logBy=lr.findByMdp(log.getMdp());
        if (logBy!=null){
            List<Token>listT=tr.findByLogOrderByIdTokenDesc(logBy);
            if(listT.size()>0){
                String toke="";
                try{
                    toke=listT.get(0).refresh();
                }catch(Exception god){

                }
                listT.get(0).setToken(toke);
                listT.get(0).setDateExpiration(new Timestamp(new Date().getTime()));
                tr.save( listT.get(0));
                
                    listT.get(0).setIdToken(null);
                    listT.get(0).getLog().setMdp(null);
                    listT.get(0).getLog().setIdentifiant(null);
                    ray.put("data",listT.get(0));
            }else{
                Token token=new Token();
                token.setLog(logBy);
                String toke="";
                try{
                    toke=token.generateToken();
                }catch(Exception god){

                }
                token.setToken(toke);
                token.setDateExpiration(new Timestamp(new Date().getTime()));
                tr.save(token);
                listT=tr.findByLogOrderByIdTokenDesc(logBy);
                if(listT.size()>0){
                        listT.get(0).setIdToken(null);
                        listT.get(0).getLog().setMdp(null);
                        listT.get(0).getLog().setIdentifiant(null);
                        ray.put("data",listT.get(0));
                }
            }
        }else{
            ray.put("error", new com.example.model.Error("404","not found log"));
        }
        return ray;

    }
    @PostMapping(value = "/signout")
    public  HashMap<String,Object> logout(@RequestBody Log log){
        HashMap<String,Object> ray=new HashMap<>();
        if (log!=null){
            List<Token>tk=tr.findByLog(log);
            if(tk.size()>0){
                    for (Token token : tk) {
                        tr.delete(token);
                    }
            }
            ray.put("data","success");
        }else{
            ray.put("error", new com.example.model.Error("404","not found log"));
        }
        return ray;

    }
    @PostMapping(value = "/signup")
    public  HashMap<String,Object> insc(@RequestBody Log log){
        HashMap<String,Object> ray=new HashMap<>();
        if (log!=null){
            lr.save(log);
            ray.put("data","success");
        }else{
            ray.put("error", new com.example.model.Error("404","not found log"));
        }
        return ray;

    }
}
