package com.bambinos.dao;

import com.bambinos.model.Child;

import java.util.List;

public interface ChildDAO {

    Child createChild(Child child);
    Child updateChild(Child child);
    List<Child> findChildByMobile(String mobile);

}
