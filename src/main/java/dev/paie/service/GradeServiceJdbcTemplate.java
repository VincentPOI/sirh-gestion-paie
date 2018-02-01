package dev.paie.service;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;
import dev.paie.util.PaieUtils;

@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	PaieUtils paieUtils;
	
	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nG) {
		String sqlUpdate = "INSERT INTO grade (code,nbHeuresbase,tauxBase) VALUES (?,?,?);";
		jdbcTemplate.update(sqlUpdate, nG.getCode(), paieUtils.formaterBigDecimal(nG.getNbHeuresBase()), paieUtils.formaterBigDecimal(nG.getTauxBase()));

	}

	@Override
	public void mettreAJour(Grade g) {

		String sqlUpdate = "UPDATE grade SET code = ?,nbHeuresbase= ?, tauxBase= ? WHERE ID = ? ";
		jdbcTemplate.update(sqlUpdate, g.getCode(), paieUtils.formaterBigDecimal(g.getNbHeuresBase()), paieUtils.formaterBigDecimal(g.getTauxBase()), g.getId());

	}

	@Override
	public List<Grade> lister() {
		RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
			Grade g = new Grade();
			g.setId(rs.getInt("id"));
			g.setCode(rs.getString("code"));
			g.setNbHeuresBase(rs.getBigDecimal("nbHeuresbase"));
			g.setTauxBase(rs.getBigDecimal("tauxBase"));
			return g;
		};
		String sql = "SELECT * FROM grade";
		return this.jdbcTemplate.query(sql, mapper);
	}

}
