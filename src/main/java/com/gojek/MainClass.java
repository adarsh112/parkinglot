package com.gojek;

import com.gojek.commands.Command;
import com.gojek.commands.CommandUtils;

import java.util.Optional;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String commandString = scanner.nextLine();
        while(!commandString.equals("exit")){
            commandString = scanner.nextLine();
            Optional<Command> commandOptional = CommandUtils.getCommand(commandString);
            if(commandOptional.isPresent()){
                Command command = commandOptional.get();
                command.execute(commandString);
            }else{
                System.out.println("Invalid Command !!");
            }
        }
    }

}
