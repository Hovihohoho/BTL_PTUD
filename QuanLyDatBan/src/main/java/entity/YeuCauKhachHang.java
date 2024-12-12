package entity;

public class YeuCauKhachHang {
    private String maYeuCau;
    private KhachHang kh;

    public YeuCauKhachHang(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public YeuCauKhachHang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public YeuCauKhachHang(KhachHang khachHang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaYeuCau() {
        return maYeuCau;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public YeuCauKhachHang(String maYeuCau, KhachHang kh) {
        this.maYeuCau = maYeuCau;
        this.kh = kh;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
