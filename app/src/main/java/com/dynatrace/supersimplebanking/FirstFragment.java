package com.dynatrace.supersimplebanking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dynatrace.supersimplebanking.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.myNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView textView = (TextView) view.findViewById(R.id.textview_first);
   //...          final TextView textView2 = (TextView) view.findViewById(R.id.textview_first);

// Instantiate the RequestQueue.

                /*// Instantiate the cache
                Cache cache = new DiskBasedCache(getContext().getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());*/

                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url ="http://ec2-3-236-120-161.compute-1.amazonaws.com:81/new_pix";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast myToast = Toast.makeText(getActivity(), "Response is: "+ response.substring(0,100), Toast.LENGTH_LONG);
                                myToast.show();

                                //textView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");

                        Toast myToastError = Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT);
                        myToastError.show();

                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}