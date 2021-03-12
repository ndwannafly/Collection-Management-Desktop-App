package Commands;

public class HelpCommand extends AbstractCommand{
    /**
     *
     * @return true always because we had checked for validity before
     */
    @Override
    public boolean execute() {
        System.out.println("help                        - display which commands can be executed.");
        System.out.println("info                        - display info of the collection ( type, creationDate, number of elements...");
        System.out.println("show                        - display all the elements of the collection");
        System.out.println("add {element}               - add new element into the collection");
        System.out.println("update {id}                 - update new values for element has corresponding id");
        System.out.println("remove_by_id {id}           - remove element has corresponding id from the collection");
        System.out.println("clear                       - clear collection");
        System.out.println("save                        - save the collection into file");
        System.out.println("execute_script file_name    - read and execute script from corresponding file. The script\n" +
                "                              contains commands in the same form in which the user enters them interactively.");
        System.out.println("exit                        - exit the program without saving to file");
        System.out.println("remove_greater {element}    - remove all elements from the collection, which are greater than " +
                "specific element");
        System.out.println("remove_lower {element}      - remove all elements from the collection, which are lower than " +
                "specific element");
        System.out.println("history:                    - print 14 last command (without argument)");
        System.out.println("group_counting_by_id        - group elements by their ID, print number of element of each group");
        System.out.println("count_less_than_birthday    - print number of elements, which have field birthday less than " +
                "specific birthday");
        System.out.println("print_field_ascending_height- print field height of all elements in ascending order");
        return true;
    }
}
