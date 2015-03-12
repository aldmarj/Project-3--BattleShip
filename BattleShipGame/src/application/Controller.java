package application;

import java.util.concurrent.ArrayBlockingQueue;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller extends Main {
	
	@FXML
	TextField hostInput;
	
	@FXML
	TextField portInput;
	
	@FXML
	TextArea textInput;
	
	@FXML
	TextArea incomingText;
	
	@FXML
	Button sendBTN;
	
	private ArrayBlockingQueue<String> channel;
	private TalkThread talker;
	
	
	@FXML
	public void initializer(){	
		
	}
	
	@FXML
	public void sendMessage(){
		
				send(textInput.getText(), hostInput.getText(), Integer.parseInt(portInput.getText()));	
				
	}
	
	private void send(String msg, String host, int port) {
	
		if (talker != null  && talker.isGoing()) {
			talker.halt();
		}
		talker = new TalkThread(msg, host, port, channel);
		new Receiver().start();
		talker.start();
	}
	
	
	private class Receiver extends Thread {
		public void run() {
			channel = new ArrayBlockingQueue<String>(2, true);
			while (talker.isGoing()) {
				
				String line;
				
				try {
					line = channel.take();
					
					Platform.runLater(() -> {
						incomingText.appendText(incomingText.getText() + "\n" + line);
					});
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Are you not working!");
				}
			}
		}
	}


}
