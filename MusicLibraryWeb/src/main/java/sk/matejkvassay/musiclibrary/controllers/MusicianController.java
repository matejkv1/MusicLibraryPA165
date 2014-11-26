package sk.matejkvassay.musiclibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author
 */
@Controller

public class MusicianController {
    
    
    @RequestMapping(value = "/musician",method = RequestMethod.GET)
    public String show_page() {
        return "musician/list";
    }
}
