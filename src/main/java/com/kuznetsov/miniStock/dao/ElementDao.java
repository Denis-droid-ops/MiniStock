package com.kuznetsov.miniStock.dao;

import com.kuznetsov.miniStock.exception.DaoException;
import com.kuznetsov.miniStock.model.Element;
import com.kuznetsov.miniStock.util.ConnectionManager;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class ElementDao implements Dao<Element,Integer> {

    private static final String SAVE_SQL = "INSERT INTO Element(name,price,count) VALUES(?,?,?)";
    private static final String FIND_ALL_SQL = "SELECT id,name,price,count FROM Element";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Element WHERE id = ?";


    @Override
    public Element save(Element object) {
        System.out.println("Begin saving element...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,object.getName());
            ps.setInt(2,object.getPrice());
            ps.setInt(3,object.getCount());
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
    public Optional<Element> findById(Integer id) {
        System.out.println("Begin finding element by id...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            Element element = null;
            if(rs.next()){
                element = buildElement(rs);
            }
            return Optional.ofNullable(element);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Element object) {
       //Code for update
    }

    @Override
    public boolean delete(Integer id) {
        System.out.println("Begin deleting element by id...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Element> findAll() {
        System.out.println("Begin finding all elements...");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            List<Element> elements = new ArrayList<>();
            while(rs.next()){
                elements.add(buildElement(rs));
            }
            return elements;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private Element buildElement(ResultSet resultSet) throws SQLException {
        return new Element(resultSet.getObject("id",Integer.class)
                           ,resultSet.getObject("name",String.class)
                           ,resultSet.getObject("price",Integer.class)
                           ,resultSet.getObject("count",Integer.class));
    }
}
