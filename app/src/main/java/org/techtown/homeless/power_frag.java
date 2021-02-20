package org.techtown.homeless;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link power_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class power_frag extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView begger_level_text;
    Button levelup_btn;
    int levelup_cost = 100;
    private Context context;



    public power_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment power_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static power_frag newInstance(String param1, String param2) {
        power_frag fragment = new power_frag();
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

        View view = inflater.inflate(R.layout.fragment_power_frag, container, false);


        begger_level_text = (TextView) view.findViewById(R.id.begger_level_text);
        levelup_btn = (Button) view.findViewById(R.id.levelup_btn);
        context = container.getContext();

        levelup_btn.setOnClickListener(this);


        MainActivity mainActivity = (MainActivity)getActivity();

        //
        begger_level_text.setText("Lv. " + mainActivity.money.get_now_level());
        levelup_btn.setText("레벨 업\n"+levelup_cost+"WON");
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.levelup_btn :
                MainActivity mainActivity = (MainActivity)getActivity();
                if(mainActivity.money.get_now_money() >= levelup_cost){
                    mainActivity.money.spend_money(levelup_cost);
                    mainActivity.money.level_up();
                    begger_level_text.setText("Lv. " + mainActivity.money.get_now_level());
                    levelup_cost += 100;
                    levelup_btn.setText("레벨 업\n"+levelup_cost+"WON");
                    mainActivity.update_now_money();

                    //toast time 조정 0.7초
                    String text = "레벨업! (" + mainActivity.money.get_add_money() + " WON 씩 오릅니다.)";
                    final Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override public void run() {
                            toast.cancel();
                        }
                    }, 700);

                }
                else{
                    //toast time 조정 0.3초
                    String text = "돈("+ (levelup_cost - mainActivity.money.get_now_money()) +" WON)이 부족합니다.";
                    final Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override public void run() {
                            toast.cancel();
                        }
                        }, 300);


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