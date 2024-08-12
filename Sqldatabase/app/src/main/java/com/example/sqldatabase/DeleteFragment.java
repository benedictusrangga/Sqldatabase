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

public class DeleteFragment extends Fragment {
    private EditText editTextIDDelete;
    private Button buttonDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        editTextIDDelete = view.findViewById(R.id.idDelete);
        buttonDelete = view.findViewById(R.id.submitDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDDelete = editTextIDDelete.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(requireContext());
                String url3 = "http://192.168.56.1/android_studio/delete.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url3,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")) {
                                    Toast.makeText(requireContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(requireContext(), "Data failed to be deleted", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("id", IDDelete);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        return view;
    }
}
