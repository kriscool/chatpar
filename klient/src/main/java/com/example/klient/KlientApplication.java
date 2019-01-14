package com.example.klient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KlientApplication extends Application {

    static ConfigurableApplicationContext context;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(KlientApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}
