package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Controller {

    @FXML
    private ListView<Country> countriesListView;
    @FXML
    private Label countryName;
    @FXML
    private Label countryCapital;

    private static String readJson() throws IOException {
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
//        countries.addAll(Arrays.asList("Austria", "asfa", "asda", "Austria", "asfa",
//                "asda", "Austria", "asfa", "asda", "Austria", "asfa", "asda", "Austria", "asfa", "asda"));

        ObservableList<Country> countriesObjects = FXCollections.observableArrayList();
        try {
            String json = readJson();
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(json);
            Iterator it = arr.iterator();

            while(it.hasNext()){
                JSONObject object = (JSONObject) it.next();
//                countriesObjects.add(new Country((String)object.get("name"), (String)object.get("capital"), (String)object.get("region"),
//                        (int)object.get("population"), (String)object.get("languages")));

                countriesObjects.add(new Country((String)object.get("name"), (String)object.get("capital")));

                countries.add((String)object.get("name"));
//                String countryName = (String)object.get("name");
//                String capitalName = (String)object.get("capital");
//                System.out.println(countryName + " " + capitalName);
//                countries.add(countryName);

            }

        } catch (IOException e) {
            System.out.println(e);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }




//        try {
//            System.out.println(readJson());
//        } catch (IOException e) {
//            System.out.println(e);
//        }


        countriesListView.setItems(countriesObjects);


        countriesListView.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>() {

            @Override
            public ListCell<Country> call(ListView<Country> param) {
                ListCell<Country> cell = new ListCell<Country>() {

                    @Override
                    protected void updateItem(Country item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });

        countriesListView.setOnMouseClicked(event -> {
            Country c =  countriesListView.getSelectionModel().getSelectedItem();
            System.out.println("clicked on " + c.getName());

            countryName.setText(c.getName());
            countryCapital.setText(c.getCapital());


        });




//        for (int i=0; i<countriesObjects.size(); i++){
//            countryName.setLabelFor();
//
//        }
    }

}
