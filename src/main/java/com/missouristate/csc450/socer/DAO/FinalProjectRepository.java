package com.missouristate.csc450.socer.DAO;

import com.missouristate.csc450.socer.DAO.custom.FinalProjectRepositoryCustom;
import com.missouristate.csc450.socer.TableEntryObjects.Function;
import org.springframework.data.repository.CrudRepository;

public interface FinalProjectRepository extends CrudRepository<Function,Integer>, FinalProjectRepositoryCustom {

}
