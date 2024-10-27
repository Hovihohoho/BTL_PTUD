/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String maNV, tenNV, sDT, email;
    private LocalDate ngayVaoLam;

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getsDT() {
        return sDT;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public NhanVien(String maNV, String tenNV, String sDT, String email) {
    	this.maNV = maNV;
        this.tenNV = tenNV;
        this.sDT = sDT;
        this.email = email;
    }

    public NhanVien() {
        this.maNV = "TM0000";
        this.tenNV = "Khong Co Ten";
        this.sDT = "0943211234";
        this.email = "khongcoemail@gmail.com";
        this.ngayVaoLam = LocalDate.now();
    }
    
}
