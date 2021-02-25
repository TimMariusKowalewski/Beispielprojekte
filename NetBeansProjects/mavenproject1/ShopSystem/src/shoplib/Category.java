/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.util.ArrayList;

/**
 * @author Kowalewski
 */
public class Category
{

    private Category _parent_category; // Eltern-Kategorie
    private ArrayList<Article> _articles; // in der Kategorie enthaltene Artikel
    private String _name; // Name der Kategorie
    private ArrayList<Image> _preview_images; // Vorschaubilder

    public void addArticle(Article Article)
    {
        this._articles.add(Article);
    }

    public ArrayList<Article> getArticles()
    {
        return this._articles;
    }

    public String getName()
    {
        return this._name;
    }

    public void setName(String Name)
    {
        this._name = Name;
    }
}
