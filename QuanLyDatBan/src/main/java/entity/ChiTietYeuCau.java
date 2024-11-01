/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class ChiTietYeuCau {
    private YeuCauKhachHang yeuCauKhachHang;
    private MonAn monAn;
    private int soLuong;
    private String ghiChu;

    public YeuCauKhachHang getYeuCauKhachHang() {
        return yeuCauKhachHang;
    }

    public void setYeuCauKhachHang(YeuCauKhachHang yeuCauKhachHang) {
        this.yeuCauKhachHang = yeuCauKhachHang;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ChiTietYeuCau(YeuCauKhachHang yeuCauKhachHang, MonAn monAn, int soLuong, String ghiChu) {
        this.yeuCauKhachHang = yeuCauKhachHang;
        this.monAn = monAn;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
