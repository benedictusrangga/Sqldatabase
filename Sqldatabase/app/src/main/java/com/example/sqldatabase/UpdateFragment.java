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

public class UpdateFragment extends Fragment {
    private EditText editTextIDUpdate, editTextValueUpdate;
    private Button buttonUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        editTextIDUpdate = view.findViewById(R.id.idUpdate);
        editTextValueUpdate = view.findViewById(R.id.valueUpdate);
        buttonUpdate = view.findViewById(R.id.submitUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDUpdate = editTextIDUpdate.getText().toString();
                String dataUpdate = editTextValueUpdate.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(requireContext());
                String url2 = "http://192.168.56.1/android_studio/update.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")) {
                                    Toast.makeText(requireContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(requireContext(), "Data failed to be updated", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", IDUpdate);
                        paramV.put("data", dataUpdate);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        return view;
    }
}
