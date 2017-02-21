package org.javaero.demo.springboot;

import org.javaero.demo.springboot.entity.Participant;
import org.javaero.demo.springboot.entity.ParticipantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationRestController {

    @Value("${application.name}")
    private String appName;

    @Autowired
    private ParticipantDao participantDao;
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from " + appName + "!";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(
            HttpEntity<String> requestEntity // gets the raw data of the request
    ) {
        return requestEntity.getBody();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Participant insertParticipant(
            @RequestBody // will fillup all the fields of the pojo that matches the json body
                    Participant participant) {
        Participant result = participantDao.save(participant);
        return  result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public Participant updateParticipant(
            @RequestBody // will fillup all the fields of the pojo that matches the json body
                    Participant participant
    ) {
        if (participantDao.exists(participant.getId())) {
            return participantDao.save(participant);
        }
        throw new RuntimeException("Invalid record " + participant.toString());
    }

}
