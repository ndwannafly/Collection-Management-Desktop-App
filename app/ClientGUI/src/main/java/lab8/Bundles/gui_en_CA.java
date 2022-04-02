package lab8.Bundles;

import java.util.ListResourceBundle;

public class gui_en_CA extends ListResourceBundle {

    private static final Object[][] contents = {
            {"execute_script","Execute Script"},
            {"Information about commands","Information about commands"},
            {"height","Height"},
            {"creationDate","Creation date"},
            {"enterID","Enter your ID"},
            {"Enter a name","Enter a name"},
            {"name","Name"},
            {"owner","Owner"},
            {"type","Type"},
            {"group_counting_by_id","Group Counting By ID"},
            {"update","Update"},
            {"add","Add"},
            {"remove_by_id","Remove by id"},
            {"count_less_than_birthday","Count Less Than Birthday"},
            {"Oops!","Oops!"},
            {"Please make sure that you entered everything correctly","Please make sure that you entered everything correctly"},
            {"Enter x coordinate of the person (must be greater than -801).","Enter x coordinate of the person (must be greater than -801)."},
            {"Enter y coordinate of the person (no more than 687).","Enter y coordinate of the person (no more than 687)."},
            {"Enter the height.","Enter the height."},
            {"Enter the birthday.","Enter the birthday."},
            {"Enter the weight.","Enter the weight."},
            {"Enter the x coordinate of the location.","Enter the x coordinate of the location."},
            {"Enter the y coordinate of the location.","Enter the y coordinate of the location."},
            {"Enter the name of the location.","Enter the name of the location."},
            {"helpButton","help"},
            {"logoutButton","Logout"},
            {"visualizeButton","Visualize"},
            {"Enter file path","Enter file path"},
            {"exitButton","Exit"},
            {"showButton","Show"},
            {"infoButton","Info"},
            {"historyButton","History"},
            {"authorization","Authorization"},
            {"Enter the password","Enter the password"},
            {"Enter your username","Enter your username"},
            {"password","Password"},
            {"login","Login"},
            {"submit","submit"},
            {"name location", "Name Location"},
            {"nationality","Nationality"},
            {"birthday","Birthday"},
            {"welcome","Welcome"},
            {"registration","Registration"},
            {"PlsLogIn","Please log in."},
            {"remove_lower","Remove Lower"},
            {"remove_greater","Remove Greater"},
            {"print_field_ascending_height","Print Field Ascending Height"},
            {"clear","Clear"},
            {"weight","Weight"},
            {"id","ID"},
            {"info","Information about the collection:"},
            {"Type of collection:","Type of collection:"},
            {"Initialization date:","Initialization date:"},
            {"Number of elements:","Number of elements:"},
            {"Recent teams:","Recent teams:"},
            {"how to sort?","how to sort?"},
            {"warning2","Failed!"},
            {"warningEmptyFields","Please fill in all the fields!"},
            {"serverProblem","The server has problems!"},
            {"Person Database","Person DataBase"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
