import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.commands.GetMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeAllChatAdministrators;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import commands.StartCommand;


public class WellNusBot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "WellNusBot";
    }

    public String getBotToken() {
        return "5216899607:AAFre4O23jNq4HrAohG0VHR3bzt2x6CMgho";
    }

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
//        List<> commandsList = new ArrayList();
//        commandsList.add(new StartCommand());
//        BotCommand[] commands = new BotCommand[]{
//                new StartCommand(),
//        };
//        commandsList.add(new StartCommand());
//        JSONObject obj = new JSONObject();
//        SetMyCommands cmds = new SetMyCommands(commands);
//        cmds.setLanguageCode("en");
//        cmds.setScope(new BotCommandScopeAllChatAdministrators());
//        try {
//            execute(cmds);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

        if(command.equals("/start")) {
            System.out.println("Running start");
            introduction(update);
        } else if (command.equals("/exit")) {
            System.out.println("Exit");
            exit(update);
        } else if (command.equals(("/shame"))) {
            System.out.println("sense of shame");
            senseOfShame(update);
        } else if (command.equals(("/intelligence"))) {
            System.out.println("/intelligence");
            intelligence(update);
        } else if (command.equals(("/self_esteem"))) {
            System.out.println("/self_esteem");
            selfEsteem(update);
        } else if (command.equals(("/minority"))) {
            System.out.println("/minority");
            minorityGroups(update);
        } else {
            introduction(update);
        }


    }

    private void minorityGroups(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String[] minorityMessages = {
                "If you feel that you are struggling to find a sense of belonging, remember that you are not alone. Adult learners come from various backgrounds and many find themselves struggling to feel like they belong. But one of the advantages of learning is the opportunity to interact with other students and grow from a sharing of experiences and perspectives. It helps to know that you are not alone in your learning journey and interacting with others in your diverse cohort will truly help you feel like you belong.",
                "Research suggests that learning with others facilitates academic learning, increased feelings of belonging, and broadened perspectives. Learning is greatly accelerated in a social setting and through a sharing of knowledge. Your unique experiences, perspectives and knowledge allows learning to be interesting and more fruitful.",
                "Networking is a great way to nurture a sense of belonging. Mingling with others in your cohort might seem intimidating, but it is a great way to assure yourself that you are not alone, develop personally, and learn soft skills.",
                "One of the major advantages of adult learning is that everyone has their fair share of experiences in a cohort composed of people of different ages, beliefs, and socio-economic background. No two persons are the same. In fact, differences allow for unique and varied perspectives which make learning exciting.",
                "If you do not feel like you belong due to your background, it is important for you to know that this is a common problem which many learners face. A sense of worry arising due to feeling that your social and economic background would make fitting in difficult for you is natural. Interacting with people of similar backgrounds as well as different backgrounds will allow you the opportunity to realize that the worry can be addressed and a sense of belonging can be fostered through an open communication and an understanding that this worry is common, but improvable."
        };
        int rnd = new Random().nextInt(minorityMessages.length);
        response.setText(minorityMessages[rnd]);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        followUp(update);
    }

    private void selfEsteem(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String[] selfEsteemMessages = {
                "The number of adult learners is swiftly growing in Singapore. Did you know that research suggests that you as an adult learner have several advantageous traits when it comes to learning? Not only do you bring your professional experience and unique, mature perspective to learning, but you are also tremendously motivated. You have made a choice to learn and this shows your perseverance and desire to grow personally and professionally.",
                "Many adult learners go through similar emotions when they are trying to develop themselves personally and professionally. In recent years, there is a recognition of the fact that education is not exclusively for the under 24. There has been a growing emphasis on lifelong learning and employers are looking for adults like yourself who are constantly upskilling or reskilling. By gaining up-to-date and relevant skills, you are making yourself highly employable and broadening your job prospects.",
                "Stepping into adult education allows you a chance to learn new skills and explore new opportunities. So, by stepping out of your comfort zone, you are allowing yourself the opportunity to equip yourself with the skills required to progress in your career.",
                "Every year, there is a need to learn new skills as old ones become less relevant. Choosing to learn ensures that you keep up to date with the constantly evolving technological world, making you more employable and allowing you to succeed in your career.",
                "It might feel like you should have it all figured out, and stepping into adult education can feel like a step backwards. But this is not true. Change is daunting, but it is important to understand that education is a lifelong necessity, and choosing to learn despite initial fears is definitely rewarding in the long run."
        };
        int rnd = new Random().nextInt(selfEsteemMessages.length);
        response.setText(selfEsteemMessages[rnd]);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        followUp(update);
    }

    private void intelligence(Update update) {
        String[] intelligenceMessages = {

        };
        //TODO
    }

    private void senseOfShame(Update update) {
        String[] senseOfShameMessages = {

        };
        //TODO
    }

    private void exit(Update update) {
        //TODO
    }

    private void followUp(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String message = "Continue learning about the experiences of adult learners...\n" +
                "Here are some of the common challenges faced and the perspectives of the learners!\n" +
                "To learn about the experience overcoming sense of shame /shame \n" +
                "To learn about the experience overcoming stereotypes about intelligence /intelligence \n" +
                "To learn about the experience overcoming stereotypes about minority groups /minority \n" +
                "To learn about the experience overcoming low self-esteem /self_esteem";


        response.setText(message);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void introduction(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String message = "Welcome to wellNUSbot.\n" +
                    "This bot is here for you to learn more about the various challenges faced by adult learners and how they overcame them.\n" +
                    "Here are some of the common challenges faced and the perspectives of the learners!\n" +
                    "To learn about the experience overcoming sense of shame /shame \n" +
                    "To learn about the experience overcoming stereotypes about intelligence /intelligence \n" +
                    "To learn about the experience overcoming stereotypes about minority groups /minority \n" +
                    "To learn about the experience overcoming low self-esteem /self_esteem";


        response.setText(message);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}