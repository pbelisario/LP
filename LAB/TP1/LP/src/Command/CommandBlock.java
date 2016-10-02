/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import Command.Command;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author higorfischerdepaulalopes
 */
public class CommandBlock extends Command{
    
    private List<Command> commands = new ArrayList<Command>();    

    public CommandBlock(int line) {
        super(line);
    }
    
    public void addCommand(Command c){
        commands.add(c);
    }
    
    public void execute(){
        for(Command command : commands){
            command.execute();
        }
    }
}
