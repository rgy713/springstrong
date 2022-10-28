
package com.xspeeder.dao;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xspeeder.common.util.DatatablesOrder;
import com.xspeeder.common.util.StringUtil;

public class SQLHelper {

   
    public String orderby(String sorts) {

        if (sorts == null || sorts.isEmpty()) return "";

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        List<Map<String, Object>> sortList = gson.fromJson(sorts, collectionType);

        String result = " ";
        if (sortList.size() > 0) {
            result = " ORDER BY ";

            for (int i = 0; i < sortList.size(); i++) {
                result += sortList.get(i).get("field") + " " + sortList.get(i).get("dir") + ",";
            }
            result = " " + result.substring(0, result.length() - 1) + " ";
        }

        return result;
    }

    public String orderby(List<DatatablesOrder> sorts) {
        if (sorts == null || sorts.isEmpty()) return "";

        String result = " ";
        if (sorts.size() > 0) {
            result = " ORDER BY ";

            Iterator<DatatablesOrder> iter = sorts.iterator();
            while (iter.hasNext()) {
                DatatablesOrder one = iter.next();
                result = result + " " + one.col_name + " " + one.dir + ",";
            }

            result = " " + result.substring(0, result.length() - 1) + " ";
        }

        return result;
    }

    
    public String limit(Integer start, Integer limit) {

        if (start == null || limit == null || limit == -1) return " ";

        String result = " LIMIT " + start + ", " + limit + " ";

        return result;
    }

    
    public String clause(String field, Object value) {

        if (field == null || "".equals(field)) return "";

        if (value == null) return " ISNULL(" + field + ") ";

        String result = "";

        if (value instanceof String) {
            result = StringEscapeUtils.escapeJava(field + "='" + value + "'");

        } else if (value instanceof Integer || value instanceof Double || value instanceof Float || value instanceof Long) {

            result = field + "=" + value;
        }

        return result;
    }

    
    public String clause(String field, String operator, Object value) {

        if (field == null || "".equals(field)) return "";

        if (value == null) return "";

        if (operator == null) operator = "=";

        String result = "";

        if (value instanceof String) {

            value = value.toString().replace("'", "\\'");

            if ("LLIKE".equals(operator)) {
                result = field + " LIKE '%" + value + "' ";
            } else if ("RLIKE".equals(operator)) {
                result = field + " LIKE '" + value + "%' ";
            } else if ("LIKE".equals(operator)) {
                result = field + " LIKE '%" + value + "%' ";
            } else if ("IN".equals(operator)) {
                result = field + " IN(" + value + ") ";
            } else if ("IS NOT NULL".equals(operator)) {
                result = field + " IS NOT NULL ";
            } else if ("IS NULL".equals(operator)) {
                result = field + " IS NULL ";
            } else result = field + operator + "'" + value + "' ";


        } else if (value instanceof Integer || value instanceof Double || value instanceof Float || value instanceof Long) {

            result = field + operator + value;
        }

        return result;
    }

   
    public String clause(String field, String operator, Object value, String where) {

        String node = this.clause(field, operator, value);
        if (!"".equals(node)) {
            if (!StringUtil.isEmpty(where)) where = where + " AND " + node;
            else where = node;
        }

        return where;
    }

    
    public String where(String where) {
        if (StringUtil.isEmpty(where)) return " ";

        return " WHERE " + where;
    }
}
