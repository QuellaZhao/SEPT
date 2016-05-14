package WeatherApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class favs {
	ArrayList<String> favs = new ArrayList<String>();
	Path uc = Paths.get("UsersChoice.txt");
	Charset cs = Charset.forName("US-ASCII");
	JPanel favPanel;
	
	public favs() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		retriveDatafromBOM rb = new retriveDatafromBOM();
	
		//Get user's choices 
		try(BufferedReader reader = Files.newBufferedReader(uc,cs)) {
			while(reader.readLine()!=null){
				favs.add(reader.readLine());
			}
		} catch (IOException e1) {
				System.out.println("read failed");
				e1.printStackTrace();
		}
					
		//add selected station in favorites
		JButton addfav = new JButton("ADD");
		addfav.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			favs.add(rb.getCity().getSelectedItem().toString());
				try(BufferedWriter writer = Files.newBufferedWriter(uc,cs,StandardOpenOption.APPEND);) {
					writer.write("\r\n" + favs.get(favs.size()-1) + "\r\n");
				} catch (IOException e1) {
					System.out.println("write failed");
					e1.printStackTrace();
				}
		}});
					
		//remove selected station in favorites
		JButton removefav = new JButton("remove");
		removefav.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			favs.remove(rb.getCity().getSelectedItem().toString());
			try(BufferedWriter writer = Files.newBufferedWriter(uc,cs,StandardOpenOption.TRUNCATE_EXISTING);) {
				for(int i = 0;i<favs.size();i++){
				writer.write(favs.get(i) + "\r\n");
				}
			} catch (IOException e1) {
				System.out.println("write failed");
				e1.printStackTrace();
			}
		}});
					
		//confirm and display all selected stations
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			mainframe mf = new mainframe();
			try {
				mf.favFrame();
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//the old window closedsd
		}});
		favPanel = rb.getMainPanel();
		favPanel.add(addfav);
		favPanel.add(removefav);
		favPanel.add(confirm);
	}
	public ArrayList<String> getFavs(){
		return favs;
	}
	public JPanel getFavPa(){
		return favPanel;
	}
}
