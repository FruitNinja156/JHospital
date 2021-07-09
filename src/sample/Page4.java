package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Page4 implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private Button btn1;

    @FXML
    private TextField txt;

    @FXML
    private TableView<modelTable> table;

    @FXML
    private TableColumn<modelTable, String> col_id;

    @FXML
    private TableColumn<modelTable, String> col_name;

    @FXML
    private TableColumn<modelTable, String> col_lname;

    @FXML
    private TableColumn<modelTable, String> col_gname;

    @FXML
    private TableColumn<modelTable, String> col_sex;

    @FXML
    private TableColumn<modelTable, String> col_pat;

    @FXML
    private TableColumn<modelTable, String> col_city;

    @FXML
    private TableColumn<modelTable, String> col_scity;

    @FXML
    private TableColumn<modelTable, Integer> col_phno;
    @FXML
    private TableColumn<modelTable, Integer> col_age;
    ObservableList<modelTable> oblist = FXCollections.observableArrayList();
    @FXML
    void delete(ActionEvent event) {
        //DELETE FROM `hospital1` WHERE name ='abebe';
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital1", "root", "");
            Statement stmt = con.createStatement();
            String s ="delete from hospital1 WHERE id ='" +txt.getText() +"';";
            stmt.executeUpdate(s);
            JOptionPane.showMessageDialog(null, "Record Deleted!");
            table.setItems(null);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error While Trying to Delete!");
        }

    }

    @FXML
    void search(ActionEvent event) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital1", "root", "");
            Statement stmt = con.createStatement();
            String s ="select *from hospital1 WHERE id ='" +txt.getText() +"'";
            ResultSet rst = stmt.executeQuery(s);
            while(rst.next()){
                oblist.add(new modelTable(rst.getString("id"), rst.getString("name"),
                        rst.getString("lname"), rst.getString("gname"), rst.getString("sex"),
                        rst.getString("pat_type"), rst.getString("city"), rst.getString("subcity"),
                        rst.getInt("phno"), rst.getInt("age")));
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error While Searching!");
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_gname.setCellValueFactory(new PropertyValueFactory<>("gname"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_pat.setCellValueFactory(new PropertyValueFactory<>("pat_type"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_scity.setCellValueFactory(new PropertyValueFactory<>("subcity"));
        col_phno.setCellValueFactory(new PropertyValueFactory<>("phno"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        table.setItems(oblist);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
