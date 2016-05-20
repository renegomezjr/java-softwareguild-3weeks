/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrary.dao;

import com.swcguild.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class DVDLibraryDAO {

    HashMap<String, DVD> DVDList = new HashMap<>();

    public static final String DVD_LIBRARY_FILE = "DVDLibrary.txt";
    private static final String DELIMITER = "::";

    public void loadDVDLibrary() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY_FILE)));

        while (sc.hasNextLine()) {
            String recordLine = sc.nextLine();
            String[] recordProperties = recordLine.split(DELIMITER);

            if (recordProperties.length != 6) {
                continue;
            }

            String title = recordProperties[0];
            String studio = recordProperties[1];
            int releaseDate = Integer.parseInt(recordProperties[2]);
            String directorName = recordProperties[3];
            String rating = recordProperties[4];
            String notes = recordProperties[5];

            DVD dvdFromFile = new DVD(title, studio, releaseDate, directorName,
                    rating, notes);

            DVDList.put(dvdFromFile.getTitle(), dvdFromFile);
        }
    }

    public void writeDVDLibrary() throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));

        Collection<DVD> allDVDsInHashMap = DVDList.values();

        for (DVD d : allDVDsInHashMap) {
            writer.println(d.getTitle() + DELIMITER
                    + d.getStudio() + DELIMITER
                    + d.getReleaseDate() + DELIMITER + d.getDirectorName()
                    + DELIMITER + d.getRating() + DELIMITER + d.getNotes());
        }
        writer.flush();
        writer.close();
    }

    public void addDVD(DVD dvdToAdd) {
        DVDList.put(dvdToAdd.getTitle(), dvdToAdd);
    }

    public DVD removeDVD(String dvdToRemove) {
        return DVDList.remove(dvdToRemove);
    }

    public Set listAll() {
        return DVDList.keySet();
    }

    public DVD findDVDInfo(String dvdName) {
        return DVDList.get(dvdName);
    }

    public ArrayList searchByKeywords(String searchTerm) {
        Set<String> keySet = DVDList.keySet();
        ArrayList<String> searchResult = new ArrayList<>();
        
        for(String k : keySet){
            if(k.contains(searchTerm)){
               searchResult.add(k);
            }
            
        }
        return searchResult;
    }

    public DVD editDVD(String dvdToEdit) {
        return DVDList.get(dvdToEdit);

    }
}
