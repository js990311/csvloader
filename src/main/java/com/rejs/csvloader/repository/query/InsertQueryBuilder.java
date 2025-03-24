package com.rejs.csvloader.repository.query;

import com.rejs.csvloader.yaml.properties.model.WorkProperty;
import org.springframework.stereotype.Component;

@Component
public class InsertQueryBuilder {
    public String buildInsertQuery(WorkProperty work){
        if(work.getInsertQuery() != null){
            /* insertQuery가 수동으로 지정되었다면 사용 */
            return work.getInsertQuery();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(work.getTableName());
        sb.append("(");
        for(int i=0;i<work.getColumns().size();i++){
            sb.append(work.getColumns().get(i).getName());
            if(i+1 != work.getColumns().size()){
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for(int i=0;i<work.getColumns().size();i++){
            if(i+1 != work.getColumns().size()){
                sb.append("?, ");
            }else {
                sb.append("?");
            }
        }
        sb.append(");");

        return sb.toString();
    }
}
