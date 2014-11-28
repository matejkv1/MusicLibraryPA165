package sk.matejkvassay.musiclibrary.controllers;

import java.beans.PropertyEditorSupport;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrary.validation.SongSpringValidation;
import sk.matejkvassay.musiclibrarybackendapi.Dto.AlbumDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.Dto.SongDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.AlbumService;
import sk.matejkvassay.musiclibrarybackendapi.Service.GenreService;
import sk.matejkvassay.musiclibrarybackendapi.Service.MusicianService;
import sk.matejkvassay.musiclibrarybackendapi.Service.SongService;

/**
 *
 * @author
 */
@Controller
@RequestMapping("/song")
public class SongController {

    final static Logger log = LoggerFactory.getLogger(MusicianController.class);

    @Inject
    private SongService songService;
    @Inject
    private MusicianService musicianService;
    @Inject
    private AlbumService albumService;
    @Inject
    private GenreService genreService;

    @Inject
    private MessageSource messageSource;

    @Inject
    private SongSpringValidation validator;

    @ModelAttribute("songs")
    public List<SongDto> allSongs() {
        return songService.getAllSongs();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("song", new SongDto());
        return "song/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showDetail(@PathVariable long id, Model model) {
        log.debug("showDetail(): displaying song details");
        SongDto song = songService.getSongById(id);
        model.addAttribute("song", song);
        
        model.addAttribute("album", albumService.getAlbumBySong(song));
        model.addAttribute("genre", genreService.findGenreBySong(song));
        model.addAttribute("musician", musicianService.getMusicianBySong(song));
        
        return "song/detail";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("song", new SongDto());
        model.addAttribute("musicians", musicianService.getAllMusicians());
        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("genres", genreService.getAllGenres());

        log.debug("edit(model={})", model);

        return "song/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes, Locale locale, UriComponentsBuilder uriBuilder) {
        SongDto songToDelete = songService.getSongById(id);
        songService.removeSong(songToDelete);
        redirectAttributes.addFlashAttribute(
                "message",
                messageSource.getMessage("song.delete.message", new Object[]{songToDelete.getTitle()}, locale)
        );
        return "redirect:" + uriBuilder.path("/song/list").build();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update_form(@PathVariable long id, Model model) {
        SongDto song = songService.getSongById(id);
        model.addAttribute("song", song);
        model.addAttribute("musicians", musicianService.getAllMusicians());
        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("genres", genreService.getAllGenres());

        log.debug("update_form(model={})", model);
        return "song/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("song") SongDto song, BindingResult bindingResult, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Locale locale, Model model) throws MusicianNameNullException {

        if (bindingResult.hasErrors()) {
            log.debug("binding errors");
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.debug("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                log.debug("FieldError: {}", fe);
            }
            model.addAttribute("musicians", musicianService.getAllMusicians());
            model.addAttribute("albums", albumService.getAllAlbums());
            model.addAttribute("genres", genreService.getAllGenres());

            return "song/edit";
        }

        if (song.getId() == null) {
            songService.addSong(song);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("song.add.message", new Object[]{song.getTitle()}, locale)
            );
        } else {
            songService.updateSong(song);
            redirectAttributes.addFlashAttribute(
                    "message",
                    messageSource.getMessage("song.edit.message", new Object[]{song.getTitle()}, locale)
            );
        }
        return "redirect:" + uriBuilder.path("/song/list").build();
    }

    @InitBinder("song")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @InitBinder("song")
    protected void initBinder2(WebDataBinder binder) {
        binder.registerCustomEditor(MusicianDto.class, new MusicianEditor(this.musicianService));
        binder.registerCustomEditor(AlbumDto.class, new AlbumEditor(this.albumService));
        binder.registerCustomEditor(GenreDto.class, new GenreEditor(this.genreService));
    }

    // private classes for transforming string into DTO
    class MusicianEditor extends PropertyEditorSupport {

        private final MusicianService musicianService;

        public MusicianEditor(MusicianService musicianService) {
            this.musicianService = musicianService;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            MusicianDto m = musicianService.getMusicianById(Long.parseLong(text));
            setValue(m);
        }
    }

    class AlbumEditor extends PropertyEditorSupport {

        private final AlbumService albumService;

        public AlbumEditor(AlbumService albumService) {
            this.albumService = albumService;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            AlbumDto a = albumService.getAlbumById(Long.parseLong(text));
            setValue(a);
        }
    }

    class GenreEditor extends PropertyEditorSupport {

        private final GenreService genreService;

        public GenreEditor(GenreService genreService) {
            this.genreService = genreService;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            GenreDto g = genreService.findGenreById(Long.parseLong(text));
            setValue(g);
        }
    }

}
