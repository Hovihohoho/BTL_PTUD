package entity;

public class YeuCauKhachHang {
    private String maYeuCau;
    private KhachHang kh;
    private String noiDungYeuCau;

    public String getMaYeuCau() {
        return maYeuCau;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public String getNoiDungYeuCau() {
        return noiDungYeuCau;
    }

    public void setNoiDungYeuCau(String noiDungYeuCau) {
        this.noiDungYeuCau = noiDungYeuCau;
    }

    public YeuCauKhachHang(String maYeuCau, KhachHang kh, String noiDungYeuCau) {
        this.maYeuCau = maYeuCau;
        this.kh = kh;
        this.noiDungYeuCau = noiDungYeuCau;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
