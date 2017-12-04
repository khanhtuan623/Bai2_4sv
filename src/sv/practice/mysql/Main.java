package sv.practice.mysql;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sv.practice.object.Student;
public class Main {

	private JFrame frame;
	private JTextField link;
	private JButton btnBrowse;
	private JButton btnInsert;
	private JButton btnShow;
	private JFileChooser fc;
	private String fileID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		btnBrowse.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == btnBrowse) {
							fc = new JFileChooser(new File(System.getProperty("user.home") + "\\Desktop"));
							fc.setDialogTitle("Select Location");
							fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
							fc.setAcceptAllFileFilterUsed(false);
		
							if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
								fileID = fc.getSelectedFile().getPath();
								link.setText(fileID);
							}
						}
						
					}
				});
		btnInsert.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						readCVS(fileID);
						
					}
				});
		
		btnShow.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JDBCStatement.statement();			
					}
				});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbFile = new JLabel("File");
		lbFile.setBounds(41, 72, 61, 16);
		frame.getContentPane().add(lbFile);
		
		link = new JTextField();
		link.setBounds(120, 67, 130, 26);
		frame.getContentPane().add(link);
		link.setColumns(10);
		
		 btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(262, 67, 117, 29);
		
		frame.getContentPane().add(btnBrowse);
		
		 btnInsert = new JButton("Insert");
		
		btnInsert.setBounds(87, 123, 80, 29);
		frame.getContentPane().add(btnInsert);
		
		btnShow = new JButton("Show");
		btnShow.setBounds(188, 123, 86, 29);
		frame.getContentPane().add(btnShow);
	}
	public static void readCVS(String path) {
		ArrayList<Student>listStudent=new ArrayList<Student>();
		try {
			FileInputStream fis = new FileInputStream(path); //đọc dữ liệu theo định dạng byte
			InputStreamReader isr = new InputStreamReader(fis); //chuyển byte sang kí tự
			BufferedReader br = new BufferedReader(isr); //đọc văn bản dựa trên kí tự
			
			//bỏ dòng đầu tiên - header
			String line = br.readLine();//đọc theo dòng
			String [] st;
			
			while ((line = br.readLine()) != null) {
				st = line.split(",");//cắt chuỗi
				listStudent.add(new Student(st[0], st[1], st[2], st[3]));//add data to list
			}
			//Insert data to database
			for(int i=0;i<listStudent.size();i++)
			{
				Insert.insertInfo(listStudent.get(i).getFname(),listStudent.get(i).getLname(),listStudent.get(i).getBd(), listStudent.get(i).getEmail());
			}
			//đóng luồng dữ liệu
			br.close();
			isr.close();
			fis.close();	
			//xuất dữ liệu
			for (Student st1 : listStudent) {
				System.out.println(st1.toString());
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
}
	

}
