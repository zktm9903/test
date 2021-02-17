package org.techtown.homeless;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView won, click1, sec1;
    LinearLayout layout1;
    Button beggar_power_btn , alba_btn, ddang_btn;
    int flag = 0;
    Money money;

    int alba_pic[] = new int[5];
    ImageView alba[] = new ImageView[5];
    FrameLayout frameLayout;

    private FragmentManager fragmentManager;
    private power_frag fragment1;
    private alba_frag fragment2;
    private power_frag fragment3;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //상태바 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        money = new Money();

        //프래그먼트 매니저
        fragmentManager = getSupportFragmentManager();

        view_find();

        //알바 쓰레드
        class alba_thread implements Runnable{

            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(10000) ;
                    } catch (Exception e) {
                        e.printStackTrace() ;
                    }
                    money.earn_alba_money();
                    update_now_money();
                    //update_now_sec_money();
                    //update_now_click_money();
                }
            }
        }

        alba_thread my_alba_thread = new alba_thread();
        Thread t = new Thread(my_alba_thread);
        t.start();

        ddang_frag mainFragment = (ddang_frag)getSupportFragmentManager().findFragmentById(R.id.frameLayout);


        //부동산 쓰레드
        class ddang_thread implements Runnable{

            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(10000) ;
                    } catch (Exception e) {
                        e.printStackTrace() ;
                    }
                    money.ddang_high();
                    update_now_money();
                    if(money.have_ddang(1) == false)
                        mainFragment.ddang_btn1.setText("판매: "+money.get_ddang_money(1)+" WON");
                }
            }
        }

        alba_thread my_ddang_thread = new alba_thread();
        Thread d = new Thread(my_ddang_thread);
        d.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout1 :
                money.now_money += money.add_money;
                won.setText(money.now_money + " WON");
                break;
            case R.id.beggar_power_btn :
                if(flag != 1){
                    flag = 1;
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, new power_frag()).commit();
//                    transaction.replace(R.id.frameLayout, fragment1).commitAllowingStateLoss();
                }
                break;
            case R.id.alba_btn :
                if(flag != 2){
                    flag = 2;
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, new alba_frag()).commit();
//                    transaction.replace(R.id.frameLayout, fragment2).commitAllowingStateLoss();
                }
                break;
            case R.id.ddang_btn :
                if(flag != 3){
                    flag = 3;
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, new ddang_frag()).commit();
//                    transaction.replace(R.id.frameLayout, fragment2).commitAllowingStateLoss();
                }
                break;
        }
    }

    public void view_find(){
        won = (TextView) findViewById(R.id.won);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        beggar_power_btn = (Button) findViewById(R.id.beggar_power_btn);
        alba_btn = (Button) findViewById(R.id.alba_btn);
        ddang_btn = (Button) findViewById(R.id.ddang_btn);
        alba[1] = (ImageView) findViewById(R.id.artist);
        alba_pic[1] = R.id.artist;
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);

        layout1.setOnClickListener(this);
        beggar_power_btn.setOnClickListener(this);
        alba_btn.setOnClickListener(this);
        ddang_btn.setOnClickListener(this);

        alba_pic[1] = R.drawable.artist;
    }

    public void update_now_money(){
        won.setText(money.get_now_money() + " WON");
    }

    public void update_now_alba(int idx){
        alba[idx].setImageResource(alba_pic[idx]);
    }

    public void update_now_sec_money(){sec1.setText(money.get_now_sec_money()+" WON/SEC");}

    public void update_now_click_money(){click1.setText(money.get_now_click_money()+" WON/CLICK");}

}