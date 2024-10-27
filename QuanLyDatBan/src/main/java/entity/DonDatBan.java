/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class DonDatBan {
    private String maHD;
    private int soLuongKhach;

    public String getMaHD() {
        return maHD;
    }

    public int getSoLuongKhach() {
        return soLuongKhach;
    }

    public void setSoLuongKhach(int soLuongKhach) {
        if(soLuongKhach < 1){
            this.soLuongKhach = 1;
        }
        else{
            this.soLuongKhach = soLuongKhach;
        }
    }

    public DonDatBan(String maHD, int soLuongKhach) {
        this.maHD = maHD;
        setSoLuongKhach(soLuongKhach);
    }
    
}
