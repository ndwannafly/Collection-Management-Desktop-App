package Core;

import Commands.*;

public class CommandManager {

    private Command AddCommand;
    private Command ClearCommand;
    private Command CountLessThanBirthdayCommand;
    private Command ExecuteScriptCommand;
    private Command ExitCommand;
    private Command GroupCountingByIDCommand;
    private Command HelpCommand;
    private Command HistoryCommand;
    private Command InfoCommand;
    private Command PrintFieldAscendingHeightCommand;
    private Command RemoveByIdCommand;
    private Command RemoveGreaterCommand;
    private Command RemoveLowerCommand;
    private Command SaveCommand;
    private Command ShowCommand;
    private Command UpdateCommand;

    public CommandManager(Command addCommand, Command clearCommand, Command countLessThanBirthdayCommand,
                          Command executeScriptCommand, Command exitCommand, Command groupCountingByIDCommand,
                          Command helpCommand, Command infoCommand, Command printFieldAscendingHeightCommand,
                          Command removeByIdCommand, Command removeGreaterCommand, Command removeLowerCommand,
                          Command saveCommand, Command showCommand, Command updateCommand){

        this.AddCommand = addCommand;
        this.ClearCommand = clearCommand;
        this.CountLessThanBirthdayCommand = countLessThanBirthdayCommand;
        this.ExecuteScriptCommand = executeScriptCommand;
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
/*
    public boolean executeScript(String argument){
        return ExecuteScriptCommand.execute(argument);
    }
*/
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
