package model.service.impl;

import model.service.MessageService;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MessageServiceImpl implements MessageService {

    String way = "src\\main\\resources\\messeges\\";

    private String getMessage(String fileName) throws Exception {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void showRulesMenu() throws Exception {
        System.out.println(getMessage(way + "Rules.txt"));
    }

    public void showStartMessage() throws Exception {
        System.out.println(getMessage(way + "startMessage.txt"));
    }

    public void showEndMessage() throws Exception {
        System.out.println(getMessage(way + "endMessage.txt"));
    }

    public void showCommandsMessage() throws Exception {
        System.out.println(getMessage(way + "Commands.txt"));
    }

    public void showCommandsInRuleMenuMessage() throws Exception {
        System.out.println(getMessage(way + "CommandsInRule.txt"));
    }
}

