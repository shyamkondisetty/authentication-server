package com.thoughtworks.authserver.customclientdetails;


import com.thoughtworks.authserver.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client" )
public class CustomClientController {

    @Autowired
    CustomClientService customClientService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    CustomClient create(@RequestBody CustomClient customClient) {
        return customClientService.create(customClient);
    }
}
