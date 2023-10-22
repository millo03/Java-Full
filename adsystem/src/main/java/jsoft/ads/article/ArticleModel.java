package jsoft.ads.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import jsoft.ConnectionPool;
import jsoft.ConnectionPoolImpl;
import jsoft.objects.ArticleObject;

public class ArticleModel {

	private Article a;


	ArticleModel(ConnectionPool cp) {
		this.a = new ArticleImpl(cp);
	}

	protected void finallize() {
		this.a = null;
	}

	public boolean addArticle(ArticleObject item) {
		return this.addArticle(item);
	}

	public boolean editArticle(ArticleObject item) {
		return this.editArticle(item);
	}

	public boolean delArticle(ArticleObject item) {
		return this.delArticle(item);
	}

	public ArticleObject getArticle(int id) {
		ArticleObject item = null;
		ResultSet rs = this.a.getArticle(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ArticleObject();

					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_author_name(rs.getString("article_author_name"));

					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));

					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return item;
	}

	public Pair<ArrayList<ArticleObject>, Integer> getArticles(Triplet<ArticleObject, Integer, Byte> infors) {
		ArrayList<ArticleObject> items = new ArrayList<>();
		ArticleObject item = null;
		int all = 0;
		ArrayList<ResultSet> res = this.a.getArticles(new Triplet<>(null, 1, (byte) 20));
		ResultSet rs = res.get(0);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new ArticleObject();

					item.setArticle_id(rs.getInt("article_id"));
					item.setArticle_title(rs.getString("article_title"));
					item.setArticle_created_date(rs.getString("article_created_date"));
					item.setArticle_summary(rs.getString("article_summary"));
					item.setArticle_content(rs.getString("article_content"));
					item.setArticle_image(rs.getString("article_image"));
					item.setArticle_author_name(rs.getString("article_author_name"));

					item.setCategory_id(rs.getShort("category_id"));
					item.setCategory_name(rs.getString("category_name"));

					item.setSection_id(rs.getShort("section_id"));
					item.setSection_name(rs.getString("section_name"));

					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		rs = res.get(1);
		if (rs != null) {
			try {
				while (rs.next()) {
					all = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return new Pair<>(items, all);
	}

	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		ArticleModel am = new ArticleModel(cp);

		Pair<ArrayList<ArticleObject>, Integer> datas = am.getArticles(new Triplet<>(null, 1, (byte) 20));
		ArrayList<ArticleObject> list = datas.getValue0();
		int total = datas.getValue1();
		list.forEach(item -> {
			System.out.print(list.indexOf(item) + "\t");
			System.out.println(item.getArticle_title());
		});
		System.out.println("Total: " + total);
	}

}
