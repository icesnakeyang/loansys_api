package cc.cdtime.loansys_api.service;


import cc.cdtime.loansys_api.entity.Application;
import cc.cdtime.loansys_api.entity.ApplicationView;

import java.util.Map;

public interface IApplicationService {
    void createLoanApplication(Map in) throws Exception;
}
