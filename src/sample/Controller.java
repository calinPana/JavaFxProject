package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Controller {

    @FXML
    private ListView<String> countriesListView;

        private static String readJson () throws IOException {
        URL url = new URL("https://restcountries.eu/rest/v2/all");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            return inputLine;
        }
        in.close();
        con.disconnect();

        return null;
    }


    @FXML
    private void initialize() {

        ObservableList<String> countries = FXCollections.observableArrayList();
        countries.addAll(Arrays.asList("Austria", "asfa", "asda", "Austria", "asfa", "asda", "Austria", "asfa", "asda", "Austria", "asfa", "asda", "Austria", "asfa", "asda"));
        try {
            System.out.println(readJson());
        }catch(IOException e){
            System.out.println(e);
        }

        countriesListView.setItems(countries);
    }

}
