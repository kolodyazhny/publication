package ua.nure.kolodiazhny.SummaryTask04.usertag;

import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Обработчик пользовательского тега.
 */
public class TableTag extends TagSupport{
    private ArrayList<Publication> publication;
    private boolean IndexPage;

    /**
     * Устанавливает значение из атрибута indexPage пользовательского тега
     * @param IndexPage
     *                 является ли страница, содержащая тег, начальной страницей
     */
    public void setIndexPage(boolean IndexPage){
        this.IndexPage = IndexPage;
    }

    /**
     * Устанавливает значение из атрибута periodicals пользовательского тега
     * @param periodicals
     *                   сиписок периодический изданий
     */
    public void setPublication(ArrayList publication){
        this.publication = publication;
    }

    @Override
    public int doStartTag(){
        try(JspWriter out = pageContext.getOut()){
            out.write("<table class=\"table table-hover\"><thead><tr> <th>ISSN</th><th>Title</th> " +
                    "<th>Publisher</th><th>Description</th>");
            if (!IndexPage){
                out.write(" <th>Price</th>");
            }
            out.write("</tr></thead><tbody>");
            for (int i = 0; i < publication.size(); i++) {
                out.write("<form action = \"claim.jsp\" method = \"post\">");
                out.write("<input type = \"hidden\" name = \"id\" value = \""+publication.get(i).getId()+"\"");
                out.write("<tr>");
                out.write("<td>"+publication.get(i).getISSN()+"</td>");
                out.write("<td><input type=\"hidden\" name=\"title\" value=\""+publication.get(i).getTitle()+"\"/>"+publication.get(i).getTitle()+"</td>");
                out.write("<td>"+publication.get(i).getPublisher()+"</td>");
                out.write("<td>"+publication.get(i).getDescription()+"</td>");
                if (!IndexPage){
                    out.write("<td><button class=\"btn btn-success btn-md\" value=\""+publication.get(i).getPrice()+"\" name = \"submit\" type=\"submit\">"
                            +publication.get(i).getPrice()+"</button></td>");
                }
                out.write("</tr></form>");
            }
            out.write("</tbody></table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag(){
        return SKIP_PAGE;
    }
}