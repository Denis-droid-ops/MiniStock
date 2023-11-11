package com.kuznetsov.miniStock.dao;

import com.kuznetsov.miniStock.exception.DaoException;
import com.kuznetsov.miniStock.model.Element;
import com.kuznetsov.miniStock.model.Moving;
import com.kuznetsov.miniStock.util.ConnectionManager;
import jakarta.enterprise.context.ApplicationScoped;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovingDao implements Dao<Moving,Integer> {

    private static final String SAVE_SQL = "INSERT INTO Moving(date_time,movable_count,remain_after,element_id) VALUES(?,?,?,?)";
    private static final String FIND_ALL_SQL = "SELECT m.id,m.date_time,m.movable_count,m.remain_after,m.element_id,e.name,e.price,e.count " +
            "FROM Moving m LEFT JOIN Element e ON m.element_id = e.id";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE id = ?";
    private static final String FIND_ALL_BY_ELEMENT_ID_SQL = FIND_ALL_SQL+" WHERE element_id = ?";
    private static final String DELETE_SQL = "DELETE FROM Moving WHERE id = ?";


    @Override
    public Moving save(Moving object) {
        System.out.println("Begin saving moving...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1,object.getDateTime());
            ps.setInt(2,object.getMovableCount());
            ps.setInt(3,object.getRemainAfter());
            ps.setInt(4,object.getElement().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                object.setId(rs.getInt(1));
            }
            return object;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

    }

    @Override
    public Optional<Moving> findById(Integer id) {
        System.out.println("Begin finding moving by id...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            Moving moving = null;
            if(rs.next()){
                moving = buildMoving(rs);
            }
            return Optional.ofNullable(moving);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Moving object) {
        //Code for update
    }

    @Override
    public boolean delete(Integer id) {
       //Code for delete
        return false;
    }

    @Override
    public List<Moving> findAll() {
        System.out.println("Begin finding all movings...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            List<Moving> movings = new ArrayList<>();
            while(rs.next()){
                movings.add(buildMoving(rs));
            }
            return movings;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public List<Moving> findAllByElementId(Integer elementId) {
        System.out.println("Begin finding all movings by element id ...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_ELEMENT_ID_SQL)) {
            ps.setInt(1,elementId);
            ResultSet rs = ps.executeQuery();
            List<Moving> movings = new ArrayList<>();
            while(rs.next()){
                movings.add(buildMoving(rs));
            }
            return movings;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private Moving buildMoving(ResultSet resultSet) throws SQLException {
        Element element =  new Element(resultSet.getObject("element_id",Integer.class)
                                      ,resultSet.getObject("name",String.class)
                                      ,resultSet.getObject("price",Integer.class)
                                      ,resultSet.getObject("count",Integer.class));
        return new Moving(resultSet.getObject("id", Integer.class)
                         ,resultSet.getObject("date_time", LocalDateTime.class)
                         ,resultSet.getObject("movable_count",Integer.class)
                         ,resultSet.getObject("remain_after",Integer.class)
                         ,element);
    }
}
