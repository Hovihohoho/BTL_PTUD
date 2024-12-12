/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Thanh Phuong
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String sDT;
    private String diaChi;
    private String gioiTinh;
    private HoaDon thoiGianTao;
    private HoaDon ngayDatBan;
    private HoaDon soLuongKhach;
    private ChiTietYeuCau ghiChu;

    // Constructors
    public KhachHang() {}

    public KhachHang(String maKH, String tenKH, String sDT, String diaChi, String gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public KhachHang(String string, String string0, int aInt, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public KhachHang(String tenKH, String sDT, int soLuongKhach, Timestamp thoiGianTao, Date ngayDatBan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public HoaDon getNgayDatBan() {
        return ngayDatBan;
    }

    public HoaDon getThoiGianTao() {
        return thoiGianTao;
    }

    public HoaDon getSoLuongKhach() {
        return soLuongKhach;
    }

    public ChiTietYeuCau getGhiChu() {
        return ghiChu;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", sDT='" + sDT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                '}';
    }
}