package com.example.demo.repository;

import com.example.demo.model.DepartmentWiseData;
import com.example.demo.model.EmployeeData;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DemoRepositoryImpl implements DemoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<EmployeeData> getAllEmployeeFromParticularCity(String cityName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(cityName));
        return mongoTemplate.find(query, EmployeeData.class);
    }

    @Override
    public List<DepartmentWiseData> getCountOfEmployeeDepartmentWise() {
        GroupOperation groupOperation = Aggregation.group("department").count().as("count");

        ProjectionOperation projectionOperation = Aggregation.project("count").and("department").previousOperation();

        MatchOperation matchOperation = Aggregation.match(Criteria.where("count").gte(1));

        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "count"));

        Aggregation aggregation = Aggregation.newAggregation(groupOperation, projectionOperation, matchOperation, sortOperation);

        AggregationResults<DepartmentWiseData> result = mongoTemplate.aggregate(aggregation, "employeeData", DepartmentWiseData.class);

        return result.getMappedResults();
    }

    @Override
    public String insertEmployee(EmployeeData employeeData) {

        EmployeeData insert = mongoTemplate.insert(employeeData);
        return "data insertion successful";
    }

    @Override
    public String updateEmployee(EmployeeData employeeData) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employee_id").is(employeeData.getEmployee_id()));

        Update update = new Update();
        if (!employeeData.getFirst_name().isEmpty()) {
            update.set("first_name", employeeData.getFirst_name());
        }

        if (!employeeData.getLast_name().isEmpty()) {
            update.set("last_name", employeeData.getLast_name());
        }

        if (employeeData.getAge() > 0) {
            update.set("age", employeeData.getAge());
        }
        if (employeeData.getSalary() > 0) {
            update.set("salary", employeeData.getSalary());
        }

        if (!employeeData.getDepartment().isEmpty()) {
            update.set("department", employeeData.getDepartment());
        }
        if (!employeeData.getCity().isEmpty()) {
            update.set("department", employeeData.getCity());
        }

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, EmployeeData.class);

        return updateResult.wasAcknowledged() ? "updated successfully" : "not updated";
    }
}
