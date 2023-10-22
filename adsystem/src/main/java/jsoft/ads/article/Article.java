package jsoft.ads.article;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Triplet;

import jsoft.objects.ArticleObject;

public interface Article {
	// Các phương thức cập nhật thông tin Người sử dụng
	public boolean addArticle(ArticleObject item);
	public boolean editArticle(ArticleObject item);
	public boolean delArticle(ArticleObject item);

	// Các phương thức / chức năng lấy thông tin Người sử dụng
	public ResultSet getArticle(int id);
	public ArrayList<ResultSet> getArticles(Triplet<ArticleObject, Integer, Byte> infors);
}
