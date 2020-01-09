package app.some.order.service;

import app.some.order.domain.Order;
import app.some.api.order.BOCreateOrderRequest;
import app.some.api.order.BOOrderView;
import app.some.api.order.BOSearchOrderRequest;
import app.some.api.order.BOSearchOrderResponse;
import app.some.api.order.BOUpdateOrderRequest;
import core.framework.db.Database;
import core.framework.db.Query;
import core.framework.db.Repository;
import core.framework.db.Transaction;
import core.framework.inject.Inject;
import core.framework.util.Strings;
import core.framework.web.exception.NotFoundException;

import java.util.stream.Collectors;

public class BOOrderService {
    @Inject
    Database database;
    @Inject
    Repository<Order> orderRepository;

    public BOOrderView get(Long id) {
        Order order = orderRepository.get(id).orElseThrow(() -> new NotFoundException("order not found, id=" + id));
        return view(order);
    }

    public BOOrderView create(BOCreateOrderRequest request) {
        Order order = new Order();
        order.remark = request.remark;
        order.id = orderRepository.insert(order).orElseThrow();
        commitTransaction();
        rollbackTransaction();
        return view(order);
    }

    public BOOrderView update(Long id, BOUpdateOrderRequest request) {
        Order order = orderRepository.get(id).orElseThrow(() -> new NotFoundException("order not found, id=" + id));
        order.remark = request.remark;
        orderRepository.partialUpdate(order);
        return view(order);
    }

    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        BOSearchOrderResponse result = new BOSearchOrderResponse();
        Query<Order> query = orderRepository.select();
        query.skip(request.skip);
        query.limit(request.limit);
        if (!Strings.isBlank(request.remark)) {
            query.where("remark = ?", request.remark);
        }
        result.orders = query.fetch().stream().map(this::view).collect(Collectors.toList());
        result.total = query.count();
        return result;
    }

    private BOOrderView view(Order order) {
        BOOrderView result = new BOOrderView();
        result.id = order.id;
        result.remark = order.remark;
        return result;
    }

    private void commitTransaction() {
        try (Transaction transaction = database.beginTransaction()) {
            insertRow("test");
            transaction.commit();
        }
    }

    private void rollbackTransaction() {
        try (Transaction transaction = database.beginTransaction()) {
            insertRow("test2");
            transaction.rollback();
        }
    }

    private void insertRow(String remark) {
        database.execute("INSERT INTO tb_orders (remark) VALUES (?)", remark);
    }
}
