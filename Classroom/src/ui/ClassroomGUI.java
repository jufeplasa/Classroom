package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.UserAccount;
import model.Gender;
import model.Browser;

public class ClassroomGUI {

	private Stage mainStage;
	

	
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;
    
    @FXML
    private TableView<UserAccount> tableView;
    
    @FXML
    private TableColumn<UserAccount, String> tcUsername;

    @FXML
    private TableColumn<UserAccount, Gender> tbGender;

    @FXML
    private TableColumn<UserAccount, String> tbCareer;

    @FXML
    private TableColumn<UserAccount, String> tbBirthday;

    @FXML
    private TableColumn<UserAccount, Browser> tbBrowser;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField txtUsername2;

    @FXML
    private TextField urlPhoto;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbOther;

    @FXML
    private CheckBox cbSoftware;

    @FXML
    private CheckBox cbTelematic;

    @FXML
    private CheckBox cbIndustrial;
    
    @FXML
    private ComboBox<Browser> cbBrowser;
    
    @FXML
    private PasswordField txtPassword2;
    
    private ToggleGroup group ;
    
    private ObservableList<UserAccount> observableList;
    
    @FXML
    private ImageView imageView;

    private Classroom classroom;
    
    public ClassroomGUI() {
    	classroom=new Classroom();
    	group = new ToggleGroup();
    }

    public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	@FXML
    public void logIn(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("UserList.fxml"));
		String username=txtUsername.getText();
		String password=txtPassword.getText();
		classroom.verification(username, password);
    	fxmlLoader.setController(this);
    	Parent root= fxmlLoader.load();
    	Scene scene= new Scene(root);
    	
    	mainStage.setScene(scene);
    	mainStage.show();
    	itializeTableView();
    }

    @FXML
    public void signIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Register.fxml"));
    	fxmlLoader.setController(this);
    	Parent root= fxmlLoader.load();
    	Scene scene= new Scene(root);
    	
    	mainStage.setScene(scene);
    	mainStage.show();
    	showOptions();
    	selectGender();
    }
    
    @FXML
    public void returnToLogin(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Login.fxml"));
    	fxmlLoader.setController(this);
    	Parent root= fxmlLoader.load();
    	Scene scene= new Scene(root);
    	
    	mainStage.setScene(scene);
    	mainStage.show();
    }
    
    
    @FXML
    public void createAcount(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String username=txtUsername2.getText();
    	String password=txtPassword2.getText();
    	String photo=urlPhoto.getText();
    	String birthDay=birthday.getPromptText();
    	String career=selectedCareer();
    	
    	boolean conti=classroom.add(new UserAccount(username, password, photo, "Male", career,birthDay,cbBrowser.getValue()));
    	if(conti) {
    		alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The contact has added successful");
			alert.showAndWait();
    	}
    	else {
    		alert.setAlertType(AlertType.WARNING);
			alert.setHeaderText("It seems there is a little mistake");
			alert.setContentText("The email it's already exist");
			alert.showAndWait();
    	}
    }
    
    @FXML
    public void searchUrl(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File selectedDirectory = fileChooser.showOpenDialog(mainStage);
    	urlPhoto.setText(selectedDirectory.getAbsolutePath());
    }	
    
    public void selectGender() {
    	
    	rbMale.setToggleGroup(group);
    	rbFemale.setToggleGroup(group);
    	rbOther.setToggleGroup(group);
    	rbOther.setSelected(true);
    }
    
    public void showOptions() {
    	ObservableList<Browser> items = FXCollections.observableArrayList();
    	items.addAll(Browser.CHROME, Browser.EDGE,Browser.FIREFOX, Browser.OPERA);
    	cbBrowser.getItems().addAll(items);
    }

    public String selectedCareer() {
    	String selection="";
    	if(cbIndustrial.isPickOnBounds()) {
    		selection=cbIndustrial.getText();
    	}
    	if(cbTelematic.isPickOnBounds()) {
    		selection+=" "+cbTelematic.getText();
    	}
    	if(cbSoftware.isPickOnBounds()) {
    		selection+=" "+cbSoftware.getText();
    	}
    	return selection;
    }
  

    public void itializeTableView() {
    	observableList= FXCollections.observableArrayList(classroom.getUser());
    	tableView.setItems(observableList);
    	tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("UserName"));
    	tbGender.setCellValueFactory(new PropertyValueFactory<UserAccount, Gender>("Gender"));
    	tbCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("Career"));
    	tbBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("Birthday"));
    	tbBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, Browser>("Browser"));
    }
    
}
