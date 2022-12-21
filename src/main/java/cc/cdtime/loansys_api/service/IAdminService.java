package cc.cdtime.loansys_api.service;

import cc.cdtime.loansys_api.entity.Admin;
import cc.cdtime.loansys_api.entity.AdminView;

import java.util.Map;

public interface IAdminService {
    void createAdmin(Map in) throws Exception;

    AdminView getAdmin(Map qIn) throws Exception;

    Map loginAdmin(Map in) throws Exception;

    Map loadStatisticLoan(Map in) throws Exception;

    Map listLoanApplication(Map in) throws Exception;
}
