package obj;

import javafx.scene.layout.VBox;

import java.sql.Date;

public class SingleInfo extends VBox {
    private int cid;
    private int pid;
    private int rid;
    private String createtime;
    private String timeliness;
    private String companyName;

    public SingleInfo(int cid, int pid, int rid, String createtime, String timeliness, String companyName) {
        this.cid = cid;
        this.pid = pid;
        this.rid = rid;
        this.createtime = createtime;
        this.timeliness = timeliness;
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTimeliness() {
        return timeliness;
    }

    public void setTimeliness(String timeliness) {
        this.timeliness = timeliness;
    }

    @Override
    public String toString() {
        return "SingleInfo{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", rid=" + rid +
                ", createtime='" + createtime + '\'' +
                ", timeliness='" + timeliness + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}

