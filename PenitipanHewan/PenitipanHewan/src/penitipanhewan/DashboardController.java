/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package penitipanhewan;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author wwwan
 */
public class DashboardController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Label username;
    @FXML
    private Button logout;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button home_btn;
    @FXML
    private Button addPets_btn;
    @FXML
    private Button purchase_btn;
    @FXML
    private AnchorPane beranda_form;
    @FXML
    private Label home_avaiblePets;
    @FXML
    private Label home_income;
    @FXML
    private Label home_totalcust;
    @FXML
    private TextField addPets_petId;
    @FXML
    private TextField addPets_breed;
    @FXML
    private Button addPets_add;
    @FXML
    private Button addPets_update;
    @FXML
    private Button addPets_clear;
    @FXML
    private Button addPets_delete;
    @FXML
    private ComboBox<?> addPets_sex;
    @FXML
    private TextField addPets_age;
    @FXML
    private TextField addPets_search;
    @FXML
    private TableView<petData> addPets_tableview;
    @FXML
    private TableColumn<?, ?> addPets_col_petId;
    @FXML
    private TableColumn<petData, String> addPets_col_breed;
    @FXML
    private TableColumn<petData, String> addPets_col_sex;
    @FXML
    private TableColumn<petData, String> addPets_col_age;
    @FXML
    private AnchorPane purchase_form;
    @FXML
    private ComboBox<?> purchase_petid;
    @FXML
    private ComboBox<?> purchase_breed;
    @FXML
    private Label purchase_total;
    @FXML
    private TextField purchase_amount;
    @FXML
    private Label purchase_balance;
    @FXML
    private Button purchase_pay;
    @FXML
    private Spinner<Integer> purchase_quantity;
    @FXML
    private Button purchase_add;
    @FXML
    private TableView<customerData> purchase_tableview;
    @FXML
    private TableColumn<customerData, String> purchase_col_petid;
    @FXML
    private TableColumn<customerData, String> purchase_col_breed;
    @FXML
    private TableColumn<customerData, String> purchase_col_sex;
    @FXML
    private TableColumn<customerData, String> purchase_col_age;
    @FXML
    private TableColumn<customerData, String> purchase_col_quantity;
    @FXML
    private TableColumn<customerData, String> purchase_col_price;
    @FXML
    private AnchorPane addPets_form;
    @FXML
    private Button minimize;
    @FXML
    private TextField addPets_price;
    @FXML
    private TableColumn<petData, String> addPets_col_price;

    //Tools Database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    @FXML
    private AreaChart<?, ?> home_chart;
    @FXML
    private Button btnprintpetreport;
    @FXML
    private Button btnprinttransactionreports;
    
    public void homeDisplayAP(){
        
        String sql = "SELECT COUNT(id) FROM pet";
        
        connect = database.connectDb();
        int countAP = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countAP = result.getInt("COUNT(id)");
            }
            
            home_avaiblePets.setText(String.valueOf(countAP));
            
        }catch(Exception e){e.printStackTrace();}
 
    }
    
    public void homeDisplayTI(){
            
        String sql = "SELECT SUM(total) FROM customer_info";    
        
        connect = database.connectDb();
        double sumTI = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                sumTI = result.getDouble("SUM(total)");
            }
            
            home_income.setText("Rp. " + String.valueOf(sumTI));
            
        }catch(Exception e){e.printStackTrace();}
        }
        

        public void homeDisplayTC(){
            
            String sql = "SELECT COUNT(id) FROM customer_info";
            
           connect = database.connectDb();
           int countTc = 0;
           try{
               prepare = connect.prepareStatement(sql);
               result = prepare.executeQuery();
               
               while(result.next()){
                   countTc = result.getInt("COUNT(id)");
               }
               
               home_totalcust.setText(String.valueOf(countTc));
               
           }catch(Exception e) {e.printStackTrace();}
            
    }
    
    public void homeChart(){
        
        home_chart.getData().clear();
        
        String sql = "SELECT date, SUM(total) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 9";
        
        
        connect = database.connectDb();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            home_chart.getData().add(chart);
        }catch(Exception e){e.printStackTrace();}
        
    }    
        
        
    @FXML
    public void addPetsAdd() {

        String sql = "INSERT INTO pet (pet_Id,breed,sex,age,price,date) VALUES(?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addPets_petId.getText().isEmpty()
                    || addPets_breed.getText().isEmpty()
                    || addPets_sex.getSelectionModel().getSelectedItem() == null
                    || addPets_age.getText().isEmpty()
                    || addPets_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tolong isi bagian yang kosong");
                alert.showAndWait();
            } else {
                String checkData = "SELECT pet_id FROM pet WHERE pet_id = '"
                        + addPets_petId.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("ID Hewan: " + addPets_petId.getText() + "Hewan Sudah Ada!!!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addPets_petId.getText());
                    prepare.setString(2, addPets_breed.getText());
                    prepare.setString(3, (String) addPets_sex.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addPets_age.getText());
                    prepare.setString(5, addPets_price.getText());
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Sukses ditambah");
                    alert.showAndWait();

                    //Untuk Update TableView
                    addPetsShowListData();
                    //Membersihkan yang sudah di isi
                    addPetsClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addPetsUpdate() {

        String sql = "UPDATE pet SET breed = '"
                + addPets_breed.getText() + "', sex = '"
                + addPets_sex.getSelectionModel().getSelectedItem() + "', age = '"
                + addPets_age.getText() + "', price = '"
                + addPets_price.getText() + "' WHERE pet_Id = '"
                + addPets_petId.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addPets_petId.getText().isEmpty()
                    || addPets_breed.getText().isEmpty()
                    || addPets_sex.getSelectionModel().getSelectedItem() == null
                    || addPets_age.getText().isEmpty()
                    || addPets_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tolong isi bagian yang kosong");
                alert.showAndWait();

            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Apakah Kamu yakin UPDATE ID Hewan : " + addPets_petId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Sukses di Update!!!");
                    alert.showAndWait();

                    //Untuk Update TableView
                    addPetsShowListData();
                    //Membersihkan yang sudah di isi
                    addPetsClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addPetsDelete() {

        String sql = "DELETE FROM pet WHERE pet_id = '" + addPets_petId.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addPets_petId.getText().isEmpty()
                    || addPets_breed.getText().isEmpty()
                    || addPets_sex.getSelectionModel().getSelectedItem() == null
                    || addPets_age.getText().isEmpty()
                    || addPets_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tolong isi bagian yang kosong");
                alert.showAndWait();

            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are You Sure you want to DELETE Pet ID: " + addPets_petId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted !!!");
                    alert.showAndWait();

                    //Untuk Update TableView
                    addPetsShowListData();
                    //Membersihkan yang sudah di isi
                    addPetsClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addPetsClear() {

        addPets_petId.setText("");
        addPets_breed.setText("");
        addPets_sex.getSelectionModel().clearSelection();
        addPets_age.setText("");
        addPets_price.setText("");

    }

    private String[] sexList = {"Male", "Female"};

    @FXML
    public void addPetsSexList() {
        List<String> listS = new ArrayList<>();

        for (String data : sexList) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        addPets_sex.setItems(listD);

    }

    public ObservableList<petData> addPetsListData() {
        ObservableList<petData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM pet";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            petData petD;

            while (result.next()) {
                petD = new petData(result.getInt("pet_id"),
                        result.getString("breed"),
                        result.getString("sex"),
                        result.getInt("age"),
                        result.getDouble("price"));

                listData.add(petD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<petData> addPetsList;

    public void addPetsShowListData() {
        addPetsList = addPetsListData();

        addPets_col_petId.setCellValueFactory(new PropertyValueFactory<>("petId"));
        addPets_col_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        addPets_col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        addPets_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        addPets_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        addPets_tableview.setItems(addPetsList);

    }

    @FXML
    public void addPetsSearch() {

        FilteredList<petData> filter = new FilteredList<>(addPetsList, e -> true);
        addPets_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicatePetData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicatePetData.getPetId().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePetData.getBreed().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePetData.getSex().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePetData.getAge().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePetData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<petData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addPets_tableview.comparatorProperty());
        addPets_tableview.setItems(sortList);

    }

    @FXML
    public void addPetsSelect() {
        petData petD = addPets_tableview.getSelectionModel().getSelectedItem();
        int num = addPets_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addPets_petId.setText(String.valueOf(petD.getPetId()));
        addPets_breed.setText(petD.getBreed());
        addPets_age.setText(String.valueOf(petD.getAge()));
        addPets_price.setText(String.valueOf(petD.getPrice()));
    }
    private double totalP = 0;

    @FXML
    public void purchaseAdd() {
        purchaseCustomerId();

        String sql = "INSERT INTO customer"
                + "(customer_id, pet_Id, breed, sex, age, quantity, price, date)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {

            Alert alert;

            if (purchase_petid.getSelectionModel().getSelectedItem() == null
                    || purchase_breed.getSelectionModel().getSelectedItem() == null
                    || qty == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid : 3");
                alert.showAndWait();
            } else {

                String sexInfo = "";
                String ageInfo = "";
                double price = 0;
                String checkData = "SELECT * FROM pet WHERE pet_Id = '"
                        + purchase_petid.getSelectionModel().getSelectedItem() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    sexInfo = result.getString("sex");
                    ageInfo = result.getString("age");
                    price = result.getDouble("price");
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerId));
                prepare.setString(2, (String) purchase_petid.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) purchase_breed.getSelectionModel().getSelectedItem());
                prepare.setString(4, sexInfo);
                prepare.setString(5, ageInfo);
                prepare.setString(6, String.valueOf(qty));

                totalP = (price * qty);

                prepare.setString(7, String.valueOf(totalP));

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(8, String.valueOf(sqlDate));

                prepare.executeUpdate();
                //Menampilkan data saat menekan tombol tambah
                purchaseShowListData();
                purchaseDisplayTotal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double totalDisplay;

    public void purchaseDisplayTotal() {
        purchaseCustomerId();
        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '" + customerId + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalDisplay = result.getDouble("SUM(price)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        purchase_total.setText("Rp. " + totalDisplay);
    }

    private double balance;
    private double amount;
    
    @FXML
    public void purchaseAmount() {

        Alert alert;
        if (totalDisplay == 0 || purchase_amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            amount = Double.parseDouble(purchase_amount.getText());
            if (amount < totalDisplay) {
                purchase_amount.setText("");
                balance = 0;
                
            } else {
                balance = (amount - totalDisplay);
                
            }
        }
        purchase_balance.setText("Rp." + String.valueOf(balance));
    }

    @FXML
    public void purchasePay(){
        purchaseCustomerId();
        String sql = "INSERT INTO customer_info (customer_id, total, date)"
                + "VALUES (?,?,?)";
        connect = database.connectDb();
        
        try{
            Alert alert;
            if(purchase_amount.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Pilih Hewan Terlebih dahulu");
                alert.showAndWait();
            }else{
                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Apakah Anda Yakin?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if(option.get().equals(ButtonType.OK)){
                       prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(totalDisplay));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(3, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Suksesss");
                    alert.showAndWait(); 
                }

            }
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    @FXML
    public void PurchasePetId() {
        String sql = "SELECT * FROM pet";
        connect = database.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("pet_Id"));
            }
            purchase_petid.setItems(listData);
            purchaseBreed();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void purchaseBreed() {
        String sql = "SELECT * FROM pet WHERE pet_Id = '" + purchase_petid.getSelectionModel().getSelectedItem() + "'";
        connect = database.connectDb();

        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("breed"));
            }
            purchase_breed.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int qty;
    private SpinnerValueFactory<Integer> spinner;

    public void purchaseSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0); //Maksimal 10
        purchase_quantity.setValueFactory(spinner);
    }

    @FXML
    public void purchaseQty() {
        qty = purchase_quantity.getValue();
    }

    public ObservableList<customerData> purchaseListData() {
        purchaseCustomerId();
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE customer_id = '" + customerId + "'";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            customerData customerD;

            while (result.next()) {
                customerD = new customerData(result.getInt("customer_id"),
                        result.getInt("pet_Id"),
                        result.getString("breed"),
                        result.getString("sex"),
                        result.getInt("age"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getDate("date"));
                listData.add(customerD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<customerData> purchaseList;

    public void purchaseShowListData() {
        purchaseList = purchaseListData();

        purchase_col_petid.setCellValueFactory(new PropertyValueFactory<>("petId"));
        purchase_col_breed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        purchase_col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        purchase_col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        purchase_tableview.setItems(purchaseList);
    }

    private int customerId;

    public void purchaseCustomerId() {

        String sql = "SELECT customer_id FROM customer";
        connect = database.connectDb();
        int checkID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                customerId = result.getInt("customer_id");
            }

            String checkData = "SELECT * FROM customer_info";
            statement = connect.createStatement();
            result = statement.executeQuery(checkData);

            while (result.next()) {
                checkID = result.getInt("customer_id");
            }

            if (customerId == 0) {
                customerId += 1;
            } else if (customerId == checkID) {
                customerId += 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            beranda_form.setVisible(true);
            addPets_form.setVisible(false);
            purchase_form.setVisible(false);
            
            //Untuk Menampilkan data pada dashboard aplikasi
            homeDisplayAP();
            homeDisplayTI();
            homeDisplayTC();
            homeChart();

        } else if (event.getSource() == addPets_btn) {
            beranda_form.setVisible(false);
            addPets_form.setVisible(true);
            purchase_form.setVisible(false);

            //Untuk Menampilkan Update data di Tabel view saat pertama kali mengklik nav tambah hewan
            addPetsShowListData();
            addPetsSexList();
            purchaseBreed();
            purchaseShowListData();
            purchaseDisplayTotal();

        } else if (event.getSource() == purchase_btn) {
            beranda_form.setVisible(false);
            addPets_form.setVisible(false);
            purchase_form.setVisible(true);
        }
        PurchasePetId();
        purchaseBreed();
        purchaseSpinner();
    }
    
    
    public void displayUsername(){
        
        String user = getData.username;
        
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }
    
    private double x = 0;
    private double y = 0;

    @FXML
    public void logout() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Yakin Mau Keluar?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                //Menghilangkan Form Dashboard
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void CetakReportpet() {
        Koneksi con = new Koneksi();
        String is = "./src/penitipanhewan/reportpetshop.jasper";
        Map map = new HashMap();
        map.put("judul", "Report Data Pet");
        con.bukaKoneksi();
        try {
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(is, map, con.dbKoneksi);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        con.tutupKoneksi();
    }
    
      @FXML
    public void CetakReportTransaksi() {
        Koneksi con = new Koneksi();
        String is = "./src/penitipanhewan/reporttransaction.jasper";
        Map map = new HashMap();
        map.put("judul", "Report Data Transaksi");
        con.bukaKoneksi();
        try {
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(is, map, con.dbKoneksi);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        con.tutupKoneksi();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        displayUsername();
        
        homeDisplayAP();
        homeDisplayTI();
        homeDisplayTC();
        homeChart();
        
        addPetsShowListData();
        addPetsSexList();
        
        PurchasePetId();
        purchaseBreed();
        purchaseSpinner();
        purchaseShowListData();
        purchaseDisplayTotal();
    }

}
