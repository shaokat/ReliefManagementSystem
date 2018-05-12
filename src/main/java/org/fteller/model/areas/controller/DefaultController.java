package org.fteller.model.areas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Abdullah Al Amin on 4/1/2018.
 */
@Controller
public class DefaultController {

@GetMapping("")
    public String documenttion(){
    return "redirect:/swagger-ui.html";
}


}
