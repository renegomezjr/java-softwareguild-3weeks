/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrary;

import com.swcguild.dvdlibrary.dao.DVDLibraryDAO;
import com.swcguild.dvdlibrary.ops.DVDLibraryController;
import java.io.IOException;

/**
 *
 * @author apprentice
 */
public class DVDLibraryApp {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        DVDLibraryController controller = new DVDLibraryController();
        
        controller.run();
        
    }
    
}
