package market.marketapp.Activities.Requester;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import market.marketapp.R;

/**
 * Created by FRANCI on 10/22/2017.
 */

public class GenericHttpRequester {

  public static int statusCode;

  public static JSONObject makeHttpGetRequest(String urlString, Activity appContext)
    throws IOException, JSONException {
    HttpURLConnection urlConnection = null;

    URL url = new URL(urlString);

    urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestMethod("GET");
    urlConnection.setRequestProperty("Content-length", "0");

    urlConnection.setReadTimeout(10000 /* milliseconds */);
    urlConnection.setConnectTimeout(15000 /* milliseconds */);

    urlConnection.connect();
    statusCode = urlConnection.getResponseCode();

    InputStream errorStream = urlConnection.getErrorStream();
    BufferedReader br = null;

    if (errorStream == null) {
      InputStream inputstream = urlConnection.getInputStream();
      br = new BufferedReader(new InputStreamReader(inputstream));
    } else {
      br = new BufferedReader(new InputStreamReader(errorStream));
    }

    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null) {
      sb.append(line).append("\n");
    }
    br.close();
    JSONObject object = new JSONObject(sb.toString());
    urlConnection.disconnect();

    return object;
  }

  public static JSONObject makeHttpPostRequest(String urlString, Activity appContext,
                                               HashMap<String, String> postValues)
    throws IOException, JSONException {
    HttpURLConnection urlConnection = null;
    JSONObject object;

    URL url = new URL(urlString);

    urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestMethod("POST");
    urlConnection.setDoInput(true);
    urlConnection.setDoOutput(true);
    urlConnection.setReadTimeout(10000 /* milliseconds */);
    urlConnection.setConnectTimeout(15000 /* milliseconds */);

    //Send request
    OutputStream os = urlConnection.getOutputStream();
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
    writer.write(getQuery(postValues));
    writer.flush();
    writer.close();
    os.close();

    //Get Response
    urlConnection.connect();
    statusCode = urlConnection.getResponseCode();

    InputStream errorStream = urlConnection.getErrorStream();
    BufferedReader br = null;

    if (errorStream == null) {
      InputStream inputstream = urlConnection.getInputStream();
      br = new BufferedReader(new InputStreamReader(inputstream));
    } else {
      br = new BufferedReader(new InputStreamReader(errorStream));
    }

    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null) {
      sb.append(line).append("\n");
    }
    br.close();
    object = new JSONObject(sb.toString());
    urlConnection.disconnect();

    return object;
  }

  private static String getQuery(HashMap<String, String> params) throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();
    boolean first = true;
    for (Map.Entry<String, String> entry : params.entrySet()) {
      if (first) {
        first = false;
      } else {
        result.append("&");
      }

      result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
      result.append("=");
      result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
    }

    return result.toString();
  }

}
