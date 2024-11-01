/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Ban {
    private String maBan, viTri;
    private int soGhe;
    private String trangThaiBan;
    private LoaiBan loaiBan;

    public String getMaBan() {
        return maBan;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getTrangThaiBan() {
        return trangThaiBan;
    }

    public void setTrangThaiBan(String trangThaiBan) {
        this.trangThaiBan = trangThaiBan;
    }

    public LoaiBan getLoaiBan() {
        return loaiBan;
    }

    public void setLoaiBan(LoaiBan loaiBan) {
        this.loaiBan = loaiBan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.maBan);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ban other = (Ban) obj;
        return Objects.equals(this.maBan, other.maBan);
    }
    
    
    
    public Ban(String maBan, LoaiBan loaiBan, String trangThaiBan, int soGhe, String viTri) {
        if(maBan.equalsIgnoreCase("")){
            this.maBan = "B000";
        }
        else{
            this.maBan = maBan;
        }
        this.viTri = viTri;
        this.soGhe = soGhe;
        this.trangThaiBan = trangThaiBan;
        this.loaiBan = loaiBan;
    }
    
    
    
    
    // contructer khong them vi tri
    public Ban(String maBan, int soGhe) {
        if(maBan.equalsIgnoreCase("")){
            this.maBan = "B000";
        }
        else{
            this.maBan = maBan;
        }
        this.viTri = "Chưa có vị trí";
        this.soGhe = soGhe;
    }
    
}
