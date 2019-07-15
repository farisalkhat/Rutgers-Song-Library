//Code by Faris Al-khatahtbeh(fa301) and Miguel Macaoay (mtm236)
 

package songLib.view;


import songLib.view.LinkedLists.Node;
import songLib.view.LinkedLists;
import songLib.view.SaveSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class songLibController {
	

		@FXML private VBox comfButtons;
		@FXML private Button Confirm;
		@FXML private Button Cancel;
		@FXML private VBox editboxes;
		@FXML private TextField titleEdit;
		@FXML private TextField	artistEdit;
		@FXML private TextField yearEdit;
		@FXML private TextField albumEdit;
		
		@FXML private Button add;
		@FXML private Button edit;
		@FXML private Button delete;
		
		@FXML ListView<Node> listView;
		@FXML Label songDetails;
		//creates and initializes LL with file data
		LinkedLists list = new LinkedLists();
		boolean isListEmpty;

		Alert error;
		
		
		public void start(Stage mainStage) {                

			SaveSystem.SSLoad(list);
			if(list.head==null) {
				isListEmpty=true;}
			
			
			
			editboxes.setVisible(false);
			Confirm.setVisible(false);
			Cancel.setVisible(false);
			
			

			
			
			//System.out.println(list.size);
			if(isListEmpty==false) {
			ObservableList<Node> entries = FXCollections.observableArrayList();
			Node head = list.head;
			while(head!=null) {
				entries.add(head);
				head=head.next;
			}
			
			listView.setItems(entries);
			
			// select the first item
		    listView.getSelectionModel().select(0);
			Node entry;
			entry = listView.getSelectionModel().getSelectedItem();
			if(entry.dateCreated!=0 && entry.album.equals("null")) {
				songDetails.setText("Title: " + entry.songName + "\n"+ 
						"Artist: " + entry.artist + "\n" +
						"Year: " + entry.dateCreated + "\n" +
						"Album: " + "" + "\n");
			}
			//1 2 
			else if(entry.dateCreated==0 && entry.album.equals("null")) {
				songDetails.setText("Title: " + entry.songName + "\n"+ 
						"Artist: " + entry.artist + "\n" +
						"Year: " + "" + "\n" +
						"Album: " + "" + "\n");
			}			
			//1 2 4 
			else if(entry.dateCreated==0) {
				songDetails.setText("Title: " + entry.songName + "\n"+ 
						"Artist: " + entry.artist + "\n" +
						"Year: " + "" + "\n" +
						"Album: " + entry.album + "\n");
			}

			
			//1 2 3 4 
			else {
			songDetails.setText("Title: " + entry.songName + "\n"+ 
								"Artist: " + entry.artist + "\n" +
								"Year: " + entry.dateCreated + "\n" +
								"Album: " + entry.album + "\n");}
			songDetails.setTextAlignment(TextAlignment.LEFT);}
			
			listView.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal)->showDetails(mainStage));
		    
		}
		
		private void showDetails(Stage mainStage) {
			Node entry;
			int isNull=0;
			entry = listView.getSelectionModel().getSelectedItem();
			//System.out.println("This is song:" + entry.songName);
			//System.out.println("This is artist:" + entry.artist);
			//System.out.println("This is year:" + entry.dateCreated);
			//System.out.println("This is album:" + entry.album);
			
			
			
			//This checks if 4 is missing.
			if(entry.album==null || entry.album.equals("null")) {
				System.out.println("album is actually null!");
				
				//1 2 3
				if(entry.dateCreated!=0) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + entry.dateCreated + "\n" +
							"Album: " + "" + "\n");
				}
				//1 2 
				else if(entry.dateCreated==0) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + "" + "\n" +
							"Album: " + "" + "\n");
					
				}
				return;
			}
			
			else {
				//1 2 3 4
				if(entry.dateCreated!=0) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + entry.dateCreated + "\n" +
							"Album: " + entry.album + "\n");
				}
				//1 2 4
				else if(entry.dateCreated==0) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + "" + "\n" +
							"Album: " + entry.album + "\n");
					
				}
				
			}
			
			
			
			
			
			/***
			if(entry.dateCreated!=0) {
				//1 2 3
				if(isNull==1) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + entry.dateCreated + "\n" +
							"Album: " + "" + "\n");
				}
				else if(entry.album=="null") {
					System.out.println("album is the string null!");
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + entry.dateCreated + "\n" +
							"Album: " + "" + "\n");
				}
				//1 2 3 4
				else {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + entry.dateCreated + "\n" +
							"Album: " + entry.album + "\n");
					
				}
			}			
			 
			else if(entry.dateCreated==0) {
				//1 2 
				if(entry.album.equals("null")) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + "" + "\n" +
							"Album: " + "" + "\n");
				}
				else if(isNull==1) {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + "" + "\n" +
							"Album: " + "" + "\n");
				}
				//1 2 4 
				else {
					songDetails.setText("Title: " + entry.songName + "\n"+ 
							"Artist: " + entry.artist + "\n" +
							"Year: " + "" + "\n" +
							"Album: " + entry.album + "\n");
				}
				
			}

			
			*///
			songDetails.setTextAlignment(TextAlignment.LEFT);
			
		}

		//when user clicks 'Add' button
		@FXML
		private void handleAddAction(ActionEvent event) {
			System.out.println("ADD clicked");
			//clears old text from box
			listView.setDisable(true);
			add.setDisable(true);
			delete.setDisable(true);
			edit.setDisable(true);
			
			titleEdit.clear();
			artistEdit.clear();
			yearEdit.clear();
			albumEdit.clear();
			//reveals edit boxes
			editboxes.setVisible(true);
			Confirm.setVisible(true);
			Cancel.setVisible(true);
			//allows edit boxes to be written into
			titleEdit.setEditable(true);
			artistEdit.setEditable(true);
			yearEdit.setEditable(true);
			albumEdit.setEditable(true);
		
			
			//action upon Confirm being clicked
			Confirm.setOnAction(eventtwo -> {
				//System.out.println("CONFIRM clicked");
				//takes the input of each box
				String titleholder = titleEdit.getText();
				String artistholder = artistEdit.getText();
				String yearholder = yearEdit.getText();
				String albumholder = albumEdit.getText();
				int newInsert;
				String check1,check2,check4;
				int check3;
				//System.out.println(titleholder+" "+artistholder+" "+yearholder+" "+albumholder);
				boolean yearholderCheck= containsDigit(yearholder);
				
				System.out.println("This is yearholder:"+yearholder);
				System.out.println("This is yearholderCheck"+ yearholderCheck);
				if(yearholderCheck==false) {
					error = new Alert(AlertType.INFORMATION);
					error.setTitle("Error!");
					error.setHeaderText("Incorrect input!");
					error.setContentText("You did not set an actual year into the year field.");
					error.showAndWait();
					
					editboxes.setVisible(false);
					titleEdit.setEditable(false);
					artistEdit.setEditable(false);
					yearEdit.setEditable(false);
					albumEdit.setEditable(false);
					Confirm.setVisible(false);
					Cancel.setVisible(false);
					
					listView.setDisable(false);
					add.setDisable(false);
					delete.setDisable(false);
					edit.setDisable(false);
					return;
					
					
				}
				
				
				//makes sure there are title and artist to insert
				if(!(titleholder.isEmpty()) && !(artistholder.isEmpty())) {
					//creates the new LL node
					Node tmp;
					if(albumholder.isEmpty() && yearholder.isEmpty()) {
						tmp = new Node(titleholder,artistholder);
						check1=titleholder;
						check2=artistholder;
						check3= 0;
						check4= "null";
					}else if(albumholder.isEmpty() ) {
						tmp = new Node(titleholder,artistholder, Integer.parseInt(yearholder));
						check1= titleholder;
						check2= artistholder;
						check3= Integer.parseInt(yearholder);
						check4= "null";
					}else if(yearholder.isEmpty()) {
						tmp = new Node(titleholder,artistholder, albumholder);
						check1= titleholder;
						check2= artistholder;
						check3= 0;
						check4= albumholder;
					}else {
						tmp = new Node(titleholder,artistholder, Integer.parseInt(yearholder), albumholder);
						check1= titleholder;
						check2= artistholder;
						check3= Integer.parseInt(yearholder);
						check4= albumholder;
					}
					//inserts the node into the LL
					//newInsert=list.search(list,check1,check2,check3,check4);
					//if(newInsert!=-1) {
						//System.out.println("newInsert is -1!");
						/***
						error = new Alert(AlertType.INFORMATION);
						error.setTitle("Error!");
						error.setHeaderText("Duplicate entry!");
						error.setContentText("The entry you've entered is a duplicate. Therefore, your input has been cancelled.");
						error.showAndWait();
						editboxes.setVisible(false);
						titleEdit.setEditable(false);
						artistEdit.setEditable(false);
						yearEdit.setEditable(false);
						albumEdit.setEditable(false);
						Confirm.setVisible(false);
						Cancel.setVisible(false);
						
						listView.setDisable(false);
						add.setDisable(false);
						delete.setDisable(false);
						edit.setDisable(false);
						*///
					//}
					
					list = list.insert(list, tmp);
					if(list.duplicate==1) {
						error = new Alert(AlertType.INFORMATION);
						error.setTitle("Error!");
						error.setHeaderText("Duplicate entry!");
						error.setContentText("The entry you've entered is a duplicate. Therefore, your input has been cancelled.");
						error.showAndWait();
						list.duplicate=0;
						
						editboxes.setVisible(false);
						titleEdit.setEditable(false);
						artistEdit.setEditable(false);
						yearEdit.setEditable(false);
						albumEdit.setEditable(false);
						Confirm.setVisible(false);
						Cancel.setVisible(false);
						
						listView.setDisable(false);
						add.setDisable(false);
						delete.setDisable(false);
						edit.setDisable(false);
						return;
					}
					
					//newInsert=list.search(list,titleholder,artistholder,yearholder,albumholder);
					
					//updates the listview and file
					SaveSystem.SSUpdate(list);
					ObservableList<Node> entries = FXCollections.observableArrayList();
					Node head = list.head;
					while(head!=null) {
						entries.add(head);
						head=head.next;
					}
/*REQUIRES LOVE*/	listView.setItems(entries);
					newInsert=list.search(list,check1,check2,check3,check4);
					System.out.println("This is newInsert!"+newInsert);
					listView.getSelectionModel().select(newInsert);
					//list.print(list);

				}else{
					error = new Alert(AlertType.INFORMATION);
					error.setTitle("Error!");
					error.setHeaderText("Missing fields!");
					error.setContentText("You are required to have inputs in both the artist and song title fields.");
					error.showAndWait();
					
					System.out.println("NOT ENOUGH FIELDS");
				}
				
				//closes the windows
				editboxes.setVisible(false);
				titleEdit.setEditable(false);
				artistEdit.setEditable(false);
				yearEdit.setEditable(false);
				albumEdit.setEditable(false);
				Confirm.setVisible(false);
				Cancel.setVisible(false);
				
				listView.setDisable(false);
				add.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
			});
			
			//action upon Cancel being clicked
			Cancel.setOnAction(eventtwo -> {
				//System.out.println("CANCEL clicked");
				//closes the windows
				editboxes.setVisible(false);
				titleEdit.setEditable(false);
				artistEdit.setEditable(false);
				yearEdit.setEditable(false);
				albumEdit.setEditable(false);
				Confirm.setVisible(false);
				Cancel.setVisible(false);
				
				listView.setDisable(false);
				add.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
			});
		}
		
		//when user clicks 'Edit' button
		@FXML
		private void handleEditAction(ActionEvent event) {
			if(list.head==null) {
				error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error!");
				error.setHeaderText("No entries!");
				error.setContentText("There are no entries, thus there is nothing to edit.");
				error.showAndWait();
				return;
				
			}
			
			
			
			
			System.out.println("ADD clicked");
			//clears old text from box
			titleEdit.clear();
			artistEdit.clear();
			yearEdit.clear();
			albumEdit.clear();
			//reveals edit boxes
			editboxes.setVisible(true);
			Confirm.setVisible(true);
			Cancel.setVisible(true);
			//allows edit boxes to be written into
			titleEdit.setEditable(true);
			artistEdit.setEditable(true);
			yearEdit.setEditable(true);
			albumEdit.setEditable(true);
			
			listView.setDisable(true);
			add.setDisable(true);
			delete.setDisable(true);
			edit.setDisable(true);
		

			
			
			//action upon Confirm being clicked
			Confirm.setOnAction(eventtwo -> {
				

				
				
				
				
				//System.out.println("CONFIRM clicked");
				//takes the input of each box
				String titleholder = titleEdit.getText();
				String artistholder = artistEdit.getText();
				String yearholder = yearEdit.getText();
				String albumholder = albumEdit.getText();
				//System.out.println(titleholder+" "+artistholder+" "+yearholder+" "+albumholder);
				int newInsert;
				String check1,check2,check4;
				int check3;
				//makes sure there are title and artist to insert
				boolean yearholderCheck= containsDigit(yearholder);
				
				System.out.println("This is yearholder:"+yearholder);
				System.out.println("This is yearholderCheck"+ yearholderCheck);
				if(yearholderCheck==false) {
					error = new Alert(AlertType.INFORMATION);
					error.setTitle("Error!");
					error.setHeaderText("Incorrect input!");
					error.setContentText("You did not set an actual year into the year field.");
					error.showAndWait();
					
					editboxes.setVisible(false);
					titleEdit.setEditable(false);
					artistEdit.setEditable(false);
					yearEdit.setEditable(false);
					albumEdit.setEditable(false);
					Confirm.setVisible(false);
					Cancel.setVisible(false);
					
					listView.setDisable(false);
					add.setDisable(false);
					delete.setDisable(false);
					edit.setDisable(false);
					return;
					
					
				}
				
				
				
					Node tmp;
					if(!(titleholder.isEmpty()) && !(artistholder.isEmpty())) {
						//creates the new LL node
						if(albumholder.isEmpty() && yearholder.isEmpty()) {
							tmp = new Node(titleholder,artistholder);
							check1=titleholder;
							check2=artistholder;
							check3= 0;
							check4= "null";
						}else if(albumholder.isEmpty()) {
							tmp = new Node(titleholder,artistholder, Integer.parseInt(yearholder));
							check1= titleholder;
							check2= artistholder;
							check3= Integer.parseInt(yearholder);
							check4= "null";
						}else if(yearholder.isEmpty()) {
							tmp = new Node(titleholder,artistholder, albumholder);
							check1= titleholder;
							check2= artistholder;
							check3= 0;
							check4= albumholder;
						}else {
							tmp = new Node(titleholder,artistholder, Integer.parseInt(yearholder), albumholder);
							check1= titleholder;
							check2= artistholder;
							check3= Integer.parseInt(yearholder);
							check4= albumholder;
						}
						
						
					//deletes currently selected node
					Node entry = listView.getSelectionModel().getSelectedItem();
					list = list.insert(list, tmp);		
					if(list.duplicate==1) {
						error = new Alert(AlertType.INFORMATION);
						error.setTitle("Error!");
						error.setHeaderText("Duplicate entry!");
						error.setContentText("The entry you've entered is a duplicate. Therefore, your input has been cancelled.");
						error.showAndWait();
						list.duplicate=0;
						
						editboxes.setVisible(false);
						titleEdit.setEditable(false);
						artistEdit.setEditable(false);
						yearEdit.setEditable(false);
						albumEdit.setEditable(false);
						Confirm.setVisible(false);
						Cancel.setVisible(false);
						
						listView.setDisable(false);
						add.setDisable(false);
						delete.setDisable(false);
						edit.setDisable(false);
						return;	
					}
					
					list = list.delete(list, entry);
					
					//updates the listview
					SaveSystem.SSUpdate(list);
					ObservableList<Node> entries = FXCollections.observableArrayList();
					Node head = list.head;
					while(head!=null) {
						entries.add(head);
						head=head.next;
					}
					
/*REQUIRES LOVE*/	listView.setItems(entries);
					newInsert=list.search(list,check1,check2,check3,check4);
					listView.getSelectionModel().select(newInsert);



				}else{
					error = new Alert(AlertType.INFORMATION);
					error.setTitle("Error!");
					error.setHeaderText("Missing fields!");
					error.setContentText("You are required to have inputs in both the artist and song title fields.");
					error.showAndWait();
					System.out.println("NOT ENOUGH FIELDS");
				}
				
				//closes the windows
				editboxes.setVisible(false);
				titleEdit.setEditable(false);
				artistEdit.setEditable(false);
				yearEdit.setEditable(false);
				albumEdit.setEditable(false);
				Confirm.setVisible(false);
				Cancel.setVisible(false);
				
				listView.setDisable(false);
				add.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
			});
			
			//action upon Cancel being clicked
			Cancel.setOnAction(eventtwo -> {
				//System.out.println("CANCEL clicked");
				//closes the windows
				editboxes.setVisible(false);
				titleEdit.setEditable(false);
				artistEdit.setEditable(false);
				yearEdit.setEditable(false);
				albumEdit.setEditable(false);
				Confirm.setVisible(false);
				Cancel.setVisible(false);
				
				listView.setDisable(false);
				add.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
			});
		}
		
		//when user clicks 'Delete' button
		@FXML
		private void handleDeleteAction(ActionEvent event) {
			System.out.println("DELETE clicked");
			
			
			if(list.head==null) {
				error = new Alert(AlertType.INFORMATION);
				error.setTitle("Error!");
				error.setHeaderText("No entries!");
				error.setContentText("There are no entries, thus there is nothing to delete.");
				error.showAndWait();
				return;
				
			}
			
			
			//reveals buttons
			Confirm.setVisible(true);
			Cancel.setVisible(true);
			
			
			listView.setDisable(true);
			add.setDisable(true);
			delete.setDisable(true);
			edit.setDisable(true);
			//action upon Confirm being clicked
			Confirm.setOnAction(eventtwo -> {
				//System.out.println("CONFIRM clicked");
				
				//find target node, and delete it
				Node entry;
				int index;
				entry = listView.getSelectionModel().getSelectedItem();
				index = listView.getSelectionModel().getSelectedIndex();
				
				list = list.delete(list, entry);
				//check1 = list.nextSong;
				//check2 = list.nextArtist;
				//check3=  list.nextDate;
				//check4 = list.nextAlbum;
				System.out.println("This is list head:" + list.head);
				//updates the listview and file
				if(list.head==null) {
					list.size=0;
				}
				SaveSystem.SSUpdate(list);
				ObservableList<Node> entries = FXCollections.observableArrayList();
				Node head = list.head;
				int size= 0;
				while(head!=null) {
					entries.add(head);
					head=head.next;
					size++;
				}
				head=list.head;
				if(head==null) {
					listView.setItems(null);
					songDetails.setText("");
					
					
				}
				else {
					listView.setItems(entries);
				}
				
				
				//answer = list.search(list, check1, check2, check3, check4);
				System.out.println("This is index:"+index);
				System.out.println("This is size:"+ size);
				
				if(size!=0) {
					if(index==0) {
						listView.getSelectionModel().select(0);
					}
					else if(index==size) {
					listView.getSelectionModel().select(index-1);}
					else {
						listView.getSelectionModel().select(index);
					}
				}
				
				
				
				//closes the windows
				Confirm.setVisible(false);
				Cancel.setVisible(false);
				
				listView.setDisable(false);
				add.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
			});
			
			//action upon Cancel being clicked
			Cancel.setOnAction(eventtwo -> {
				//System.out.println("CANCEL clicked");
				//closes the windows
				Confirm.setVisible(false);
				Cancel.setVisible(false);
				
				listView.setDisable(false);
				add.setDisable(false);
				delete.setDisable(false);
				edit.setDisable(false);
			});
		}
		
		
		private void showItem(Stage mainStage) {                
		      Alert alert = 
		         new Alert(AlertType.INFORMATION);
		      //alert.initModality(Modality.NONE);
		      alert.initOwner(mainStage);
		      alert.setTitle("List Item");
		      alert.setHeaderText(
		           "Selected list item properties");

		      String content = "Index: " + 
		          listView.getSelectionModel()
		                   .getSelectedIndex() + 
		          "\nValue: " + 
		          listView.getSelectionModel()
		                   .getSelectedItem();

		          alert.setContentText(content);
		          alert.showAndWait();
		          // alert.show();  
		          //System.out.println("hey there!");
		          
		   }
		public static boolean containsDigit(final String aString)
		{
		    if (aString != null && !aString.isEmpty())
		    {
		        for (char c : aString.toCharArray())
		        {
		            if (Character.isDigit(c)==false)
		            {
		                return false;
		            }
		        }
		    }

		    return true;
		}
	
	
	}

