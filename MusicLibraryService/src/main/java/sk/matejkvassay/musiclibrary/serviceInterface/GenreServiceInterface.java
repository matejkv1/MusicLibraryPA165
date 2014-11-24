/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrary.serviceInterface;

import java.util.List;
import sk.matejkvassay.musiclibrary.Dto.GenreDto;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public interface GenreServiceInterface {
    /**
     * Get count of all genres.
     * @return Count of all genres.
     */
    public int getAllGenresCount();   
    
    /**
     * Get sublist of ordered list of all genres.
     * @param from Limit from.
     * @param to Limit to.
     * @return Sublist of list of all genres.
     */
    public List<GenreDto> getAllGenresOrderedByNameLimit(int from, int to);
}
