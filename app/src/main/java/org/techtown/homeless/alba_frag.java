package org.techtown.homeless;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link alba_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class alba_frag extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView alba_text1;
    Button alba_btn1;
    int employ_alba_cost = 100;

    public alba_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment alba_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static alba_frag newInstance(String param1, String param2) {
        alba_frag fragment = new alba_frag();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alba_frag, container, false);
        // Inflate the layout for this fragment

        alba_text1 = (TextView) view.findViewById(R.id.alba_text1);
        alba_btn1 = (Button) view.findViewById(R.id.alba_btn1);

        alba_btn1.setOnClickListener(this);
        MainActivity mainActivity = (MainActivity)getActivity();
        alba_text1.setText("아티스트 Lv."+mainActivity.money.get_alba_level(1));
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.alba_btn1 :
                MainActivity mainActivity = (MainActivity)getActivity();

                if(mainActivity.money.get_now_money() >= employ_alba_cost){
                    if(mainActivity.money.chk_alba(1) == 0){
                        mainActivity.money.employ_alba(1);
                        mainActivity.update_now_alba(1);
                        alba_btn1.setText("레벨 업");
                    }
                    else{
                        mainActivity.money.employ_alba(1);
                    }
                    alba_text1.setText("아티스트 Lv."+mainActivity.money.get_alba_level(1));

                    mainActivity.money.spend_money(employ_alba_cost);
                    mainActivity.update_now_money();
                    employ_alba_cost += 100;
                }

                mainActivity.update_now_money();

                break;
            case R.id.beggar_power_btn :
                break;
            case R.id.alba_btn :
                break;
        }
    }
}