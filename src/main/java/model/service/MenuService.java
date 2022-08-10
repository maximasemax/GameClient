package model.service;

import java.io.IOException;
import java.sql.SQLException;

public interface MenuService {
    void startMenuService() throws Exception;

    void startGame() throws IOException, InterruptedException, SQLException;

    void endGame() throws Exception;

    void ruleMenu() throws Exception;

    void exitGame() throws Exception;
}
