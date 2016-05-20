/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.baseballleague.ops;

import com.swcguild.baseballleague.dao.BaseballLeagueDAO;
import com.swcguild.baseballleague.dto.Player;
import com.swcguild.baseballleague.ui.ConsoleIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class BaseballLeagueController {

    private ConsoleIO console = new ConsoleIO();
    private BaseballLeagueDAO daoLayer = new BaseballLeagueDAO();

    public void run() throws IOException {
        boolean keepRunning = true;
        int menuSelect = 0;

        try {
            daoLayer.loadLeagueRoster();
        } catch (FileNotFoundException ex) {
            keepRunning = false;
            console.print("No Roster Available.");
        }
        while (keepRunning) {
            printMenu();
            menuSelect = console.readInt("Select an option...");
            switch (menuSelect) {
                case 1:
                    createTeam();
                    break;

                case 2:
                    createPlayer();
                    break;

                case 3:
                    listAllTeams();
                    break;

                case 4:
                    viewTeamRoster();
                    break;

                case 5:
                    tradePlayer();
                    break;

                case 6:
                    removePlayer();
                    break;
                case 7:
                    daoLayer.writeLeagueRoster();
                    keepRunning = false;
            }

        }
    }

    private void printMenu() {
        console.print("Main Menu");
        console.print("1. Create a New Team");
        console.print("2. Create a New Baseball Player");
        console.print("3. View All Teams in the League");
        console.print("4. View Roster of Your Favorite Team");
        console.print("5. Trade a Player From One Team To Another");
        console.print("6. Remove a Player From the League");
        console.print("7. Exit");
    }

    private void createTeam() {
        String teamName = console.readString("Enter the team name: ");
        daoLayer.createTeam(teamName);
    }

    private void createPlayer() { //Does the copied ArrayList get copied in the same order as the original?
        String playerToAdd = console.readString("Enter the name of the player to add: ");
        String newPlayersTeam = console.readString("Enter the team to add the player to: ");
        Player addedPlayer = daoLayer.createPlayer(newPlayersTeam, playerToAdd);
        console.print(addedPlayer.getPlayerName() + " was successfully added to the roster.");
    }

    private void listAllTeams() {
        Set<String> listOfTeams = daoLayer.listAllTeams();
        console.print("League Roster\n");
        for (String s : listOfTeams) {
            console.print(s + "\n");
        }
    }

    private void viewTeamRoster() {
        String team = console.readString("Which roster would you like to see?");
        ArrayList<Player> roster = daoLayer.viewTeamRoster(team);
        console.print(team + " Roster\n");
        for (Player p : roster) {
            console.print(p.getPlayerName() + "\n");
        }
    }

    private void tradePlayer() {
        String playerName = console.readString("Enter the players name who will be traded");
        String oldTeam = console.readString("Enter the Players current team");
        String newTeam = console.readString("Enter the team the player is being traded to");

        Player tradedPlayer = daoLayer.tradePlayer(oldTeam, playerName, newTeam);

        console.print(tradedPlayer.getPlayerName() + " was traded");
        ArrayList<Player> newRoster = daoLayer.viewTeamRoster(newTeam);

        console.print(newTeam + "'s new roster is: \n");
        for (Player p : newRoster) {
            console.print(p.getPlayerName() + "\n");
        }

    }

    private void removePlayer() {
        listAllTeams();
        String teamName = console.readString("From which team would you like to remove a player?");

        ArrayList<Player> roster = daoLayer.viewTeamRoster(teamName);
        console.print(teamName + " Roster\n");
        for (Player p : roster) {
            console.print(p.getPlayerName() + "\n");
        }

        String playerName = console.readString("Which player would you like to remove? ");
        Player playerRemoved = daoLayer.removePlayer(teamName, playerName);

        if (playerRemoved != null) {
            console.print(playerRemoved.getPlayerName() + " was successfully removed.");
        } else {
            console.print("Your trade is not approved because the player is not on that team.");
        }
    }
}
