package com.missouristate.csc450.socer.DAO.impl;

import com.missouristate.csc450.socer.DAO.custom.FinalProjectRepositoryCustom;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import com.missouristate.csc450.socer.TableEntryObjects.QFunction;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinalProjectRepositoryImpl extends QuerydslRepositorySupport implements FinalProjectRepositoryCustom {

    QFunction functionTable = QFunction.function;
    
    public FinalProjectRepositoryImpl() {
        super(Function.class);

    }

    @Override
    public ArrayList<Function> getFunctions() {
        
        return (ArrayList<Function>) from(functionTable).fetch();
    }

}
