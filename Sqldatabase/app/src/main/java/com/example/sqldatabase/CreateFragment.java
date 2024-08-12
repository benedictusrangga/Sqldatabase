package com.example.sqldatabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class CreateFragment extends Fragment {
    private EditText editTextCreate;
    private Button buttonCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        editTextCreate = view.findViewById(R.id.valueCreate);
        buttonCreate = view.findViewById(R.id.submitCreate);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editTextCreate.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(requireContext());
                String url = "http://192.168.56.1/android_studio/create.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")) {
                                    Toast.makeText(requireContext(), "Data has been added", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(requireContext(), "Data failed to be added", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("data", data);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        return view;
    }
}
