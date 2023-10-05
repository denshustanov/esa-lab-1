package org.example.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.repository.OrderRepository;
import org.example.entity.Order;
import org.example.repository.util.Page;
import org.hibernate.Session;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Stateless
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {
//    @PersistenceContext(unitName = "default")
    private final EntityManager entityManager = HibernateUtils.entityManager;

    @Resource
    protected SessionContext sessionContext;

    @Override
    public Order save(Order order) {
        entityManager.persist(order);
        entityManager.flush();
        return order;
    }

    @Override
    public void delete(UUID id) {
        entityManager.remove(entityManager.find(Order.class, id));
    }

    @Override
    public Order update(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public Optional<Order> get(UUID id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public Page<Order> getAll(int page, int size) {
        Query queryData = entityManager.createQuery("from Order");
        queryData.setFirstResult(page*size);
        queryData.setMaxResults(size);
        List<Order> data = queryData.getResultList();

        Query queryTotal = entityManager.createQuery("Select count(*) from Order o");
        long totalCount = (Long) queryTotal.getSingleResult();
        System.out.printf("TOTAL COUNT: %s\n", totalCount);

        Page<Order> page1 = new Page<>();
        page1.setData(data);
        page1.setSize(size);
        page1.setNumber(page);
        page1.setTotalCount(totalCount);
        page1.setTotalPages(totalCount/size + (totalCount%size>0? 1: 0));
        return page1;
    }
}
