package org.sqlcommand;

import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.jdbc.core.JdbcTemplate;

public class QueryInjector {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public String executeQuery(String query) throws Exception{
		List<Map<String, Object>> result = jdbcTemplate.queryForList(query.substring(0,query.length()-1));
		OutputFormatter outputFormatter = (OutputFormatter) CommandExec.context.getBean("outputFormatter");
		return outputFormatter.formatResult(result);
	}
	public void executeUpdate(String query) throws Exception{
		jdbcTemplate.execute(query.substring(0,query.length()-1));
	}
}
