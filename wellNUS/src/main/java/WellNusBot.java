import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;

import com.mongodb.client.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.Document;
import org.json.JSONObject;
import static java.lang.Math.toIntExact;


public class WellNusBot extends TelegramLongPollingBot {
    StateEnum currentState = null;



    public String printIntelligence() {
        MongoClient mongo = new MongoClient(new MongoClientURI("mongodb+srv://WELLNUS:wellnus123@cluster0.jvu6f.mongodb.net/test"));
        MongoDatabase database = mongo.getDatabase("bot");
        MongoCollection<Document> collection = database.getCollection("intelligence");
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        String exp="";
        while (it.hasNext()) {
            exp=exp+it.next().toString().replaceAll("[{}]", "").substring(23)+"\n";
        }
        return exp;
    }

    public String printShame() {
        MongoClient mongo = new MongoClient(new MongoClientURI("mongodb+srv://WELLNUS:wellnus123@cluster0.jvu6f.mongodb.net/test"));
        MongoDatabase database = mongo.getDatabase("bot");
        MongoCollection<Document> collection = database.getCollection("shame");
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        String exp="";
        while (it.hasNext()) {
            exp=exp+it.next().toString().replaceAll("[{}]", "").substring(23)+"\n";
        }
        return exp;
    }

    public String printMinority() {
        MongoClient mongo = new MongoClient(new MongoClientURI("mongodb+srv://WELLNUS:wellnus123@cluster0.jvu6f.mongodb.net/test"));
        MongoDatabase database = mongo.getDatabase("bot");
        MongoCollection<Document> collection = database.getCollection("minority");
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        String exp="";
        while (it.hasNext()) {
            exp=exp+it.next().toString().replaceAll("[{}]", "").substring(23)+"\n";
        }
        return exp;
    }

    public String printSelfEsteem() {
        MongoClient mongo = new MongoClient(new MongoClientURI("mongodb+srv://WELLNUS:wellnus123@cluster0.jvu6f.mongodb.net/test"));
        MongoDatabase database = mongo.getDatabase("bot");
        MongoCollection<Document> collection = database.getCollection("esteem");
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        String exp="";
        while (it.hasNext()) {
            exp=exp+it.next().toString().replaceAll("[{}]", "").substring(23)+"\n";
        }
        return exp;
    }




    public String getBotUsername() {
        return "WellNusBot";
    }

    public String getBotToken() {
        return "5216899607:AAFre4O23jNq4HrAohG0VHR3bzt2x6CMgho";
    }

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();

        if(command.equals("/start") ||command.equals("/shame") ||command.equals("/intelligence") ||
                command.equals("/self_esteem") ||command.equals("/minority")) {
            currentState = null;
        }


        if(command.equals("/start") || currentState == StateEnum.INTRO) {
            System.out.println("Running start");
            introduction(update);
        } else if (command.equals("/register") || currentState == StateEnum.CREATEACCOUNT) {
            System.out.println("Create account");
            createAccount(update);
        } else if (command.equals(("/shame"))) {
            System.out.println("/shame");
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
        String minorityMessages = printMinority();

        response.setText(minorityMessages);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void selfEsteem(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String esteemMessages = printSelfEsteem();

        response.setText(esteemMessages);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void intelligence(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String intelligenceMessages = printIntelligence();

        response.setText(intelligenceMessages);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void senseOfShame(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String senseOfShameMessages = printShame();
        response.setText(senseOfShameMessages);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void createAccount(Update update) {
        //TODO
    }

    private void introduction(Update update) {
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        String message = null;
        if(currentState != StateEnum.INTRO) {
            currentState = StateEnum.INTRO;

            message = "Welcome to wellNUSbot.\n" +
                    "This bot is here for you to learn more about the various challenges faced by adult learners and how they overcame them.\n" +
                    "Here are some of the common challenges faced and the perspectives of the learners!\n" +
                    "To learn about the experience overcoming sense of shame /shame \n" +
                    "To learn about the experience overcoming stereotypes about intelligence /intelligence \n" +
                    "To learn about the experience overcoming stereotypes about minority groups /minority \n" +
                    "To learn about the experience overcoming low self-esteem /self_esteem";

        } else {
            message = "Learn more about the experiences of adult learners by executing any of the following commands! :)\n\n" +
                    "To learn about the experience overcoming sense of shame /shame \n" +
                    "To learn about the experience overcoming stereotypes about intelligence /intelligence \n" +
                    "To learn about the experience overcoming stereotypes about minority groups /minority \n" +
                    "To learn about the experience overcoming low self-esteem /self_esteem";

        }
        response.setText(message);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}