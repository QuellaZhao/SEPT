package WeatherApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class showTable {
	JPanel wjp = new JPanel();
	public showTable() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		retriveDatafromBOM rd = new retriveDatafromBOM();
		
		String[] coName = {"Date/Time WST","Temp","App Temp","Dew Point","Rel Hum","Delta-T","Wind Direction","Press MSL hPa","Rain since 9am"};
		Object[][] datas = {
				{rd.getTime()[2],rd.getTemp()[2],rd.getAppTemp()[2],rd.getDew()[2],rd.getRel()[2],rd.getDeltat()[2],
					rd.getWinddir()[2],rd.getPress()[2],rd.getRain()[2]},
				{rd.getTime()[1],rd.getTemp()[1],rd.getAppTemp()[1],rd.getDew()[1],rd.getRel()[1],rd.getDeltat()[1],
					rd.getWinddir()[1],rd.getPress()[1],rd.getRain()[1]},
				{rd.getTime()[0],rd.getTemp()[0],rd.getAppTemp()[0],rd.getDew()[0],rd.getRel()[0] ,rd.getDeltat()[2],
							rd.getWinddir()[2],rd.getPress()[2],rd.getRain()[2]}
		};
	
		//show the graph
		JTable wTable = new JTable(datas,coName);
		wjp.setLayout(new BorderLayout());
		wjp.add(wTable.getTableHeader(),BorderLayout.PAGE_START);
		wjp.add(wTable,BorderLayout.CENTER);
		JPanel bp = new JPanel();
		JButton graphb = new JButton("Graph");
		graphb.setPreferredSize(new Dimension(100,25));
		JButton refresh = new JButton("Refresh");
		refresh.setPreferredSize(new Dimension(100,25));
		bp.add(graphb);
		bp.add(refresh);
		graphb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainframe mf = new mainframe();
				try {
					mf.graphFrame();
				} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		wjp.add(bp,BorderLayout.PAGE_END);
	}
	public JPanel getTablePanel(){
		return wjp;
	}
}
