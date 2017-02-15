package ua.nure.kolodiazhny.SummaryTask04.dao;

import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationDao extends DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(PublicationDao.class);

    public PublicationDao(Connection con) {
        super(con);
    }

    @Override
    String getSelectQuery() {
        return "SELECT * FROM publication";
    }

    @Override
    String getUpdateQuery() {
        return "UPDATE publication SET title = ?, price = ?, publisher = ?, description = ?, ISSN = ? WHERE id = ?";
    }

    @Override
    void preparStForUpdate(PreparedStatement ps, Object obj){
        if (obj instanceof Publication){
            Publication publication = (Publication) obj;
            try{
                ps.setString(1,publication.getTitle());
                ps.setInt(2,publication.getPrice());
                ps.setString(3,publication.getPublisher());
                ps.setString(4,publication.getDescription());
                ps.setString(5, publication.getISSN());
                ps.setInt(6, publication.getId());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }

    }

    @Override
    String getDeleteQuery() {
        return "DELETE FROM publication WHERE id = ?";
    }

    @Override
    void preparStForDelete(PreparedStatement ps, Object obj){
        if (obj instanceof Publication){
        	Publication publication= (Publication) obj;
            try{
                ps.setInt(1,publication.getId());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    String getInsertQuery() {
        return "INSERT INTO publication (title, price, publisher, description, ISSN) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    void preparStForInsert(PreparedStatement ps, Object obj){
        if (obj instanceof Publication){
            Publication publication = (Publication) obj;
            try {
                ps.setString(1,publication.getTitle());
                ps.setInt(2,publication.getPrice());
                ps.setString(3,publication.getPublisher());
                ps.setString(4,publication.getDescription());
                ps.setString(5,publication.getISSN());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }

    }

    @Override
    List<Publication> parseResultSet(ResultSet rs){
        List<Publication> list = new ArrayList<Publication>();
        int id;
        String title;
        int price;
        String publisher;
        String description;
        String ISSN;
        try{
            while (rs.next()){
                id = rs.getInt(1);
                title = rs.getString(2);
                price = rs.getInt(3);
                publisher = rs.getString(4);
                description = rs.getString(5);
                ISSN = rs.getString(6);
                list.add(new Publication(id, title,price,publisher,description,ISSN));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    @Override
    String getSubscribtionDeleteQuery() {
        return "DELETE FROM subscription WHERE publication_id = ?";
    }
}