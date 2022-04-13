package tech.criasystem.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import tech.criasystem.telegram.TelegramBot;

@Service
public class TelegramService {

	public void sendFile(File file) throws TelegramApiException {
		TelegramBot telegramBot = new TelegramBot();
        InputFile inputFile = new InputFile(file);
        telegramBot.sendDocUploadingAFile(inputFile);
	}
}
