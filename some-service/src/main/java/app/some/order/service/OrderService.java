package app.some.order.service;

import app.ErrorCodes;
import app.some.api.order.CreateOrderRequest;
import app.some.api.order.OrderView;
import app.some.api.order.SearchOrderRequest;
import app.some.api.order.SearchOrderResponse;
import app.some.api.order.UpdateOrderRequest;
import app.some.order.demo.Painter;
import app.some.order.domain.Order;
import core.framework.db.Database;
import core.framework.db.Query;
import core.framework.db.Repository;
import core.framework.db.Transaction;
import core.framework.inject.Inject;
import core.framework.util.Strings;
import core.framework.web.exception.NotFoundException;

import java.util.stream.Collectors;

public class OrderService {
    @Inject
    Painter painter;
    @Inject
    Database database;
    @Inject
    Repository<Order> orderRepository;
    
    public OrderView get(Long id) {
        painter.draw();
        Order order = orderRepository.get(id).orElseThrow(() -> new NotFoundException("Book not found, id=" + id, ErrorCodes.ORDER_NOT_FOUND));
        return view(order);
    }

    public OrderView create(CreateOrderRequest request) {
        Order order = new Order();
        order.remark = request.remark;
        order.id = orderRepository.insert(order).orElseThrow();
        commitTransaction();
        rollbackTransaction();
        return view(order);
    }

    public OrderView update(Long id, UpdateOrderRequest request) {
        Order order = orderRepository.get(id).orElseThrow(() -> new NotFoundException("order not found, id=" + id));
        order.remark = request.remark;
        orderRepository.partialUpdate(order);
        return view(order);
    }

    public SearchOrderResponse search(SearchOrderRequest request) {
        SearchOrderResponse result = new SearchOrderResponse();
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

    private OrderView view(Order order) {
        OrderView result = new OrderView();
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
        database.execute("INSERT INTO orders (remark) VALUES (?)", remark);
    }
}
