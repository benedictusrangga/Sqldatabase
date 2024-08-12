package com.example.sqldatabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

public class ReadFragment extends Fragment {
    private Button buttonShowData;
    private TextView showData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        buttonShowData = view.findViewById(R.id.buttonShowData);
        showData = view.findViewById(R.id.dataShow);

        buttonShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fetchDataAndDisplay();
            }
        });

        return view;
    }

    private void fetchDataAndDisplay() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url4 = "http://192.168.56.1/android_studio/read.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url4,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            displayData(jsonArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    private void displayData(JSONArray jsonArray) {
        showData.setText("");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String data = jsonObject.getString("data");
                showData.append("ID = " + id + " | Value = " + data + "\n");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
