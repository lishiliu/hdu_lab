package cn.charlesxu.LabManager.entity.define;

/**
 * Created by
 * User: Charles Xu
 * Date: 1/2/2018
 * Time: 20:09
 */
public final class OrderDefine {
    public static final int unArranged = 0;
    public static final String unArrangedString = "未安排";

    public static final int ArrangedFirstOrder = 1;
    public static final String ArrangedFirstOrderString = "已安排第一志愿";
    public static final int ArrangedSecondOrder = 2;
    public static final String ArrangedSecondOrderString = "已安排第二志愿";
    public static final int ArrangedThirdOrder = 3;
    public static final String ArrangedThirdOrderString = "已安排第三志愿";

    public static final int failArranged = -1;
    public static final String failArrangedString = "安排失败（冲突）";
}
