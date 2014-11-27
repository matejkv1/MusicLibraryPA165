package sk.matejkvassay.musiclibrary.controllers;

import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import sk.matejkvassay.musiclibrary.validation.AlbumSpringValidation;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.AlbumService;

/**
 *
 * @author Matej Bordac
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    
    final static Logger log = LoggerFactory.getLogger(AlbumController.class);
    
    @Inject
    private AlbumService albumService;

    @Inject
    private MessageSource messageSource;
    
//    TODO: probably delete
//    @ModelAttribute("albums")
//    public List<AlbumDto> allAlbums() {
//        log.debug("allAlbums(): settings albums attribute");
//        return albumService.getAllAlbums();
//    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new AlbumSpringValidation());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list(): displaying all albums");
        // TODO order by title?
        model.addAttribute("albums", albumService.getAllAlbums());
        // TODO
        //model.addAttribute("musician", musicianService);
        return "album/list";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showDetail(@PathVariable long id, Model model) {
        log.debug("showDetail(): displaying album details");
        model.addAttribute("album", albumService.getAlbumById(id));
        // TODO, order songs by posiion in album
        //model.addAttribute("songs", songService);
        //model.addAttribute("musician", musicianService);
        return "album/detail";
    }
    
    // NYI
    /*@RequestMapping(value = "/search/{searchStr}", method = RequestMethod.GET)
    public String searchByTitle(@PathVariable String searchStr, Model model) {
        
        return "album/list";
    }*/
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        log.debug("delete(): deleting stuff");
        AlbumDto album = albumService.getAlbumById(id);
        albumService.removeAlbum(album);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("album.delete.message", new Object[]{album.getTitle(), album.getId()}, locale)
        );
        return "redirect:" + uriBuilder.path("/album").build();
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUpdateForm(@PathVariable long id, Model model) {
        AlbumDto album = albumService.getAlbumById(id);
        model.addAttribute("album", album);
        // TODO
        //model.addAttribute("musicians", musicianService.getAll);
        log.debug("getUpdateForm(): editing album");
        return "album/edit";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute AlbumDto album, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale) {
        log.debug("update(): updating DB");
        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: ", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: ", fe);
            }
            return album.getId() == null ? "/album" : "album/edit";
        }
        
        if (album.getId() == null) {
            log.debug("adding album");
            albumService.addAlbum(album);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("album.add.message", new Object[]{album.getTitle(), album.getId()}, locale)
            );
        } else {
            log.debug("changing album");
            albumService.updateAlbum(album);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("album.update.message", new Object[]{album.getTitle(), album.getId()}, locale)
            );
        }
        
        return "redirect:" + uriBuilder.path("/album").build();
    }
    
    
    
    
}
