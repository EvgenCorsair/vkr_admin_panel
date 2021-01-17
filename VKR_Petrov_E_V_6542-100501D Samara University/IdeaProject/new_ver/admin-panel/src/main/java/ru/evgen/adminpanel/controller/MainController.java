package ru.evgen.adminpanel.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.evgen.adminpanel.entity.XMLRequest;
import ru.evgen.adminpanel.entity.XMLResponse;

import javax.servlet.http.HttpServletResponse;
import java.beans.Encoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Controller
public class MainController {

    public String elsysIP;
    public String elsysPass;


    public long CID;
    public long SID;

    @GetMapping(path = "/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping(path = "/users")
    public String users(Model model, Principal principal) {
        return "users";
    }
    @GetMapping(path = "/roles")
    public String roles(Model model, Principal principal) {
        return "roles";
    }
    @GetMapping(path = "/connect")
    public String connect(Model model, Principal principal) {
        return "connect";
    }
    @GetMapping(path = "/config")
    public String config(Model model, Principal principal) {
        return "config";
    }
    @GetMapping(path = "/control")
    public String control(Model modelб, Principal principal) {
        return "control";
    }
    @GetMapping(path = "/")
    public String index(Model model, Principal principal) {
        return "index";
    }

    @GetMapping(path = "/initSession")
    public void initSession(@RequestParam String ip, @RequestParam String password){
        RestTemplate restTemplate = new RestTemplate();
        this.elsysIP = ip;
        this.elsysPass = password;

        XMLRequest body = new XMLRequest();
        body.setCID(0);
        body.setSIDResp(0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<XMLRequest> request = new HttpEntity<XMLRequest>(body, headers);
        ResponseEntity<XMLResponse> response = restTemplate.postForEntity("http://"+elsysIP+":80", request, XMLResponse.class);

        this.SID = response.getBody().getSID();
        this.CID++;



    }

    public void sendMessage(@RequestParam XMLRequest request) throws NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        protectHeaders(headers,request.toString());
        ResponseEntity<XMLResponse> response = restTemplate.postForEntity("http://"+elsysIP+":80", request, XMLResponse.class);
        this.SID = response.getBody().getSID();
        this.CID++;
    }


    public void protectHeaders(HttpHeaders headers, String content) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String Nonce = encoder.encodeToString(bytes);

        Date created = new Date();

        String postUri ="POST /xmlapi/std HTTP/1.1";

        String concat = Nonce+created+postUri+content;

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] encodedhash = digest.digest(
                concat.getBytes(StandardCharsets.UTF_8));
        String Digest = Base64.getUrlEncoder().encodeToString(encodedhash);

        headers.add("ECNC-Auth","Nonce="+Nonce+";Created="+created.toString()+";Digest="+digest.toString());
    }

}
