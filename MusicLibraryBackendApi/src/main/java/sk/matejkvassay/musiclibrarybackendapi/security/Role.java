/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibrarybackendapi.security;

/**
 *
 * @author Matej Kvassay <www.matejkvassay.sk>
 */
public enum Role {
    NONE(1), USER(2), ADMIN(3);
    
    private final int value;
    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
