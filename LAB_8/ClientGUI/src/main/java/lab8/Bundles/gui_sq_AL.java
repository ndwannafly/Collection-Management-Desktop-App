package lab8.Bundles;

import java.util.ListResourceBundle;

public class gui_sq_AL  extends ListResourceBundle {

    private static final Object[][] contents = {
        {"execute_script","Ekzekutoni Skriptin"},
            {"Information about commands","Informacion rreth komandave"},
        {"height","Lartësia"},
        {"creationDate","Data e krijimit"},
        {"enterID","Vendosni ID tuaj"},
        {"Enter a name","Vendosni një emër"},
        {"name","Emrin"},
        {"owner","Pronar"},
        {"type","Lloji"},
        {"group_counting_by_id","Numërimi në grup me ID"},
        {"update","Përditëso"},
        {"add","Shto"},
        {"remove_by_id","Hiq me ID"},
        {"count_less_than_birthday","Numëroni më pak se ditëlindja"},
        {"Oops!","Oops!"},
        {"Please make sure that you entered everything correctly","Ju lutemi sigurohuni që keni futur gjithçka në mënyrë korrekte"},
        {"Enter x coordinate of the person (must be greater than -801).","Vendosni koordinaten x te personit (duhet te jetë më e madhe se -801)."},
        {"Enter y coordinate of the person (no more than 687).","Vendosni koordinaten e personit (jo më shumë se 687)."},
        {"Enter the height.","Futni lartesine."},
        {"Enter the birthday.","Hyni ne ditelindjen."},
        {"Enter the weight.","Vendosni peshen."},
        {"Enter the x coordinate of the location.","Vendosni koordinatën x te vendndodhjes."},
        {"Enter the y coordinate of the location.","Vendosni koordinatën y te vendndodhjes."},
        {"Enter the name of the location.","Vendosni emrin e vendndodhjes."},
        {"helpButton","ndihme"},
        {"logoutButton","Shkyc"},
        {"visualizeButton","Vizualizoj"},
        {"Enter file path","Fut rrugën e skedarit"},
        {"exitButton","Dalja"},
        {"showButton","Shfaqje"},
        {"infoButton","Info"},
        {"historyButton","Historia"},
        {"authorization","Autorizimi"},
        {"Enter the password","Vendosni fjalëkalimin"},
        {"Enter your username","Vendosni emrin tuaj të përdoruesit"},
        {"password","Fjalëkalimi"},
        {"login","Hyrja"},
        {"submit","nënshtrohen"},
        {"name location"," Emri Vendndodhja"},
        {"nationality","Kombësia"},
        {"birthday","Ditëlindja"},
        {"welcome","Mirëseardhje"},
        {"registration","Regjistrimi"},
        {"PlsLogIn","Ju lutem identifikohuni"},
        {"remove_lower","Hiqeni Ulët"},
        {"remove_greater","Hiqe Madhe"},
        {"print_field_ascending_height","Fusha e Shtypjes Lartësia në ngjitje"},
        {"clear","Qartë"},
        {"weight","Pesha"},
        {"id","ID"},
        {"info","Informacion rreth koleksionit:"},
        {"Type of collection:","Lloji i koleksionit:"},
        {"Initialization date:","Data e fillimit:"},
        {"Number of elements:","Numri i elementeve:"},
        {"Recent teams:","Ekipet e fundit:"},
        {"how to sort?","si të rendit?"},
        {"warning2","Dështoi!"},
        {"warningEmptyFields","Ju lutemi plotësoni të gjitha fushat!"},
        {"serverProblem","Serveri ka probleme!"},
        {"Person Database","Baza e të Dhënave të Personave"}

    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
