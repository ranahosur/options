package com.bambinos.dao.impl;

import com.bambinos.dao.ContactInfoDAO;
import com.bambinos.model.ContactInfo;
import com.bambinos.model.Login;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactInfoDAOImpl extends BaseDaoImpl implements ContactInfoDAO {

    private static final Logger logger = Logger.getLogger(ContactInfoDAOImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ContactInfo createContactInfo(ContactInfo contactInfo) {
        String sql = "insert into contact_info(contact_info_id, parent_table, parent_id,  primary_email, secondary_email, address_line1, address_line2, city, " +
                " state, pincode, mobile_number, alt_mobile_number, land_line)" +
                "values( ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?)";

        contactInfo.setContactInfoId(generateId());
        logger.debug("ContactInfo created with id "+ contactInfo.getContactInfoId());
        jdbcTemplate.update(sql,  contactInfo.getContactInfoId(), contactInfo.getParentTable(), contactInfo.getParentId(),
                contactInfo.getPrimaryEmail(), contactInfo.getSecondaryEmail(), contactInfo.getAddress1(),
                contactInfo.getAddress2(),contactInfo.getCity(),contactInfo.getState(),contactInfo.getPincode(),contactInfo.getMobileNumber(),contactInfo.getAltMobileNumber(),contactInfo.getLandLine());
        return contactInfo;
    }

    @Override
    public ContactInfo updateContactInfo(ContactInfo contactInfo) {
        String sql = "UPDATE contact_info SET parent_table  = ? , parent_id  = ? ,  primary_email  = ? , secondary_email  = ? , address_line1  = ? , address_line2  = ? , city  = ? , \" +\n" +
                "                \" state  = ? , pincode  = ? , mobile_number  = ? , alt_mobile_number  = ? , land_line  = ?   WHERE Login_id = ?";
        jdbcTemplate.update(sql,  contactInfo.getParentTable(), contactInfo.getParentId(),
                contactInfo.getPrimaryEmail(), contactInfo.getSecondaryEmail(), contactInfo.getAddress1(),
                contactInfo.getAddress2(),contactInfo.getCity(),contactInfo.getState(),contactInfo.getPincode(),contactInfo.getMobileNumber(),contactInfo.getAltMobileNumber(),contactInfo.getLandLine(),contactInfo.getContactInfoId());
        return contactInfo;
    }

    @Override
    public void deleteContactInfo(String contactInfoId) {
        String sql = "Delete from contact_info  WHERE contact_info_id = ?";
        jdbcTemplate.update(sql, contactInfoId);

    }

    @Override
    public ContactInfo findContactInfoByParentIdParentTable(String parentId, String parentTable) {
        String sql = "select * from contact_info  WHERE parent_id = '" + parentId +"' and parent_table = '"+parentTable+"'";

        List<ContactInfo> contactInfos = jdbcTemplate.query(sql, new ContactInfoMapper());
        return contactInfos.size() > 0 ? contactInfos.get(0) : null;
    }

    class ContactInfoMapper implements RowMapper<ContactInfo> {

        public ContactInfo mapRow(ResultSet rs, int arg1) throws SQLException {
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setContactInfoId(rs.getString("contact_info_id"));
            ;
            contactInfo.setAddress1(rs.getString("address_line1"));
            contactInfo.setAddress2(rs.getString("address_line1"));
            contactInfo.setPrimaryEmail(rs.getString("primary_email"));
            contactInfo.setSecondaryEmail(rs.getString("secondary_email"));
            contactInfo.setParentTable(rs.getString("parent_table"));
            contactInfo.setParentId(rs.getString("parent_id"));
            contactInfo.setCity(rs.getString("city"));
            contactInfo.setCity(rs.getString("state"));
            contactInfo.setPincode(rs.getString("pincode"));
            contactInfo.setMobileNumber(rs.getString("mobile_number"));
            contactInfo.setAltMobileNumber(rs.getString("alt_mobile_number"));
            contactInfo.setLandLine(rs.getString("land_line"));
            contactInfo.setCreateDate(rs.getTimestamp("create_date"));
            return contactInfo;
        }
    }
}
