package pl.bubblenow.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bubblenow.models.*;
import pl.bubblenow.models.DTOs.BubbleTeaDTO;
import pl.bubblenow.repositories.*;

import java.math.BigDecimal;
import java.util.Date;

@Service
@AllArgsConstructor
public class OrderService {
   private final BubbleTeaRepository bubbleTeaRepository;
   private final OrderRepository orderRepository;
    private final AdditionRepository additionRepository;
    private final SyrupRepository syrupRepository;
    private final SizeRepository sizeRepository;
    private final KindRepository kindRepository;
    private final MilkRepository milkRepository;

    public BigDecimal countPrice(Addition addition, Milk milk, Size size) {
        BigDecimal priceAddition = addition == null ?
                BigDecimal.valueOf(0) :
                addition.getPrice();

        BigDecimal priceMilk = milk == null ?
                BigDecimal.valueOf(0) :
                milk.getPrice();

        BigDecimal priceSize = size == null ?
                BigDecimal.valueOf(0) :
                size.getPrice();

        return priceAddition
                .add(priceMilk)
                .add(priceSize);
    }

    public int create(BubbleTeaDTO bubbleTeaDTO) {
        BubbleTea bubbleTea = new BubbleTea();

        Addition addition = additionRepository.findById(bubbleTeaDTO.getAddition().getId());
        Syrup syrup = syrupRepository.findById(bubbleTeaDTO.getSyrup().getId());
        Milk milk = milkRepository.findById(bubbleTeaDTO.getMilk().getId());
        Size size = sizeRepository.findById(bubbleTeaDTO.getSize().getId());
        Kind kind = kindRepository.findById(bubbleTeaDTO.getKind().getId());

        bubbleTea.setAddition(addition);
        bubbleTea.setMilk(milk);
        bubbleTea.setSize(size);
        bubbleTea.setSyrup(syrup);
        bubbleTea.setKind(kind);
        bubbleTeaRepository.save(bubbleTea);


        Order order = new Order();
        order.setDate(new Date());
        order.setNumber(getNextNumber());
        order.setPrice(countPrice(addition, milk, size));

        order.setBubbleTea(bubbleTea);
        orderRepository.save(order);

        return order.getNumber();
    }

    public int getNextNumber() {
        Order latestOrder = orderRepository.findTopByOrderByIdDesc();

        return latestOrder == null ? 1 : latestOrder.getNumber() + 1;
    }

    public void updateToNextStatus(int id) {
        Order order = orderRepository.findById(id);

        if (order == null) {
            return;
        }

        switch (order.getStatus()) {
            case "Nowe" -> order.setStatus("W przygotowaniu");
            case "W przygotowaniu" -> order.setStatus("Gotowe do odbioru");
            case "Gotowe do odbioru" -> order.setStatus("Odebrane");
        }
        orderRepository.save(order);
    }

}
