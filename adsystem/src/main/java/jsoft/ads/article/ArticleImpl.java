package jsoft.ads.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ads.basic.BasicImpl;
import jsoft.objects.ArticleObject;

public class ArticleImpl extends BasicImpl implements Article {

	public ArticleImpl(ConnectionPool cp) {
		super(cp, "Article");
	}

	@Override
	public boolean addArticle(ArticleObject item) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean editArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delArticle(ArticleObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getArticle(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Integer, Byte> infors) {
		// TODO Auto-generated method stub
		int at = infors.getValue1();
		byte total = infors.getValue2();


		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.*, c.category_id, c.category_name, s.section_id, s.section_name ");
		sql.append("FROM tblarticle a ");
		sql.append("LEFT JOIN tblcategory c ON a.article_category_id = c.category_id ");
		sql.append("LEFT JOIN tblsection s ON c.category_section_id = s.section_id ");
		sql.append("ORDER BY a.article_id DESC ");
		sql.append("LIMIT ").append(at).append(", ").append(total).append(";");
		sql.append("SELECT COUNT(article_id) AS total FROM tblarticle; ");


		return this.getReList(sql.toString());
	}



}
