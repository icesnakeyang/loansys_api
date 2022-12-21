package cc.cdtime.loansys_api.dao;

import cc.cdtime.loansys_api.entity.Application;
import cc.cdtime.loansys_api.entity.ApplicationView;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface ApplicationDao {
    void createLoanApplication(Application application);

    ApplicationView getApplication(String email);

    Map sumApplyLoan(Map qIn);

    ArrayList<ApplicationView> listApplication(Map qIn);
}
