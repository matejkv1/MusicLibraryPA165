package sk.matejkvassay.musiclibrary.rest;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.matejkvassay.musiclibrarybackendapi.Service.GenreService;

@RestController
@RequestMapping("/rest/genres")
public class GenreRestController {
    
    @Inject
    private GenreService genreService;
    
    // get all
    @RequestMapping(value="", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getGenres() {
        return genreService.getAllGenres().toString();
    }
    
    // get one
    @RequestMapping(value="{id}", method=RequestMethod.GET, headers="Accept=text/plain")
    public void getGenre(@PathVariable Integer id) {
        
    }
    
    // get one in json
    @RequestMapping(value="{id}", method=RequestMethod.GET, headers="Accept=application/json")
    public void getGenreAsJson(@PathVariable Integer id) {
        
    }
    
    // get count
    @RequestMapping(value="count", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getGenreCount(@PathVariable Integer id) {
        return "";
    }
    
    // add as json
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addGenreInJson() {
        
    }
    
    // edit
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void editGenreInJson() {
        
    }
    
    // delete
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void deleteGenre(@PathVariable Integer id) {
        
    }
    
}
