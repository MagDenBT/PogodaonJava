import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Updater {
    private Logic logic;
    private ObservableList<Weather> ol;
    private EventLogic eventListener;

    Updater(String URL, EventLogic eventListener) {
        this.eventListener = eventListener;
        this.ol = ol;
        logic = new Logic(URL);
    }

    public boolean updateWeather() {
        ArrayList<Weather> al = null;
        try {
            al = getWeatherPackList(logic.getData());
        } catch (IOException e) {
            createEvent(eventListener, "Не удалось обновить прогноз");
            return false;
        }
        ol = FXCollections.observableArrayList(al);
        return true;
    }

    private ArrayList<Weather> getWeatherPackList(HashMap<String, ArrayList<ArrayList<String>>> hm) {
        ArrayList<Weather> result = new ArrayList<Weather>();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> me : hm.entrySet()) {
            for (ArrayList<String> al : me.getValue()) {
                result.add(new Weather(me.getKey(), al.get(0), al.get(1), al.get(2), al.get(3),
                        al.get(4), al.get(5)));
            }
        }


        return result;
    }

    public String getURL() {
        return logic.getURL();
    }

    private <T extends EventLogic> void createEvent(T listener, String status) {
        listener.statusResponce(status);
    }

    public ObservableList<Weather> getObservableList() throws IOException {
        updateWeather();
        return ol;
    }
}
