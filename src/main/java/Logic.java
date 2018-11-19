import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Logic {
    private int startIndex = 0;
    private String firstFiltr = "\\d{2}.\\d{2}.*";



    private String URL = "http://pogoda.spb.ru/";
    private int waitTime = 3000;

    Logic() {
    }

    Logic(String URL) {
        this.URL = URL;
    }

    Logic(int waitTime) {
        this.waitTime = waitTime;
    }

    Logic(String URL, int waitTime) {
        this.waitTime = waitTime;
        this.URL = URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public String getURL() {
        return URL;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public HashMap<String, ArrayList<ArrayList<String>>> getData() throws IOException {
        Element table = getPage().select("table[class=wt]").first();
        Elements allEl = table.getElementsByTag("tr");

        return getStructuredData(allEl);
    }


    private Document getPage() throws IOException {
        Document page = Jsoup.parse(new URL(URL), waitTime);
        return page;
    }

    private HashMap<String, ArrayList<ArrayList<String>>> getStructuredData(Elements data) {
        HashMap<String, ArrayList<ArrayList<String>>> hm = new HashMap();

        for (; startIndex < data.size(); startIndex++) {
            Element buffer = data.get(startIndex);
            String forCompare = buffer.text();
            if (forCompare.matches(firstFiltr)) {
                String[] claeanKey = forCompare.split(" ", 3);
                hm.put(claeanKey[0] + " " + claeanKey[1], getData(data));
            }
        }
        startIndex = 0;
        return hm;
    }

    private ArrayList<ArrayList<String>> getData(Elements data) {
        ArrayList<ArrayList<String>> result = new ArrayList();
        startIndex++;
        for (; startIndex < data.size(); startIndex++) {
            Element buffer = data.get(startIndex);
            String forCompare = buffer.text();


            if (forCompare.length() != 0 && !forCompare.matches(firstFiltr)) {
                ArrayList<String> value = new ArrayList<String>();
                Elements elementsValue = buffer.getElementsByTag("td");
                for (Element el : elementsValue) {
                    value.add(el.text());
                }
                result.add(value);
            } else if (forCompare.matches(firstFiltr)) {
                startIndex--;
                break;
            }

        }


        return result;
    }


}




