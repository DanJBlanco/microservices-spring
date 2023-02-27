package com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.adapter;

import com.food.ordering.system.order.service.dataaccess.outbox.payment.exception.PaymentOutboxNotFoundException;
import com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.mapper.ApprovalOutboxDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.repository.ApprovalOutboxJapRepository;
import com.food.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.repository.ApprovalOutboxRepository;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ApprovalOutboxRepositoryImpl implements ApprovalOutboxRepository {

    private final ApprovalOutboxJapRepository approvalOutboxJapRepository;
    private final ApprovalOutboxDataAccessMapper approvalOutboxDataAccessMapper;

    public ApprovalOutboxRepositoryImpl(ApprovalOutboxJapRepository approvalOutboxJapRepository,
                                        ApprovalOutboxDataAccessMapper approvalOutboxDataAccessMapper) {
        this.approvalOutboxJapRepository = approvalOutboxJapRepository;
        this.approvalOutboxDataAccessMapper = approvalOutboxDataAccessMapper;
    }

    @Override
    public OrderApprovalOutboxMessage save(OrderApprovalOutboxMessage orderApprovalOutboxMessage) {
        return approvalOutboxDataAccessMapper
                .approvalOutboxEntityToOrderApprovalOutboxMessage( approvalOutboxJapRepository
                        .save(approvalOutboxDataAccessMapper
                                .orderApprovalOutboxMessageToOutboxEntity(orderApprovalOutboxMessage)));
    }

    @Override
    public Optional<List<OrderApprovalOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String type, OutboxStatus outboxStatus, SagaStatus... sagaStatus) {
        return Optional.of(
                approvalOutboxJapRepository.findByTypeAndOutboxStatusAndSagaStatusIn(
                                type,
                                outboxStatus,
                                Arrays.asList(sagaStatus))
                        .orElseThrow(() -> new PaymentOutboxNotFoundException("Payment outbox object " +
                                "could not be found for saga type " + type))
                        .stream()
                        .map(approvalOutboxDataAccessMapper::approvalOutboxEntityToOrderApprovalOutboxMessage)
                        .toList());
    }

    @Override
    public Optional<OrderApprovalOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String type, UUID sagaId, SagaStatus... sagaStatuses) {
        return approvalOutboxJapRepository.findByTypeAndSagaIdAndSagaStatusIn(type, sagaId, Arrays.asList(sagaStatuses))
                .map(approvalOutboxDataAccessMapper::approvalOutboxEntityToOrderApprovalOutboxMessage);
    }

    @Override
    public void deleteByTypeAndOutboxStatusAndSagaStatus(String type, OutboxStatus outboxStatus, SagaStatus... sagaStatuses) {
        approvalOutboxJapRepository.deleteByTypeAndOutboxStatusAndSagaStatusIn(type, outboxStatus, Arrays.asList(sagaStatuses));

    }
}
