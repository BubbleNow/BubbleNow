package pl.bubblenow.services;

import org.springframework.stereotype.Service;
import pl.bubblenow.models.*;
import pl.bubblenow.repositories.*;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderService {
    BubbleTeaRepository bubbleTeaRepository;
    OrderRepository orderRepository;

    public OrderService(
            BubbleTeaRepository bubbleTeaRepository,
            OrderRepository orderRepository
    ) {
        this.bubbleTeaRepository = bubbleTeaRepository;
        this.orderRepository = orderRepository;
    }

    public BigDecimal countPrice(Addition addition, Base base, Size size) {
        BigDecimal priceAddition = addition == null ?
                BigDecimal.valueOf(0) :
                addition.getPrice();

        return priceAddition
                .add(base.getPrice())
                .add(size.getPrice());
    }

    public Order create(Addition addition, Syrup syrup, Base base, Size size, Kind kind) {
        BubbleTea bubbleTea = new BubbleTea();

        bubbleTea.setAddition(addition);
        bubbleTea.setBase(base);
        bubbleTea.setSize(size);
        bubbleTea.setSyrup(syrup);
        bubbleTea.setKind(kind);
        bubbleTeaRepository.save(bubbleTea);

        Order order = new Order();
        order.setDate(new Date());
        order.setNumber(getNextNumber());
        order.setPrice(countPrice(addition, base, size));

        order.setBubbleTea(bubbleTea);
        orderRepository.save(order);

        return order;
    }

    public int getNextNumber() {
        int latestNumber = orderRepository.findTopByOrderByIdDesc().getNumber();

        return latestNumber >= 100 ? 1 : latestNumber + 1;
    }

}
