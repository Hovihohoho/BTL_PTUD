/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class LoaiBan {

 

   
    private String maLoai;
    private String tenLoai;
    
    public String getMaLoai() {
        return maLoai;
}

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai() {
        this.tenLoai = tenLoai;
    }
    
    public void setMaLoai(String maLoai) {
        this.maLoai=maLoai;
    }
    
    public LoaiBan(String maLoai) {
        if(maLoai.equalsIgnoreCase("")){
            this.maLoai = "L000";
        }
        else{
            this.maLoai = maLoai;
        }
      
    }

    public LoaiBan(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        setTenLoai();
    }
}
