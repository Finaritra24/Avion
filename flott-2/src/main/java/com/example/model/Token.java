package com.example.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name="Token")
public class Token {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_generator")
    Long idToken;
    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "identifiant", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    Log log;
    String token;
    Timestamp dateExpiration;
    @Transient
    String type="Bearer";
    @Transient
    static int timeAdd=10;

    public Token() {
    }

    public Token(Log log, String token, Timestamp dateExpiration) {
        this.log = log;
        this.token = token;
        this.dateExpiration = dateExpiration;
    }

    public Long getIdToken() {
        return this.idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public Log getLog() {
        return this.log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateExpiration() {
        return this.dateExpiration;
    }

    public void setDateExpiration(Timestamp dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public String generateToken()throws Exception{
        GeneratorEncrypt gr=new GeneratorEncrypt();
        return gr.generateEncrypt(this.log);
    }
    public String refresh()throws Exception{
        GeneratorEncrypt gr=new GeneratorEncrypt();
        return gr.refreshtoken(this);
    }
    public static boolean checkIfEndSession(Token ray){
        if(ray!=null){
            Long timeMil=ray.getDateExpiration().getTime();
            Timestamp vao2=new Timestamp(timeMil+timeAdd*60*1000);
            Date date = new Date();
            Timestamp present = new Timestamp(date.getTime());
            if(present.after(vao2)){
                return true;
            }else{
                return false;
            }
        }
          
        return true;
    }


}
