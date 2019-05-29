package cn.charlesxu.LabManager.mapper;

import cn.charlesxu.LabManager.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateByUserName(User user);

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User selectByUsername(@Param("username") String username);

    int selectCountByUserName(@Param("userName") String userName);

    int selectCountByEmail(@Param("email") String email);

    int selectCountByPhone(@Param("phone") String phone);

    List<User> selectAllUser();
}