package sk.matejkvassay.musiclibrary.controllers;

import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import sk.matejkvassay.musiclibrary.daoimpl.exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrary.validation.MusicianSpringValidation;
import sk.matejkvassay.musiclibrarybackendapi.dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.service.AlbumService;
import sk.matejkvassay.musiclibrarybackendapi.service.MusicianService;

/**
 *
 * @author
 */
@Controller
@RequestMapping("/musician")
public class MusicianController {
    
    final static Logger LOG = LoggerFactory.getLogger(MusicianController.class);
    
    @Inject
    private MusicianService musicianService;
    
    @Inject
    private AlbumService albumService;
    
    @Inject
    private MessageSource messageSource;
    
    @Inject
    private MusicianSpringValidation validator;
    
    @ModelAttribute("musicians")
    public List<MusicianDto> allMusicians() {
        LOG.debug("allMusicians()");
        return musicianService.getAllMusicians();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        LOG.debug("listMusician()");
        model.addAttribute("musician", new MusicianDto());
        return "musician/list";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("q") String q, Model model) {
        LOG.debug("search()");
        model.addAttribute("musician", new MusicianDto());
        if (q.isEmpty()) {
            return "redirect:list";
        } else {
            model.addAttribute("q", q);
            model.addAttribute("musicians", musicianService.getMusiciansByName(q));
            return "musician/search";
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showDetail(@PathVariable long id, Model model) {
        LOG.debug("showDetail(): displaying musician details");
        MusicianDto musician = musicianService.getMusicianById(id);
        model.addAttribute("musician", musician);
        
        model.addAttribute("albums", albumService.getAlbumsByMusician(musician));
        return "musician/detail";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        LOG.debug("delete({})", id);
        MusicianDto musician = musicianService.getMusicianById(id);
        musicianService.removeMusician(musician);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("musician.delete.message", new Object[]{musician.getName()}, locale)
        );
        return "redirect:" + uriBuilder.path("/musician/list").build();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNew(Model model) {
        model.addAttribute("musician", new MusicianDto());
        
        LOG.debug("addNew(): editing musician");
        
        return "musician/edit";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable long id, Model model) {
        MusicianDto musician = musicianService.getMusicianById(id);
        model.addAttribute("musician", musician);
        LOG.debug("update_form(model={})", model);
        return "musician/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("musician") MusicianDto musician, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) throws MusicianNameNullException {
        LOG.debug("update(locale={}, musician={})", locale, musician);
        if (bindingResult.hasErrors()) {
            LOG.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                LOG.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                LOG.debug("FieldError: {}", fe);
            }
            return "musician/edit";
        }
        if (musician.getId() == null) {
            musicianService.addMusician(musician);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("musician.add.message", new Object[]{musician.getName()}, locale)
            );
        } else {
            musicianService.updateMusician(musician);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("musician.updated.message", new Object[]{musician.getName()}, locale)
            );
        }
        return "redirect:" + uriBuilder.path("/musician/list").build();
    }
    
    
    @InitBinder()
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}
