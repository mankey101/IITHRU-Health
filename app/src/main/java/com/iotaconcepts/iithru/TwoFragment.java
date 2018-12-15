package com.iotaconcepts.iithru;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TwoFragment extends Fragment
{
    // Required empty public constructor
    public TwoFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View myInflatedView = inflater.inflate(R.layout.fragment_two, container, false);
        Button b1=(Button)myInflatedView.findViewById(R.id.button);
        return myInflatedView;
    }





}