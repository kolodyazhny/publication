package ua.nure.kolodiazhny.SummaryTask04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask04.entities.Subscription;

/**
 * Абстрактный класс, описывающий DAO для работы с БД.
 * @param <T>
 *     тип объекта, для которого создается DAO.
 */

public abstract class DaoFactory<T> {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    protected Connection con;

    public DaoFactory(Connection con){
        this.con = con;
    }

    /**
     * Возвращает SQL выражение, которое считывает все содержимое таблицы.
     * @return
     */
    abstract String getSelectQuery();

    /**
     * Возвращает SQL выражение, которое обновляет все содержимое таблицы.
     * @return
     */
    abstract String getUpdateQuery();

    /**
     * Устанавливает значения в Prepared Statement, считанные с объекта.
     * @param ps
     * @param obj
     */
    abstract void preparStForUpdate(PreparedStatement ps, T obj);

    /**
     * Возвращает SQL выражение, которое удаляет строку из таблицы.
     * @return
     */
    abstract String getDeleteQuery();

    /**
     * Устанавливает значения для удаления в Prepared Statement, считанные с объекта.
     * @param ps
     * @param obj
     */
    abstract void preparStForDelete(PreparedStatement ps, T obj);

    /**
     * Возвращает SQL выражение, которое вставляет строку в таблицу.
     * @return
     */
    abstract String getInsertQuery();

    /**
     * Устанавливает значения для вставки в Prepared Statement, считанные с объекта.
     * @param ps
     * @param obj
     */
    abstract void preparStForInsert(PreparedStatement ps, T obj);

    /**
     * Разбирает, полученный после выполнения SQL-выражения, результат (Result Set)
     * и возвращает список, считанных объектов.
     * @param rs
     * @return
     */
    abstract List parseResultSet(ResultSet rs);

    /**
     * Возвращает SQL выражение, которое удаляет строку из таблицы subscription.
     * @return
     */
    abstract String getSubscribtionDeleteQuery();

    /**
     * Считывает все содержимое таблицы и возвращает его в виде списка объектов.
     * @return
     */

    public ArrayList<T> readAll(){
        List<T> list = new ArrayList<T>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(getSelectQuery());
            list = parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            try{ statement.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
            try{ rs.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
        }
        return (ArrayList<T>) list;
    }

    /**
     * Вставляет объект в таблицу.
     * @param obj
     *          объект для вставки.
     * @return
     *        возвращает id только, что вставленного элемента.
     */
    public int insert(T obj){
        int res = 0;
        String sql = getInsertQuery();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            preparStForInsert(ps,obj);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        sql = "SELECT LAST_INSERT_ID()";
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            rs = ps.executeQuery();
            rs.next();
            res = Integer.parseInt(rs.getString(1));
            if (res == 0){
                LOGGER.error("Не верное значение ID");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }

    /**
     * Обновляет, полученный объект, в БД.
     * @param obj
     */
    public void update(T obj){
        PreparedStatement ps = null;
        try {
            String sql = getUpdateQuery();
            ps = con.prepareStatement(sql);
            preparStForUpdate(ps,obj);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            try{ ps.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
        }
    }

    /**
     * Удаляет объкт из базы данных.
     * Если объект являлся пользователем или периодическим изданием, то сначала удаляется
     * строка из таблицы subscription, содержащая id данного объекта.
     * @param obj
     */
    public void delete(T obj){
        PreparedStatement ps = null;
        if (!(obj instanceof Subscription)){
            try {
                String sql = getSubscribtionDeleteQuery();
                ps = con.prepareStatement(sql);
                preparStForDelete(ps, obj);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try{ ps.close();} catch (SQLException e) {e.printStackTrace();}
            }
        }
        try {
            String sql = getDeleteQuery();
            ps = con.prepareStatement(sql);
            preparStForDelete(ps, obj);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{ ps.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
}