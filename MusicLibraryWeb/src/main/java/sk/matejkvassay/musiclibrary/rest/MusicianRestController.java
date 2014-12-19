package sk.matejkvassay.musiclibrary.rest;

import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sk.matejkvassay.musiclibrary.DaoImpl.Exception.MusicianNameNullException;
import sk.matejkvassay.musiclibrary.rest.exceptions.MusicianInvalidArgumentException;
import sk.matejkvassay.musiclibrary.rest.exceptions.MusicianNotFoundException;
import sk.matejkvassay.musiclibrarybackendapi.Dto.MusicianDto;
import sk.matejkvassay.musiclibrarybackendapi.Service.MusicianService;

@RestController
@RequestMapping("/rest/musicians")
public class MusicianRestController {
    
    @Inject
    private MusicianService musicianService;
    
    /**
     * Get all musicians in plain text
     */
    @RequestMapping(value="", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getMusicians() {
        return musicianService.getAllMusicians().toString();
    }
    
    /**
     * Get single musician in plain text
     * @param id musician id
     */
    @RequestMapping(value="{id}", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getMusician(@PathVariable Long id) {
        return musicianService.getMusicianById(id).toString();
    }
    
    /**
     * Get all musician in supported format (xml, json)
     * @param id musician id
     */
    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public MusicianDto getMusicianAsJson(@PathVariable Long id) {
        return musicianService.getMusicianById(id);
    }
    
    /**
     * Get musician count
     */
    @RequestMapping(value="count", method=RequestMethod.GET, headers="Accept=text/plain")
    public String getMusicianCount() {
        return String.valueOf(musicianService.getAllMusicians().size());
    }
    
    /**
     * Add new musician
     * 
     * @throws MusicianNameNullException
     * @throws MusicianInvalidArgumentException 
     */
    @RequestMapping(value="new", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMusicianInJson(@RequestBody MusicianDto musician) throws MusicianNameNullException, MusicianInvalidArgumentException {
        List<MusicianDto> musicians = musicianService.getAllMusicians();
        for (MusicianDto m: musicians) {
            if (m.getName().equals(musician.getName())) throw new MusicianInvalidArgumentException(String.valueOf(musician.getId()), "Name exists");
        }
        
        try {
            musicianService.addMusician(musician);
        } catch (MusicianNameNullException ex) {
            throw new MusicianInvalidArgumentException(String.valueOf(musician.getId()), ex.getMessage());
        }
        
        return new ResponseEntity<String>(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(musician.getId()).toUriString(), HttpStatus.OK);
    }
    
    /**
     * Edit existing musician
     * 
     * @throws MusicianNotFoundException
     * @throws MusicianNameNullException 
     */
    @RequestMapping(value="{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editMusicianInJson(@PathVariable Long id, @RequestBody MusicianDto musician) throws MusicianNotFoundException, MusicianNameNullException {
        MusicianDto m = musicianService.getMusicianById(id);
        if (m == null) throw new MusicianNotFoundException(String.valueOf(id));

        List<MusicianDto> musicians = musicianService.getAllMusicians();
        for (MusicianDto mu: musicians) {
            if (mu.getId() == id) continue;
            if (mu.getName().equals(musician.getName())) throw new MusicianInvalidArgumentException(String.valueOf(musician.getId()), "Name exists");
        }
        
        m.setBiography(musician.getBiography());
        m.setName(musician.getName());
        
        try {
            musicianService.updateMusician(m);
        } catch (MusicianNameNullException ex) {
            throw new MusicianInvalidArgumentException(String.valueOf(musician.getId()), "Name null");
        }
        
        return new ResponseEntity<String>(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(musician.getId()).toUriString(), HttpStatus.OK);
    }
    
    /**
     * Delete existing musician
     * @param id musician id
     * @throws MusicianNotFoundException 
     */
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void deleteMusician(@PathVariable Long id) throws MusicianNotFoundException{
        MusicianDto m;
        try {
            m = musicianService.getMusicianById(id);
        } catch (DataAccessException ex) {
            throw new MusicianNotFoundException(String.valueOf(id));
        }
        
        musicianService.removeMusician(m);
    }
    
}
