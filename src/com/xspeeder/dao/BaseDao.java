/**
 * Copyright Jog Web Development Team
 */
package com.xspeeder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.xspeeder.common.util.ListUtil;
import com.xspeeder.common.util.StringUtil;

public class BaseDao extends SQLHelper {

    private DataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Map<String, Object> getDBSession(String sess_id) {

        Map<String, Object> whereData = new HashMap<String, Object>();
        whereData.put("id", sess_id);

        return this.selectOne("tbl_sys_session", null, whereData);
    }

    public Map<String, Object> getUserSubscribeInfo(Long user_id, Long opponent_id) {

        Map<String, Object> whereData = new HashMap<String, Object>();
        whereData.put("uid", user_id);
        whereData.put("opponent_id", opponent_id);

        return this.selectOne("tbl_user_subscribe", new String[]{"is_friend"}, whereData);
    }

    public Long insert(String tableName, Map<String, Object> data) {

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.dataSource);

        List<String> keylist = new ArrayList<String>(data.keySet());

        insertActor.withTableName(tableName);
        insertActor.setColumnNames(keylist);
        insertActor.usingGeneratedKeyColumns("id");

        Number user_id = insertActor.executeAndReturnKey(data);
        return user_id.longValue();
    }

    public Long insert(String tableName, Map<String, Object> data, String primary_key) {

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.dataSource);

        List<String> keylist = new ArrayList<String>(data.keySet());

        insertActor.withTableName(tableName);
        insertActor.setColumnNames(keylist);
        insertActor.usingGeneratedKeyColumns(primary_key);

        Number user_id = insertActor.executeAndReturnKey(data);
        return user_id.longValue();
    }

    public void insertNoId(String tableName, Map<String, Object> data) {

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.dataSource);

        List<String> keylist = new ArrayList<String>(data.keySet());

        insertActor.withTableName(tableName);
        insertActor.setColumnNames(keylist);

        insertActor.execute(data);
    }

    public void insert(String tableName, String[] columns, Object... args) {
        String sql = "INSERT INTO " + tableName + "(";

        for (int i = 0; i < columns.length; i++) {
            sql = sql + "`" + columns[i] + "`,";
        }

        sql = sql.substring(0, sql.length() - 1);
        sql = sql + ") VALUES (";

        for (int i = 0; i < args.length; i++) {
            sql = sql + "?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + ");";

        jdbcTemplate.update(sql, args);
    }

    public void update(String tableName, Map<String, Object> updateData, Map<String, Object> whereData) {

        List<String> updateColumnNames = new ArrayList<String>(updateData.keySet());
        List<String> whereColumnNames = new ArrayList<String>(whereData.keySet());
        List<Object> updateDataArray = new ArrayList<Object>(updateData.values());
        List<Object> whereDataArray = new ArrayList<Object>(whereData.values());

        List<Object> valueList = new ArrayList<Object>(updateDataArray);
        valueList.addAll(whereDataArray);

        String sql = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < updateColumnNames.size(); i++) {
            sql += updateColumnNames.get(i) + "=?,";
        }
        sql = sql.substring(0, sql.length() - 1) + " WHERE ";
        for (int i = 0; i < whereColumnNames.size(); i++) {
            sql += whereColumnNames.get(i) + "=? AND ";
        }
        sql = sql.substring(0, sql.length() - 4) + ";";

        this.jdbcTemplate.update(sql, valueList.toArray());
    }

    public void update(String tableName, Map<String, Object> updateData, Long id) {

        List<String> updateColumnNames = new ArrayList<String>(updateData.keySet());
        List<Object> updateDataArray = new ArrayList<Object>(updateData.values());

        List<Object> valueList = new ArrayList<Object>(updateDataArray);
        valueList.add(id);

        String sql = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < updateColumnNames.size(); i++) {
            sql += updateColumnNames.get(i) + "=?,";
        }
        sql = sql.substring(0, sql.length() - 1) + " WHERE id=?;";

        this.jdbcTemplate.update(sql, valueList.toArray());
    }

    public void update(String tableName, Map<String, Object> updateData, String whereStr) {

        List<String> updateColumnNames = new ArrayList<String>(updateData.keySet());
        List<Object> updateDataArray = new ArrayList<Object>(updateData.values());

        String sql = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < updateColumnNames.size(); i++) {
            sql += updateColumnNames.get(i) + "=?,";
        }
        sql = sql.substring(0, sql.length() - 1) + " WHERE " + whereStr + ";";

        this.jdbcTemplate.update(sql, updateDataArray.toArray());
    }

    public void delete(String tableName, Map<String, Object> whereData) {

        List<String> whereColumnNames = new ArrayList<String>(whereData.keySet());
        List<Object> whereDataArray = new ArrayList<Object>(whereData.values());

        String sql = "DELETE FROM " + tableName + " WHERE ";
        for (int i = 0; i < whereColumnNames.size(); i++) {
            sql += whereColumnNames.get(i) + "=? AND ";
        }
        sql = sql.substring(0, sql.length() - 4) + ";";

        this.jdbcTemplate.update(sql, whereDataArray.toArray());
    }

    public void delete(String tableName, Long id) {

        String sql = "DELETE FROM " + tableName + " WHERE id=?;";

        this.jdbcTemplate.update(sql, id);
    }

    public void delete(String tableName, String whereStr) {

        String sql = "DELETE FROM " + tableName + " WHERE " + whereStr + ";";

        this.jdbcTemplate.update(sql);
    }

    public void delete(String tableName, String field, Object value) {

        String sql = "DELETE FROM " + tableName + " WHERE " + field + " = ?;";

        this.jdbcTemplate.update(sql, value);
    }

    public void deleteAll(String tableName) {

        String sql = "DELETE FROM " + tableName + ";";

        this.jdbcTemplate.update(sql);
    }

    public Map<String, Object> selectOne(String tableName, String[] selectColumns, Map<String, Object> whereData) {

        List<String> whereColumnNames = new ArrayList<String>(whereData.keySet());
        List<Object> whereDataArray = new ArrayList<Object>(whereData.values());

        String sql = "SELECT ";
        if (selectColumns == null) {
            sql += "* ";
        } else {
            for (int i = 0; i < selectColumns.length; i++) {
                sql += selectColumns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }
        sql += " FROM " + tableName;

        if (!ListUtil.isEmpty(whereDataArray)) {
            sql += " WHERE ";
            for (int i = 0; i < whereColumnNames.size(); i++) {
                sql += whereColumnNames.get(i) + "=? AND ";
            }
            sql = sql.substring(0, sql.length() - 4) + ";";
        }

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, whereDataArray.toArray());

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public Map<String, Object> selectOne(String tableName, String[] whereColumns, Object[] values) {

        String sql = "SELECT * FROM " + tableName;

        if (!ListUtil.isEmpty(whereColumns) && !ListUtil.isEmpty(values)) {
            sql += " WHERE ";
            for (int i = 0; i < whereColumns.length; i++) {
                sql += whereColumns[i] + "=? AND ";
            }
            sql = sql.substring(0, sql.length() - 4) + ";";
        }

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, values);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<Map<String, Object>> select(String tableName, String[] whereColumns, Object[] values) {

        String sql = "SELECT * FROM " + tableName;

        if (!ListUtil.isEmpty(whereColumns) && !ListUtil.isEmpty(values)) {
            sql += " WHERE ";
            for (int i = 0; i < whereColumns.length; i++) {
                sql += whereColumns[i] + "=? AND ";
            }
            sql = sql.substring(0, sql.length() - 4);
        }

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, values);

        if (ListUtil.isEmpty(result)) {
            return null;
        }

        return result;
    }

    public List<Map<String, Object>> select(String tableName, String[] whereColumns, Object[] values, Integer start, Integer limit) {

        String sql = "SELECT * FROM " + tableName;

        if (!ListUtil.isEmpty(whereColumns) && !ListUtil.isEmpty(values)) {
            sql += " WHERE ";
            for (int i = 0; i < whereColumns.length; i++) {
                sql += whereColumns[i] + "=? AND ";
            }
            sql = sql.substring(0, sql.length() - 4);
        }

        sql = sql + limit(start, limit);

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, values);

        if (ListUtil.isEmpty(result)) {
            return null;
        }

        return result;
    }

    public Map<String, Object> selectOne(String tableName, String[] selectColumns, Long id) {

        if (id == null)
            return null;

        String sql = "SELECT ";
        if (selectColumns == null) {
            sql += " * ";
        } else {
            for (int i = 0; i < selectColumns.length; i++) {
                sql += selectColumns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " FROM " + tableName + " WHERE id=?;";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, id);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public Map<String, Object> selectOne(String tableName, String[] selectColumns, String whereStr) {

        String sql = "SELECT ";
        if (selectColumns == null) {
            sql += " * ";
        } else {
            for (int i = 0; i < selectColumns.length; i++) {
                sql += selectColumns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " FROM " + tableName;

        if (!StringUtil.isEmpty(whereStr))
            sql += " WHERE " + whereStr + ";";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (ListUtil.isEmpty(result)) {
            return null;
        }

        return result.get(0);
    }

    public List<Map<String, Object>> select(String tableName, String[] selectColumns, Map<String, Object> whereData) {

        List<String> whereColumnNames = new ArrayList<String>(whereData.keySet());
        List<Object> whereDataArray = new ArrayList<Object>(whereData.values());

        String sql = "SELECT ";
        if (selectColumns == null) {
            sql += "* ";
        } else {
            for (int i = 0; i < selectColumns.length; i++) {
                sql += selectColumns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }
        sql += " FROM " + tableName;

        if (!ListUtil.isEmpty(whereDataArray)) {
            sql += " WHERE ";

            for (int i = 0; i < whereColumnNames.size(); i++) {
                sql += whereColumnNames.get(i) + "=? AND ";
            }
            sql = sql.substring(0, sql.length() - 4) + ";";
        }

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, whereDataArray.toArray());

        if (ListUtil.isEmpty(result)) {
            return null;
        }

        return result;
    }

    public List<Map<String, Object>> select(String tableName, String[] selectColumns, String whereStr) {

        String sql = "SELECT ";
        if (selectColumns == null) {
            sql += " * ";
        } else {
            for (int i = 0; i < selectColumns.length; i++) {
                sql += selectColumns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " FROM " + tableName;

        if (!StringUtil.isEmpty(whereStr))
            sql += " WHERE " + whereStr + ";";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result;
        }
    }

    public List<Map<String, Object>> selectAll(String tableName) {

        String sql = "SELECT * FROM " + tableName;

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result;
        }
    }

    public List<Map<String, Object>> selectAll(String tableName, String[] columns) {

        String sql = "SELECT ";

        if (columns == null) {
            sql += " * ";
        } else {
            for (int i = 0; i < columns.length; i++) {
                sql += columns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " FROM " + tableName + ";";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result;
        }
    }

    public List<Map<String, Object>> selectAll(String tableName, String[] columns, String orderby) {

        String sql = "SELECT ";

        if (columns == null) {
            sql += " * ";
        } else {
            for (int i = 0; i < columns.length; i++) {
                sql += columns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " FROM " + tableName + " " + orderby + ";";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result;
        }
    }

    public List<Map<String, Object>> select(String tableName, String[] selectColumns, String whereStr, String orderBy, String limit) {

        String sql = "SELECT ";
        if (selectColumns == null) {
            sql += " * ";
        } else {
            for (int i = 0; i < selectColumns.length; i++) {
                sql += selectColumns[i] + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " FROM " + tableName + " WHERE " + whereStr + orderBy + limit + ";";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if (ListUtil.isEmpty(result)) {
            return null;
        } else {
            return result;
        }
    }

}
