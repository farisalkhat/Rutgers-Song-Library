//Code by Faris Al-khatahtbeh(fa301) and Miguel Macaoay (mtm236)

package songLib.view;

public class LinkedLists {
	Node head;
	public int size = 0;
	String nextArtist;
	String nextSong;
	int nextDate;
	String nextAlbum;
	int duplicate = 0;
	
	public static class Node {
		String artist;
		String songName;
		int dateCreated;
		String album;
		Node next;
		
		
		public Node(String songName, String artist){  //If the user only inserts the required artist and song title.
			this.artist= artist;
			this.songName = songName;
			next = null;
			
		}
		
		public Node(String songName, String artist, int dateCreated){ //If the user inputs the two required inputs along with the optional date.
			this.artist= artist;
			this.songName = songName;
			this.dateCreated = dateCreated;
			next = null;
			
		}
		
		public Node(String songName, String artist,  String album){//If the user inputs the two required inputs along with the optional album.
			this.artist= artist;
			this.songName = songName;
			this.album=album;
			next = null;
			
		}
		
		public Node(String songName, String artist, int dateCreated, String album){ //If the user inputs all 4 inputs. 
			this.artist= artist;
			this.songName = songName;
			this.dateCreated = dateCreated;
			this.album = album;
			next = null;
			
		}
		
		public String getSongName(){
			return songName;
		}
		
		public String getArtistName(){
			return artist;
		}
		
		public String toString() {
			return(getSongName() + ", "+ getArtistName());
		}
		
		
		
	}
	
	

		
		

		
		
		public LinkedLists insert(LinkedLists list, Node entry) {
			
			//Check if this is the first entry.
			if(list.head==null) {
				list.head = entry;
			}
			
			//Check if there is a duplicate

			
			
			//If it's not the first entry, add it to the end.
			else {
				
				Node search=list.head;
				while(search!=null) {
					if(search.artist.equals(entry.artist) && search.songName.equals(entry.songName)) {
						list.duplicate=1;
						return list;
						}
				
					search=search.next;
				}
				
				
				Node last =list.head;
				while(last.next!=null) {
					last = last.next;
				}
				last.next = entry;
			}
			//Now we want to sort it alphabetically by song title. 
			boolean swapped;
			do {
				Node current = head;
				Node previous = null;
				Node next = head.next;
				swapped = false;
				
				while(next!=null) {
					String s1 = current.songName;
					String s2= next.songName;
					if (s1.compareToIgnoreCase(s2)>0) {
						swapped= true;
						if (previous!=null){
							Node temp = next.next;
							previous.next=next;
							next.next = current;
							current.next=temp;}
						else {
							Node temp = next.next;
							head = next;
							next.next = current;
							current.next= temp;}
						previous = next;
						next = current.next;}

					
					

					else {
							previous = current;
							current = next;
							next = next.next;}}
				}while(swapped);
			
			
			do {
				Node current = head;
				Node previous = null;
				Node next = head.next;
				swapped = false;
				
				while(next!=null) {
					String s1 = current.songName;
					String s2= next.songName;
					String s3 = current.artist;
					String s4 = next.artist;
					if (s1.compareToIgnoreCase(s2)==0 && s3.compareTo(s4)>0) {
						swapped= true;
						if (previous!=null){
							Node temp = next.next;
							previous.next=next;
							next.next = current;
							current.next=temp;}
						else {
							Node temp = next.next;
							head = next;
							next.next = current;
							current.next= temp;}
						previous = next;
						next = current.next;}
					else {
							previous = current;
							current = next;
							next = next.next;}}
				}while(swapped);
			
			
			

				
				
			
			
			
			//Finally, return the list so that listView can reorder everything. 
			list.size++;
			return list;
		}
		
		
		
public LinkedLists delete(LinkedLists list, Node entry) {		
			Node prev = null;
			Node found;
			Node next;
	
			//Check if this is the first entry.
			if(list.head==null) {
				return null;
			}
			//If it's not the first entry, add it to the end.
			
			found = list.head;
			next = found.next;
			while(found!=null) {
				if(found==entry) {
					//case1: it's the first node
					if(prev==null) {
						if(found.next==null) {
							list.head=null;
							return list;
						}
						
						
						list.head=list.head.next;
						list.nextArtist=head.artist;
						list.nextSong=head.songName;
						list.nextDate=head.dateCreated;
						list.nextAlbum=head.album;
						list.size--;
						
						return list;
					}
					//case2: it's at the end
					else if(next==null) {
						prev.next=null;
						
						list.size--;
						list.nextArtist=prev.artist;
						list.nextSong=prev.songName;
						list.nextDate=prev.dateCreated;
						list.nextAlbum=prev.album;
						
						return list;
					}
					//cas3: it's in the middle
					else if(prev!=null && next!=null) {
						prev.next = found.next;
						
						list.size--;
						list.nextArtist=next.artist;
						list.nextSong=next.songName;
						list.nextDate=next.dateCreated;
						list.nextAlbum=next.album;
						
						return list;
					}
				}
				else {
					prev = found;
					found= found.next;
					next= found.next;
				}
			}
			System.out.println("Somehow didn't find a match.");
			return list; 
			
		}
public void print(LinkedLists list) {
	Node head= list.head;
	while(head!=null) {
		System.out.println(head.album);
		head=head.next;
	}
	
}			
		
public int search(LinkedLists list,String titleholder,String artistholder,int yearholder,String albumholder) {
	Node head = list.head;
	int nullchecker = 0;
	
	if(albumholder.equals("null")) {
		nullchecker = 1;
	}
	int answer = 0;
	while(head!=null) {
		if(titleholder.equals(head.songName) && artistholder.equals(head.artist) && yearholder==head.dateCreated){  
			if(nullchecker==1) {
				if(head.album==null) {
					System.out.println("head.album is null!");
					return answer;
				}
				
			}
			
			else if(albumholder.equals(head.album)) {
				System.out.println("Album is equal to the string null");
				return answer;
			}

			else{
				System.out.println("First three match, but not album");
				answer++;
				head=head.next;
			}}
		
		
			else {
				answer++;
				head=head.next;
			}
	}
		
	
	System.out.println("This is answer:" + answer);
	return -1;
}
	}

	/***
		 public static void main(String[] args) 
		    { 
		        
		        LinkedLists list = new LinkedLists(); 
		  
		        // 
		        // ******INSERTION****** 
		        // 
				Node test1 = new Node("a-artist","songname");
				
				Node test2 = new Node("b-artist","songname");
				Node test3 = new Node("c-artist","songname");
				Node test4 = new Node("d-artist","songname");
				Node test5 = new Node("e-artist","songname");
				Node test6 = new Node("f-artist","songname");
		        // Insert the values 
		        list = insert(list, test1); 
		        list = insert(list, test2); 
		        list = insert(list, test3); 
		        list = insert(list, test4); 
		        list = insert(list, test5); 

		    } 
		***/
	
	

	
	
	
	
	

