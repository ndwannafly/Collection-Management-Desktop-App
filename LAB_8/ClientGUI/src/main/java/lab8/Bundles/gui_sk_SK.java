package lab8.Bundles;

import java.util.ListResourceBundle;

public class gui_sk_SK  extends ListResourceBundle {

    private static final Object[][] contents = {
            {"execute_script","Wykonaj skrypt"},
            {"Information about commands","Informacje o poleceniach:"},
            {"height","Wysokocz"},
            {"creationDate","Data utworzenia"},
            {"enterID","Wprowadz ID"},
            {"Enter a name","Wpisz imic"},
            {"name","Nazwa"},
            {"owner","Wlasciciel"},
            {"type","Rodzaj"},
            {"group_counting_by_id","Liczenie grupowe wedzcug ID"},
            {"update","Aktualizacja"},
            {"add","Dodaj"},
            {"remove_by_id","Usun przez id"},
            {"count_less_than_birthday","Licz mniej niz urodziny"},
            {"Oops!","Oops!"},
            {"Please make sure that you entered everything correctly","Upewnij sie, ze wszystko wpisales poprawnie"},
            {"Enter x coordinate of the person (must be greater than -801).","Wprowadz wspóerzadnz x osoby (musi byz wixksza niz -801)."},
            {"Enter y coordinate of the person (no more than 687).","Wprowadz wspóerzscnz y osoby (nie wizcej niz 687)."},
            {"Enter the height.","Podaj wysokocz."},
            {"Enter the birthday.","Wpisz datz urodzin."},
            {"Enter the weight.","Wprowadz wagc."},
            {"Enter the x coordinate of the location.","Wprowadz wspóerzsdnz x lokalizacji."},
            {"Enter the y coordinate of the location.","Wprowadz wspóerzsdnz y lokalizacji."},
            {"Enter the name of the location.","Wpisz nazwc lokalizacji."},
            {"helpButton","Pomoc"},
            {"logoutButton","Wyloguj"},
            {"visualizeButton","Wyobrażać sobie"},
            {"Enter file path","Podaj dcieckz pliku"},
            {"exitButton","Wyjście"},
            {"showButton","pokazać"},
            {"infoButton","informacje"},
            {"historyButton","historia"},
            {"authorization","upoważnienie"},
            {"Enter the password","Podaj hasło"},
            {"Enter your username","Wpisz twoją nazwę użytkownika"},
            {"password","hasło"},
            {"login","Zaloguj sie"},
            {"submit","Zatwierdź"},
            {"name location", "nazwa lokalizacji"},
            {"nationality","Narodowość"},
            {"birthday","Birthday"},
            {"welcome","Urodziny"},
            {"registration","Rejestracja"},
            {"PlsLogIn","Proszę się zalogować."},
            {"remove_lower","Usuń dolny"},
            {"remove_greater","Usuń większe"},
            {"print_field_ascending_height","Wysokość pola wydruku rosnąco"},
            {"clear","Jasny"},
            {"weight","Waga"},
            {"id","ID"},
            {"info","Informacje o kolekcji:"},
            {"Type of collection:","Rodzaj kolekcji:"},
            {"Initialization date:","Data inicjalizacji:"},
            {"Number of elements:","Liczba elementów:"},
            {"Recent teams:","Ostatnie drużyny:"},
            {"how to sort?","jak sortować?"},
            {"warning2","Nie udało się!"},
            {"warningEmptyFields","Proszę wypełnić wszystkie pola!"},
            {"serverProblem","Serwer ma problemy!"},
            {"Person Database","Baza danych osób"},
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
