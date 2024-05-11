//package application;
//
//import java.io.File;
//
//import java.io.FileNotFoundException;
//import java.net.MalformedURLException;
//import java.util.*;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//
///**
// * MinCostProject:
// * 
// * By:Rasha Mansour -1210773 A JavaFX application for calculating minimum costs
// * and finding shortest paths between cities. It reads city costs from a file,
// * applies dynamic programming algorithms, and provides an intuitive user
// * interface for analysis.
// */
//
//public class MINcOSTtest extends Application {
//
//	// Static variables to hold city data and algorithm results
//	public static int CitiesNum = 0;
//	public static int StartCity = 0;
//	public static int EndCity = 0;
//	public static String Goal;
//	public static String[][] next;
//	public static String[] city;
//	public static String Start = "";
//	public static String input = "";
//	public static int[][] table;
//	public static int cityIndexOffset = 0;
//	// UI elements
//	Label startCity;
//	Label endCity;
//	ComboBox<String> start;
//	ComboBox<String> end;
//	Scanner sc;
//	TextField MinCostF;
//	TextField PathF;
//	TextArea DPtable;
//	TextArea AltPath;
//	Scene DynamicTable;
//	Scene scene;
//	// Add a boolean variable to track whether the file has been loaded
//	private boolean fileLoaded = false;
//
//	@Override
//	public void start(Stage primaryStage) throws MalformedURLException {
//
//		// Title
//		Label titleLabel = new Label("Minimum Cost between cities project");
//		titleLabel.setStyle(
//				"-fx-font-family: 'Verdana'; -fx-font-size: 17px; -fx-text-fill: white; -fx-font-weight: bold;");
//		HBox titleBox = new HBox(titleLabel);
//		titleBox.setAlignment(Pos.CENTER);
//		titleBox.setBackground(
//				new Background(new BackgroundFill(javafx.scene.paint.Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
//
//		// Convert file path to URL
//		File file1 = new File("load.png");
//		String imageUrl1 = file1.toURI().toURL().toString();
//
//		// Load image using the URL
//		ImageView LoadIcon = new ImageView(new Image(imageUrl1));
//		LoadIcon.setFitWidth(20); // Set the desired width
//		LoadIcon.setFitHeight(20); // Set the desired height
//
//		Button loadFileButton = new Button("Load File", LoadIcon);
//		loadFileButton.setPrefWidth(150);
//		loadFileButton.setPrefHeight(30);
//
//		icons(loadFileButton);
//		Effect(loadFileButton);
//
//		loadFileButton.setOnAction(e -> loadFile(primaryStage));
//
//		startCity = new Label("Start: ");
//		startCity.setStyle("-fx-text-fill: white; -fx-font-weight: bold;"); // Apply white color and bold style to the
//																			// label text
//
//		endCity = new Label("End: ");
//		endCity.setStyle("-fx-text-fill: white; -fx-font-weight: bold;"); // Apply white color and bold style to the
//																			// label text
//
//		start = new ComboBox<>();
//		end = new ComboBox<>();
//		start.setStyle("-fx-background-color: transparent;");
//		end.setStyle("-fx-background-color: transparent;");
//
//		HBox hb1 = new HBox();
//		hb1.getChildren().addAll(startCity, start);
//		hb1.setSpacing(5);
//		hb1.setAlignment(Pos.CENTER);
//
//		HBox hb2 = new HBox();
//		hb2.getChildren().addAll(endCity, end);
//		hb2.setSpacing(5);
//		hb2.setAlignment(Pos.CENTER);
//
//		File file2 = new File("find.png");
//		String imageUrl2 = file2.toURI().toURL().toString();
//
//		// Load image using the URL
//		ImageView FindIcon = new ImageView(new Image(imageUrl2));
//		FindIcon.setFitWidth(20); // Set the desired width
//		FindIcon.setFitHeight(20); // Set the desired height
//
//		Button findMinCost = new Button("Find", FindIcon);
//		loadFileButton.setPrefWidth(150);
//		loadFileButton.setPrefHeight(30);
//
//		icons(findMinCost);
//		Effect(findMinCost);
//
//		HBox ComboBoxhb = new HBox();
//		ComboBoxhb.getChildren().addAll(hb1, hb2);
//		ComboBoxhb.setSpacing(200);
//		ComboBoxhb.setAlignment(Pos.CENTER);
//
//		Label MinCostL = new Label("Minimum Cost: ");
//		MinCostL.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
//		MinCostF = new TextField();
//		MinCostF.setEditable(false);
//		MinCostF.setPromptText("Minimum Cost");
//		MinCostF.setPrefWidth(200); // Adjust the width as needed
//
//		HBox hb3 = new HBox(MinCostL, MinCostF);
//		hb3.setSpacing(20);
////		hb3.setAlignment(Pos.CENTER);
//
//		Label PathL = new Label("Min Cost Path: ");
//		PathL.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
//		PathF = new TextField();
//		PathF.setEditable(false);
//		PathF.setPromptText("Minimum Cost Path");
//		PathF.setPrefWidth(200); // Adjust the width as needed
//
//		HBox hbPath = new HBox();
//		hbPath.getChildren().addAll(PathL, PathF);
//		hbPath.setSpacing(20);
////		hbPath.setAlignment(Pos.CENTER);
//
//		AltPath = new TextArea();
//		AltPath.setEditable(false);
//		AltPath.setPrefHeight(300);
//		AltPath.setPrefWidth(300);
//		AltPath.setPromptText("Alternative paths");
//
//		File fille3 = new File("path.png");
//		String imageUrl3 = fille3.toURI().toURL().toString();
//
//		// Load image using the URL
//		ImageView PathIcon = new ImageView(new Image(imageUrl3));
//		PathIcon.setFitWidth(20); // Set the desired width
//		PathIcon.setFitHeight(20); // Set the desired height
//
//		Button AltPathsBtn = new Button("Alternative paths", PathIcon);
//		AltPathsBtn.setPrefWidth(200);
//		AltPathsBtn.setPrefHeight(30);
//
//		icons(AltPathsBtn);
//		Effect(AltPathsBtn);
//
//		File fille4 = new File("img_1.png");
//		String imageUrl4 = fille4.toURI().toURL().toString();
//
//		// Load image using the URL
//		ImageView PathIcon2 = new ImageView(new Image(imageUrl4));
//		PathIcon2.setFitWidth(20); // Set the desired width
//		PathIcon2.setFitHeight(20); // Set the desired height
//
//		Button DPTbtn = new Button("Table", PathIcon2);
//		DPTbtn.setPrefWidth(150);
//		DPTbtn.setPrefHeight(30);
//
//		icons(DPTbtn);
//		Effect(DPTbtn);
//
//		VBox vBox = new VBox();
//		vBox.getChildren().addAll(titleBox, loadFileButton, ComboBoxhb, findMinCost, hb3, hbPath, AltPathsBtn, AltPath,
//				DPTbtn);
//		vBox.setSpacing(20);
//		vBox.setPadding(new Insets(20));
//		vBox.setAlignment(Pos.CENTER);
//		vBox.setStyle("-fx-background-color: linear-gradient(to right, #000000, #333333);");
//
//		scene = new Scene(vBox, 500, 600);
//
//		DPtable = new TextArea();
//		DPtable.setEditable(false);
//		DPtable.setPrefHeight(600);
//		DPtable.setPrefWidth(600);
//
//		File fille5 = new File("show.png");
//		String imageUrl5 = fille5.toURI().toURL().toString();
//
//		// Load image using the URL
//		ImageView PathIcon3 = new ImageView(new Image(imageUrl5));
//		PathIcon3.setFitWidth(20); // Set the desired width
//		PathIcon3.setFitHeight(20); // Set the desired height
//
//		Button showTable = new Button("Show", PathIcon3);
//		showTable.setPrefWidth(150);
//		showTable.setPrefHeight(30);
//
//		icons(showTable);
//		Effect(showTable);
//
//		File fille6 = new File("back.png");
//		String imageUrl6 = fille6.toURI().toURL().toString();
//
//		// Load image using the URL
//		ImageView PathIcon4 = new ImageView(new Image(imageUrl6));
//		PathIcon4.setFitWidth(20); // Set the desired width
//		PathIcon4.setFitHeight(20); // Set the desired height
//
//		Button back = new Button("Back", PathIcon4);
//		back.setPrefWidth(150);
//		back.setPrefHeight(30);
//
//		icons(back);
//		Effect(back);
//
//		HBox hbTableCost = new HBox();
//		hbTableCost.getChildren().addAll(back, showTable);
//		hbTableCost.setSpacing(40);
//
//		// Table Scene setup
//		VBox vbTable = new VBox(DPtable, hbTableCost);
//		vbTable.setSpacing(50);
//		vbTable.setPadding(new Insets(20)); // Add padding to the VBox to avoid sticking to the edges
//		vbTable.setStyle("-fx-background-color: linear-gradient(to right, #000000, #333333);");
//
//		DynamicTable = new Scene(vbTable, 800, 600);
//
//		// Button actions
//		findMinCost.setOnAction(e -> handleFindMinCost());
//		AltPathsBtn.setOnAction(e -> handleAltPathsBtn());
//		DPTbtn.setOnAction(e -> switchToTableScene(primaryStage));
//		back.setOnAction(e -> goBackToMainScene(primaryStage));
//		showTable.setOnAction(e -> handleShowTable());
//
//		primaryStage.setTitle("Rasha Project");
//		Image icon = new Image(new File("img.png").toURI().toString());
//
//		// Set the icon for the stage
//		primaryStage.getIcons().add(icon);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//
//	// load the file
////	O(n),where n is the number of lines in the file
//	private void loadFile(Stage primaryStage) {
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Open Resource File");
//		File selectedFile = fileChooser.showOpenDialog(primaryStage);
//
//		if (selectedFile != null) {
//			try {
//				sc = new Scanner(selectedFile);
//				// Proceed with reading the file and updating UI accordingly
//				updateUIAfterFileLoad();
//				// Set fileLoaded to true
//				showAlert("success", "File loaded successfuly.", null);
//				fileLoaded = true;
//			} catch (FileNotFoundException ex) {
//				showAlert("File Not Found", "File Not Found", "The selected file does not exist.");
//				fileLoaded = false;
//			} catch (NoSuchElementException ex) {
//				showAlert("Invalid File", "Invalid File Content",
//						"The selected file is empty or does not contain the expected content.");
//				fileLoaded = false;
//			}
//		} else {
//			showAlert("No File Selected", "No File Selected", "Please select a file.");
//			fileLoaded = false;
//		}
//	}
//
//	// update the Ui after loading the file
////	O(m), where m is the number of cities in the file.
//	private void updateUIAfterFileLoad() {
//		// Clear existing ComboBox items
//		start.getItems().clear();
//		end.getItems().clear();
//
//		String number = sc.nextLine();
//		CitiesNum = Integer.parseInt(number); // number of cities
//
//		int numOfLine = 0;
//		city = new String[CitiesNum];
//
//		String cityEnd = "";
//
//		input = "";
//
//		while (sc.hasNext()) {
//			numOfLine++;
//			if (numOfLine == 1) {
//				String s = sc.nextLine();
//				String[] str = s.split(", ");
//				cityEnd = str[1];
//				city[0] = str[0];
//				city[CitiesNum - 1] = str[1];
//				continue;
//			}
//
//			String s = sc.nextLine(); // line
//
//			input += s + "\n";
//
//			String[] str = s.split(", "); // start city in each line
//
//			city[numOfLine - 2] = str[0]; // add cities to the array
//
//			start.getItems().addAll(str[0]); // add cities to comboBox
//			end.getItems().addAll(str[0]); // add cities to comboBox
//		} // end of while loop
//
//		start.getItems().addAll(cityEnd); // add endCity to comboBox
//		end.getItems().addAll(cityEnd); // add endCity to comboBox
//
//		start.setValue(start.getItems().get(0));
//		end.setValue(end.getItems().get(CitiesNum - 1));
//
//	}
//
//	// O(n^3), where n is the number of cities.
//	private void handleFindMinCost() {
//	    if (!fileLoaded) {
//	        showAlert("File Not Loaded", "File Not Loaded", "You cannot perform this action without loading the file.");
//	        return;
//	    }
//	    
//	    // Get the selected cities from the ComboBoxes
//	    Start = start.getValue();
//	    Goal = end.getValue();
//
//	    // Update StartCity and EndCity indices
//	    StartCity = start.getSelectionModel().getSelectedIndex();
//	    EndCity = end.getSelectionModel().getSelectedIndex();
//
//	    // Check for valid input
//	    if (StartCity == -1 || EndCity == -1 || StartCity == EndCity) {
//	        showAlert("Invalid Selection", "Invalid Selection", "Please select different and valid cities for start and end.");
//	        return;
//	    }
//
//	    try {
//	        findMinimum(start, end, input);
//	        int minCost = table[StartCity][EndCity];
//	        String PathL = "";
//	        if (minCost != Integer.MAX_VALUE) {
//	            // Convert EndCity index to city name
//	            String endCityName = end.getValue();
//	            PathL = printPath(next, Start, endCityName) + Goal;
//	        }
//	        MinCostF.setText(Integer.toString(minCost));
//	        PathF.setText(PathL);
//	    } catch (Exception ex) {
//	        showAlert("Error", "An unexpected error occurred", "Please try again.");
//	    }
//	}
//
//	// Method to handle printing alternative paths,Time Complexity: Depends on the
//	// number of alternative paths found
//	private void handleAltPathsBtn() {
//		if (!fileLoaded) {
//			showAlert("File Not Loaded", "File Not Loaded", "You cannot perform this action without loading the file.");
//			return;
//		}
//		if (EndCity >= StartCity && table[0][CitiesNum - 1] != Integer.MAX_VALUE) {
//			AltPath.setText(printaltPaths(table, city, Start, Goal));
//		} else {
//			AltPath.setText("Sorry, There is no direct way:(.");
//		}
//	}
//
//	// Method to switch to the table scene,Constant time
//	private void switchToTableScene(Stage primaryStage) {
//		if (!fileLoaded) {
//			showAlert("File Not Loaded", "File Not Loaded", "You cannot perform this action without loading the file.");
//			return;
//		}
//		primaryStage.setScene(DynamicTable);
//	}
//
//	// Method to go back to the main scene,Constant time
//	private void goBackToMainScene(Stage primaryStage) {
//		primaryStage.setScene(scene);
//		DPtable.clear();
//	}
//
//	// Method to handle displaying the table,O(n ^2 ), where n is the number of
//	// cities.
//	private void handleShowTable() {
//		DPtable.setText("");
//		if (EndCity >= StartCity) {
//			StringBuilder outputBuilder = new StringBuilder();
//			for (int i = 1; i < EndCity; i++) {
//				if (next[0][i].equals("X")) {
//					next[0][i] = Start;
//				}
//			}
//
//			outputBuilder.append("     ");
//
//			for (int i = cityIndexOffset; i <= EndCity + cityIndexOffset; i++) {
//				outputBuilder.append(String.format("%-10s", city[i]));
//			}
//
//			outputBuilder.append("\n");
//
//			for (int i = 0; i < CitiesNum; i++) {
//				outputBuilder.append(String.format("%-10s", city[i + cityIndexOffset]));
//				for (int j = 0; j < CitiesNum; j++) {
//					if (table[i][j] == Integer.MAX_VALUE || j < i) {
//						outputBuilder.append(String.format("%-10s", "X"));
//					} else {
//						outputBuilder.append(String.format("%-10s", table[i][j]));
//					}
//					if (j == CitiesNum - 1) {
//						outputBuilder.append("\n");
//						outputBuilder.append("\n");
//					}
//				}
//			}
//			DPtable.setStyle("-fx-font-family: 'Courier New', monospaced;");
//			DPtable.appendText(outputBuilder.toString());
//		} else {
//			DPtable.appendText("Sorry, The road is in one way and you can't back:(.");
//		}
//	}
//
////	O(n^2), where n is the number of cities.
//	public static String printPath(String[][] arr, String src, String cities) {
//		int size = EndCity;
//		String path = "";
//		int diff = 0;
//		while (!cities.equals(src)) {
//			for (int j = size; j >= StartCity; j--) {
//				if (j == StartCity)
//					break;
//
//				if (city[j + cityIndexOffset].equals(cities)) {
//
//					diff = size - j;
//					path = city[j + cityIndexOffset] + " -> " + path;
//					cities = arr[0][EndCity - diff];
//				}
//			}
//
//			// we had reached the starting city
//		}
//		return src + " -> " + path;
//	}
//
//	public static String printaltPaths(int[][] table, String[] city, String source, String destination) {
//		String result = "";
//
//		int shortestDistance = table[0][CitiesNum - 1];
//
//		// If there is a direct connection from the source to the destination
//		if (shortestDistance != Integer.MAX_VALUE) {
//			ArrayList<Path> shortestPaths = new ArrayList<>();
//			ArrayList<String> currentPath = new ArrayList<>();
//			currentPath.add(city[StartCity]);
//			printAltPathsHelper(table, city, StartCity, EndCity, currentPath, shortestPaths);
//
//			result += "Shortest paths from " + source + " to " + destination + ":\n";
//			if (shortestPaths.isEmpty()) {
//				result += "No paths found.\n";
//			} else {
//				int count = 0;
//				for (int i = 1; i < shortestPaths.size(); i++) {
//					Path path = shortestPaths.get(i);
//					result += "Path: " + path.getPath() + "\n";
//					result += "Cost: " + path.getCost() + "\n";
//					count++;
//					if (count == 2) {
//						break;
//					}
//				}
//			}
//			return result;
//		}
//		return "No direct connection from " + source + " to " + destination;
//	}
//
//	private static void printAltPathsHelper(int[][] table, String[] city, int currentIndex, int destinationIndex,
//			ArrayList<String> currentPath, ArrayList<Path> shortestPaths) {
//		if (currentIndex == destinationIndex) {
//			// Join elements of the currentPath ArrayList using a loop
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < currentPath.size(); i++) {
//				sb.append(currentPath.get(i));
//				if (i < currentPath.size() - 1) {
//					sb.append(" -> ");
//				}
//			}
//			String pathString = sb.toString();
//			// Add the current path to the list of shortest paths
//			shortestPaths.add(new Path(pathString, calculatePathCost(table, currentPath)));
//		} else {
//			for (int i = currentIndex + 1; i < table.length; i++) {
//				if (table[currentIndex][i] != Integer.MAX_VALUE) {
//					currentPath.add(city[i]);
//					printAltPathsHelper(table, city, i, destinationIndex, currentPath, shortestPaths);
//					currentPath.remove(currentPath.size() - 1);
//				}
//			}
//
//		}
//	}
//
//	// method to calculate cost,O(n), where n is the number of cities in the path.
//	private static int calculatePathCost(int[][] table, ArrayList<String> path) {
//		int cost = 0;
//		for (int i = 0; i < path.size() - 1; i++) {
//			int city1Index = getIndex(path.get(i));
//			int city2Index = getIndex(path.get(i + 1));
//			cost += table[city1Index][city2Index];
//		}
//		return cost;
//	}
//
//	// get index,O(n), where n is the number of cities.
//	private static int getIndex(String cityName) {
//		// Assuming the city array contains unique city names
//		for (int i = 0; i < city.length; i++) {
//			if (city[i].equals(cityName)) {
//				return i;
//			}
//		}
//		return -1;
//	}
//
//	// Method to apply the algorithm for finding minimum cost,O(n^3), where n is the
//	// number of cities
//	public void findMinimum(ComboBox<String> start, ComboBox<String> end, String input) {
//		Start = start.getValue();
//		Goal = end.getValue();
//
//		StartCity = start.getSelectionModel().getSelectedIndex();
//		EndCity = end.getSelectionModel().getSelectedIndex();
//
//		cityIndexOffset = StartCity;
//
//		CitiesNum = EndCity - StartCity + 1;
//
//		table = new int[CitiesNum][CitiesNum];
//		next = new String[CitiesNum][CitiesNum];
//
//		for (int i = 0; i < CitiesNum; i++) { // fill the table with initial values
//			for (int j = 0; j < CitiesNum; j++) {
//				if (i == j)
//					table[i][j] = 0;
//				else
//					table[i][j] = Integer.MAX_VALUE;
//				next[i][j] = "X";
//			}
//		}
//
//		String[] line = new String[CitiesNum - 1];
//
//		line = input.split("\n");
//
//		for (int i = 0; i < CitiesNum - 1; i++) {
//			String[] parts = line[i + StartCity].split(", (?=\\[)");
//
//			for (int j = 1; j < parts.length; j++) {
//				String[] cityAndCosts = parts[j].replaceAll("[\\[\\]]", "").split(",");
//
//				String item = cityAndCosts[0].trim(); // item is the city
//
//				int city2 = 0;
//
//				for (int k = StartCity; k <= EndCity; k++) { // to get the index of city2 in the comboBox
//					if (start.getItems().get(k).equals(item)) {
//						city2 = k - StartCity;
//						break;
//					}
//				}
//				int petrolCost = Integer.parseInt(cityAndCosts[1].trim());
//
//				int hotelCost = Integer.parseInt(cityAndCosts[2].trim());
//
//				table[i][city2] = petrolCost + hotelCost;
//
//			}
//		}
//
//		for (int i = 0; i < CitiesNum; i++) {
//			for (int j = 0; j < CitiesNum; j++) {
//				if (j < i)
//					table[i][j] = Integer.MAX_VALUE;
//				if (i == j)
//					table[i][j] = 0;
//			}
//		}
//
//		for (int i = 0; i < CitiesNum; i++) { // fill the table with minimum
//			for (int j = 0; j < CitiesNum; j++) {
//				for (int k = 0; k < CitiesNum; k++) {
//					if (table[j][i] == Integer.MAX_VALUE || table[i][k] == Integer.MAX_VALUE)
//						continue;
//
//					if (table[j][k] > table[j][i] + table[i][k]) {
//						table[j][k] = table[j][i] + table[i][k];
//						next[j][k] = city[i + StartCity];
//					}
//
//				}
//			}
//		}
//
//		for (int i = 1; i < CitiesNum; i++) {
//			if (next[0][i].equals("X"))
//				next[0][i] = Start;
//			else
//				break;
//		}
//
//		// StartCity and EndCity should be adjusted to match the index of the selected
//		// cities in the new table
//		StartCity = 0;
//		EndCity = CitiesNum - 1;
//
//		for (int i = 0; i < next.length; i++)
//			if (table[0][i] == 0 || table[0][i] == Integer.MAX_VALUE)
//				next[0][i] = "X";
//	}
//
//	// Method to set button effects
//	public static void Effect(Button b) {
//		b.setOnMouseMoved(e -> {
//			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 15;\n"
//					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + "-fx-text-fill: #CE2029;\n"
//					+ "-fx-background-color: #d8d9e0;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width: 3.5;"
//					+ "-fx-background-radius: 25 25 25 25");
//		});
//		b.setOnMouseExited(e -> {
//			b.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 15;\n"
//					+ "-fx-font-family: Times New Roman;\n" + "-fx-font-weight: Bold;\n" + "-fx-text-fill: #f2f3f4;\n"
//					+ "-fx-background-color: transparent;\n" + "-fx-border-color: #d8d9e0;\n" + "-fx-border-width: 3.5;"
//					+ "-fx-background-radius: 25 25 25 25");
//		});
//	}
//
//	// Method to make change in icons. image,size and color
//	public static void icons(javafx.scene.Node l) {
//		l.setStyle("-fx-border-radius: 25 25 25 25;\n" + "-fx-font-size: 15;\n" + "-fx-font-family: Times New Roman;\n"
//				+ "-fx-font-weight: Bold;\n" + "-fx-text-fill: #f2f3f4;\n" + "-fx-background-color: transparent;\n"
//				+ "-fx-border-color: #d8d9e0;\n" + "-fx-border-width: 3.5;" + "-fx-background-radius: 25 25 25 25");
//	}
//
//	// method to show alerts
//	private void showAlert(String title, String headerText, String contentText) {
//		Alert alert = new Alert(AlertType.WARNING);
//		alert.setTitle(title);
//		alert.setHeaderText(headerText);
//		alert.setContentText(contentText);
//		alert.showAndWait();
//	}
//
//	public static void main(String[] args) throws FileNotFoundException {
//		launch(args);
//
//	}
//}