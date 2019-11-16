package com.gojek;

import com.gojek.commands.Command;
import com.gojek.commands.CommandUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        if (args.length == 1) {
            String filePath = args[0];
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String commandString;
                while ((commandString = br.readLine()) != null) {
                    proccessCommand(commandString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String commandString = scanner.nextLine();
                if (commandString.equals("exit")) {
                    break;
                }
                proccessCommand(commandString);
            }
            scanner.close();
        }
    }

    private static void proccessCommand(String commandString) {
        Optional<Command> commandOptional = CommandUtils.getCommand(commandString);
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            command.execute(commandString);
        } else {
            System.out.println("Invalid Command !!");
        }
    }

}
