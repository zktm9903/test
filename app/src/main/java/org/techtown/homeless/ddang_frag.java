package org.techtown.homeless;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
//하하호호
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ddang_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ddang_frag extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button ddang_btn1;
    TextView ddang_text1;
    int ddang_medo_cost = 10000;
    MainActivity mainActivity;

    public ddang_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ddang_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static ddang_frag newInstance(String param1, String param2) {
        ddang_frag fragment = new ddang_frag();
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

        View view = inflater.inflate(R.layout.fragment_ddang_frag, container, false);

        ddang_btn1 = (Button) view.findViewById(R.id.ddang_btn1);
        ddang_text1 = (TextView) view.findViewById(R.id.ddang_text1);

        ddang_btn1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ddang_btn1 :
                 mainActivity = (MainActivity)getActivity();

                if(mainActivity.money.have_ddang(1) == false){
                    if(mainActivity.money.get_now_money() >= ddang_medo_cost){
                        mainActivity.money.spend_money(ddang_medo_cost);
                        mainActivity.money.ddang_mesu(1);
                        ddang_btn1.setText("판매: "+mainActivity.money.get_ddang_money(1)+" WON");

                        mainActivity.update_now_money();
                    }
                }



                break;
        }
    }
    public void changetxtbtn(){
        ddang_btn1.setText("판매: "+ mainActivity.money.get_ddang_money(1)+" WON");
    }
}