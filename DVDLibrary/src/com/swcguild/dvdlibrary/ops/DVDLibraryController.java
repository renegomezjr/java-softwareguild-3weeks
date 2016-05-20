/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrary.ops;

import com.swcguild.dvdlibrary.dao.DVDLibraryDAO;
import com.swcguild.dvdlibrary.dto.DVD;
import com.swcguild.dvdlibrary.ui.ConsoleIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class DVDLibraryController {

    private ConsoleIO console = new ConsoleIO();
    private DVDLibraryDAO daoLayer = new DVDLibraryDAO();

    public void run() throws IOException {

        boolean keepRunning = true;
        int menuSelect = 0;
        
        try {
            daoLayer.loadDVDLibrary();
        }catch (FileNotFoundException ex) {
            keepRunning = false;
            console.print("DVD for which you are looking is not currently available");
        }
        
        while (keepRunning) {
            printMenu();
            menuSelect = console.readInt("Select an option...");
            switch (menuSelect) {

                case 1:
                    addDVD();
                    break;

                case 2: //Remove DVD from Collection
                    removeDVD();
                    break;

                case 3: //List All Titles in Collection
                    listAllDVDs();
                    break;

                case 4: //Find Additional Information about Specific DVD
                    findDVDInfo();
                    break;

                case 5: //Keyword Search for particular DVD's
                    searchByKeywords();
                    break;

                case 6: //Edit DVD Library Information
                    editDVDsInCollection();
                    break;

                case 7: //exit
                    daoLayer.writeDVDLibrary(); 
                    keepRunning = false;
                    break;
            }
        }

    }

    private void printMenu() {
        console.print("Main Menu");
        console.print("1. Add DVD to Collection");
        console.print("2. Remove DVD from Collection");
        console.print("3. List All Titles in Collection");
        console.print("4. Find Additional Information about Specific DVD");
        console.print("5. Keyword Search for particular DVD's");
        console.print("6. Edit DVD Library Information");
        console.print("7. Exit");

    }

    private void addDVD() {
        String title = console.readString("Enter the title of the DVD you wish to add: ");
        String studio = console.readString("Enter the studio: ");
        int releaseDate = console.readInt("Enter the release year: ");
        String directorName = console.readString("Enter the director: ");
        String rating = console.readString("Enter the rating: ");
        String notes = console.readString("Enter notes: ");

        DVD dvdToAdd = new DVD(title, studio, releaseDate, directorName, rating, notes);

        daoLayer.addDVD(dvdToAdd);

        String doAgain = console.readString("To continue adding DVDs, type \"A\".  Otherwise, press any other letter");

        if (doAgain.equals("A")) {
            addDVD();
        }

    }

    private void removeDVD() {
        String title = console.readString("Enter the title of the DVD you wish to remove: ");
        DVD removedDVD = daoLayer.removeDVD(title);
        if (removedDVD == null) {
            console.print("This DVD was not found in the library.");
        } else {
            console.print(removedDVD.getTitle() + " was successfully removed from the library.");
        }

        String doAgain = console.readString("To remove another DVD, type \"A\".  Otherwise, press any other letter");

        if (doAgain.equals("A")) {
            removeDVD();
        }
    }

    private void listAllDVDs() {
        Set<String> listDVD = daoLayer.listAll(); //sort if possible
        console.print("Here are your titles: \n");
        for (String key : listDVD) {
            console.print(key + "\n");
        }
    }

    private void findDVDInfo() {
        String dvdName = console.readString("Please enter the name of the DVD for which\n"
                + " you would like detailed information: ");
        DVD dvdReturned = daoLayer.findDVDInfo(dvdName);
        console.print(dvdReturned.getTitle() + "\n"
                + "\t" + dvdReturned.getReleaseDate() + "\n"
                + "\t" + dvdReturned.getRating() + "\n"
                + "\t" + dvdReturned.getDirectorName() + "\n"
                + "\t" + dvdReturned.getStudio() + "\n"
                + "\t" + dvdReturned.getNotes() + "\n");
    }

    private void searchByKeywords() {
        String searchTerm = console.readString("enter keyword(s) to search DVD titles: ");
        ArrayList<String> returnedList = daoLayer.searchByKeywords(searchTerm);

        if (returnedList == null) {
            console.print("No titles matched your search.");
        } else {
            for (String s : returnedList) {
                console.print(s);
            }
        }
    }

    private void editDVDsInCollection() {
        String dvdToEdit = console.readString("Enter the title of the DVD to edit: ");
        DVD dvdEdit = daoLayer.editDVD(dvdToEdit);
        boolean keepEditing = true;

        if (dvdEdit == null) {
            console.print("That title was not found.");
        } else {
            console.print("Edit DVD Information");
            console.print("1. Edit Title");
            console.print("2. Edit Release Date");
            console.print("3. Edit Rating");
            console.print("4. Edit Director Name");
            console.print("5. Edit Studio");
            console.print("6. Edit Notes");
            console.print("7. Exit Edit DVD Menu");
            while (keepEditing) {

                switch (console.readInt("Enter a choice: ")) {
                    case 1: //edit title
                        console.print(dvdEdit.getTitle() + "\n");
                        String newTitle = console.readString("Enter new title: \n");
                        if (!newTitle.isEmpty()) {
                            dvdEdit.setTitle(newTitle);
                        }
                        break;
                    case 2: //edit date
                        console.print(dvdEdit.getReleaseDate() + "\n");
                        int newDate = console.readInt("Enter new date: \n");
                        dvdEdit.setReleaseDate(newDate);
                        break;
                    case 3: //edit rating
                        console.print(dvdEdit.getRating() + "\n");
                        String newRating = console.readString("Enter new rating: \n");
                        dvdEdit.setRating(newRating);
                        break;
                    case 4: //edit director
                        console.print(dvdEdit.getDirectorName() + "\n");
                        String getDirector = console.readString("Enter new director: \n");
                        dvdEdit.setDirectorName(getDirector);
                        break;
                    case 5: //edit studio
                        console.print(dvdEdit.getStudio() + "\n");
                        String getStudio = console.readString("Enter new studio: \n");
                        dvdEdit.setStudio(getStudio);
                        break;
                    case 6: //edit notes
                        console.print(dvdEdit.getNotes() + "\n");
                        String getNotes = console.readString("Enter new notes: \n");
                        dvdEdit.setNotes(getNotes);
                        break;
                    case 7: //done editing
                        keepEditing = false;
                        break;
                    default:
                        keepEditing = false;
                        break;

                }
            }
        }
        String doAgain = console.readString("To edit another DVD, type \"A\".  Otherwise, press any other letter");

        if (doAgain.equals("A")) {
            editDVDsInCollection();
        }
    }
}
