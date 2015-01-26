
package sk.matejkvassay.musiclibrary.controllers;

import java.util.Locale;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import sk.matejkvassay.musiclibrary.validation.UserSpringValidation;
import sk.matejkvassay.musiclibrarybackendapi.dto.UserDto;
import sk.matejkvassay.musiclibrarybackendapi.security.PwEncoding;
import sk.matejkvassay.musiclibrarybackendapi.security.Role;
import sk.matejkvassay.musiclibrarybackendapi.service.UserService;

/**
 *
 * @author
 */
@Controller
@RequestMapping("/")
public class UserController {
    
    final static Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    @Inject
    private UserService userService;
    
    @Inject
    private MessageSource messageSource;
    
    @Inject
    private UserSpringValidation validator;
    
    
    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_panel";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        Model model) {

        if (error != null) {
            model.addAttribute("error", "Log in error.");
        }

        return "login";
 
    }
    
    @RequestMapping(value = "/user/new", method = RequestMethod.POST)
    public String addNew(@Valid @ModelAttribute("user") UserDto user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder,
            Locale locale, Model model,
            @RequestParam(value = "user_role", required = false) Boolean userRole) {
        
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "user_panel";
        }
        
        user.setPassword(PwEncoding.getPwHash(user.getPassword()));
        if (userRole != null) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        userService.addUser(user);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("user.add.message", new Object[]{user.getUsername(), user.getId()}, locale)
        );
        
        return "redirect:" + uriBuilder.path("/user").build();
        
    }
    
    
}
