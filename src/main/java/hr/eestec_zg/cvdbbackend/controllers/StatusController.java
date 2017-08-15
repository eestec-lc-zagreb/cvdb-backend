package hr.eestec_zg.cvdbbackend.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class StatusController {

    @RequestMapping(
            value = "/status",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Map<String, String> getStatus() {
        return Collections.singletonMap("status", "OK");
    }

}
