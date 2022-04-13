package tech.criasystem.telegram;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.springframework.web.context.annotation.ApplicationScope;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@ApplicationScope
public class TelegramBot extends TelegramLongPollingBot{

	
    private String token;
    private String username;
    private String chatId;
    
	public TelegramBot() {
		super();
		getProp();
	}

	public TelegramBot(DefaultBotOptions options) {
		super(options);
	}

	public void sendMessage(String message) throws TelegramApiException {
	        	SendMessage send = SendMessage.builder()
	                    .text(message)
	                    .chatId(chatId)
	                    .build();
	        	execute(send);
	        
	    }

	public void sendDocUploadingAFile(InputFile file) throws TelegramApiException {

	    SendDocument send = SendDocument.builder()
                .document(file)
                .chatId(chatId)
                .build();
    	execute(send);
	}
	
	public void getProp() {
		try {
			Path resourceDirectory = Paths.get("src","main","resources");
			String absolutePath = resourceDirectory.toFile().getAbsolutePath();
			String outFile = "application";
			Properties props = new Properties();
			FileInputStream file = new FileInputStream(
					absolutePath+"/"+outFile + ".properties");
			props.load(file);
			setToken(props.getProperty("telegram.token"));
			setUsername(props.getProperty("telegram.username"));
			setChatId(props.getProperty("telegram.chatId"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
		
		@Override
		public void onUpdateReceived(Update update) {
			
		}

		@Override
		public String getBotUsername() {
			return username;
		}

		@Override
		public String getBotToken() {
			return token;
		}
		
		
		 public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getChatId() {
			return chatId;
		}

		public void setChatId(String chatId) {
			this.chatId = chatId;
		}
}
