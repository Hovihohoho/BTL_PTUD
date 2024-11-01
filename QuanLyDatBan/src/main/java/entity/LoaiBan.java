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

    public void setTenLoai(String maLoai) {
        if(tenLoai.equalsIgnoreCase("")){
            this.tenLoai = "Chưa phân loại";
        }
        else{
            this.tenLoai = tenLoai;
        }
    }
    
    public LoaiBan(String maLoai) {
        if(maLoai.equalsIgnoreCase("")){
            this.maLoai = "L000";
        }
        else{
            this.maLoai = maLoai;
        }
        tenLoai = "Chưa phân loại";
    }

    public LoaiBan(String maLoai, String tenLoai) {
        if(maLoai.equalsIgnoreCase("")){
            this.maLoai = "L000";
        }
        else{
            this.maLoai = maLoai;
        }
        setTenLoai(tenLoai);
    }
}
