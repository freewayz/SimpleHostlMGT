/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fgc_kano.beansfiles;

/**
 *
 * @author root
 */
public class DatabaseException extends Exception{
    private static final long serialVersionUID = 1L;
    
    
    private Exception error;

    public DatabaseException(Exception error) {
        this.error = error;
    }

    public DatabaseException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        error.printStackTrace();
    }
    
    
    
    
    
}
