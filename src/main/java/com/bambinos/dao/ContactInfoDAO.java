package com.bambinos.dao;

import com.bambinos.model.ContactInfo;

public interface ContactInfoDAO {

    ContactInfo createContactInfo(ContactInfo contactInfo);
    ContactInfo updateContactInfo(ContactInfo contactInfo);
    void deleteContactInfo(String contactInfoId);
    ContactInfo findContactInfoByParentIdParentTable(String parentId,String parentTable);

}
