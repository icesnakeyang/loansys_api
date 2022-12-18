package cc.cdtime.loansys_api.dao;

import cc.cdtime.loansys_api.entity.Application;
import cc.cdtime.loansys_api.entity.ApplicationView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationDao {
    void createLoanApplication(Application application);

    ApplicationView getApplication(String email);
}
