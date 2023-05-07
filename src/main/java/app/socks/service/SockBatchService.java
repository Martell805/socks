package app.socks.service;

import app.socks.entity.SockBatch;
import app.socks.enums.Operations;
import app.socks.repoistory.SockBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SockBatchService {
    private final SockBatchRepository sockBatchRepository;

    public SockBatch income(SockBatch sockBatch) {
        SockBatch existing = sockBatchRepository.getByColorAndCottonPart(
                sockBatch.getColor(),
                sockBatch.getCottonPart()
        ).orElse(null);

        if (existing == null) {
            return sockBatchRepository.save(sockBatch);
        }

        existing.setQuantity(existing.getQuantity() + sockBatch.getQuantity());

        return sockBatchRepository.save(existing);
    }

    public SockBatch outcome(SockBatch sockBatch) {
        SockBatch existing = sockBatchRepository.getByColorAndCottonPart(
                sockBatch.getColor(),
                sockBatch.getCottonPart()
        ).orElse(null);

        if (existing == null) {
            return null;
        }

        if (existing.getQuantity() < sockBatch.getQuantity()) {
            return null;
        }

        existing.setQuantity(existing.getQuantity() - sockBatch.getQuantity());

        return sockBatchRepository.save(existing);
    }

    public Integer get(String color, Operations operation, Integer cottonPart) {
        switch (operation) {
            case equal -> {
                return sockBatchRepository.getByColorAndCottonPart(color, cottonPart).stream()
                        .reduce(new SockBatch(0, "", 0, 0),
                                (a, b) -> new SockBatch(0, "", 0, a.getQuantity() + b.getQuantity())
                        ).getQuantity();
            }
            case lessThan -> {
                return sockBatchRepository.getAllByColorAndCottonPartLessThan(color, cottonPart).stream()
                        .reduce(new SockBatch(0, "", 0, 0),
                                (a, b) -> new SockBatch(0, "", 0, a.getQuantity() + b.getQuantity())
                        ).getQuantity();
            }
            case moreThan -> {
                return sockBatchRepository.getAllByColorAndCottonPartMoreThan(color, cottonPart).stream()
                        .reduce(new SockBatch(0, "", 0, 0),
                                (a, b) -> new SockBatch(0, "", 0, a.getQuantity() + b.getQuantity())
                        ).getQuantity();
            }
        }

        return null;
    }
}
