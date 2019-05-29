package cn.charlesxu.LabManager.entity.form;

import java.util.List;
import java.util.Map;

public class OrderForm1 {
    private String username;
    private int no;
    private Map<String, List<String>> data;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }
}
