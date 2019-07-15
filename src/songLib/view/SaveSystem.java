//Code by Faris Al-khatahtbeh(fa301) and Miguel Macaoay (mtm236)

package songLib.view;

import java.io.*;

import songLib.view.LinkedLists;
import songLib.view.LinkedLists.Node;




public final class SaveSystem {


	public static void SSUpdate(LinkedLists list) {
		//overwrites existing data in file with new LL data	
		try {
			FileWriter fw = new FileWriter("Save.txt");
			PrintWriter pw = new PrintWriter(fw);
			System.out.println("This is list.size!" + list.size);
			if(list.size==0) {
				pw.close();
				System.out.println("hello, list.size is 0 so I am returning early!");
				return;
			}

			//goes through each node, creates a @ separated string, and saves it to the file
			Node ptr = list.head;
			while(ptr.next!=null) {
					pw.println(ptr.songName+"@"+ptr.artist+"@"+Integer.toString(ptr.dateCreated)+"@"+ptr.album+"@ ");
					ptr= ptr.next;
			}
			pw.println(ptr.songName+"@"+ptr.artist+"@"+Integer.toString(ptr.dateCreated)+"@"+ptr.album+"@ ");
			pw.close();
			
		}catch(IOException e){
			System.out.println("An ERROR occured while updating file");
		}	
	}	
		
	public static void SSLoad(LinkedLists list) {
		//loads file into LL at start of program
				
		try {
			FileReader fr = new FileReader("Save.txt");
			BufferedReader br = new BufferedReader(fr);
			
			//loops line by line in the file and creates nodes for the list
			String str;
			while((str = br.readLine()) != null) {
				
				String [] info = str.split("@");
				
				//creates the new LL node
				Node tmp;
				if(info[3].isEmpty() && info[2].isEmpty()) {
					tmp = new Node(info[0],info[1]);
				}else if(info[3].isEmpty()) {
					tmp = new Node(info[0],info[1], Integer.parseInt(info[2]));
				}else if(info[2].isEmpty()) {
					tmp = new Node(info[0],info[1], info[3]);
				}else {
					tmp = new Node(info[0],info[1], Integer.parseInt(info[2]), info[3]);
				}
				//inserts the node into the LL
				list = list.insert(list, tmp);	
				
			}
			
			br.close();
		}catch(IOException e){
			System.out.println("File not found");
		}
	}	
	
	
}

	
	
	
	
	

