/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class LoaiMon {
    private String maLoai;
    private String tenLoai;

    public String getMaLoai() {
        return maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        if(tenLoai.equalsIgnoreCase("")){
            this.tenLoai = "Chưa phân loại";
        }
        else{
            this.tenLoai = tenLoai;
        }
    }

    public LoaiMon(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }
    
    @Override
    public String toString() {
        return tenLoai; // Hiển thị tên loại món trong ComboBox
    }
}
