package ui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.image.Image;
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
    private PasswordField txtPassword;
    
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
    private PasswordField txtPassword2;

    @FXML
    private TextField urlPhoto;
    
    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbFemale;
    
    @FXML
    private RadioButton rbOther;

    @FXML
    private ToggleGroup rbGender;

    @FXML
    private CheckBox cbSoftware;

    @FXML
    private CheckBox cbTelematic;

    @FXML
    private CheckBox cbIndustrial;
    
    @FXML
    private ComboBox<Browser> cbBrowser;
    
    private ObservableList<UserAccount> observableList;
    
    @FXML
    private ImageView imageView;

    private Classroom classroom;
    
    public ClassroomGUI() {
    	classroom=new Classroom();
    }

    public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	@FXML
    public void logIn(ActionEvent event) throws IOException{
		Alert alert = new Alert(null);
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("UserList.fxml"));
		String username=txtUsername.getText();
		String password=txtPassword.getText();
		boolean conti=classroom.verification(username, password);
		if(conti) {
			fxmlLoader.setController(this);
			imageView=new ImageView(classroom.putImage(username));
			Parent root= fxmlLoader.load();
			Scene scene= new Scene(root);
			mainStage.setScene(scene);
			Image imagen=new Image(classroom.putImage(username));
			imageView.setImage(imagen);;
			mainStage.show();
			itializeTableView();
		}
		else {
			alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("Error Login");
			alert.setHeaderText("You can't login");
			alert.setContentText("Your username or password doesn't exist");
			alert.showAndWait();
		}
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
    
    private String br;
    
    @FXML
    public void createAcount(ActionEvent event) {
    	Alert alert = new Alert(null);
    	String username=txtUsername2.getText();
    	String password=txtPassword2.getText();
    	String photo=urlPhoto.getText();
    	LocalDate birthDay=birthday.getValue();
    	String career=selectCareer();
    	Gender gender=selectGender();

    	if(username==null|| password==null|| photo==null|| gender==null|| career==null|| birthDay==null|| br==null) {
    		alert.setAlertType(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
			alert.setHeaderText("You have not complete the register");
			alert.setContentText("You have to complete all the information");
			alert.showAndWait();
    	}
    	else {
    		boolean conti=classroom.add(new UserAccount(username, password, photo, gender, career,birthDay,br));
    		if(conti) {
    			alert.setAlertType(AlertType.INFORMATION);
    			alert.setTitle("Confirmation Dialog");
    			alert.setHeaderText("Successful action");
    			alert.setContentText("The contact has added successful");
    			alert.showAndWait();
    		}
    		else {
    			alert.setAlertType(AlertType.WARNING);
    			alert.setHeaderText("It seems there is a little mistake");
    			alert.setContentText("The username it's already exist");
    			alert.showAndWait();
    		}
    	}
    }
    
    public Gender selectGender() {
    	if(rbMale.isSelected()) {
    		return Gender.MALE;
    	}
    	else if(rbFemale.isSelected()) {
    		return Gender.FEMALE;
    	}
    	else {
    		return Gender.OTHER;
    	}
    }

    
    public String selectCareer() {
    	String selectIt="";
    	if(cbSoftware.isSelected()) {
    		selectIt+="Software \n";
    	}
    	if(cbTelematic.isSelected()) {
    		selectIt+="Telematic \n";
    	}
    	if(cbIndustrial.isSelected()) {
    		selectIt+="Industrial";
    	}
    	return selectIt;
    }
    
    
    @FXML
    public void searchUrl(ActionEvent event) throws MalformedURLException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File selectedDirectory = fileChooser.showOpenDialog(mainStage);
    	urlPhoto.setText(selectedDirectory.toURI().toURL().toString());
    }	
    
    
    public void showOptions() {
    	ObservableList<Browser> items = FXCollections.observableArrayList();
    	items.addAll(Browser.CHROME, Browser.EDGE,Browser.FIREFOX, Browser.OPERA);
    	cbBrowser.getItems().addAll(items);
    	cbBrowser.setOnAction(new EventHandler<ActionEvent>() {     
    		public void handle(ActionEvent e)  {    
    			br=cbBrowser.getValue()+"";
    		}       
    	});
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
