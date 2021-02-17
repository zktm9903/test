package org.techtown.homeless;

public class Money {
    public int now_money = 0;
    public int add_money = 0;
    public int alba_money = 0;
    public int level = 0;
    public int alba_arr[] = new int[3];
    public int ddang_arr[] = new int[5];

    public Money(){
        this.now_money = 0;
        this.add_money = 1;
        this.level = 0;
        this.alba_money = 0;

        for(int i=0;i<3;i++){
            alba_arr[i] = 0;
        }
    }
    public int get_now_sec_money(){return this.alba_money;}

    public int get_now_click_money(){return this.level;}

    public int get_now_money(){
        return this.now_money;
    }

    public int get_now_level(){
        return this.level;
    }

    public int get_add_money(){
        return this.add_money;
    }

    public int get_alba_money(){
        return this.alba_money;
    }

    public void level_up(){
        this.level += 1;
        this.add_money += 10;
    }

    public void spend_money(int spend){
        this.now_money -= spend;
    }

    public int chk_alba(int idx){
        return this.alba_arr[idx-1];
    }

    public void employ_alba(int idx){
        this.alba_arr[idx-1] += 1;
        this.alba_money += alba_arr[idx-1] * 100;
    }

    public int get_alba_level(int idx){
        return this.alba_arr[idx-1];
    }

    public void earn_alba_money(){
        this.now_money += alba_money;
    }

    public void ddang_mesu(int idx){
        ddang_arr[idx-1] += 10000;
    }

    public void ddang_high(){
        for(int i=0;i<5;i++){
            if(ddang_arr[i] != 0)
                ddang_arr[i] *= 2;
        }
    }

    public int get_ddang_money(int idx){
        return ddang_arr[idx-1];
    }

    public boolean have_ddang(int idx){
        if(ddang_arr[idx-1] != 0)
            return true;
        return false;
    }

}
