package sk.matejkvassay.musiclibrary.rest;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.matejkvassay.musiclibrarybackendapi.Service.MusicianService;

@RestController
@RequestMapping("/rest/musicians")
public class MusicianRestController {
    
    @Inject
    private MusicianService musicianService;
    
    // get all
    @RequestMapping(value="", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getMusicians() {
        return musicianService.getAllMusicians().toString();
    }
    
    // get one
    @RequestMapping(value="{id}", method=RequestMethod.GET, headers="Accept=text/plain")
    public void getMusician(@PathVariable Integer id) {
        
    }
    
    // get one in json
    @RequestMapping(value="{id}", method=RequestMethod.GET, headers="Accept=application/json")
    public void getMusicianAsJson(@PathVariable Integer id) {
        
    }
    
    // get count
    @RequestMapping(value="count", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getMusicianCount(@PathVariable Integer id) {
        return "";
    }
    
    // add as json
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addMusicianInJson() {
        
    }
    
    // edit
    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public void editMusicianInJson() {
        
    }
    
    // delete
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void deleteMusician(@PathVariable Integer id) {
        
    }
    
}
