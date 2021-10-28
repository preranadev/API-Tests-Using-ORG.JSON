import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataSet {
    private String strData;
    private JSONArray jsoData;
    //private List<List<Object>> keyList;
    //private List<Object> dataList;
    public DataSet(String uriX) throws IOException, InterruptedException {
        //Fetching data using API and storing/printing it as a String.
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uriX)).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        strData = response.body();
        System.out.println(strData);
        //Storing data from the String to a JSONArray.
        jsoData = new JSONArray(strData);
        Iterator<Object> it = jsoData.iterator();
        while(it.hasNext()) {
            JSONObject jo = (JSONObject) it.next();
            Iterator<String> keys = jo.keys();
            int num = 0;
            while(keys.hasNext()) {
                String key = keys.next();
				/*if(keyList.contains(key) == false) {
					keyList.add(key);
				}*/
                String value = jo.get(key).toString();
                System.out.println(key + "=" + value + "          " + num);
                num++;
            }
            System.out.println("-------------------");
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        DataSet temp = new DataSet("https://api.covidtracking.com/v1/states/current.json");
    }
}
