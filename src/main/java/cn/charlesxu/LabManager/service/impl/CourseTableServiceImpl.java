package cn.charlesxu.LabManager.service.impl;

import cn.charlesxu.LabManager.dao.SystemParameterDao;
import cn.charlesxu.LabManager.entity.*;
import cn.charlesxu.LabManager.entity.form.CourseTableCell;
import cn.charlesxu.LabManager.entity.form.EmptyCellObject;
import cn.charlesxu.LabManager.entity.form.OccupyObject;
import cn.charlesxu.LabManager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by
 * User: Charles Xu
 * Date: 1/2/2018
 * Time: 21:18
 */
@Service
public class CourseTableServiceImpl implements CourseTableService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private LabService labService;

    @Autowired
    private SystemParameterDao systemParameterDao;

    @Autowired
    private SemesterService semesterService;

    public CourseTable getCourseTable(String Username, int week) {
        //读取系统参数中的"当前学期"
        SystemParameter systemParameter = systemParameterDao.select();
        Semester semester = semesterService.getSemesterById(systemParameter.getThisSemesterId());
        String semesterString = semester.getSemesterString();

        List<Order> orderList = orderService.getOrderByUsernameAndSemester(Username, semesterString);
        orderList = orderService.getFinishedOrderList(orderList);
        CourseTable courseTable = OrderListToCourseTable(orderList);
        return courseTable;
    }

    public CourseTable getCourseTableBySemester(String Username, int week, String semesterString) {
        List<Order> orderList = orderService.getOrderByUsernameAndSemester(Username, semesterString);
        orderList = orderService.getFinishedOrderList(orderList);
        CourseTable courseTable = OrderListToCourseTable(orderList);
        return courseTable;
    }

    public CourseTable getCourseTable(int labId, int week) {
        //读取系统参数中的"当前学期"
        SystemParameter systemParameter = systemParameterDao.select();
        Semester semester = semesterService.getSemesterById(systemParameter.getThisSemesterId());
        String semesterString = semester.getSemesterString();

        List<Order> orderList = orderService.getOrderByLabIdAndSemester(labId, semesterString);
        orderList = orderService.getFinishedOrderList(orderList);
        CourseTable courseTable = OrderListToCourseTable(orderList);
        return courseTable;
    }

    public CourseTable getCourseTableBySemester(int labId, int week, String semesterString) {
        List<Order> orderList = orderService.getOrderByLabIdAndSemester(labId, semesterString);
        orderList = orderService.getFinishedOrderList(orderList);
        CourseTable courseTable = OrderListToCourseTable(orderList);
        return courseTable;
    }

    public CourseTable rawCourseTableToCourseTable(ArrayList<ArrayList<ArrayList<Object>>> rawCourseTable) {
        ArrayList<ArrayList<ArrayList<Object>>> courseTable = new ArrayList<ArrayList<ArrayList<Object>>>();
        CourseTable courseTableObject = new CourseTable();
        OccupyObject occupyObject = new OccupyObject();

        //将rawCourseTable中，上下行一样的进行合并。合并处填上OccupyObject。
        for (int i = 0; i < 7; i++) {
            int index = 0;//设置指针，标记相同格子的第一个位置
            for (int j = 0; j < 12; j++) {
                //若index指向的为空，将index值指向非空的第一个
                while (rawCourseTable.get(index).get(i).size() == 0 && index < 11) {
                    index++;
                }

                if (index == 11) {
                    break;
                }

                //如果j在index上方或者==index，j = index的下一项
                if (j <= index & j < 11) {
                    j = index + 1;
                }

                //如果指针位置格子和循环变量j的位置List大小不同，则直接跳过
                if (rawCourseTable.get(j).get(i).size() != rawCourseTable.get(index).get(i).size()) {
                    //更新指针位置
                    index = j;
                } else {
                    //如果List大小相同，判断内容是否相同
                    ArrayList<Object> indexList = new ArrayList<Object>(rawCourseTable.get(index).get(i));
                    indexList.retainAll(rawCourseTable.get(j).get(i));
                    //如果是全部相同
                    if (indexList.size() == rawCourseTable.get(j).get(i).size()) {
                        //把对应格子第一项设为OccupyObject
                        rawCourseTable.get(j).get(i).set(0, occupyObject);
                        //把index格子里的所有CourseTableCell的row值+1
                        for (Object cell : rawCourseTable.get(index).get(i)) {
                            CourseTableCell cell1 = (CourseTableCell) cell;
                            cell1.setRow(cell1.getRow() + 1);
                        }
                    } else {
                        index = j;
                    }
                }
            }
        }

        //将空闲的位置都填上EmptyCellObject
        EmptyCellObject emptyCellObject = new EmptyCellObject();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 7; j++) {
                if (rawCourseTable.get(i).get(j).size() == 0) {
                    rawCourseTable.get(i).get(j).add(emptyCellObject);
                }
            }
        }

        //再将rawCourseTable中OccupyObject对象处理掉
        for (int i = 0; i < 12; i++) {
            ArrayList<ArrayList<Object>> row = new ArrayList<ArrayList<Object>>();
            for (int j = 0; j < 7; j++) {
                if (rawCourseTable.get(i).get(j).get(0).getClass() != occupyObject.getClass()) {
                    row.add(rawCourseTable.get(i).get(j));
                }
            }
            courseTable.add(row);
        }
        courseTableObject.setCourseTable(courseTable);
        return courseTableObject;
    }

    public CourseTable OrderListToCourseTable(List<Order> orderList) {
        //List<Object>[][] rawCourseTable = new ArrayList[12][7];
        ArrayList<ArrayList<ArrayList<Object>>> rawCourseTable = new ArrayList<ArrayList<ArrayList<Object>>>();

        for (int i = 0; i < 12; i++) {
            ArrayList<ArrayList<Object>> row = new ArrayList<ArrayList<Object>>();
            for (int j = 0; j < 7; j++) {
                ArrayList<Object> emptylist = new ArrayList<Object>();
                row.add(emptylist);
            }
            rawCourseTable.add(row);
        }

        for (Order order : orderList) {
            CourseTableCell courseTableCell = new CourseTableCell();

            //处理教师名
            courseTableCell.setTeacherName(userService.getNicknameByUsername(order.getUserName()));

            //处理课程号
            courseTableCell.setCourseId(order.getClassId());

            //处理课程名字
            courseTableCell.setCourse(order.getClassName());

            //获得安排的OrderDetail
            OrderDetail ArrangedOrderDetail = new OrderDetail();
            for (int i = 0; i < order.getOrderDetails().size(); i++) {
                if (order.getPassFlag() == order.getOrderDetails().get(i).getType()) {
                    ArrangedOrderDetail = order.getOrderDetails().get(i);
                    break;
                }
            }

            //如果获得不到对象（即取出来的OrderDetail不是被安排的那一个），则直接跳出
            if (ArrangedOrderDetail.getOrderWeekString() == null) {
                continue;
            }

            //处理得到weeks(String)
            List<Integer> orderweek = ArrangedOrderDetail.getOrderWeek();
            Collections.sort(orderweek);
            String weeks = null;
            for (Integer week : orderweek) {
                if (weeks == null) {
                    weeks = week.toString();
                } else {
                    weeks = weeks + "," + week.toString();
                }
            }
            courseTableCell.setWeeks(weeks);

            //处理得到Place
            List<Lab> labList = new ArrayList<Lab>();
            if (ArrangedOrderDetail.getFirstLab() != null) {
                labList.add(labService.selectLabByLabId(ArrangedOrderDetail.getFirstLab()));
            }

            if (ArrangedOrderDetail.getSecondLab() != null) {
                labList.add(labService.selectLabByLabId(ArrangedOrderDetail.getSecondLab()));
            }

            if (ArrangedOrderDetail.getThirdLab() != null) {
                labList.add(labService.selectLabByLabId(ArrangedOrderDetail.getThirdLab()));
            }


            String place = null;
            for (Lab lab : labList) {
                if (place == null) {
                    place = lab.getLabBuild() + "教" + lab.getLabNumber();
                } else {
                    place = ";" + lab.getLabBuild() + "教" + lab.getLabNumber();
                }
            }
            courseTableCell.setPlace(place);

            //处理row
            courseTableCell.setRow(1);

            //处理放置courseTableCell的位置
            //行（开始的是第几节）
            List<Integer> arrayRowList = ArrangedOrderDetail.getClassNum();
            Collections.sort(arrayRowList);
            //列（周几）
            Integer arrayColumn = ArrangedOrderDetail.getWeekDays().get(0) - 1;


            //将courseTableCell添加到对应位置
            for (Integer arrayRow : arrayRowList) {
                //数组内位置和实际数据要-1
                arrayRow -= 1;
                rawCourseTable.get(arrayRow).get(arrayColumn).add(new CourseTableCell(courseTableCell));//调用复制构造
            }
        }


        return rawCourseTableToCourseTable(rawCourseTable);
    }
}

