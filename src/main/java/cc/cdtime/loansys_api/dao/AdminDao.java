package cc.cdtime.loansys_api.dao;

import cc.cdtime.loansys_api.entity.Admin;
import cc.cdtime.loansys_api.entity.AdminView;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AdminDao {
    void createAdmin(Admin admin);

    AdminView getAdmin(Map qIn);

    void updateAdmin(Map qIn);
}
