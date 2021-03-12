package Core;

import Commands.*;

public class CommandManager {

    private final Command AddCommand;
    private final Command ClearCommand;
    private final Command CountLessThanBirthdayCommand;
    private final Command ExitCommand;
    private final Command GroupCountingByIDCommand;
    private final Command HelpCommand;
    private final Command InfoCommand;
    private final Command PrintFieldAscendingHeightCommand;
    private final Command RemoveByIdCommand;
    private final Command RemoveGreaterCommand;
    private final Command RemoveLowerCommand;
    private final Command SaveCommand;
    private final Command ShowCommand;
    private final Command UpdateCommand;

    public CommandManager(Command addCommand, Command clearCommand, Command countLessThanBirthdayCommand,
                          Command exitCommand, Command groupCountingByIDCommand,
                          Command helpCommand, Command infoCommand, Command printFieldAscendingHeightCommand,
                          Command removeByIdCommand, Command removeGreaterCommand, Command removeLowerCommand,
                          Command saveCommand, Command showCommand, Command updateCommand){

        this.AddCommand = addCommand;
        this.ClearCommand = clearCommand;
        this.CountLessThanBirthdayCommand = countLessThanBirthdayCommand;
        this.ExitCommand = exitCommand;
        this.GroupCountingByIDCommand = groupCountingByIDCommand;
        this.HelpCommand = helpCommand;
        this.InfoCommand = infoCommand;
        this.PrintFieldAscendingHeightCommand = printFieldAscendingHeightCommand;
        this.RemoveByIdCommand = removeByIdCommand;
        this.RemoveGreaterCommand = removeGreaterCommand;
        this.RemoveLowerCommand = removeLowerCommand;
        this.SaveCommand = saveCommand;
        this.ShowCommand = showCommand;
        this.UpdateCommand = updateCommand;
    }



    public boolean help(){
        return HelpCommand.execute();
    }

    public boolean info(){
        return InfoCommand.execute();
    }

    public boolean show(){
        return ShowCommand.execute();
    }

    public boolean add(){
        return AddCommand.execute();
    }

    public boolean countLessThanBirthday(String argument){
        return CountLessThanBirthdayCommand.execute(argument);
    }
    public boolean update(String argument){
        return UpdateCommand.execute(argument);
    }

    public boolean removeById(String argument){
        return RemoveByIdCommand.execute(argument);
    }

    public boolean clear(){
        return ClearCommand.execute();
    }

    public boolean save(String fileName){
        return SaveCommand.execute(fileName);
    }


    public boolean exit(){
        return ExitCommand.execute();
    }

    public boolean removeGreater(){
        return RemoveGreaterCommand.execute();
    }

    public boolean groupCountingById(){
        return GroupCountingByIDCommand.execute();
    }

    public boolean printFieldAscendingHeight(){
        return PrintFieldAscendingHeightCommand.execute();
    }

    public boolean removeLower(){
        return RemoveLowerCommand.execute();
    }
}
