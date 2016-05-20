/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.baseballleague.dao;

import com.swcguild.baseballleague.dto.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class BaseballLeagueDAO {

    HashMap<String, ArrayList<Player>> leagueRoster = new HashMap<>();

    public static final String LEAGUE_ROSTER_FILE = "LeagueRoster.txt";
    private static final String DELIMITER = "::";

    public void loadLeagueRoster() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(LEAGUE_ROSTER_FILE)));

        while (sc.hasNextLine()) {

            String recordLine = sc.nextLine();
            String[] recordProperties = recordLine.split(DELIMITER);

            if (recordProperties.length < 0) {
                continue;
            }

            String teamName = recordProperties[0];

            ArrayList<Player> tempList = new ArrayList<>();

            for (int i = 1; i < recordProperties.length; i++) {
                Player tempPlayer = new Player(recordProperties[i]);
                tempList.add(tempPlayer);

            }

            leagueRoster.put(teamName, tempList);
        }

    }

    public void writeLeagueRoster() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(LEAGUE_ROSTER_FILE));

        Set<Map.Entry<String, ArrayList<Player>>> allElements = leagueRoster.entrySet();

        for (Map.Entry<String, ArrayList<Player>> ent : allElements) {
            writer.print(ent.getKey());
            for (Player p : ent.getValue()) {
                writer.print(DELIMITER + p.getPlayerName());
            }

            writer.print("\n");
        }
        writer.flush();
        writer.close();
    }

    public void createTeam(String teamName) {
        ArrayList<Player> newTeam = new ArrayList<>();
        leagueRoster.put(teamName, newTeam);
    }

    public Player createPlayer(String teamName, String playerName) {
        Player newPlayer = new Player(playerName);
        ArrayList<Player> originalTeam = leagueRoster.get(teamName);
        originalTeam.add(newPlayer);
        int placeholder = -1;

        leagueRoster.put(teamName, originalTeam);

        for (Player p : originalTeam) {
            if (playerName.equals(p.getPlayerName())) {
                placeholder = originalTeam.indexOf(p);
            }
        }

        ArrayList<Player> teamWithNewPlayer = leagueRoster.get(teamName);
        Player confirmedPlayer = teamWithNewPlayer.get(placeholder);
        return confirmedPlayer;

    }

    public Set listAllTeams() {
        return leagueRoster.keySet();
    }

    public ArrayList<Player> viewTeamRoster(String team) {
        return leagueRoster.get(team);
    }

    public Player tradePlayer(String teamName, String playerName, String newTeam) {
        Player tradedPlayer = removePlayer(teamName, playerName);
        ArrayList<Player> teamTradedTo = viewTeamRoster(newTeam);

        teamTradedTo.add(tradedPlayer);
        leagueRoster.put(newTeam, teamTradedTo);
        return tradedPlayer;

    }

    public Player removePlayer(String teamName, String playerName) {
        ArrayList<Player> originalTeam = leagueRoster.get(teamName);
        int temp = -1;
        Player playerRemoved = null;

        for (Player p : originalTeam) {
            if (p.getPlayerName().equals(playerName)) {
                temp = originalTeam.indexOf(p);
            }
        }

        if (temp > -1) {
            playerRemoved = originalTeam.remove(temp);
        }

        leagueRoster.put(teamName, originalTeam);
        return playerRemoved;
    }
}
