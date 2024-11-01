package entity;

public class YeuCauKhachHang {
    private String maYeuCau;
    private KhachHang kh;

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
