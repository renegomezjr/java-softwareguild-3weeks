/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.baseballleague;

import com.swcguild.baseballleague.ops.BaseballLeagueController;
import java.io.IOException;

/**
 *
 * @author apprentice
 */
public class BaseballLeagueApp {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        BaseballLeagueController controller = new BaseballLeagueController();

        controller.run();
    }

}
