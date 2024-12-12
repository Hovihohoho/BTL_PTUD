package entity;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    private String tenTK;
    private String matKhau, chucVu;
    private String maTK;

    public TaiKhoan(String tenTK, String matKhau) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
    }
    
    public TaiKhoan(String maTK) {
        this.maTK = maTK;
    }

    // Getters và setters
    public String gettenTK() {
        return tenTK;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }
    
    
    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getmatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    public String getMaTK() {
        return maTK;
    }

    public TaiKhoan(String maTK, String tenTK, String chucVu, String matKhau) {
        // can kiem tra dieu kien ma phai dung voi TKXXX
        if(maTK.equalsIgnoreCase("")){
            this.maTK = "TK000";
        }else{
            this.maTK = maTK;
}
        setTenTK(tenTK);
        setChucVu(chucVu);
        setMatKhau(matKhau);
    }
    
}
