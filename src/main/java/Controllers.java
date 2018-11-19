import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Controllers implements EventLogic {
    private Button update;
    private Label status;
    private Updater updater;
    private EventHandler<ActionEvent> eh;

    Controllers(){
        update = new Button("Обновить прогноз");
        status = new Label();
        initEvent();
        setEvent();
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }
    private void initEvent() {

            eh = (event)  ->{
                if(updater.updateWeather()) {
                    status.setText("");
                    status.setText("Прогноз обновлен");
                }
            };

    }

    private void updateStatus(String text) {
        status.setText(text);
    }

    private void setEvent() {
        update.setOnAction(eh);
    }

    public void statusResponce(String responce) {
        updateStatus(responce);
    }

    public Node[] getControllers(){
        Node nodes[] = {update, status};
        return nodes;
    }
}
