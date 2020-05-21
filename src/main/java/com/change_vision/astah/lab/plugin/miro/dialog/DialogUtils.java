package com.change_vision.astah.lab.plugin.miro.dialog;

import com.change_vision.astah.lab.plugin.miro.MiroClient;
import com.change_vision.astah.lab.plugin.miro.authorization.Authorization;
import com.change_vision.astah.lab.plugin.miro.board.Board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DialogUtils {
    static Map<String, String> getBoardMap(String teamId) {
        MiroClient client = new MiroClient();
        try {
            List<Board> boardList = client.getBoardList(teamId).getData();
            Map<String, String> boardMap = new HashMap<>();
            for (Board board : boardList) {
                boardMap.put(board.getName(), board.getId());
            }
            return boardMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String getTeamId() {
        MiroClient client = new MiroClient();
        try {
            Authorization authorization = client.getAuthorization();
            Map team = authorization.getTeam();
            return team.get("id").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
