package com.example.demo.service;


import com.example.demo.model.Employee;
import com.example.demo.model.KYCCommQstView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NumberService {
    private static final Logger LOG = LoggerFactory.getLogger(NumberService.class);

    private List<Employee> employees = new ArrayList<>();

    @Caching(cacheable = {
            @Cacheable(cacheNames = "employeeCache", cacheManager = "ehCacheManager", key = "#radius"),
            @Cacheable(cacheNames = "employeeCache", cacheManager = "hazelcastCacheManager", key = "#radius")
    })
    //@CachePut(cacheNames = "employeeCache", key = "#radius")
    public double areaOfCircle(int radius) {
        LOG.info("calculate the area of a circle with a radius of {}", radius);
        return Math.PI * Math.pow(radius, 2);
    }


    public List<Employee> getAllEmployee() {

        for (int i = 1; i <= 100; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setFirstName("Bayvao");
            employee.setLastName("Verma");
            employee.setSalary(20000.00);

            employees.add(employee);
        }
        return employees;
    }

    @Caching(cacheable = {
           // @Cacheable(cacheNames = "employeeCache", cacheManager = "ehCacheManager", key = "#id"),
            @Cacheable(cacheNames = "employeeCache", cacheManager = "hazelcastCacheManager", key = "#id")
    })
    public Employee getEmployee(int id) {
        LOG.info("fetching data from service");
        return employees.get(id);
    }

    @Caching(cacheable = {
            // @Cacheable(cacheNames = "employeeCache", cacheManager = "ehCacheManager", key = "#id"),
            @Cacheable(cacheNames = "comQstCache", cacheManager = "hazelcastCacheManager", key = "#id")
    })
    public KYCCommQstView getKYCCommQstView(int id) {
        LOG.info("fetching kyc from service");
        KYCCommQstView kycCommQstView = new KYCCommQstView();
        kycCommQstView.setQstId ("gender");
        kycCommQstView.setSection("Country Appendix Additional Member");
        kycCommQstView.setFormId ("CNTRY MEM" ) ;
        kycCommQstView.setClientType("BANK");
        kycCommQstView.setCtryCode("IN" );
        kycCommQstView.setIsParent("N");
        kycCommQstView.setQstSeq("135");
        kycCommQstView.setIndexQstId ("NA" );
        kycCommQstView.setPreEvent("NA");
        kycCommQstView.setPostEvent ("NA" );
        kycCommQstView.setFieldId("7ffd361b-9a68-4707-a35d-12a3979f69e");
        kycCommQstView.setFieldDesc("Gender");
        kycCommQstView.setLangId ("EN");
        kycCommQstView.setFieldType("QuestionSelect");
        kycCommQstView.setFldMaxSize("1");
        kycCommQstView.setFldMinSize("1");
        kycCommQstView.setPickLstType("india_gender_question_ID");
        kycCommQstView.setPropName("com.citi.kyc.core.app.forms.member.domain.Member.memberGender");
        kycCommQstView.setIsMandatory("y");
        kycCommQstView.setGoldenLock("N");
        kycCommQstView.setRuleId("RULE_NA" );
        kycCommQstView.setChildqst("QST_NA");
        kycCommQstView.setIsMultirow("N");
        kycCommQstView.setParentQstSeq("135");
        kycCommQstView.setIsDefault("N");
        kycCommQstView.setRiskRating("ALL");
        kycCommQstView.setFormsVersionNum(43);
        kycCommQstView. setModuleName("Country Appendix");
        kycCommQstView.setAutoPopulationRule("N");
        kycCommQstView.setHiddenQst("N");
        kycCommQstView. setTableName ("MEMBER_INFORMATION" );
        kycCommQstView. setColumnName ("MEMBER_GENDER");
        return kycCommQstView;
    }

}