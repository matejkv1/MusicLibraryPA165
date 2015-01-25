
package sk.matejkvassay.musiclibrary.controllers;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sk.matejkvassay.musiclibrarybackendapi.service.UserService;

/**
 *
 * @author
 */
@Controller
@RequestMapping("/")
public class UserController {
    
    final static Logger LOG = LoggerFactory.getLogger(AlbumController.class);
    
    @Inject
    private UserService userService;
    
    @Inject
    private MessageSource messageSource;
    
//    @InitBinder("user")
//    protected void initBinder(WebDataBinder binder) {
//        binder.addValidators(new UserSpringValidation());
//    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        LOG.debug("list(): displaying all albums");
        // TODO order by title
        
        return "user_panel";
    }
    
    
}
