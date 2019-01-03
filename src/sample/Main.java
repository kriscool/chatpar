package sample;

import com.caucho.hessian.client.HessianProxyFactory;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.application.Platform;
import sample.messege.Message;
import sample.messege.MessageService;
import sample.thread.message.ReadMessage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Controller controller = fxmlLoader.getController();
                        try {
                            controller.End();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Application Closed by click to Close Button(X)");
                        System.exit(0);

                    }
                });
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }


}