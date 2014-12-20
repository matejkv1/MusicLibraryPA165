package sk.matejkvassay.musiclibrary.rest;

import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sk.matejkvassay.musiclibrary.rest.exceptions.GenreInvalidArgumentException;
import sk.matejkvassay.musiclibrary.rest.exceptions.GenreNotFoundException;
import sk.matejkvassay.musiclibrarybackendapi.Dto.GenreDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.GenreService;

@RestController
@RequestMapping("/rest/genres")
public class GenreRestController {
    
    @Inject
    private GenreService genreService;
    
    /**
     * Gets all genres in plain text
     */
    @RequestMapping(value="", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getGenres() {
        return genreService.getAllGenres().toString();
    }
    
    /**
     * Gets all genres in json
     */
    @RequestMapping(value="", method=RequestMethod.GET, headers="Accept=application/json")
    public List<GenreDto> getGenresFormated() {
        return genreService.getAllGenres();
    }
    
    /**
     * Returns genre in plain text
     * @param id genre id
     */
    @RequestMapping(value="{id}", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getGenreAsText(@PathVariable Integer id) {
        GenreDto g = genreService.findGenreById(id);
        if (g == null) throw new GenreNotFoundException(String.valueOf(id));
        
        return g.toString();
    }
    
    /**
     * Returns genre in supported format (xml, json)
     * @param id genre id
     */
    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public GenreDto getGenre(@PathVariable Integer id) {
        GenreDto g = genreService.findGenreById(id);
        if (g == null) throw new GenreNotFoundException(String.valueOf(id));
        
        return g;
    }
    
    /**
     * Adds new genre
     */
    @RequestMapping(value="new", method=RequestMethod.POST)
    public ResponseEntity<String> addGenreInJson(@RequestBody GenreDto genre) {
        List<GenreDto> genres = genreService.getAllGenres();
        for (GenreDto g: genres) {
            if (g.getName().equals(genre.getName())) throw new GenreInvalidArgumentException(String.valueOf(genre.getId()), "Name exists");
        }
        
        genreService.addGenre(genre);
        
        return new ResponseEntity<String>(ServletUriComponentsBuilder.fromCurrentContextPath().path("/rest/genres/{id}")
                .buildAndExpand(genreService.findGenreByName(genre.getName()).getId()).toUriString(), HttpStatus.OK);
    }
    
    /**
     * Edits existing genre
     * 
     * @throws GenreNotFoundException 
     */
    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> editGenreInJson(@PathVariable Long id, @RequestBody GenreDto genre) throws GenreNotFoundException {
        GenreDto g = genreService.findGenreById(id);
        if (g == null) throw new GenreNotFoundException(String.valueOf(id));
        
        List<GenreDto> genres = genreService.getAllGenres();
        for (GenreDto ge: genres) {
            if (ge.getId() == id) continue;
            if (ge.getName().equals(genre.getName())) throw new GenreInvalidArgumentException(String.valueOf(genre.getId()), "Name exists");
        }
        
        g.setDescription(genre.getDescription());
        g.setName(genre.getName());
        
        genreService.updateGenre(g);
        
        return new ResponseEntity<String>(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/")
                .toUriString(), HttpStatus.OK);
    }
    
    /**
     * Deletes existing genre
     * @param id genre id
     * @throws GenreNotFoundException 
     */
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void deleteGenre(@PathVariable Integer id) throws GenreNotFoundException{
        GenreDto g = genreService.findGenreById(id);
        if (g == null) throw new GenreNotFoundException(String.valueOf(id));
        
        genreService.removeGenre(g);
    }
    
}
