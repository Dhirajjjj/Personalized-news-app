package com.example.thyme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link personal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class personal extends Fragment implements AdapterPer.OnNoteListener {


    View view;
    RecyclerView recyclerview;
    List<ModalPer> mList;
    AdapterPer adapterPer;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public personal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment personal.
     */
    // TODO: Rename and change types and number of parameters
    public static personal newInstance(String param1, String param2) {
        personal fragment = new personal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

            String topic = "Covid-19";

            mList = new ArrayList<>();
            mList.add(new ModalPer(R.drawable.black_background, "Recommended"));
            mList.add(new ModalPer(R.drawable.black_background, "Topic: "+topic));
            mList.add(new ModalPer(R.drawable.business, "Business"));
            mList.add(new ModalPer(R.drawable.science, "Science"));
            mList.add(new ModalPer(R.drawable.sports, "Sports"));
            mList.add(new ModalPer(R.drawable.health, "Health"));
            mList.add(new ModalPer(R.drawable.technology1, "Technology"));
            mList.add(new ModalPer(R.drawable.entertainment, "Entertainment"));



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerview = view.findViewById(R.id.recyclerViewPer);


        adapterPer = new AdapterPer(mList, getContext(),this);
        recyclerview.setAdapter(adapterPer);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;

    }

    @Override
    public void OnNoteClick(int position) {

        switch (position) {

            case 0:
                Intent intent = new Intent(getContext(), RecommendedDet.class);
                startActivity(intent);
                break;

            case 1:
                Intent intent1 = new Intent(getContext(), SpecificTopicDet.class);
                startActivity(intent1);
                break;

            case 2:
                Intent intent2 = new Intent(getContext(), businessDet.class);
                startActivity(intent2);
                break;

            case 3:
                Intent intent3 = new Intent(getContext(), scienceDet.class);
                startActivity(intent3);
                break;

            case 4:
                Intent intent4 = new Intent(getContext(), sportsDet.class);
                startActivity(intent4);
                break;

            case 5:
                Intent intent5 = new Intent(getContext(), healthDet.class);
                startActivity(intent5);
                break;

            case 6:
                Intent intent6 = new Intent(getContext(), technologyDet.class);
                startActivity(intent6);
                break;

            case 7:
                Intent intent7 = new Intent(getContext(), entertainmentDet.class);
                startActivity(intent7);
                break;

        }
    }
}