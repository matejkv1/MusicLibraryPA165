package sk.matejkvassay.musiclibrary.controllers;

import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import sk.matejkvassay.musiclibrarybackendapi.Dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.MusicianService;

/**
 *
 * @author
 */
@Controller
@RequestMapping("/musician")
public class MusicianController {
    
    final static Logger log = LoggerFactory.getLogger(MusicianController.class);
    
    @Inject
    private MusicianService musicianService;
    
    @Inject
    private MessageSource messageSource;
    
    @ModelAttribute("musicians")
    public List<MusicianDto> allMusicians() {
        log.debug("allMusicians()");
        return musicianService.getAllMusicians();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("listMusician()");
        model.addAttribute("musician", new MusicianDto());
        return "musician/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        log.debug("delete({})", id);
        MusicianDto musician = musicianService.getMusicianById(id);
        musicianService.removeMusician(musician);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("book.delete.message", new Object[]{musician.getName(), musician.getBiography(), musician.getId()}, locale)
        );
        return "redirect:" + uriBuilder.path("/musician/list").build();
    }
    
    @RequestMapping(value = "/musician",method = RequestMethod.GET)
    public String show_page() {
        return "musician/list";
    }
}
