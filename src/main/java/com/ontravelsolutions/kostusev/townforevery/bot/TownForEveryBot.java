package com.ontravelsolutions.kostusev.townforevery.bot;

import com.ontravelsolutions.kostusev.townforevery.dto.TownDto;
import com.ontravelsolutions.kostusev.townforevery.dto.TownInformationDto;
import com.ontravelsolutions.kostusev.townforevery.service.TownInformationService;
import com.ontravelsolutions.kostusev.townforevery.service.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource(value = "classpath:messages.properties")
@Component
public class TownForEveryBot extends TelegramLongPollingBot {

    private final TownInformationService informationService;
    private final TownService townService;

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${bot.help.message}")
    private String helpMessage;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            switch (message.getText()) {
                case "/start":
                    break;
                case "/help":
                    sendMessage(message, helpMessage);
                    break;
                case "/towns":
                    sendMessage(message, getTowns(townService.findAll()));
                    break;
                default:
                    sendMessage(message, getTownInformation(message));
            }
        }
    }

    private void sendMessage(Message message, String text) {
        SendMessage response = new SendMessage();
        Long chatId = message.getChatId();
        response.setChatId(chatId);
        response.setText(text);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getTowns(List<TownDto> towns) {
        return towns.stream()
                .map(TownDto::getName)
                .collect(joining(", "));
    }

    private String getTownInformation(Message message) {
        List<TownInformationDto> townInformation = informationService.findAllByTown(message.getText());
        TownInformationDto information = informationService.findRandomInformationByTown(townInformation);
        return information.getDescription();
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
